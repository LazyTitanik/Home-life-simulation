package cz.cvut.k36.omo.smart_home.builder.houseBuilders;

import cz.cvut.k36.omo.smart_home.builder.FloorBuilder;
import cz.cvut.k36.omo.smart_home.builder.RoomBuilder;
import cz.cvut.k36.omo.smart_home.built.Floor;
import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.built.Rooms.*;
import cz.cvut.k36.omo.smart_home.devices.*;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Daughter;
import cz.cvut.k36.omo.smart_home.living.people.Father;
import cz.cvut.k36.omo.smart_home.living.people.Mother;
import cz.cvut.k36.omo.smart_home.living.people.Son;
import cz.cvut.k36.omo.smart_home.living.pets.Cat;
import cz.cvut.k36.omo.smart_home.living.pets.Dog;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * JSONParsing class.
 * In this class we read configuration file with .json type and add new elements to our house
 */
public class JSONParsing {
    /**
     * {@link #problem} count of logic errors in configuration file.
     */
    private int problem = 0;
    private static Logger logger = Logger.getLogger(JSONParsing.class.getName());

    /**
     * Create new device in the room.
     * @param name of the device
     * @return new device
     */
    public Device creationObj(String name){
        Device device;
        switch (name){
            case "Fridge":
                device = new Fridge();
                break;
            case "Car":
                device = new Car();
                break;
            case "CircuitBreaker":
                device = new CircuitBreaker();
                break;
            case "FireAlarm":
                APIFireAlarm fireAlarmAPI = new APIFireAlarm(); // Creates API for the Fire alarm
                device = new FireAlarm();
                fireAlarmAPI.setFireAlarm((FireAlarm)device);   // Gets link to the Fire alarm
                break;
            case "Heating":
                device = new Heating();
                break;
            case "Light":
                device = new Light();
                break;
            case "Remote":
                device = new Remote();
                break;
            case "Skis":
                device = new Skis();
                break;
            case "Television":
                device = new Television();
                break;
            case "Thermometer":
                device = new Thermometer();
                break;
            case "Console":
                device = new Console();
                break;
            case "Cooker":
                device = new Cooker();
                break;
            default:
                logger.log(Level.WARNING, "{0}", String.format("Incorrect device's name: %s", name));
                device = null;
                break;
        }
        return device;
    }

    /**
     * Create in Living.
     * @param name stuff of the living
     * @return new Living
     */
    public Living creatingLiving(String name){
        Living living;
        switch(name) {
            case "Father":
                living = new Father();
                break;
            case "Mother":
                living = new Mother();
                break;
            case "Son":
                living = new Son();
                break;
            case "Daughter":
                living = new Daughter();
                break;
            case "Cat":
                living = new Cat();
                break;
            case "Dog":
                living = new Dog();
                break;
            default:
                logger.log(Level.WARNING, "{0}", String.format("Incorrect living's name: %s", name));
                living = null;
                break;
        }
        return living;
    }

    /**
     * Check if there are logic errors.
     * @param device read device
     * @param room room, where device is
     * @param floor floor where room is
     * @return true if no logic errors, false else
     */
    public boolean check(Device device, String room, int floor){
        if(device.getName().equals("Car") && (floor > 1 || !room.equals("Garage"))){
            return false;
        }
        if(device.getName().equals("Fridge") && !room.equals("Kitchen")){
            return false;
        }
        if(device.getName().equals("Bath") && !room.equals("Bathroom")){
            return false;
        }
        if(device.getName().equals("Cooker") && !room.equals("Kitchen")){
            return false;
        }
        return true;
    }

    /**
     * Read device information from file and create it.
     * @param roomJSON
     * @param floor floor where room is
     * @return ready room with devices
     */
    public Room readDevice(Object roomJSON, int floor){
        JSONObject roomObj = (JSONObject) roomJSON;
        JSONArray deviceListJSON = new JSONArray();
        try{
            deviceListJSON.addAll( (JSONArray) roomObj.get("devices"));
        } catch(ClassCastException e){
            deviceListJSON.add(roomObj.get("devices"));
        } catch (NullPointerException e){
            logger.log(Level.WARNING, "Warning: no devices in room");
            problem++;
        }
        if(deviceListJSON.size() == 0){
            problem++;
            logger.log(Level.WARNING, "Warning: no devices in room");
        }
        String nameOfRoom = (String)roomObj.get("name");
        RoomBuilder roomBuilder = new RoomBuilder();
        roomBuilder.addName(nameOfRoom);
        for(Object deviceJSON: deviceListJSON){
            JSONObject deviceObj = (JSONObject) deviceJSON;
            String nameOfDevice = (String)deviceObj.get("name");
            Device device = creationObj(nameOfDevice);
            if(device != null && check(device, nameOfRoom, floor)){
                device.setName(nameOfDevice);
                roomBuilder.addDevice(device);
            }
        }
        return roomBuilder.build();
    }

    /**
     * Read room information from the configuration file.
     * @param roomListJSON
     * @param builder builder for the creating floor
     * @return ready floor with ready rooms
     */
    public Floor readRoom(JSONArray roomListJSON, FloorBuilder builder){
        for(Object roomJSON: roomListJSON){
            Room room = readDevice(roomJSON, builder.getNum());
            if(room != null){
                builder.addRoom(room.getNameOfRoom(), room);
            }
        }
        return builder.build();
    }

    /**
     * Read information about living and creat new living.
     * @param house
     * @param livingListJSON
     * @return true if living was created, false else
     */
    public boolean readLiving(House house, JSONArray livingListJSON){
        for(Object livingJSON: livingListJSON){
            JSONObject livingObj = (JSONObject) livingJSON;
            String livingName = (String) livingObj.get("name");
            String livingStuff = (String) livingObj.get("stuff");
            int floorNum = ((Long)livingObj.get("floorNum")).intValue();
            if(livingName != null){
                Living living = creatingLiving(livingStuff);
                String roomName = (String)livingObj.get("room");
                Map<String, Room> roomListInFloor = house.getFloorsList().get(floorNum - 1).getRoomsList();
                Room roomForLiving = null;
                for (Map.Entry<String,Room> entry : roomListInFloor.entrySet())
                {
                    Room room = entry.getValue();
                    if(room.getNameOfRoom().equals(roomName)){
                        roomForLiving = room;
                        break;
                    }
                }
                if(living != null && roomForLiving != null){
                    living.setName(livingName);
                    living.setRoom(roomForLiving);
                    house.addLiving(living);
                } else{
                    logger.log(Level.WARNING, "Warning: living is in a room which doesn't exist");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method for reading from .JSON file.
     * @param house
     * @param fileName name of the configuration file
     * @return true if all elements were added to house without any errors, false else
     */
    public boolean parse(House house, String fileName){

        JSONParser parser = new JSONParser();
        FileReader reader = null;
        JSONObject confJsonObject = null;

        try(FileReader reader_try = new FileReader(fileName)) {
            reader = reader_try;
            confJsonObject =  (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Warning: configuration file not found");
            return false;
        }
        if(reader != null){

            int days = ((Long)confJsonObject.get("daysCount")).intValue();
            if(days <= 0){
                problem++;
                logger.log(Level.WARNING, "Warning: very few days");
            } else{
                house.setDays(days);
            }
            List<Device> deviceList = new ArrayList<>();
            JSONArray floorListJSON = new JSONArray();
            try{
                floorListJSON.addAll((JSONArray)confJsonObject.get("floors"));
            } catch (ClassCastException e){
                floorListJSON.add(confJsonObject.get("floors"));
            }catch (NullPointerException e){
                logger.log(Level.WARNING, "Warning: no floors in house");
                problem++;
            }
            if(floorListJSON.size() == 0){
                problem++;
                logger.log(Level.WARNING, "Warning: no floors in house");
            }
            for (Object floorJSON: floorListJSON) {
                JSONObject floorObj = (JSONObject) floorJSON;
                FloorBuilder floorBuilder = new FloorBuilder();
                int num = ((Long)floorObj.get("num")).intValue();
                floorBuilder.addNum(num);
                JSONArray roomListJSON = new JSONArray();
                try{
                    roomListJSON.addAll((JSONArray) floorObj.get("rooms"));
                }catch(ClassCastException e){
                    roomListJSON.add(floorObj.get("rooms"));
                }catch (NullPointerException e){
                    logger.log(Level.WARNING, "Warning: no rooms in floor");
                    problem++;
                }
                if(roomListJSON.size() == 0){
                    problem++;
                    logger.log(Level.WARNING, "Warning: no rooms in floor");
                }
                Floor floor = readRoom(roomListJSON, floorBuilder);
                house.addFloor(floor);
            }
            for (Floor floor: house.getFloorsList()) {
                for (Map.Entry<String, Room> room : floor.getRoomsList().entrySet()) {
                    deviceList.addAll(room.getValue().getDevices());
                }
            }
            house.setDevicesList(deviceList);
            if(house.getDevicesList() != null){
                house.setSportDevices(house.getDevicesList().stream().filter(Device::isSport).collect(Collectors.toList()));
            }
            JSONArray livingListJSON = new JSONArray();
            try{
                livingListJSON.addAll((JSONArray) confJsonObject.get("Living"));
            }catch (ClassCastException e){
                livingListJSON.add(confJsonObject.get("Living"));
            }catch (NullPointerException e){
                logger.log(Level.WARNING, "Warning: no alive creatures in the house");
                problem++;
            }
            if(livingListJSON.size() == 0){
                problem++;
                logger.log(Level.WARNING, "Warning: no alive creatures in the house");
            }

            if(!readLiving(house, livingListJSON)){
                problem++;
            }
        }
        if(problem > 0){
            return false;
        }
        return true;
    }
}


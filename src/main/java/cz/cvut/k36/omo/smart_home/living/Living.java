package cz.cvut.k36.omo.smart_home.living;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.built.Floor;
import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.built.Rooms.NoRoom;
import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.eventObserver.EventListener;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.strategies.Strategy;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.k36.omo.smart_home.events.EventType.DAY;
import static cz.cvut.k36.omo.smart_home.events.EventType.FIRE;

/**
 * Living class.
 * {@link #deviceUsed} which device is using by this living now
 * {@link #name} name of the living
 * {@link #role} role of the living
 * {@link #room} in which room this living now is.
 * {@link #strategy} what strategy this living has in this day
 */
public abstract class Living implements EventListener {
    // Attributes
    private static Logger logger = Logger.getLogger(Living.class.getName());
    protected Device deviceUsed;
    protected String name;
    protected String role;
    protected Room room;
    protected Strategy strategy;

    protected Living(){
        EventManager.getInstance().subscribe(FIRE, this);
        EventManager.getInstance().subscribe(DAY, this);
    }

    /**
     * Go to another room with name(String) "nameOfRoom".
     * @param nameOfRoom in which room living will go. If this variable is NULL then living goes outside.
     */
    public void goToAnotherRoom(String nameOfRoom){
        Room tmp = room;
        String nameStartRoom;
        if(tmp == null){
            nameStartRoom = "Outside";
        } else{
            nameStartRoom = tmp.getNameOfRoom();
        }
        Room roomTmp;
        if(nameOfRoom.equals("")){
            roomTmp = randomRoom();
            nameOfRoom = roomTmp.getNameOfRoom();
        } else{
            roomTmp = findRoom(nameOfRoom);
        }
        if(nameStartRoom.equals(nameOfRoom)){
            logger.log(Level.FINE, "{0}", String.format("%s stays in %s", name, nameOfRoom));
            return;
        }
        if(roomTmp == null){
            logger.log(Level.WARNING,"{0}", String.format("%s Cannot find room %s ", name, nameOfRoom));
        } else {
            this.room = roomTmp;
            logger.log(Level.FINE, "{0}", String.format("%s (%s) goes from %s -> %s", name,role,nameStartRoom,roomTmp.getNameOfRoom()));
        }
    }

    /**
     * Interact with device.
     * @param device
     */
    public abstract void interactWithDevice(Device device);

    /**
     * Break the device.
     * @param roomTmp
     */
    public void breakDevice(Room roomTmp){
        Device device;
        List<Device> deviceList = roomTmp.getDevices().stream().filter(Device::isFree).toList();
        if(!deviceList.isEmpty()){
            device = deviceList.get(BetterRandom.nextInt(deviceList.size()));
            device.breakDevice(this);
            logger.log(Level.INFO, "{0}", String.format("%s intentionally broke device %s", name, device.getName()));
        }
    }

    public abstract void stopInteract();

    public void livingDoStuff(){
        strategy.doStuff(this);
    }

    /**
     * Stop using the device.
     */
    public void freeDevice(){
        if(deviceUsed == null){
            return;
        }
        logger.log(Level.FINE, "{0}", String.format("%s freed device %s", name,deviceUsed.getName()));
        deviceUsed.setIsFree(true);
        deviceUsed = null;
    }

    /**
     * Go to another room "roomTmp".
     * @param roomTmp in which room living will go.
     */
    public void goToAnotherRoom(Room roomTmp){
        String roomStartName = room.getNameOfRoom();
        if(room == roomTmp){
            logger.log(Level.FINE, "{0}", String.format("%s stays in %s", name, roomStartName));
        } else{
            this.room = roomTmp;
            logger.log(Level.FINE, "{0}", String.format("%s (%s) goes from %s -> %s", name,role,roomStartName,roomTmp.getNameOfRoom()));
        }
    }

    /**
     * Find certain device in the room.
     * @param name
     * @return
     */
    public Device findDevice(String name){
        for (Floor floor: House.getInstance().getFloorsList()) {
            for(Map.Entry<String, Room> entry: floor.getRoomsList().entrySet()){
                for (Device device: entry.getValue().getDevices()){
                    if(device.getName().equals(name) && device.isFree()) {
                        return device;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Find certain room.
     * @param nameOfRoom
     * @return
     */
    public Room findRoom(String nameOfRoom){
        for (Floor floor: House.getInstance().getFloorsList()) {
            for(Map.Entry<String, Room> entry: floor.getRoomsList().entrySet()){
                if (entry.getKey().equals(nameOfRoom)){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * CHoose random room.
     * @return
     */
    public Room randomRoom(){
        List<Floor> floorList = House.getInstance().getFloorsList();
        Floor floor = floorList.get(BetterRandom.nextInt(floorList.size()));
        Map<String, Room> roomMap = floor.getRoomsList();
        List<String> keys = new ArrayList<>(roomMap.keySet());
        String randomKey = keys.get(BetterRandom.nextInt(keys.size()) );
        return roomMap.get(randomKey);
    }

    // Reactions
    @Override
    public void update(Event event){
        if(event.getType() == FIRE){
            goToAnotherRoom(NoRoom.getInstance());
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }


    public String getRole() {
        return role;
    }

    public Device getDeviceUsed() {
        return deviceUsed;
    }

    // Setters
    public void setRoom(Room room) {
        this.room = room;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeviceUsed(Device deviceUsed) {
        this.deviceUsed = deviceUsed;
    }
}

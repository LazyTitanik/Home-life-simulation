package cz.cvut.k36.omo.smart_home.builder;

import cz.cvut.k36.omo.smart_home.built.Rooms.*;
import cz.cvut.k36.omo.smart_home.devices.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builder for the room.
 */
public class RoomBuilder {
    private static Logger logger = Logger.getLogger(RoomBuilder.class.getName());
    private final List<Device> deviceList = new ArrayList<>();
    private String name;

    /**
     * Create the room that we want.
     * @param name name of the creating room
     * @return new room
     */
    public Room creatingRoom(String name){
        Room room;
        switch (name){
            case "Bathroom":
                room = new Bathroom();
                break;
            case "Bedroom":
                room = new BedRoom();
                break;
            case "Garage":
                room = new Garage();
                break;
            case "Kitchen":
                room = new Kitchen();
                break;
            case "LivingRoom":
                room = new LivingRoom();
                break;
            case "Toilet":
                room = new Toilet();
                break;
            default:
                logger.log(Level.WARNING, "{0}", String.format("Incorrect room's name: %s", name));
                room = null;
                break;
        }
        return room;
    }

    /**
     * Add device in the room.
     * @param device device that we want to add
     */
    public void addDevice(Device device){
        deviceList.add(device);
    }

    /**
     * Add name of the room.
     * @param name name of the room
     */
    public void addName(String name){
        this.name = name;
    }

    /**
     * Create new room.
     * @return new room
     */
    public Room build(){
        Room room =  creatingRoom(name);
        room.setDevicesList(deviceList);
        for (Device device: deviceList) {
            device.setRoom(room);
        }
        return room;
    }
}

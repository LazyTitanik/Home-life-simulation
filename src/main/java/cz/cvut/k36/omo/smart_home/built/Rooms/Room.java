package cz.cvut.k36.omo.smart_home.built.Rooms;

import cz.cvut.k36.omo.smart_home.devices.Device;

import java.util.List;

/**
 * Room abstract class.
 */
public abstract class Room {
    /**
     * {@link #floorNum} number of the floor in which this room is.
     */
    protected int floorNum;
    /**
     * {@link #devicesList} list of devices in this room.
     */
    protected List<Device> devicesList = null;
    /**
     * {@link #nameOfRoom} name of the room.
     */
    protected String nameOfRoom = null;

    public List<Device> getDevices() {
        return devicesList;
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setDevicesList(List<Device> devicesList) {
        this.devicesList = devicesList;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

}

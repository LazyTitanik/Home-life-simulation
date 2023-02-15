package cz.cvut.k36.omo.smart_home.built.Rooms;

import cz.cvut.k36.omo.smart_home.devices.Device;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Outside.
 */
public class NoRoom extends Room{
    private static Logger logger = Logger.getLogger(NoRoom.class.getName());
    private static NoRoom instance = null;

    private NoRoom(){
        floorNum = 0;
        devicesList = null;
        nameOfRoom = "Outside";
    }

    public static NoRoom getInstance(){
        if(instance == null){
            instance = new NoRoom();
        }
        return instance;
    }

    @Override
    public void setDevicesList(List<Device> devicesList){
        logger.log(Level.WARNING,"Warning: you cannot add devices to NoRoom" );
    }

    @Override
    public void setFloorNum(int floorNum){
        logger.log(Level.WARNING,"Warning: you cannot set floor number to NoRoom");
    }
}

package cz.cvut.k36.omo.smart_home.events.devices;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.Living;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event that device broke.
 */
public class DeviceBroke extends Event {
    private static Logger logger = Logger.getLogger(DeviceBroke.class.getName());
    private final Device device;
    private final Living living;

    public DeviceBroke(Living living, Device device){
        super();
        this.device = device;
        this.living = living;
        this.type = EventType.DEVICEBROKE;
        logger.log(Level.INFO, "{0}", toString());
        EventManager.getInstance().notifySubscribers(this);
    }

    @Override
    public String toString() {
        return "\t\tThe device " + device.getName() + " is broken by " + living.getName() + ".\n";
    }

    public Device getDevice(){
        return device;
    }
}

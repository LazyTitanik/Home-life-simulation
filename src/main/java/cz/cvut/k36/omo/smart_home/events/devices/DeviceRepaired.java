package cz.cvut.k36.omo.smart_home.events.devices;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event that device has been repaired.
 */
public class DeviceRepaired extends Event {
    private static Logger logger= Logger.getLogger(DeviceRepaired.class.getName());
    private final Device device;
    private final Person person;

    public DeviceRepaired(Person person, Device device){
        super();
        this.type = EventType.DEVICEREPAIRED;
        this.person = person;
        this.device = device;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        return "\t\tThe device " + device.getName() + " has been repaired by " +person.getName() + ".\n";
    }
}

package cz.cvut.k36.omo.smart_home.events.devices;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Event that device is using.
 */
public class DeviceUsing extends Event {
    private static Logger logger = Logger.getLogger(DeviceUsing.class.getName());
    private final Person person;
    private final Device device;

    public DeviceUsing(Person person, Device device){
        super();
        this.person = person;
        this.device = device;
        this.type = EventType.DEVICEUSING;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        return "\t\t"+person.getName() + " is using " + device.getName() + "\n";
    }
}

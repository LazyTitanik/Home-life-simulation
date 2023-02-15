package cz.cvut.k36.omo.smart_home.events.devices;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.reports.ActivityNUsageReport;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Event that device has been switched off.
 */
public class DeviceSwitshedOff extends Event {
    private static Logger logger = Logger.getLogger(DeviceSwitshedOff.class.getName());
    private final Device device;
    private final Person person;

    public DeviceSwitshedOff(Person person, Device device){
        super();
        this.person = person;
        this.device = device;
        this.type = EventType.DEVICESWITCHOFF;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        return "\t\t"+person.getName() + " switched " + device.getName() + " off.\n";
    }
}

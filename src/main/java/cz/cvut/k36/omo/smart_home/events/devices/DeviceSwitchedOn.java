package cz.cvut.k36.omo.smart_home.events.devices;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.reports.ActivityNUsageReport;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event that device has been switched on.
 */
public class DeviceSwitchedOn extends Event {
    private static Logger logger = Logger.getLogger(DeviceSwitchedOn.class.getName());
    private final Person person;
    private final Device device;

    public DeviceSwitchedOn(Person person, Device device){
        super();
        this.type = EventType.DEVICESWITCHON;
        this.person = person;
        this.device = device;
        logger.log(Level.INFO, "{0}", toString());
        ActivityNUsageReport.getInstance().addUsage(this);
    }

    @Override
    public String toString() {
        return "\t\t"+person.getName() + " turned " + device.getName() + " on.\n";
    }

    public Person getPerson() {
        return person;
    }

    public Device getDevice() {
        return device;
    }
}

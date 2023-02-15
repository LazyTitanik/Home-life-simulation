package cz.cvut.k36.omo.smart_home.events.devices;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.pets.Pet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event that was interaction with device.
 */
public class DeviceInteracted extends Event {
    private static Logger logger = Logger.getLogger(DeviceInteracted.class.getName());
    private final Pet pet;
    private final Device device;

    public DeviceInteracted(Pet pet, Device device){
        super();
        this.pet = pet;
        this.device = device;
        this.type = EventType.DEVICEINTERACTED;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        return "\t\t"+pet.getName() + " interacted with " + device.getName() + ".\n";
    }
}

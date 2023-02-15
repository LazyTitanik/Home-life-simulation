package cz.cvut.k36.omo.smart_home.states;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceRepaired;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Father;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.living.people.Son;

public class BrokenState implements DeviceState{
    private final Device device;

    public BrokenState(Device device){
        this.device = device;
    }

    @Override
    public boolean interact(Living living) {
        return false;
    }

    @Override
    public boolean switchOn(Person person) {
        return false;
    }

    @Override
    public boolean switchOff(Person person) {
        return false;
    }

    @Override
    public boolean breakDevice(Living living) {
        return false;
    }

    /**
     * If the person is Son then he repairs it with a probability of 60%.
     * If the person is Father then he repairs it with a probability of 100%.
     * @param person
     * @return Returns true if repairing was successful false otherwise
     */
    @Override
    public boolean repairDevice(Person person) {
        if(person instanceof Father){
            device.setIsFree(true);
            device.setStateOfDevice(new InactiveState(device));
            new DeviceRepaired(person, device);
            return true;
        }
        if(person instanceof Son && BetterRandom.nextInt()%100 < 60){
            new DeviceRepaired(person, device);
            return true;
        }
        return false;
    }
}

package cz.cvut.k36.omo.smart_home.states;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.devices.*;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Father;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.living.people.Son;
import cz.cvut.k36.omo.smart_home.living.pets.Pet;
import cz.cvut.k36.omo.smart_home.reports.ActivityNUsageReport;
import cz.cvut.k36.omo.smart_home.reports.EventReport;

public class InactiveState implements DeviceState{
    private final Device device;

    public InactiveState(Device device){
        this.device = device;
    }

    /**
     * If the living is pet then it breakes it with a probability of 10%.
     * @param living
     * @return Returns true if the interaction has been done.
     */
    @Override
    public boolean interact(Living living) {
        if(living instanceof Person){
            return false;
        }
        if(living instanceof Pet pet){
            device.setIsFree(false);
            if(BetterRandom.nextInt() % 100 <= 10){
                new DeviceBroke(pet, device);
                device.setStateOfDevice(new BrokenState(device));
            } else{
                new DeviceInteracted(pet, device);
            }
        }
        return true;
    }

    /**
     * Try to switch on device, but breaks this device with some chance.
     * @param person
     * @return true if it was switched on
     */
    @Override
    public boolean switchOn(Person person) {
        float chance = (((float)(device.getUsageReserve() - device.getCurrentUsageReserve())) / device.getUsageReserve()) * 100;
        if(chance > BetterRandom.nextInt() % 100){
            breakDevice(person);
            return false;
        }
        new DeviceSwitchedOn(person, device);
        device.setStateOfDevice(new ActiveState(device));
        return true;
    }

    /**
     * Switch off the device.
     * @param person
     * @return always return true because device is already inactive
     */
    @Override
    public boolean switchOff(Person person){
        return true;
    }

    public boolean breakDevice(Living living){
        new DeviceBroke(living, device);
        device.setStateOfDevice(new BrokenState(device));
        return true;
    }

    @Override
    public boolean repairDevice(Person person) {
        if(person instanceof Father){
            return true;
        }
        if(person instanceof Son && BetterRandom.nextInt()%100 < 60){
            return true;
        }
        return false;
    }
}

package cz.cvut.k36.omo.smart_home.states;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceBroke;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceInteracted;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceSwitshedOff;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceUsing;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Father;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.living.people.Son;
import cz.cvut.k36.omo.smart_home.living.pets.Pet;
import cz.cvut.k36.omo.smart_home.reports.EventReport;

import java.util.Random;

public class ActiveState implements DeviceState{
    private final Device device;

    public ActiveState(Device device){
        this.device = device;
    }

    /**
     * If the living is pet then it breakes it with a probability of 10%.
     * Else the person interact with device if it is not broken.
     * @param living
     * @return Returns true if the interaction has been done.
     */
    @Override
    public boolean interact(Living living) {
            device.setIsFree(false);
            if(living instanceof Person person){
                new DeviceUsing(person, device);
            } else if(living instanceof Pet pet){
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
     * Switch off device.
     * @param person
     * @return
     */
    @Override
    public boolean switchOff(Person person) {
        new DeviceSwitshedOff(person, device);
        device.setStateOfDevice(new InactiveState(device));
        device.getCounter().increment(device.getConsumption());
        return true;
    }

    /**
     * Switch on device.
     * @param person
     * @return always return true because device is already active
     */
    @Override
    public boolean switchOn(Person person){
        return true;
    }

    @Override
    public boolean breakDevice(Living living){
        new DeviceBroke(living, device);
        device.setStateOfDevice(new BrokenState(device));
        return true;
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
            return true;
        }
        if(person instanceof Son && BetterRandom.nextInt()%100 < 60){
            return true;
        }
        return false;
    }
}

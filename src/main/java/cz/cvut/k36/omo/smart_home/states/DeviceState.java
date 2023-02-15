package cz.cvut.k36.omo.smart_home.states;

import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;

public interface DeviceState {
    /**
     * Returns true if the interaction has been done. Breaking the device while interacting counts as done interaction.
     * @param living
     * @return
     */
    boolean interact(Living living);

    /**
     * If device is off, changes the state to on. If it's broken, does nothing.
     * @param person
     * @return Returns true if switching was successful (device did not break), false otherwise
     */
    boolean switchOn(Person person);

    /**
     * If device is on, changes the state to off. If it's broken, does nothing.
     * @param person
     * @return Returns true if switching was successful (device did not break), false otherwise
     */
    boolean switchOff(Person person);

    /**
     * Changes state to "BrokenState".
     * @param living
     * @return Returns false if it was already broken, true otherwise
     */
    boolean breakDevice(Living living);

    /**
     * Repair device.
     * @param person
     * @return Returns true if repairing was successful false otherwise
     */
    boolean repairDevice(Person person);
}

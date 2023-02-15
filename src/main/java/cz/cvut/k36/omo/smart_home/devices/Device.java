package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.eventObserver.EventListener;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.states.ActiveState;
import cz.cvut.k36.omo.smart_home.states.BrokenState;
import cz.cvut.k36.omo.smart_home.states.DeviceState;

import static cz.cvut.k36.omo.smart_home.events.EventType.*;

/**
 * Device class.
 * {@link #room} in which room this device is
 * {@link #isSport} if it is the sport device
 * {@link #isFree} if this device is free now
 * {@link #nameOfDevice} name of the device
 * {@link #consumption} consumption of this device
 * {@link #counter} information about used resources
 * {@link #usageReserve} after how many days this device will be broken before starting the simulation
 * {@link #currentUsageReserve} how many days are left before the breakdown
 */
public abstract class Device implements EventListener {

    protected Room room;
    protected boolean isSport;
    protected boolean isFree = true;

    protected String nameOfDevice;

    protected Consumption consumption;

    protected ConsumptionRecord counter;

    protected DeviceState stateOfDevice;

    protected int usageReserve;

    protected int currentUsageReserve;

    public DeviceState getStateOfDevice() {
        return stateOfDevice;
    }

    public void setStateOfDevice(DeviceState stateOfDevice) {
        this.stateOfDevice = stateOfDevice;
    }

    /**
     * Returns true if the interaction has been done.
     * Breaking the device while interacting counts as done interaction
     * @param living who interacts with this device now
     * @return
     */
    public boolean interact(Living living){
        return stateOfDevice.interact(living);
    }

    /**
     * If device is on/off, changes the state to off/on.
     * If it's broken, does nothing
     * @param person person who did this
     * @return Returns true if switching was successful (device did not break), false otherwise
     */

    public boolean switchOn(Person person){
        return stateOfDevice.switchOn(person);
    }

    /**
     * If device is on/off, changes the state to off/on.
     * If it's broken, does nothing
     * @param person person who did this
     * @return Returns true if switching was successful (device did not break), false otherwise
     */
    public boolean switchOff(Person person){
        return stateOfDevice.switchOff(person);
    }

    /**
     * Changes state to "BrokenState".
     * @param living living who did this
     * @return Returns false if it was already broken, true otherwise
     */
    public boolean breakDevice(Living living){
        return stateOfDevice.breakDevice(living);
    }

    /**
     * Device repairing.
     * @param person who trying to repair it
     * @return Returns true if repairing was successful, false otherwise
     */
    public boolean repair(Person person){
        if (stateOfDevice.repairDevice(person)){
            currentUsageReserve = usageReserve;
            return true;
        }
        return false;
    }

    protected Device(){
        EventManager.getInstance().subscribe(DAY, this);
    }

    /**
     * Starts new day for the device. Increments consumption and decrements usage reserve.
     */
    @Override
    public void newDay(){
        if(stateOfDevice instanceof ActiveState){
            counter.increment(consumption);
            currentUsageReserve--;
            if(currentUsageReserve <= 0){
                setStateOfDevice(new BrokenState(this));
            }
        }
    }

    public String getName() {
        return nameOfDevice;
    }

    public void setName(String nameOfDevice) {
        this.nameOfDevice = nameOfDevice;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public ConsumptionRecord getCounter() {
        return counter;
    }

    public int getUsageReserve() {
        return usageReserve;
    }

    public int getCurrentUsageReserve() {
        return currentUsageReserve;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setIsFree(boolean free) {
        isFree = free;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isSport() {
        return isSport;
    }

    public void setSport(boolean sport) {
        isSport = sport;
    }

    /**
     * The vast majority of devices only react to an event of
     * new day, which is handled by newDay() method.
     * That's why this method is empty
     * In case some device is supposed to react to an event, override
     * and define the behaviour
     * @param event
     */
    @Override
    public void update(Event event){
    }
}

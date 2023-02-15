package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.InactiveState;
/**
 * Car class.
 */
public class Car extends Device{
    public Car() {
        super();
        nameOfDevice = "Car";
        consumption = new Consumption(0, 10, 0);
        usageReserve = currentUsageReserve = 360;
        counter = new ConsumptionRecord();
        stateOfDevice = new InactiveState(this);
    }
}

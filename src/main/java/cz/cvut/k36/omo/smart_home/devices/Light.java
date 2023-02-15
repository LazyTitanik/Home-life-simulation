package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

/**
 * Light class.
 */
public class Light extends Device{
    public Light() {
        super();
        nameOfDevice = "Light";
        isSport = false;
        consumption = new Consumption(3, 0, 0);
        usageReserve = currentUsageReserve = 100;
        counter = new ConsumptionRecord();
        stateOfDevice = new InactiveState(this);
    }
}

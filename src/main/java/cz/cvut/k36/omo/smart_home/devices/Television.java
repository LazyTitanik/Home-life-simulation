package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

/**
 * Television class.
 */
public class Television extends  Device{
    public Television() {
        super();
        nameOfDevice = "Television";
        isSport = false;
        consumption = new Consumption(6, 0, 0);
        usageReserve = currentUsageReserve = 600;
        counter = new ConsumptionRecord();
        stateOfDevice = new InactiveState(this);
    }
}

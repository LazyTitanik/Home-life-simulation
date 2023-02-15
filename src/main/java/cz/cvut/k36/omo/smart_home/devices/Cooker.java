package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

/**
 * Cooker class.
 */
public class Cooker extends Device{
    public Cooker() {
        super();
        nameOfDevice = "Cooker";
        isSport = false;
        consumption = new Consumption(0, 5, 0);
        usageReserve = currentUsageReserve = 500;
        counter = new ConsumptionRecord();
        stateOfDevice = new InactiveState(this);
    }
}

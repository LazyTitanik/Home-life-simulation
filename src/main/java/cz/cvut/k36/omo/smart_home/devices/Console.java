package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

/**
 * Console class.
 */
public class Console extends Device{
    public Console() {
        super();
        nameOfDevice = "Console";
        isSport = false;
        consumption = new Consumption(5, 0, 0);
        usageReserve = currentUsageReserve = 1200;
        counter = new ConsumptionRecord();
        stateOfDevice = new InactiveState(this);
    }
}

package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.ActiveState;

/**
 * Remote class.
 */
public class Remote extends Device{
    public Remote() {
        super();
        nameOfDevice = "Remote";
        isSport = false;
        consumption = new Consumption(0, 0, 0);
        usageReserve = currentUsageReserve = 200;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }
}

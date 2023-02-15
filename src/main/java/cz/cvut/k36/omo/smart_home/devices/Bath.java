package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.ActiveState;

/**
 * Bath class.
 */
public class Bath extends Device{
    public Bath() {
        super();
        nameOfDevice = "Bath";
        isSport = true;
        consumption = new Consumption(0, 0, 12);
        usageReserve = currentUsageReserve = 1200;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }
}

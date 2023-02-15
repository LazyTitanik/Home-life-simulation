package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.ActiveState;

/**
 * Fridge class.
 */
public class Fridge extends Device{
    public Fridge() {
        super();
        nameOfDevice = "Fridge";
        isSport = false;
        consumption = new Consumption(4, 0, 0);
        usageReserve = currentUsageReserve = 1500;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }
}

package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.ActiveState;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

/**
 * Skis class.
 */
public class Skis extends Device{
    public Skis() {
        super();
        nameOfDevice = "Skis";
        isSport = true;
        consumption = new Consumption(0, 0, 0);
        usageReserve = currentUsageReserve = 1200;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }
}

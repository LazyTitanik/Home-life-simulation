package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

/**
 * Heating class.
 */
public class Heating extends Device{
    public Heating() {
        super();
        nameOfDevice = "Heating";
        isSport = false;
        consumption = new Consumption(0, 9, 0);
        usageReserve = currentUsageReserve = 2000;
        counter = new ConsumptionRecord();
        stateOfDevice = new InactiveState(this);
    }
}

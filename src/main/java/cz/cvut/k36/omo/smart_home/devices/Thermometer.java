package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.ActiveState;

/**
 * Thermometer class.
 */
public class Thermometer extends Device{
    public Thermometer() {
        super();
        nameOfDevice = "Thermometer";
        isSport = false;
        consumption = new Consumption(1, 0, 0);
        usageReserve = currentUsageReserve = 1000;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }
}

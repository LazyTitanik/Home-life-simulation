package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.states.ActiveState;

/**
 * Rag class.
 */
public class Rag extends Device{
    public Rag() {
        super();
        nameOfDevice = "Rag";
        isSport = false;
        consumption = new Consumption(0, 0, 2);
        usageReserve = currentUsageReserve = 12000;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }
}

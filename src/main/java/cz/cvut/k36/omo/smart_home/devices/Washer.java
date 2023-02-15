package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.states.ActiveState;
/**
 * Washer class.
 */
public class Washer extends Device {
    public Washer() {
        super();
        nameOfDevice = "Washer";
        isSport = false;
        consumption = new Consumption(4, 0, 3);
        usageReserve = currentUsageReserve = 1200;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }


}

package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.eventObserver.EventListener;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.events.fire.FireCodes;
import cz.cvut.k36.omo.smart_home.events.fire.FireFighting;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.states.ActiveState;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

import static cz.cvut.k36.omo.smart_home.events.EventType.FIRE;

/**
 * Fire alarm class.
 */
public class FireAlarm extends Device {
    public FireAlarm() {
        super();
        nameOfDevice = "Fire alarm";
        isSport = false;
        consumption = new Consumption(1, 0, 0);
        usageReserve = currentUsageReserve = 5000;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }

    @Override
    public boolean breakDevice(Living living){
        return false;
    }
}

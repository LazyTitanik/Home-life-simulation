package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.devices.consumption.Consumption;
import cz.cvut.k36.omo.smart_home.devices.consumption.ConsumptionRecord;
import cz.cvut.k36.omo.smart_home.eventObserver.EventListener;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.states.ActiveState;
import cz.cvut.k36.omo.smart_home.states.InactiveState;

import java.util.List;

import static cz.cvut.k36.omo.smart_home.events.EventType.BLACKOUT;

/**
 * Circuit Brearker class.
 */
public class CircuitBreaker extends Device implements EventListener {
    public CircuitBreaker() {
        super();
        EventManager.getInstance().subscribe(BLACKOUT, this);
        nameOfDevice = "Circuit Breaker";
        consumption = new Consumption(1, 0, 0);
        usageReserve = currentUsageReserve = 5000;
        counter = new ConsumptionRecord();
        stateOfDevice = new ActiveState(this);
    }

    @Override
    public void update(Event event) {
        if(event.getType() != BLACKOUT){
            return;
        }
        if(!(stateOfDevice instanceof ActiveState)){
            return;
        }
        List<Device> activeDevices =
                House.getInstance().getDevicesList().stream().filter(n-> n.getStateOfDevice() instanceof ActiveState).toList();

        for(Device device : activeDevices){
            if(!(device instanceof Fridge ||
               device instanceof FireAlarm ||
               device instanceof CircuitBreaker)){
                device.setStateOfDevice(new InactiveState(device));
            }
        }
    }

    @Override
    public boolean breakDevice(Living living){
        return false;
    }
}

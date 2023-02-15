package cz.cvut.k36.omo.smart_home.devices;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.eventObserver.EventListener;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.fire.FireCodes;
import cz.cvut.k36.omo.smart_home.events.fire.FireFighting;
import cz.cvut.k36.omo.smart_home.states.ActiveState;

import static cz.cvut.k36.omo.smart_home.events.EventType.FIRE;

public class APIFireAlarm implements EventListener {

    private FireAlarm fireAlarm;

    public APIFireAlarm() {
        EventManager.getInstance().subscribe(FIRE, this);
    }

    @Override
    public void update(Event event) {
        if(event.getType() != FIRE){
            return;
        }
        if(!(fireAlarm.stateOfDevice instanceof ActiveState)){
            return;
        }
        int rand = BetterRandom.nextInt(100);
        if(rand == 1){
            new FireFighting(FireCodes.ALARMNOTCOPED);
        }else{
            new FireFighting(FireCodes.ALARMCOPED);
        }
    }

    public FireAlarm getFireAlarm() {
        return fireAlarm;
    }

    public void setFireAlarm(FireAlarm fireAlarm) {
        this.fireAlarm = fireAlarm;
    }
    @Override
    public void newDay() {
        fireAlarm.newDay();
    }
}

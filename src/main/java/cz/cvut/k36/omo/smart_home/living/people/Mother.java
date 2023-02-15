package cz.cvut.k36.omo.smart_home.living.people;

import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.events.RanOutOfFood;
import org.junit.jupiter.api.ClassOrderer;

import static cz.cvut.k36.omo.smart_home.events.EventType.RANOUTOFFOOD;

/**
 * Mother class.
 */
public class Mother extends Person {

    public Mother(){
        super();
        role = "Mother";
        EventManager.getInstance().subscribe(RANOUTOFFOOD, this);
    }
    @Override
    public void update(Event event) {
        super.update(event);
        if(event.getType() == RANOUTOFFOOD){
            goShopping();
        }
    }
}

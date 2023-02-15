package cz.cvut.k36.omo.smart_home.events.fire;

import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event that a fire has began.
 */
public class FireEvent extends Event {
    private static Logger logger = Logger.getLogger(FireFighting.class.getName());
    public FireEvent(){
        super();
        this.type = EventType.FIRE;
        logger.log(Level.INFO, "{0}", toString());
        EventManager.getInstance().notifySubscribers(this);
    }

    @Override
    public String toString() {
        return "A fire has began!\n";
    }
}

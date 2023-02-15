package cz.cvut.k36.omo.smart_home.events;

import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event: there is no food in the fridge.
 */
public class RanOutOfFood extends Event{
    private static Logger logger = Logger.getLogger(RanOutOfFood.class.getName());
    public RanOutOfFood(){
        super();
        this.type = EventType.RANOUTOFFOOD;
        logger.log(Level.INFO, "{0}", toString());
        EventManager.getInstance().notifySubscribers(this);
    }

    @Override
    public String toString() {
        return "There is no food in the fridge.\n";
    }
}

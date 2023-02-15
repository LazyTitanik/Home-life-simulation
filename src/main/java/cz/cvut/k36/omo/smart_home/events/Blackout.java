package cz.cvut.k36.omo.smart_home.events;

import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event: The electricity has been switched off.
 */
public class Blackout extends Event{
    private static Logger logger = Logger.getLogger(Blackout.class.getName());

    public Blackout(){
        super();
        this.type = EventType.BLACKOUT;
        logger.log(Level.INFO, "{0}", toString());
        EventManager.getInstance().notifySubscribers(this);
    }

    @Override
    public String toString() {
        return "The electricity has been switched off!!!\n";
    }
}

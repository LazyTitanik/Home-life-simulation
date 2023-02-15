package cz.cvut.k36.omo.smart_home.events;

import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;

import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.k36.omo.smart_home.events.EventType.NIGHT;

/**
 * Event: A night has begun.
 */
public class Night extends Event{
    private static Logger logger = Logger.getLogger(Night.class.getName());
    public Night(){
        super();
        this.type = NIGHT;
        logger.log(Level.INFO, "{0}", toString());
        EventManager.getInstance().notifySubscribers(this);
    }

    @Override
    public String toString() {
        return "A night has begun. Time to sleep.\n";
    }
}

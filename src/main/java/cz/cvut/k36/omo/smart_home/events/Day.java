package cz.cvut.k36.omo.smart_home.events;

import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event: day has began.
 */
public class Day extends Event{
    private static Logger logger = Logger.getLogger(Day.class.getName());
    private static int days =0;
    private int thisDayNum;

    public Day() {
        super();
        this.type = EventType.DAY;
        logger.log(Level.INFO, "{0}", toString());
        days++;
        thisDayNum = days;
        EventManager.getInstance().notifySubscribers(this);
    }

    @Override
    public String toString(){
        return "Day №" + thisDayNum +" has begun. Time to do some shit.\n";
    }
}

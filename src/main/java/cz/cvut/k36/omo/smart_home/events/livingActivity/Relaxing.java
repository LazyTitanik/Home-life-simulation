package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Person is relaxing.
 */
public class Relaxing extends Event {
    private static Logger logger = Logger.getLogger(Relaxing.class.getName());
    private Person person;

    public Relaxing(Person person) {
        super();
        this.person = person;
        this.type = EventType.RELAXING;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString(){
        return "\t"+person.getName() + " is relaxing\n";
    }
}

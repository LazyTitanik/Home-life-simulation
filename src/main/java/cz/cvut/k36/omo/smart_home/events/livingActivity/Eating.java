package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Person is eating.
 */
public class Eating extends Event {
    private static Logger logger = Logger.getLogger(Eating.class.getName());
    private Person person;

    public Eating(Person person) {
        super();
        this.type = EventType.EATING;
        this.person = person;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString(){
        return "\t"+person.getName() + " is eating\n";
    }
}

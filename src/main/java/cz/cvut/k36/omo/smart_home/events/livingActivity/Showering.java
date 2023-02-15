package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Person take a shower.
 */
public class Showering extends Event {
    private static Logger logger = Logger.getLogger(Showering.class.getName());
    private Person person;

    public Showering(Person person) {
        super();
        this.type = EventType.SHOWERING;
        this.person = person;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString(){
        return "\t"+person.getName() + " is taking a shower\n";
    }
}

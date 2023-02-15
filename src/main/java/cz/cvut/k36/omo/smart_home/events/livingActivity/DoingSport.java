package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Person is doing sport.
 */
public class DoingSport extends Event {
    private static Logger logger = Logger.getLogger(DoingSport.class.getName());
    private Person person;

    public DoingSport(Person person){
        super();
        this.type = EventType.DOINGSPORT;
        this.person = person;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        return "\t"+person.getName() + " is doing sports.\n";
    }
}

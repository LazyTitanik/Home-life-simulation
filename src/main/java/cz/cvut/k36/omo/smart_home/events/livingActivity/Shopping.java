package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Person is going or driving to shopping.
 */
public class Shopping extends Event {
    private static Logger logger = Logger.getLogger(Shopping.class.getName());
    private Person person;
    private boolean isCar;

    public Shopping(Person person, boolean isCar) {
        super();
        this.type = EventType.SHOPPING;
        this.person = person;
        this.isCar = isCar;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString(){
        if(isCar){
            return "\t"+person.getName() + " is driving for shopping\n";
        }else{
            return "\t"+person.getName() + " is going for shopping\n";
        }
    }
}

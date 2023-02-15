package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.living.pets.Pet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The pet is cuddling.
 */
public class Cuddling extends Event {
    private static Logger logger = Logger.getLogger(Cuddling.class.getName());
    private Pet pet;
    private Person person;

    public Cuddling(Pet pet, Person person) {
        super();
        this.type = EventType.CUDDLING;
        this.pet = pet;
        this.person = person;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString(){
        return "\t"+pet.getName() + " is cuddling with " + person.getName() + "\n";
    }
}

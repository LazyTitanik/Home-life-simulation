package cz.cvut.k36.omo.smart_home.events;

import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.living.pets.Pet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event: pet wants to eat.
 */
public class NeedFood extends Event{
    private static Logger logger = Logger.getLogger(NeedFood.class.getName());
    private Pet pet;

    public NeedFood(Pet pet){
        super();
        this.type = EventType.NEEDFOOD;
        this.pet = pet;
        logger.log(Level.INFO, "{0}", toString());
        EventManager.getInstance().notifySubscribers(this);
    }

    public Pet getPet() {
        return pet;
    }

    @Override
    public String toString() {
        return pet.getName() + " wants to eat.\n";
    }
}

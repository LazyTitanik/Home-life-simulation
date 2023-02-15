package cz.cvut.k36.omo.smart_home.events.livingActivity;

import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The person is cleaning.
 */
public class Cleaning extends Event {
    private static Logger logger = Logger.getLogger(Cleaning.class.getName());
    private Person person;
    private Room room;
    public Cleaning(Person person, Room room){
        super();
        this.type = EventType.CLEANING;
        this.person = person;
        this.room = room;
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        return "\t"+person.getName() + " is cleaning " + room.getNameOfRoom() + "\n";
    }
}

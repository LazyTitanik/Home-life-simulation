package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.Map;

public class HungryStrategy implements PersonStrategy{

    private int numOfMention = 0;

    @Override
    public void doStuff(Living living) {
        Person person = (Person)living;
        switch (numOfMention){
            case 0,7:
                person.goToEat();
                break;
            case 3,9:
                person.stopEating();
                person.goToAnotherRoom("");
                break;
            case 4:
                person.relax("Television");
                break;
            case 5:
                person.stopRelax();
                break;
            default:
                break;
        }
        numOfMention++;
    }

}

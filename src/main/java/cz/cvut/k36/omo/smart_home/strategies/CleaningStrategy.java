package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;

public class CleaningStrategy implements  PersonStrategy {

    private int numOfMention = 0;

    @Override
    public void doStuff(Living living) {
        Person person = (Person)living;
        switch (numOfMention) {
            case 0:
                person.cleanRoom(person.randomRoom());
                break;
            case 2:
                person.stopCleaning();
                person.takeShower();
                break;
            case 3:
                person.stopShower();
                break;
            case 4:
                person.goToEat();
                break;
            case 5:
                person.stopEating();
            case 6:
                person.goShopping();
                break;
            case 9:
                person.stopShopping();
            default:
                break;
        }
        numOfMention++;
    }

}

package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;

public class LazyStrategy implements PersonStrategy{

    private int numOfMention = 0;

    @Override
    public void doStuff(Living living) {
        Person person  = (Person)living;
        switch (numOfMention){
            case 0:
                person.relax("Television");
                if(person.getDeviceUsed() == null){
                    person.relax("Console");
                }
                break;
            case 1:
                person.stopRelax();
                person.relax("Console");
                if(person.getDeviceUsed() == null){
                    person.relax("Television");
                }
                break;
            case 3:
                person.stopRelax();
                break;
            case 4:
                person.goToEat();
                break;
            case 5:
                person.stopEating();
                person.sport();
                break;
            case 7:
                person.stopSport();
                break;
            default:
                break;
        }
        numOfMention++;
    }

}

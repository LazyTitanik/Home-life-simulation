package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.built.Floor;
import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.built.Rooms.NoRoom;
import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.devices.Skis;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportStrategy implements PersonStrategy{

    private int numOfMention = 0;

    @Override
    public void doStuff(Living living) {
        Person person = (Person) living;
        switch (numOfMention){
            case 0,8:
                person.sport();
                break;
            case 2:
                person.goToAnotherRoom("");
                person.stopSport();
                person.goToAnotherRoom("");
                break;
            case 3:
                person.goToEat();
                break;
            case 4:
                person.stopEating();
                person.sport();
                break;
            case 6:
                person.stopSport();
                person.takeShower();
                break;
            case 7:
                person.stopShower();
                break;
            case 9:
                person.stopSport();
                break;
            default:
                break;
        }
        numOfMention++;
    }
}

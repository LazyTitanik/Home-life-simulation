package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.built.Floor;
import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.living.pets.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class LayingStrategy implements PetStrategy{

    @Override
    public void doStuff(Living living) {
        int cond = BetterRandom.nextInt(10);
        if(cond > 0 && cond < 3){
            cond = 1;
        }
        else if (cond > 2){
            cond = 2;
        }
        switch (cond){
            case 0:
                living.interactWithDevice(null);
                living.stopInteract();
                break;
            case 1:
                List<Living> listTmp =
                    House.getInstance().getLivingList().stream().filter(n-> n instanceof Person).toList();
                if(!listTmp.isEmpty()){
                    Person person = (Person) listTmp.get(BetterRandom.nextInt(listTmp.size()));
                    Pet pet = (Pet) living;
                    pet.cuddle(person);
                }
                living.goToAnotherRoom("");
                break;
            case 2:
                break;
        }
    }
}

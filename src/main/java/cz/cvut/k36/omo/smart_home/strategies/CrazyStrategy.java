package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.living.Living;


public class CrazyStrategy implements PetStrategy{

    @Override
    public void doStuff(Living living) {

        int cond = BetterRandom.nextInt(8);
        if(cond > 3)
            cond = 3;

        switch (cond){
            case 0:
                living.breakDevice(living.getRoom());
                break;
            case 1:
                break;
            case 2:
                living.goToAnotherRoom("");
                break;
            case 3:
                living.goToAnotherRoom("");
                living.interactWithDevice(null);
                living.stopInteract();
                break;
        }
    }
}

package cz.cvut.k36.omo.smart_home;

import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.events.Blackout;
import cz.cvut.k36.omo.smart_home.events.Day;
import cz.cvut.k36.omo.smart_home.events.Night;
import cz.cvut.k36.omo.smart_home.events.fire.FireCodes;
import cz.cvut.k36.omo.smart_home.events.fire.FireFighting;
import cz.cvut.k36.omo.smart_home.living.Living;

/**
 * SmartHome class.
 * {@link #maxCountOfActivities} is count of activities for each person per day
 */
public class SmartHome {
    private static final int maxCountOfActivities = 10;
    private static SmartHome instance = null;

    private SmartHome(){}

    /**
     * Method return instance of SmartHome of create new SmartHome if instance does not exist.
     * @return {@link #instance} of SmartHome
     */
    public static SmartHome getInstance() {
        if (instance == null) {
            instance =  new SmartHome();
        }
        return instance;
    }

    /**
     * Method start simulation.
     */
    public void run(){
        for (int i = 0; i < House.getInstance().getDays(); i++) {
            new Day();
            for (int j = 0; j < maxCountOfActivities; j++) {
                if(BetterRandom.nextInt(100) == 1){
                    new Blackout();
                }
                if(BetterRandom.nextInt(10000) == 1){
                    House.getInstance().beginFire();
                }
                if(House.getInstance().getIsInFire()){
                    new FireFighting(FireCodes.CATASTROPHE);
                    return;
                }
                if(j == 7){
                    new Night();
                }
                for (Living living : House.getInstance().getLivingList()){
                    living.livingDoStuff();
                }
            }
        }
    }
}

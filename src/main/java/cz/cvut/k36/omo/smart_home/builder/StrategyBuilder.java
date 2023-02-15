package cz.cvut.k36.omo.smart_home.builder;

import cz.cvut.k36.omo.smart_home.strategies.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gives strategy for the each living.
 */
public class StrategyBuilder {
    private static Logger logger = Logger.getLogger(StrategyBuilder.class.getName());

    private StrategyBuilder() {
    }

    /**
     * Finds strategy for the pets.
     * @param name name of the strategy
     * @return new strategy
     */
    public static PetStrategy getPetStrategy(String name){
        PetStrategy strategy;
        switch (name){
            case "Crazy":
                strategy = new CrazyStrategy();
                break;
            case "Laying":
                strategy = new LayingStrategy();
                break;
            default:
                logger.log(Level.SEVERE, "{0}", String.format("Incorrect strategy name: %s", name));
                strategy = null;
                break;
        }
        return strategy;
    }
    /**
     * Finds strategy for the person.
     * @param name name of the strategy
     * @return new strategy
     */
    public static PersonStrategy getPersonStrategy(String name){
        PersonStrategy strategy;
        switch (name){
            case "Lazy":
                strategy = new LazyStrategy();
                break;
            case "Cleaning":
                strategy = new CleaningStrategy();
                break;
            case "Hungry":
                strategy = new HungryStrategy();
                break;
            case "Sport":
                strategy = new SportStrategy();
                break;
            default:
                logger.log(Level.SEVERE, "{0}", String.format("Incorrect strategy name: %s", name));
                strategy = null;
                break;
        }
        return strategy;
    }
}

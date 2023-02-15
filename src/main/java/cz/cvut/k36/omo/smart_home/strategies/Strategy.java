package cz.cvut.k36.omo.smart_home.strategies;

import cz.cvut.k36.omo.smart_home.living.Living;

public interface Strategy {
    /**
     * Follows the strategy.
     * @param living
     */
    void doStuff(Living living);
}

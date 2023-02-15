package cz.cvut.k36.omo.smart_home.eventObserver;

import cz.cvut.k36.omo.smart_home.events.Event;

public interface EventListener {
    void update(Event event);

    /**
     * Starts new day for the listeners.
     */
    void newDay();
}

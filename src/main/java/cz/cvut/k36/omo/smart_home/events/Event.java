package cz.cvut.k36.omo.smart_home.events;

import cz.cvut.k36.omo.smart_home.reports.EventReport;

/**
 * Event class.
 */
public abstract class Event {
    protected EventType type;
    protected Event() {
        EventReport.getInstance().addEvent(this);
    }

    public EventType getType(){
        return type;
    }

    public abstract String toString();
}

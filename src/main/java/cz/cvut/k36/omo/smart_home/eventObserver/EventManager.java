package cz.cvut.k36.omo.smart_home.eventObserver;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.Day;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Father;
import cz.cvut.k36.omo.smart_home.living.people.Son;

import java.util.*;

import static cz.cvut.k36.omo.smart_home.events.EventType.*;

/**
 * Event Manager class(Singleton).
 * {@link #listeners} list of listener for the different events
 * {@link #hasSon} if the son is exists in this configuration.
 */
public class EventManager {
    private Map<EventType, List<EventListener>> listeners;
    private static EventManager instance = null;
    private boolean hasSon;

    private EventManager(){}

    /**
     * |Create new eventManager.
     * @return new event Manager
     */
    private static EventManager create(){
        if(instance == null){
            instance = new EventManager();
            instance.listeners = new EnumMap<>(EventType.class);
        }
        return instance;
    }

    /**
     *
     * @return existing instance of the Event Manager.
     */
    public static EventManager getInstance() {
        if(instance == null) {
            return create();
        }else{
            return instance;
        }
    }

    /**
     * Subscribe to the deviceBroke event.
     * @param listener
     * @return
     */
    private boolean toSubscribeBroke(EventListener listener){
        if(listener instanceof Son){
            if(!hasSon){
                hasSon = true;
                for(EventListener father : listeners.get(DEVICEBROKE).stream().filter(Father.class::isInstance).toList()){
                    unsubscribe(DEVICEBROKE, father);
                }
            }
            return true;
        }
        if(listener instanceof Father){
            return !hasSon;
        }
        return false;
    }

    /**
     * Subscribe new listener to the new eventType.
     * @param eventType
     * @param listener
     */
    public void subscribe(EventType eventType, EventListener listener){
        if(eventType == DEVICEBROKE){
            // Do not merge if statements
            if(!toSubscribeBroke(listener)){
                return;
            }
        }

        if(instance.listeners.containsKey(eventType)){
            instance.listeners.get(eventType).add(listener);
        } else{
            List<EventListener> list = new ArrayList<>();
            list.add(listener);
            instance.listeners.put(eventType, list);
        }
    }

    /**
     * Unsubscribe listener from event.
     * @param eventType
     * @param listener
     */
    public void unsubscribe(EventType eventType, EventListener listener){
        if(instance.listeners.containsKey(eventType)){
            instance.listeners.get(eventType).remove(listener);
        }
    }

    /**
     * Notifies subscribers of event.
     * @param event
     */
    public void notifySubscribers(Event event){
        if(event.getType() == DAY){
            this.newDayUpdate();
        }
        List<EventListener> list = listeners.get(event.getType());
        if(list == null){
            return;
        }
        for (EventListener listener : list) {
            listener.update(event);
        }
    }

    /**
     * Notify all listeners about starting of new day.
     */
    public void newDayUpdate(){
        List<EventListener> listOfDevices = instance.listeners.get(DAY);
        if(listOfDevices != null){
            for (EventListener listener : listOfDevices) {
                listener.newDay();
            }
        }
    }
}

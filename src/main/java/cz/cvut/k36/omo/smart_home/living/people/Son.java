package cz.cvut.k36.omo.smart_home.living.people;

import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceBroke;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.k36.omo.smart_home.events.EventType.DEVICEBROKE;
import static cz.cvut.k36.omo.smart_home.events.EventType.RANOUTOFFOOD;

/**
 * Son class.
 * {@link #findingTime} how long he finds instruction for the certain device
 * {@link #queue} queue of the broken devices that he needs to repair
 * {@link #lookingForManual} if he is looking for manual now
 * {@link #repairingDevice} the device that he is repairing now
 * {@link #father} his father
 */
public class Son extends Person {
    private static Logger logger = Logger.getLogger(Son.class.getName());

    private int findingTime = 0;
    private Queue<DeviceBroke> queue = new ArrayDeque<>();
    private boolean lookingForManual = false;

    private Event eventForBrokenDevice = null;
    private Device repairingDevice;
    Father father = null;

    public Son(){
        super();
        role = "Son";
        EventManager.getInstance().subscribe(DEVICEBROKE, this);
    }
    @Override
    public void update(Event event) {
        super.update(event);
        if(event.getType() == DEVICEBROKE){
            DeviceBroke tmp = (DeviceBroke) event;
            logger.log(Level.FINE, "{0}", String.format("%s got task to repair %s", name, tmp.getDevice().getName()));
            if(repairingDevice != null){
                queue.add(tmp);
            } else{
                lookingForManual = true;
                repairingDevice = tmp.getDevice();
                eventForBrokenDevice = tmp;
            }
        }
    }
    /**
     * Execute strategy and adopt the executing if he needs to repair device.
     * If he cannot repair the device he asks his father if father is in this house
     */
    @Override
    public void livingDoStuff(){

        if(repairingDevice != null){
            if(++findingTime > 2){
                lookingForManual = false;
                if(!repairingDevice.repair(this)){
                    if(father == null){
                        try{
                            father =(Father)House.getInstance().getLivingList().stream().filter(Father.class::isInstance).toList().get(0);
                            father.update(eventForBrokenDevice);
                        } catch (ArrayIndexOutOfBoundsException e){
                            logger.log(Level.FINE, "Son did not find the father");
                        }
                    }else{
                        father.update(eventForBrokenDevice);
                        repairingDevice = null;
                        findingTime = 0;
                    }
                }
                repairingDevice = null;
                findingTime = 0;
            }
        }

        if(queue.size() != 0){
            update(queue.poll());
        } else{
            strategy.doStuff(this);
        }


    }
}

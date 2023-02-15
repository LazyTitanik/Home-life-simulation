package cz.cvut.k36.omo.smart_home.living.people;

import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.devices.Light;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.devices.DeviceBroke;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.k36.omo.smart_home.events.EventType.*;

/**
 * Father class.
 * {@link #findingTime} how long he finds instruction for the certain device
 * {@link #queue} queue of the broken devices that he needs to repair
 * {@link #lookingForManual} if he is looking for manual now
 * {@link #repairingDevice} the device that he is repairing now
 */
public class Father extends Person {
    private static Logger logger = Logger.getLogger(Father.class.getName());
    private int findingTime = 0;
    private Queue<DeviceBroke> queue = new ArrayDeque<>();
    private boolean lookingForManual = false;

    private Event eventForBrokenDevice = null;
    private Device repairingDevice;
    public Father(){
        super();
        role = "Father";
        EventManager.getInstance().subscribe(DEVICEBROKE, this);
        EventManager.getInstance().subscribe(RANOUTOFFOOD, this);
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
        if(event.getType() == RANOUTOFFOOD){
            goShopping();
        }
    }

    /**
     * Execute strategy and adopt the executing if he needs to repair device.
     */
    @Override
    public void livingDoStuff(){

        if(repairingDevice != null){
            if(++findingTime > 2){
                lookingForManual = false;
                repairingDevice.repair(this);
                repairingDevice = null;
                findingTime = 0;
            }
        }

        if(!queue.isEmpty()){
            update(queue.poll());
        } else{
            strategy.doStuff(this);
        }
    }
}

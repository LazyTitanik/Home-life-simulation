package cz.cvut.k36.omo.smart_home.events.fire;

import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.EventType;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Alarm tries to fight with a fire.
 */
public class FireFighting extends Event {
    private static Logger logger = Logger.getLogger(FireFighting.class.getName());
    private final FireCodes code;

    public FireFighting(FireCodes code) {
        super();
        this.code = code;
        this.type = EventType.FIREFIGHTING;
        if(code == FireCodes.ALARMCOPED){
            House.getInstance().stopFire();
        }
        logger.log(Level.INFO, "{0}", toString());
    }

    @Override
    public String toString() {
        if(code == FireCodes.ALARMCOPED)
            return "One of the fire alarms did cope with putting out the fire\n";
        if(code == FireCodes.STOPPED)
            return "The fire has been put out successfully. Nobody is hurt\n";
        if(code == FireCodes.ALARMNOTCOPED)
            return "One of fire alarms did not succeed in putting out the fire\n";
        if(code == FireCodes.CATASTROPHE)
            return """
                    Unfortunately, fire alarm did not cope with the accident. We are sorry to inform, that simulation
                        has been stopped, because there is nothing to simulate""";
        return "Error: unknown fire code\n";
    }
}

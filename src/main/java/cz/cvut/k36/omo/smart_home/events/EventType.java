package cz.cvut.k36.omo.smart_home.events;

/**
 * Event Type.
 */
public enum EventType {
    BLACKOUT,           //Device
    DOINGSPORT,         // Report
    FIRE,               // People
    FIREFIGHTING,
    NEEDFOOD,           // People
    NIGHT,              // People
    DAY,                // People
    RANOUTOFFOOD,       // Mother & Father
    DEVICEBROKE,        // Father & Son & Report
    DEVICEINTERACTED,   // Report
    DEVICEREPAIRED,     // Report
    DEVICESWITCHON,     // Report
    DEVICESWITCHOFF,    // Report
    DEVICEUSING,        // Report
    CLEANING,
    CUDDLING,
    EATING,
    SHOPPING,
    RELAXING,
    SHOWERING;
}


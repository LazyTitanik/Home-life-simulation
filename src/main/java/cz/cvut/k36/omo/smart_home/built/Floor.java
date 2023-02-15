package cz.cvut.k36.omo.smart_home.built;

import cz.cvut.k36.omo.smart_home.built.Rooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Floor class.
 * {@link #number} number of this floor
 */
public class Floor {

    private int number;
    private Map<String, Room> roomsList;

    public Floor(int number, Map<String, Room> roomsList){
        this.number = number;
        this.roomsList = roomsList;
        roomsList = new HashMap<String, Room>() {
        };
    }

    public Map<String, Room> getRoomsList() {
        return roomsList;
    }

    public int getNumber() {
        return number;
    }
}

package cz.cvut.k36.omo.smart_home.builder;

import cz.cvut.k36.omo.smart_home.built.Floor;
import cz.cvut.k36.omo.smart_home.built.Rooms.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builder for the floor.
 */
public class FloorBuilder {
    private final Map<String, Room> roomList = new HashMap<>();
    private int num;

    public int getNum() {
        return num;
    }

    /**
     * Added number of the floor.
     * @param num number of the floor
     */
    public void addNum(int num){
        this.num = num;
    }

    /**
     * Added room in the floor.
     * @param name room's name
     * @param room
     */
    public void addRoom(String name, Room room){
        roomList.put(name, room);
        room.setFloorNum(num);
    }

    /**
     * Create new floor.
     * @return new floor
     */
    public Floor build(){
        return new Floor(num, roomList);
    }
}

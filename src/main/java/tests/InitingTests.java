package tests;

import cz.cvut.k36.omo.smart_home.built.Floor;
import cz.cvut.k36.omo.smart_home.built.House;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class InitingTests {

    @Test
    public void simpleFloorsFirst(){
        House house = House.getInstance("configurations/test_01.json");
        List<Floor> floorList = house.getFloorsList();
        assertEquals(1, floorList.get(0).getNumber(), "First floor doesnt have number=1");
    }

    @Test
    public void simpleFloorsSecond(){
        House house = House.getInstance("configurations/test_01.json");
        List<Floor> floorList = house.getFloorsList();
        assertEquals(2, floorList.get(1).getNumber(), "Second floor doesnt have number=8");
    }

    @Test
    public void floorsNumber(){
        House house = House.getInstance("configurations/test_01.json");
        List<Floor> floorList = house.getFloorsList();
        assertEquals(2, floorList.size(), "Number of floors does not correspond");
    }


}

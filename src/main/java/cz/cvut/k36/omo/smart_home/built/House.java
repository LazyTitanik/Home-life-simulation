package cz.cvut.k36.omo.smart_home.built;


import cz.cvut.k36.omo.smart_home.builder.houseBuilders.JSONParsing;
import cz.cvut.k36.omo.smart_home.builder.houseBuilders.XMLtoJSON;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.fire.FireEvent;
import cz.cvut.k36.omo.smart_home.living.Living;

import java.util.ArrayList;
import java.util.List;

/**
 * House class.
 * {@link #days} how many day will be simulation
 * {@link #devicesList} list of devices in this house
 * {@link #floorsList} list of floors in this house
 * {@link #sportDevices} list of sport devices in this house
 * {@link #listOfPersonStrategy} list of strategy for the person
 * {@link #listOfPetStrategy} list of strategy for the pets
 * {@link #isInFire} if house is in fire
 */
public class House {

    private int days;
    private static House instance = null;

    private List<Living> livingList;
    private List<Device> devicesList;
    private List<Device> sportDevices;

    private static List<String> listOfPersonStrategy;
    private static List<String> listOfPetStrategy;

    private List<Floor> floorsList;
    private boolean isInFire = false;
    private static boolean isCreated = false;
    private House (){}

    /**
     * Create list of possible strategies.
     */
    public static void setListsOfStrategies(){
        listOfPetStrategy = new ArrayList<>();
        listOfPersonStrategy = new ArrayList<>();
        listOfPersonStrategy.add("Hungry");
        listOfPersonStrategy.add("Lazy");
        listOfPersonStrategy.add("Sport");
        listOfPetStrategy.add("Crazy");
        listOfPetStrategy.add("Laying");
        listOfPersonStrategy.add("Cleaning");
    }

    /**
     * Gets instance of existing house or create new if instance does not exist.
     * @param fileName name of the configuration file
     * @return house instance
     */
    public static House getInstance(String fileName) {
        if (instance == null) {
            if(fileName.contains(".xml")){
                XMLtoJSON.convert(fileName);
                fileName = fileName.replace(".xml", ".json");
            }
            House house = new House();
            house.floorsList = new ArrayList<>();
            house.livingList = new ArrayList<>();
            JSONParsing parser = new JSONParsing();
            instance = house;
            if(parser.parse(house, fileName)){
                isCreated = true;
                setListsOfStrategies();
            }

        }
        return instance;
    }

    public static House getInstance() {
        return instance;
    }


    public List<Floor> getFloorsList() {
        return floorsList;
    }


    public List<Living> getLivingList() {
        return livingList;
    }

    public void addFloor(Floor floor){
        floorsList.add(floor);
    }

    public void addLiving(Living living){ livingList.add(living); }

    public List<String> getListOfPersonStrategy() {
        return listOfPersonStrategy;
    }

    public List<String> getListOfPetStrategy() {
        return listOfPetStrategy;
    }

    public List<Device> getDevicesList() {
        return devicesList;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setDevicesList(List<Device> devicesList) {
        this.devicesList = devicesList;
    }

    public List<Device> getSportDevices() {
        return sportDevices;
    }

    public void setSportDevices(List<Device> sportDevices) {
        this.sportDevices = sportDevices;
    }

    public void beginFire(){
        new FireEvent();
        this.isInFire = true;
    }

    public void stopFire(){
        this.isInFire = false;
    }

    public boolean getIsInFire(){
        return isInFire;
    }

    public boolean isCreated() {
        return isCreated;
    }

}
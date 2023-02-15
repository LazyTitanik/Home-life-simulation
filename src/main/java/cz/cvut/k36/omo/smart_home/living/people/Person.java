package cz.cvut.k36.omo.smart_home.living.people;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.builder.StrategyBuilder;
import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.built.Rooms.NoRoom;
import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.eventObserver.EventManager;
import cz.cvut.k36.omo.smart_home.events.livingActivity.*;
import cz.cvut.k36.omo.smart_home.events.Event;
import cz.cvut.k36.omo.smart_home.events.NeedFood;
import cz.cvut.k36.omo.smart_home.living.Living;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.k36.omo.smart_home.events.EventType.*;

/**
 * Person class.
 */
public abstract class Person extends Living{
    private static Logger logger = Logger.getLogger(Person.class.getName());
    protected Person(){
        super();
        EventManager.getInstance().subscribe(NEEDFOOD, this);
        EventManager.getInstance().subscribe(NIGHT, this);
    }

    /**
     * The person is relaxing by using the device, which he got as parameter.
     * @param deviceName
     * @return true if person interacted with device, false otherwise
     */
    public boolean relax(String deviceName){
        Device device = findDevice(deviceName);
        if(device == null){
            logger.log(Level.WARNING, "{0}", String.format("Person %s did not find the device %s (relax)", name, deviceName));
            return false;
        }
        goToAnotherRoom(device.getRoom());
        if(!device.switchOn(this)){
            logger.log(Level.WARNING, "{0}", String.format("%s (%s) couldn't turn on %s",name, role,deviceName));
            return false;
        }
        new Relaxing(this);
        deviceUsed = device;
        deviceUsed.setIsFree(false);
        logger.log(Level.FINE, "{0}", String.format("%s (%s) started using %s",name, role,deviceName));
        return device.interact(this);
    }

    /**
     * Person go to eat.
     * @return true if he could find food in the fridge or cook food, false otherwise.
     */
    public boolean goToEat(){
        Device device = findDevice("Fridge");
        if(device == null){
            device = findDevice("Cooker");
            if (device == null){
                logger.log(Level.WARNING, "No fridge or cooker found, {0} cannot eat", name);
                return false;
            }
        }
        new Eating(this);
        goToAnotherRoom(device.getRoom());
        if(!device.switchOn(this)){
            logger.log(Level.WARNING, "{0}",String.format("%s couldn't turn on %s (eating)", name, device.getName()));
        }
        deviceUsed = device;
        deviceUsed.setIsFree(false);
        device.interact(this);
        return true;
    }

    /**
     * The person doing sport.
     */
    public void sport(){
        List<Device> list = House.getInstance().getSportDevices().stream().filter(Device::isFree).toList();
        if(list.isEmpty()){
            logger.log(Level.WARNING, "No sport devices found!");
            return;
        }
        new DoingSport(this);
        Device device = list.get(BetterRandom.nextInt(list.size()));
        goToAnotherRoom(device.getRoom());
        if(device.interact(this)) {
            deviceUsed = device;
            deviceUsed.setIsFree(false);
            this.room = NoRoom.getInstance();
            goToAnotherRoom(this.room);
        }
    }

    /**
     * The person go or drive shopping.
     */
    public void goShopping(){
        Device car = findDevice("Car");
        if(car == null){
            goToAnotherRoom(NoRoom.getInstance());
        } else{
            goToAnotherRoom(car.getRoom());
            if(!car.switchOn(this)){
                logger.log(Level.WARNING, "{0}", String.format("%s couldn't switch car on",name));
                goToAnotherRoom(NoRoom.getInstance());
            }else{
                deviceUsed = car;
                car.setIsFree(false);
                car.interact(this);
            }
        }
        new Shopping(this, car == null);
    }

    /**
     * The person clean the room that he got as a parameter.
     * @param roomTmp
     */
    public void cleanRoom(Room roomTmp){
        Device rag = findDevice("Rag");
        if(rag == null){
            logger.log(Level.WARNING, "Rag was not found (cleanRoom)");
            return;
        }
        new Cleaning(this, room);
        deviceUsed = rag;
        rag.setIsFree(false);
        goToAnotherRoom(rag.getRoom());
        goToAnotherRoom(roomTmp);
        interactWithDevice(rag);
    }

    /**
     * The person go the Bathroom and take a shower.
     */
    public void takeShower(){
        Device bath = findDevice("Bath");
        if(bath == null){
            logger.log(Level.WARNING, "No Bath found (takeShower)");
            return;
        }
        new Showering(this);
        deviceUsed = bath;
        bath.setIsFree(false);
        goToAnotherRoom(bath.getRoom());
        interactWithDevice(bath);
    }

    @Override
    public void interactWithDevice(Device device){
        if(device == null){
            logger.log(Level.WARNING, "{0}", String.format("%s got null as device (interactWithDevice)", name));
            return;
        }
        goToAnotherRoom(device.getRoom());
        if(!device.switchOn(this)){
            logger.log(Level.WARNING, "{0}", String.format("%s couldnt turn on %s (interactWithDevice)", name,device.getName()));
            return;
        }
        deviceUsed = device;
        device.interact(this);
    }

    /**
     * The person stop relax.
     */
    public void stopRelax(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped relaxing", name));
        freeDevice();
    }
    /**
     * The person stop eating.
     */
    public void stopEating(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped eating", name));
        if(deviceUsed == null){
            return;
        }
        if(deviceUsed.getName().equals("Cooker")){
            deviceUsed.switchOff(this);
        }
        freeDevice();
    }
    /**
     * The person stop doing sport.
     */
    public void stopSport(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped sporting", name));
        if(deviceUsed == null){
            return; // ???
        }
        goToAnotherRoom(deviceUsed.getRoom());
        freeDevice();
    }
    /**
     * The person stop shopping.
     */
    public void stopShopping(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped shopping", name));
        goToAnotherRoom(randomRoom());
        if(deviceUsed == null){
            return;
        }
        freeDevice();
    }
    /**
     * The person stop cleaning.
     */
    public void stopCleaning(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped cleaning", name));
        freeDevice();
    }
    /**
     * The person stop taking a shower.
     */
    public void stopShower(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped showering", name));
        freeDevice();
    }

    @Override
    public void stopInteract(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped interacting", name));
        if(getDeviceUsed() == null){
            return;
        }
        deviceUsed.switchOff(this);
        freeDevice();
    }

    // Reactions to events
    @Override
    public void newDay(){
        List<String> list = House.getInstance().getListOfPersonStrategy();
        String strategyName= list.get(BetterRandom.nextInt(list.size()));
        this.strategy = StrategyBuilder.getPersonStrategy(strategyName);
        logger.log(Level.FINE, "{0}", String.format("%s chose strategy %s",name, strategyName));
    }

    @Override
    public void update(Event event){
        super.update(event);
        if(event.getType() == NEEDFOOD){
            NeedFood tmp = (NeedFood) event;
            goToAnotherRoom(tmp.getPet().getRoom());
        }
        if(event.getType() == NIGHT){
            Device device = findDevice("Light");
            if(device != null){
                device.switchOn(this);
            }
        }
        if(event.getType() == DAY){
            Device device = findDevice("Light");
            if(device != null){
                device.switchOff(this);
            }
        }
    }
}

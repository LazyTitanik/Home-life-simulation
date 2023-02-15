package cz.cvut.k36.omo.smart_home.living.pets;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.builder.StrategyBuilder;
import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.built.Rooms.Room;
import cz.cvut.k36.omo.smart_home.devices.Device;
import cz.cvut.k36.omo.smart_home.events.livingActivity.Cuddling;
import cz.cvut.k36.omo.smart_home.living.Living;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.states.BrokenState;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Pet extends Living{
    private static Logger logger = Logger.getLogger(Pet.class.getName());

    protected Pet(){
        super();
    }

    // Activities
    @Override
    public void interactWithDevice(Device device){
        if(device == null){
            Room roomTmp = room;
            List<Device> deviceList =
                    roomTmp.getDevices().stream().filter(Device::isFree).filter(n->
                    !(n.getStateOfDevice() instanceof BrokenState)).toList();
            if(!deviceList.isEmpty()){
                device = deviceList.get(BetterRandom.nextInt(deviceList.size()));
            } else{
                logger.log(Level.WARNING, "{0}", String.format("Warning: pet %s did not find device to interact with", name));
                return;
            }
        }
        deviceUsed = device;
        device.interact(this);
    }

    /**
     * Cuuddle with certain person.
     * @param person
     */
    public void cuddle(Person person){
        goToAnotherRoom(person.getRoom());
        new Cuddling(this, person);
    }

    @Override
    public void stopInteract(){
        logger.log(Level.FINE, "{0}", String.format("%s stopped interacting", name));
        freeDevice();
    }

    // Reactions to events
    @Override
    public void newDay(){
        List<String> list = House.getInstance().getListOfPetStrategy();
        String strategyName = list.get(BetterRandom.nextInt(list.size()));
        this.strategy = StrategyBuilder.getPetStrategy(strategyName);
        logger.log(Level.FINE, "{0}", String.format("%s chose strategy %s",name, strategyName));
    }
}

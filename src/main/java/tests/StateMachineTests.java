package tests;

import cz.cvut.k36.omo.smart_home.BetterRandom;
import cz.cvut.k36.omo.smart_home.devices.*;
import cz.cvut.k36.omo.smart_home.living.people.*;
import cz.cvut.k36.omo.smart_home.living.pets.*;
import cz.cvut.k36.omo.smart_home.reports.EventReport;
import cz.cvut.k36.omo.smart_home.states.BrokenState;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateMachineTests {
    // Initing state is Active

    @Test
    public void activeInteractingPersonEvent(){
        Person daughter = new Daughter();
        daughter.setName("CJ");
        Device fridge = new Fridge();
        fridge.interact(daughter);
        assertEquals("\t\tCJ is using Fridge\n",
                EventReport.getInstance().getLastEvent().toString(),
            "Event DeviceUsing was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void activeInteractingPetEvent(){
        BetterRandom.init(2);
        Pet dog = new Dog();
        dog.setName("Tobi");
        Device fridge = new Fridge();
        fridge.interact(dog);
        assertEquals("\t\tTobi interacted with Fridge.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "Event DeviceInteracted was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void activeInteractingPetBreakEvent(){
        BetterRandom.init(49);
        Pet dog = new Dog();
        dog.setName("Tobi");
        Device fridge = new Fridge();
        fridge.interact(dog);
        assertEquals("\t\tThe device Fridge is broken by Tobi.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "Event DeviceBroke was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void activeSwitchEvent(){
        Person daughter = new Daughter();
        daughter.setName("CJ");
        Device fridge = new Fridge();
        fridge.switchOff(daughter);
        assertEquals("\t\tCJ switched Fridge off.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "Event DeviceSwitchedOff was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void activeBreakEvent(){
        Person daughter = new Daughter();
        daughter.setName("CJ");
        Device fridge = new Fridge();
        fridge.breakDevice(daughter);
        assertEquals("\t\tThe device Fridge is broken by CJ.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "Event DeviceBroke was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void activeRepairFather(){
        Person dad = new Father();
        Device fridge = new Fridge();
        assertTrue(fridge.repair(dad), "Father cannot repair device!");
    }

    @Test
    public void activeRepairMomDaughter(){
        Person mom = new Mother();
        Person daughter = new Daughter();
        Device fridge = new Fridge();
        assertFalse(fridge.repair(mom) || fridge.repair(daughter), "Mom or Daughter can repair device!");
    }

    // Initing state is inactive

    @Test
    public void inactiveInteractPerson(){
        Person daughter = new Daughter();
        Device cooker = new Cooker();
        assertFalse(cooker.interact(daughter), "Can interact with inactive device");
    }

    @Test
    public void inactiveInteractPet(){
        BetterRandom.init(2);
        Pet dog = new Dog();
        dog.setName("Tobi");
        Device cooker = new Cooker();
        cooker.interact(dog);
        assertEquals("\t\tTobi interacted with Cooker.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "Device interacted was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void inactiveInteractPetBreak(){
        BetterRandom.init(49);
        Pet dog = new Dog();
        dog.setName("Tobi");
        Device cooker = new Cooker();
        cooker.interact(dog);
        assertEquals("\t\tThe device Cooker is broken by Tobi.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "Device interacted was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void inactiveSwitch(){
        Person daughter = new Daughter();
        Device cooker = new Cooker();
        assertTrue(cooker.switchOn(daughter), "Daughter cannot switch device on");
    }

    @Test
    public void inactiveSwitchEvent(){
        Person daughter = new Daughter();
        daughter.setName("CJ");
        Device cooker = new Cooker();
        cooker.switchOn(daughter);
        assertEquals("\t\tCJ turned Cooker on.\n",
                EventReport.getInstance().getLastEvent().toString(),
                "DeviceSwitchedOn was generated incorrectly: " + EventReport.getInstance().getLastEvent().toString());
    }

    @Test
    public void inactiveRepairFather(){
        Person dad = new Father();
        Device cooker = new Cooker();
        assertTrue(cooker.repair(dad), "Father cannot repair device!");
    }

    @Test
    public void inactiveRepairMomDaughter(){
        Person mom = new Mother();
        Person daughter = new Daughter();
        Device cooker = new Cooker();
        assertFalse(cooker.repair(mom) || cooker.repair(daughter), "Mom or Daughter can repair device!");
    }

    // Broken

    @Test
    public void brokenInteract(){
        Person dad = new Father();
        Device cooker = new Cooker();
        cooker.setStateOfDevice(new BrokenState(cooker));
        assertFalse(cooker.interact(dad), "Able to interact with broken device");
    }

    @Test
    public void brokenSwitch(){
        Person dad = new Father();
        Device cooker = new Cooker();
        cooker.setStateOfDevice(new BrokenState(cooker));
        assertFalse(cooker.switchOn(dad), "Able to switch broken device");
    }

    @Test
    public void brokenBreak(){
        Person dad = new Father();
        Device cooker = new Cooker();
        cooker.setStateOfDevice(new BrokenState(cooker));
        assertFalse(cooker.breakDevice(dad), "Able to break broken device =)");
    }

    @Test
    public void brokenRepairDad(){
        Person dad = new Father();
        Device cooker = new Cooker();
        cooker.setStateOfDevice(new BrokenState(cooker));
        assertTrue(cooker.repair(dad), "Father cannot repair broken device");
    }

    @Test
    public void brokenRepairMomDaughter(){
        Person mom = new Mother();
        Person daughter = new Daughter();
        Device cooker = new Cooker();
        cooker.setStateOfDevice(new BrokenState(cooker));
        assertFalse(cooker.switchOn(mom) || cooker.repair(daughter), "Mom or daughter can repair device");
    }
}

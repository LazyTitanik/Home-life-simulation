package cz.cvut.k36.omo.smart_home.devices.consumption;

/**
 * Information about resources that device use.
 */
public class Consumption {
    private final int electricity;
    private final int gas;
    private final int water;

    public Consumption(int electricity, int gas, int water){
        this.electricity = electricity;
        this.gas = gas;
        this. water = water;
    }

    public int getElectricity() {
        return electricity;
    }

    public int getGas() {
        return gas;
    }

    public int getWater() {
        return water;
    }
}

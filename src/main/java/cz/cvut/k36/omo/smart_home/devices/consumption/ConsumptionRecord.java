package cz.cvut.k36.omo.smart_home.devices.consumption;

/**
 * Store information how many resources used device.
 */
public class ConsumptionRecord {
    private int electricity;
    private int gas;
    private int water;

    public ConsumptionRecord(){
        electricity = 0;
        gas = 0;
        water = 0;
    }

    /**
     * Increases the amount of resources used.
     * @param data
     */
    public void increment(Consumption data){
        electricity += data.getElectricity();
        gas += data.getGas();
        water += data.getWater();
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

    @Override
    public String toString() {
        return  "electricity=" + electricity +
                ", gas=" + gas +
                ", water=" + water;
    }
}

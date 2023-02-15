package cz.cvut.k36.omo.smart_home;

import java.util.Random;

/**
 * It was used when testing the program.
 */
// Singleton
public class BetterRandom {
    private static Random rand = null;

    public static void init(long seed){
        rand = new Random(seed);
    }

    public static int nextInt(){
        if(rand == null){
            rand = new Random();
        }
        int value = rand.nextInt();
        return value < 0 ? -value : value;
    }

    public static int nextInt(int bound){
        if(rand == null){
            rand = new Random();
        }
        int value = rand.nextInt() % bound;
        return value < 0 ? -value : value;
    }

    private BetterRandom(){}
}

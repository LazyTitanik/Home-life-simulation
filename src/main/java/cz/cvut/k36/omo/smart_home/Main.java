package cz.cvut.k36.omo.smart_home;

import cz.cvut.k36.omo.smart_home.built.House;
import cz.cvut.k36.omo.smart_home.living.people.Person;
import cz.cvut.k36.omo.smart_home.reports.ActivityNUsageReport;
import cz.cvut.k36.omo.smart_home.reports.ConsumptionReport;
import cz.cvut.k36.omo.smart_home.reports.EventReport;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Here is the start point of the program
     * @param args array of 4 parameters
     *             1. name of configuration file,
     *             2. file for the Event report,
     *             3. file for the Consumption report
     *             4. file for the Activity and Usage report
     */
    public static void main(String[] args) {
        if(args.length < 4){
            logger.log(Level.INFO, "Not enough count of arguments were received. Exiting the application!");
            return;
        }
        BetterRandom.init(LocalTime.now().toNanoOfDay());
        House.getInstance(args[0]);
        SmartHome smartHome = SmartHome.getInstance();
        if(House.getInstance().isCreated()){
            smartHome.run();
            EventReport.getInstance().printReport(args[1]);
            ConsumptionReport.getInstance().printReport(args[2]);
            ActivityNUsageReport.getInstance().printReport(args[3]);
        }
    }
}

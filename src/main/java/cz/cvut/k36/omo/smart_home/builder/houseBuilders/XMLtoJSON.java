package cz.cvut.k36.omo.smart_home.builder.houseBuilders;
import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adapter.
 */
public class XMLtoJSON {
    private static Logger logger = Logger.getLogger(XMLtoJSON.class.getName());

    /**
     * Convert configuration file with .xml format to .json format.
     * @param filename name of the configuration file
     */
    public static void convert(String filename) {
        String jsonString;
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNextLine()){
                builder.append(scanner.nextLine());
            }
            String xmlContent = builder.toString();

            JSONObject json = XML.toJSONObject(xmlContent);
            jsonString = json.toString();
        }catch (JSONException | IOException e) {
            logger.log(Level.SEVERE, "{0}", e.getMessage());
            return;
        }
        String jsonFilename = filename.replace(".xml", ".json");

        try (FileWriter myWriter = new FileWriter(jsonFilename)){
            logger.log(Level.FINE, "xml file has been converted to json");
            myWriter.write(jsonString);
        }catch(IOException e){
            logger.log(Level.SEVERE, "{0}", e.getMessage());
        }
    }


}
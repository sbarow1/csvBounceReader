import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

//
// This class read the csv File
//
public class CsvFileReader {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Lead attributes index
    private static final int LEAD_FNAME_IDX = 1;
    private static final int LEAD_LNAME_IDX = 2;
    private static final int LEAD_COMPANY_IDX = 5;
    private static final int LEAD_EMAIL_IDX = 10;
    private static final int LEAD_BOUNCE_IDX = 16;

    public static void readCsvFile(String filename){
        
        BufferedReader fileReader = null;

        try {
        
            //Create a new list of leads to be filled by CSV file data
            Hashtable bounceLeads = new Hashtable();
            Enumeration names;
            String str;
            String bounceString;

            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(filename));

            //Reader the CSV file header to skip it
            fileReader.readLine();

            System.out.println("\nGot to this point:");
            // Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //System.out.println("\nGot a new line:");
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0){
                    //System.out.println(tokens[LEAD_BOUNCE_IDX]);
                    //If email bounced create a new lead object
                    bounceString = (String)tokens[LEAD_BOUNCE_IDX];
                    if (bounceString.equals("true")){
                        //System.out.print("Got a true!");
                        System.out.println("\n:");
                        bounceLeads.put(tokens[LEAD_LNAME_IDX], tokens[LEAD_EMAIL_IDX]);
                    }                
                }
            }

            // Show all balances in hash table.
            names = bounceLeads.keys();

            while(names.hasMoreElements()){
                str = (String) names.nextElement();
                System.out.println(str + ": " + bounceLeads.get(str));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        String fileName = "/users/seanbarow/downloads/2442560.csv";

        System.out.println("\nRead CSV file:");
        readCsvFile(fileName);
    }
}
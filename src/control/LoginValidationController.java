package control;

import java.io.*;
// import fileiohandler

import entity.Admin;

/**
 * Represents the class that handles admin login validation.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class LoginValidationController {
    // path to credentials csv file
    static String credPath = "./database/credentials.csv";

    /**
     * Validates the login credentials from a CSV file, returns a boolean of
     * verified or not
     * 
     * @param admin
     * @return Boolean
     */
    public static Boolean validateLogin(Admin admin) {
        // get the username and password from the admin object
        String username = admin.getUsername();
        String password = admin.getPassword();
        Boolean result = false;
        String inputLine;

        try {
            // use the fileiohandler to open the csv file
            BufferedReader brStream = FileIOHandler.openFile(credPath);
            // read the first line of the file

            while ((inputLine = brStream.readLine()) != null) {
                // split the line into an array of strings
                String values[] = inputLine.split(",");
                // if the username and password matches the credentials in the csv file
                if (values[0].equals(username) && values[1].equals(password)) {
                    // set the result to true
                    result = true;
                    return result;
                } else {
                    // set the result to false
                    result = false;
                }
            }

            // close the file reader
            brStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
            System.exit(0);
        } catch (IOException e) {

            e.printStackTrace();
        }

        return result;
    }
}
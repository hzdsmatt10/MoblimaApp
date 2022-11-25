package control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the class of FileIOHandler, which handles any
 * opening/closing/modifying of files
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */

public class FileIOHandler {

    /**
     * This method is used to open a specified file
     * 
     * @param path
     * @return BufferedReader
     */
    public static BufferedReader openFile(String path) {
        FileReader frStream = null;
        BufferedReader brStream = null;

        try {
            frStream = new FileReader(path);
            brStream = new BufferedReader(frStream);
            // brStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("openFile Error: " + e.getMessage());
            System.exit(0);
        }

        return brStream;
    }

    /**
     * Reads a specficied file, finds the line with the specified query/keyword, and
     * returns the attributes
     * 
     * @param dir
     * @param query
     * @return String[]
     */
    public static String[] readAttribute(String dir, String query) {
        String readLine;
        BufferedReader br = openFile(dir);

        try {
            while ((readLine = br.readLine()) != null) {
                String[] row = readLine.split(","); // split the line into an array strings
                if (row[0].equals(query)) {
                    br.close();
                    return row;
                    // returns a String[] array, use row[1] to get the value
                }
            }
        } catch (IOException e) {
            System.out.println("readRow Error: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Failed to find attribute: " + query);
        return null; // fails
    }

    /**
     * Reads a specified file, finds the line with the specified query/keyword with
     * a custom delimiter and returns the attributes
     * 
     * @param dir
     * @param query
     * @param delimiter
     * @return String[]
     */
    // overloading with new delimiter
    public static String[] readAttribute(String dir, String query, String delimiter) {
        String readLine;
        BufferedReader br = openFile(dir);

        try {
            while ((readLine = br.readLine()) != null) {
                String[] row = readLine.split(delimiter); // split the line into an array strings
                if (row[0].equals(query)) {
                    br.close();
                    return row;
                    // returns a String[] array, use row[1] to get the value
                }
            }
        } catch (IOException e) {
            System.out.println("readRow Error: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Failed to find attribute: " + query);
        return null; // fails
    }

    /**
     * Reads a specified file and dumps entire file content into a 2D array of
     * String
     * 
     * @param dir
     * @return String[][]
     */
    public static String[][] readTo2DArray(String dir) {
        String readLine;
        List<String[]> list = new ArrayList<String[]>();
        BufferedReader br = openFile(dir);
        try {
            while ((readLine = br.readLine()) != null) {
                String[] row = readLine.split(","); // split the line into an array strings
                list.add(row);
            }

            br.close();
            String[][] array = new String[list.size()][0];
            list.toArray(array);
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // error
        return null;
    }

    /**
     * Writes a specified 2D array of String into a specified file, returns true is
     * successful
     * 
     * @param dir
     * @param data
     * @return boolean
     */
    public static boolean writeToCsv2D(String dir, String[][] data) {
        StringBuilder stringMBuilder = new StringBuilder();
        for (String[] outerString : data) {
            for (String innerString : outerString) {
                stringMBuilder.append(innerString + ",");
            }
            stringMBuilder.append("\r\n");
        }

        File appendTo = new File(dir);
        try {
            FileWriter fileManager = new FileWriter(appendTo, false);
            fileManager.write(stringMBuilder.toString()); // write to file
            stringMBuilder.delete(0, stringMBuilder.length()); // clear the stringbuilder
            fileManager.close(); // close file
            return true;
        } catch (IOException e) {
            System.out.println("writeToCsv2D Error: " + e.getMessage());
        }

        return false;
    }

    /**
     * Appends a line of String into a specified file
     * 
     * @param dir
     * @param data
     * @return boolean
     */
    public static boolean appendToFile(String dir, String data) {
        File appendTo = new File(dir);
        try {
            FileWriter fileManager = new FileWriter(appendTo, true);
            fileManager.write(data); // write to file
            fileManager.close(); // close file
            return true;
        } catch (IOException e) {
            System.out.println("appendToFile Error: " + e.getMessage());
        }

        return false;

    }
}

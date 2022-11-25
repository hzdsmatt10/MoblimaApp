package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Represents the controller class that handles the modification of showtimes data in the cineplex folder.
 * 
 * @author Matthias
 * @version 1.0
 * @since 1.0
 */
public class ShowtimesController {
    protected static String showtimeDir = "./database/Shaw";
    protected static Scanner sc = new Scanner(System.in);
   /**
     * Adds the screening details and the blank seating layout to the database.
     * 
     * @param newShowtimes
     * @return boolean
     */
    public static boolean addShowtime(Showtimes newShowtimes) {
        File CreateFolder = new File(showtimeDir + "/" + newShowtimes.getLocation() + "/" + newShowtimes.getSuite()
                + "/" + newShowtimes.getHall() + "/" + newShowtimes.getDay() + "/" + newShowtimes.getTiming()); // creation
                                                                                                                // of
                                                                                                                // the
                                                                                                                // folder
                                                                                                               // System.out.println(CreateFolder);
        CreateFolder.mkdirs();

        //////////////////////////////////// creation of csv
        //////////////////////////////////// files////////////////////////////////////////
        File screening = new File(showtimeDir + "/" + newShowtimes.getLocation() + "/" + newShowtimes.getSuite()
                + "/" + newShowtimes.getHall() + "/" + newShowtimes.getDay() + "/" + newShowtimes.getTiming()
                + "/ScreeningDetails.csv");
        File seatings = new File(showtimeDir + "/" + newShowtimes.getLocation() + "/" + newShowtimes.getSuite()
                + "/" + newShowtimes.getHall() + "/" + newShowtimes.getDay() + "/" + newShowtimes.getTiming()
                + "/seating.csv");

        //////////////////////////////////////// appending of values in csv
        //////////////////////////////////////// file///////////////////////////////////////
        try {
            FileWriter fileManager = new FileWriter(screening, false);
            StringBuilder stringManager = new StringBuilder();
            // basic boilerplate for Screeningdetails csv
            screening.createNewFile();
            stringManager.append("movie," + newShowtimes.getMovieTitle() + "," + "\r\n"); // movie title
            // stringManager.append("type,"+ newShowtimes.getType()+"," + "\r\n"); //type
            // stringManager.append("blockbuster,"+ newShowtimes.getBlockBuster()+"," +
            // "\r\n"); /////blockbuster
            stringManager.append("date," + newShowtimes.getDate() + "," + "\r\n"); //// date
            fileManager.write(stringManager.toString()); // write to file
            stringManager.delete(0, stringManager.length()); // clear the stringbuilder
            fileManager.close(); // close file

            // creating a new file for the movie details
            seatings.createNewFile();
            fileManager = new FileWriter(seatings, false);
            stringManager.append(newShowtimes.getLayout()); // layout
            fileManager.write(stringManager.toString()); // write to file
            stringManager.delete(0, stringManager.length()); // clear the stringbuilder
            fileManager.close(); // close file

            return true; // successful writing

        } catch (FileNotFoundException e) {
            System.out.println("addShowtime Error: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("addShowtime Error: " + e.getMessage());
            return false;
        }
    }

    ///////////////////////////////// search
    ///////////////////////////////// timing////////////////////////////////////
     /**
     * Method to check if the timing of a particular screening exists in the database, given the timing and file directory.
     * Returns true if the timing exists, false if not.
     *The parameter dir is used to show the specific hall of a
     * specific suite of a 
     * speciic cineplex
     * @param timing
     * @param dir
     * @return Boolean
     */
    public static Boolean searchTiming(String timing2, String dir) {

        try {
            File[] timingFolder = new File(dir).listFiles();

            for (File timing : timingFolder) {

                String workingtimingName = timing.getName();
                if (workingtimingName.equals(".DS_Store")) {
                    continue;
                }

                if (workingtimingName.equals(timing2)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
        return false;

    }

    /////////////////////////////////////////////////////// list
    /////////////////////////////////////////////////////// showtime///////////////////////////////////////
       /**
     * Method to list all the timings in a specific hall in the database. 
     * The parameter dir is used to show the specific hall of a
     * specific suite of a 
     * speciic cineplex
     * 
     * @param dir
     */
    public static void listShowtimes(String dir) {
        System.out.println(dir);
        int showlistcounter = 1;
        try {
            File[] showFolder = new File(dir).listFiles();

            for (File show : showFolder) {
                if (show.getName().equals(".DS_Store")) {
                    continue;
                }

                System.out.println(showlistcounter + ". " + show.getName());
                showlistcounter++;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

    }
      /**
     * Method to list all the halls in a specific suite in the database. 
     * The parameter dir is used to show the
     * specific suite of a 
     * speciic cineplex
     * 
     * @param dir
     */
    public static void listHalls(String dir) {
        int halllistcounter = 1;
        try {
            File[] hallFolder = new File(dir).listFiles();

            for (File hall : hallFolder) {
                if (hall.getName().equals(".DS_Store")) {
                    continue;
                }

                System.out.println(halllistcounter + ". " + hall.getName());
                halllistcounter++;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

    }

    //////////////////////////////// updatetiming///////////////////////////////////////////
          /**
     * Method to update the timings in the ScreeningDetails.csv in a 
     * particular timing folder
     * The parameter dir is used to show the specific hall of a
     * specific suite of a 
     * speciic cineplex
     * couple dir with timings and u get the specific csv in that particular timing folder
     * @param dir
     * @param timing
     */
    public static void updateTiming(String timing, String dir) {
        System.out.println("""
                \nPlease Enter the following detail you'd want to edit:
                1. Movie Name
                2. Movie Date
                """);
        System.out.print("Choice: ");
        int choice = sc.nextInt();

        String dirnew = dir + "/" + timing + "/ScreeningDetails.csv";

        String[][] details = FileIOHandler.readTo2DArray(dirnew);
        do {
            if (choice == 1) {

                System.out.print("Enter new movie name: ");
                sc.nextLine();
                String newMovieName = sc.nextLine();

                details[0][1] = newMovieName;
                if (FileIOHandler.writeToCsv2D(dirnew, details)) {
                    System.out.println("Movie name updated successfully!");
                } else {
                    System.out.println("Movie name update failed!");
                }
            } else if (choice == 2) {

                System.out.print("Enter new movie date: ");
                String newMoviedate = sc.next();
                details = FileIOHandler.readTo2DArray(dirnew);
                details[1][1] = newMoviedate;
                if (FileIOHandler.writeToCsv2D(dirnew, details)) {
                    System.out.println("Movie date updated successfully!");
                } else {
                    System.out.println("Movie date update failed!");
                }
            }
        } while (choice != 1 && choice != 2);
    }
          /**
     * Method to delete the particular timing folder in a 
     * particular hall folder
     * The parameter dir is used to show the specific hall of a
     * specific suite of a 
     * speciic cineplex
     * couple dir with timings and u get the specific csv in that particular timing folder
     * @param dir
     * @param timing
     */
    public static boolean deleteTiming(String dir, String timing) {
        File[] movieFolder = new File(dir).listFiles();
        for (File folder : movieFolder) {
            if (timing.equals(folder.getName())) {
                if (folder.isDirectory()) {
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        file.delete();
                    }
                    if (folder.delete()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return false;
    }

}
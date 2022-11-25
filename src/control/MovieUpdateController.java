package control;

import java.util.ArrayList;
import java.io.File;

/**
 * Represents the class which updates the details.csv for movies folder
 * 
 * @author Matthias, Owen
 * @version 1.0
 * @since 1.0
 */
public class MovieUpdateController extends MovieController {
    /**
     * Method to update the movie name in the csv with
     * the new movie name
     * 
     * @param movieName
     * @param newMovieName
     * @return boolean
     */
    public static boolean updateMovieName(String movieName, String newMovieName) {
        File oldMovie = new File(moviesDBDir + "/" + movieName);
        File newMovie = new File(moviesDBDir + "/" + newMovieName);
        if (oldMovie.renameTo(newMovie)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie run time in the csv with
     * the new run time
     * 
     * @param movieName
     * @param newMovieRuntime
     * @return boolean
     */
    public static boolean updateMovieRuntime(String movieName, int newMovieRuntime) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[0][1] = Integer.toString(newMovieRuntime);

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie status in the csv with
     * the new movie status
     * 
     * @param movieName
     * @param newMovieStatus
     * @return boolean
     */
    public static boolean updateMovieStatus(String movieName, String newMovieStatus) {
        if (newMovieStatus == "END_OF_SHOW") {
            String dirLocation = "./database/Shaw";
            File[] showFolderLocation = new File(dirLocation).listFiles();
            deleteMovie(movieName);

            /////////////////////////////////////// loop through
            /////////////////////////////////////// locations/////////////////////////////////////////////
            for (File location : showFolderLocation) {
                {
                    if (location.getName().equals(".DS_Store")) {
                        continue;
                    }
                    ;

                    /////////////////////////////////////// loop through
                    /////////////////////////////////////// suites/////////////////////////////////////////////
                    String dirSuite = "./database/Shaw" + "/" + location.getName();
                    File[] showFolderSuite = new File(dirSuite).listFiles();
                    // System.out.println(dirSuite);
                    for (File suite : showFolderSuite) {
                        if (suite.getName().equals(".DS_Store")) {
                            continue;
                        }

                        /////////////////////////////////////// loop through
                        /////////////////////////////////////// halls/////////////////////////////////////////////
                        String dirHall = "./database/Shaw" + "/" + location.getName() + "/" + suite.getName();
                        File[] showFolderHall = new File(dirHall).listFiles();
                        // System.out.println(dirSuite);
                        for (File hall : showFolderHall) {
                            if (hall.getName().equals(".DS_Store")) {
                                continue;
                            } // System.out.println(hall);

                            /////////////////////////////////////// loop through
                            /////////////////////////////////////// Days/////////////////////////////////////////////
                            String dirDay = "./database/Shaw" + "/" + location.getName() + "/" + suite.getName()
                                    + "/" + hall.getName();
                            File[] showFolderDay = new File(dirDay).listFiles();
                            // System.out.println(dirSuite);
                            for (File day : showFolderDay) {
                                if (day.getName().equals(".DS_Store")) {
                                    continue;
                                } // System.out.println(day);

                                /////////////////////////////////////// loop through
                                /////////////////////////////////////// timings/////////////////////////////////////////////
                                String dirTiming = "./database/Shaw" + "/" + location.getName() + "/"
                                        + suite.getName() + "/" + hall.getName()
                                        + "/" + day.getName();
                                File[] showFolderTiming = new File(dirTiming).listFiles();
                                // System.out.println(dirSuite);
                                for (File timing : showFolderTiming) {
                                    if (timing.getName().equals(".DS_Store")) {
                                        continue;
                                    } // System.out.println(timing);

                                    String dirScreeningDetails = "./database/Shaw" + "/" + location.getName() + "/"
                                            + suite.getName() + "/"
                                            + hall.getName() + "/" + day.getName() + "/" + timing.getName() + "/"
                                            + "ScreeningDetails.csv"; // get the csv details

                                    String[][] details = FileIOHandler.readTo2DArray(dirScreeningDetails);

                                    details[0][1] = details[0][1].toLowerCase();
                                    if (details[0][1].toLowerCase().equals(movieName.toLowerCase())) // if the csv
                                                                                                     // contains the
                                                                                                     // movie
                                    {
                                         System.out.println(dirTiming + " at " + timing.getName() + " has "+details[0][1].toLowerCase());

                                        ShowtimesController.deleteTiming(dirTiming, timing.getName());
                                    }

                                }

                            }

                        }

                    }

                }

            }
            return true;
        } else {
            String dir = moviesDBDir + "/" + movieName + "/details.csv";
            String[][] details = FileIOHandler.readTo2DArray(dir);
            details[1][1] = newMovieStatus;

            if (FileIOHandler.writeToCsv2D(dir, details)) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * Method to update the age rating in the csv with
     * the new age rating
     * 
     * @param movieName
     * @param newAgeRatingString
     * @return boolean
     */
    public static boolean updateMovieAgeRating(String movieName, String newAgeRatingString) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[2][1] = newAgeRatingString;

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie director in the csv with
     * the new movie director
     * 
     * @param movieName
     * @param newMovieDirector
     * @return boolean
     */
    public static boolean updateMovieDirector(String movieName, String newMovieDirector) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[3][1] = newMovieDirector;

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie cast in the csv with
     * the new movie cast
     * 
     * @param movieName
     * @param newMovieCast
     * @return boolean
     */
    public static boolean updateMovieCast(String movieName, ArrayList<String> newMovieCast) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);

        // clear out details array first
        for (int i = 1; i < details[4].length; i++) {
            details[4][i] = "";
        }

        details[4][0] = "movieCast";
        details[4][1] = newMovieCast.toString().replace("[", "").replace("]", ""); // replace
        // brackets

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie synopsis in the csv with
     * the new movie synopsis
     * 
     * @param movieName
     * @param newMovieSynopsis
     * @return boolean
     */
    public static boolean updateMovieSynopsis(String movieName, String newMovieSynopsis) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);

        // hacky workaround, dont have time to figure out proper solution.
        details[5][0] = "movieSynopsis;" + newMovieSynopsis;

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie sales in the csv with
     * the new movie sales
     * 
     * @param movieName
     * @param newMovieSales
     * @return boolean
     */
    public static boolean updateSales(String movieName, int newMovieSales) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[6][1] = Integer.toString(newMovieSales);

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie type in the csv with
     * the new movie type
     * 
     * @param movieName
     * @param newMovieType
     * @return boolean
     */
    public static boolean updateMovieType(String movieName, String newMovieType) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";

        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[7][1] = newMovieType;

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update the movie blockbuster in the csv with
     * the new movie block buster
     * 
     * @param movieName
     * @param newMovieType
     * @return boolean
     */
    public static boolean updateMovieBlockbuster(String movieName, Boolean newMovieBlockbusterBool) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";

        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[8][1] = newMovieBlockbusterBool.toString();

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            return true;
        } else {
            return false;
        }
    }
}

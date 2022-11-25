package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Movie;

/**
 * Represents the controller class that handles the modification of movie data.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class MovieController {
    protected static String moviesDBDir = "./database/Movies";
    protected static Scanner sc = new Scanner(System.in);

    /**
     * Adds a movie to the database.
     * 
     * @param newMovie
     * @return boolean
     */
    public static boolean addMovie(Movie newMovie) {
        File CreateFolder = new File(moviesDBDir + "/" + newMovie.getMovieTitle());
        CreateFolder.mkdirs();

        File reviews = new File(moviesDBDir + "/" + newMovie.getMovieTitle() + "/reviews.csv");
        File details = new File(moviesDBDir + "/" + newMovie.getMovieTitle() + "/details.csv");

        try {
            FileWriter fileManager = new FileWriter(reviews, false);
            StringBuilder stringManager = new StringBuilder();
            // basic boilerplate for reviews csv
            reviews.createNewFile();
            stringManager.append("totalScore,NA," + "\r\n");
            stringManager.append("totalReviews,NA," + "\r\n");
            stringManager.append("Reviewer,Review,Rating,Date," + "\r\n");
            fileManager.write(stringManager.toString()); // write to file
            stringManager.delete(0, stringManager.length()); // clear the stringbuilder
            fileManager.close(); // close file

            // creating a new file for the movie details
            details.createNewFile();
            fileManager = new FileWriter(details, false);
            stringManager.append("movieRuntime," + newMovie.getMovieRunTime() + "," + "\r\n");
            stringManager.append("movieStatus," + newMovie.getMovieStatus() + "," + "\r\n");
            stringManager.append("PGRating," + newMovie.getMovieAgeRating() + "," + "\r\n");
            stringManager.append("movieDirector," + newMovie.getMovieDirector() + "," + "\r\n");
            stringManager.append("movieCast,"
                    + newMovie.getMovieCast().toString().replace("[", "").replace("]", "") + "," + "\r\n");
            stringManager.append("movieSynopsis;" + newMovie.getMovieSynopsis() + ";" + "\r\n");
            stringManager.append("movieSales," + newMovie.getMovieSales() + "," + "\r\n");
            stringManager.append("movieType," + newMovie.getMovieType() + "," + "\r\n");
            stringManager.append("BlockBuster," + newMovie.getMovieBlockbuster() + "," + "\r\n");
            fileManager.write(stringManager.toString()); // write to file
            stringManager.delete(0, stringManager.length()); // clear the stringbuilder
            fileManager.close(); // close file

            return true; // successful writing

        } catch (FileNotFoundException e) {
            System.out.println("addMovie Error: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("addMovie Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a movie from the database, returns a boolean to indicate success.
     * 
     * @param movieName
     * @return boolean
     */
    public static boolean deleteMovie(String movieName) {
        File[] movieFolder = new File(moviesDBDir).listFiles();
        movieName = movieName.toLowerCase();
        for (File folder : movieFolder) {
            if (movieName.equals(folder.getName().toLowerCase())) {
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

    /**
     * Updates the movie details, guides the user through the process of updating
     * the movie details.
     * 
     * @param movieName
     */
    public static void updateMovie(String movieName) {
        System.out.println("Please enter the following detail you'd want to edit:");
        System.out.println("1. Movie Name");
        System.out.println("2. Movie Runtime");
        System.out.println("3. Movie Status");
        System.out.println("4. Movie Age Rating");
        System.out.println("5. Movie Director");
        System.out.println("6. Movie Cast");
        System.out.println("7. Movie Synopsis");
        System.out.println("8. Movie Sales");
        System.out.println("9. Movie Type");
        System.out.println("0. Movie Blockbuster type");
        System.out.print("Choice: ");

        int choice = sc.nextInt();

        String dir = moviesDBDir + "/" + movieName + "/details.csv";

        switch (choice) {

            case 1:
                System.out.print("Enter new movie name: ");
                String newMovieName = sc.next();
                File oldMovie = new File(moviesDBDir + "/" + movieName);
                File newMovie = new File(moviesDBDir + "/" + newMovieName);
                if (oldMovie.renameTo(newMovie)) {
                    System.out.println("Movie name updated successfully!");
                } else {
                    System.out.println("Movie name update failed!");
                }

                break;

            case 2:
                System.out.println("Current movie runtime: " + getMovieRuntime(movieName));
                System.out.print("Enter new movie runtime: ");
                int newMovieRuntime = sc.nextInt();
                String[][] details = FileIOHandler.readTo2DArray(dir);
                details[0][1] = Integer.toString(newMovieRuntime);

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie runtime updated successfully!");
                } else {
                    System.out.println("Movie runtime update failed!");
                }
                break;

            case 3:
                System.out.println("Current movie status: " + getMovieStatus(movieName));

                String newMovieStatus = null;
                do {
                    System.out.println("Enter new movie status: ");
                    System.out.println("1. Coming Soon");
                    System.out.println("2. Showing");
                    System.out.println("3. Preview");
                    System.out.println("4. END_OF_SHOW");
                    System.out.print("Choice: ");
                    int statusChoice = sc.nextInt();

                    if (statusChoice == 1) {
                        newMovieStatus = "Coming Soon";
                    } else if (statusChoice == 2) {
                        newMovieStatus = "Showing";
                    } else if (statusChoice == 3) {
                        newMovieStatus = "Not Showing";
                    } else if (statusChoice == 4) {
                        newMovieStatus = "END_OF_SHOW";
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (newMovieStatus == null);

                details = FileIOHandler.readTo2DArray(dir);
                details[1][1] = newMovieStatus;

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie status updated successfully!");
                } else {
                    System.out.println("Movie status update failed!");
                }
                break;

            case 4:
                System.out.println("Current movie age rating: " + getMovieAgeRating(movieName));

                String newAgeRatingString = null;
                do {
                    System.out.println("Enter new movie age rating: ");
                    System.out.println("1. G");
                    System.out.println("2. PG");
                    System.out.println("3. PG13");
                    System.out.println("4. NC16");
                    System.out.println("5. M18");
                    System.out.println("6. R21");
                    System.out.print("Choice: ");
                    int movieAgeRating = sc.nextInt();

                    switch (movieAgeRating) {
                        case 1:
                            newAgeRatingString = "G";
                            break;
                        case 2:
                            newAgeRatingString = "PG";
                            break;
                        case 3:
                            newAgeRatingString = "PG13";
                            break;
                        case 4:
                            newAgeRatingString = "NC16";
                            break;
                        case 5:
                            newAgeRatingString = "M18";
                            break;
                        case 6:
                            newAgeRatingString = "R21";
                            break;
                        default:
                            System.out.println("Invalid choice, try again.");
                    }

                } while (newAgeRatingString == null);

                details = FileIOHandler.readTo2DArray(dir);
                details[2][1] = newAgeRatingString;

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie age rating updated successfully!");
                } else {
                    System.out.println("Movie age rating update failed!");
                }
                break;

            case 5:
                System.out.println("Current movie director: " + getMovieDirector(movieName));
                System.out.print("Enter new movie director: ");
                String newMovieDirector = sc.next();
                details = FileIOHandler.readTo2DArray(dir);
                details[3][1] = newMovieDirector;

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie director updated successfully!");
                } else {
                    System.out.println("Movie director update failed!");
                }
                break;

            case 6:
                System.out.println("Current movie cast: ");
                getMovieCast(movieName);
                System.out.print("Enter new movie cast: ");

                ArrayList<String> newMovieCast = new ArrayList<String>();
                while (true) {
                    System.out.println("Enter cast member name (enter '-' to finish): ");
                    String castMember = sc.next();
                    if (castMember.equals("-")) {
                        break;
                    } else {
                        newMovieCast.add(castMember);
                    }
                }

                details = FileIOHandler.readTo2DArray(dir);

                // clear out details array first
                for (int i = 1; i < details[4].length; i++) {
                    details[4][i] = "";
                }

                details[4][0] = "movieCast";
                details[4][1] = newMovieCast.toString().replace("[", "").replace("]", ""); // replace
                // brackets

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie cast updated successfully!");
                } else {
                    System.out.println("Movie cast update failed!");
                }
                break;

            case 7:
                System.out.println("Current movie synopsis: " + getMovieSynopsis(movieName));
                System.out.print("Enter new movie synopsis: ");
                String newMovieSynopsis = sc.next();
                details = FileIOHandler.readTo2DArray(dir);

                // hacky workaround, dont have time to figure out proper solution.
                details[5][0] = "movieSynopsis;" + newMovieSynopsis;

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie synopsis updated successfully!");
                } else {
                    System.out.println("Movie synopsis update failed!");
                }
                break;

            case 8:
                System.out.println("Current movie sales: " + getMovieSales(movieName));
                System.out.print("Enter new movie sales: ");
                int newMovieSales = sc.nextInt();
                details = FileIOHandler.readTo2DArray(dir);
                details[6][1] = Integer.toString(newMovieSales);

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie sales updated successfully!");
                } else {
                    System.out.println("Movie sales update failed!");
                }
                break;

            case 9:
                System.out.println("Current movie type: " + getMovieType(movieName));

                String newMovieType = null;
                do {
                    System.out.println("Enter new movie type: ");
                    System.out.println("1. 2D");
                    System.out.println("2. 3D");
                    System.out.print("Choice: ");
                    int typeChoice = sc.nextInt();

                    if (typeChoice == 1) {
                        newMovieType = "2D";
                    } else if (typeChoice == 2) {
                        newMovieType = "3D";
                    } else {
                        System.out.println("Invalid choice, try again.");
                    }
                } while (newMovieType == null);

                details = FileIOHandler.readTo2DArray(dir);
                details[7][1] = newMovieType;

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie details updated successfully!");
                } else {
                    System.out.println("Movie details update failed!");
                }
                break;

            case 0:
                sc.nextLine();
                System.out.println("Current movie blockbuster: " + getMovieBlockbuster(movieName));

                String newMovieBlockbuster = null;
                Boolean newMovieBlockbusterBool = false;
                do {
                    System.out.println("\nIs it a Blockbuster? (Y/N): ");
                    newMovieBlockbuster = sc.nextLine();
                    newMovieBlockbuster = newMovieBlockbuster.toLowerCase();
                    if (newMovieBlockbuster.equals("y")) {
                        newMovieBlockbusterBool = true;
                        break;
                    } else if (newMovieBlockbuster.equals("n")) {
                        newMovieBlockbusterBool = false;
                        break;
                    } else {
                        System.out.println("Invalid choice, try again.");
                        newMovieBlockbuster = "";
                    }
                } while (newMovieBlockbuster != "y" || newMovieBlockbuster != "n");

                details = FileIOHandler.readTo2DArray(dir);
                details[8][1] = newMovieBlockbusterBool.toString();

                if (FileIOHandler.writeToCsv2D(dir, details)) {
                    System.out.println("Movie blockbuster updated successfully!");
                } else {
                    System.out.println("Movie blockbuster update failed!");
                }
                break;

        }
    }

    /**
     * Method to add a review to a movie given the movie name and the review
     * details. Returns true if review is added successfully, false otherwise.
     * 
     * @param movieName
     * @param review
     * @param rating
     * @return boolean
     */
    public static boolean addReview(String movieName, String review, int rating) {
        String dir = moviesDBDir + "/" + movieName + "/reviews.csv";

        try {
            String[][] details = FileIOHandler.readTo2DArray(dir);
            // if the totalScore and totalReviews are empty
            if (details[0][1].equals("NA")) {
                details[0][1] = Integer.toString(rating);
                details[1][1] = "1";
            } else {
                int currentNumReviews = Integer.parseInt(details[1][1]);

                int currentTotalRatings = 0;

                for (int i = 3; i < details.length; i++) {
                    currentTotalRatings += Integer.parseInt(details[i][2]);
                }

                double newAvgRating = ((double) rating + (double) currentTotalRatings)
                        / ((double) currentNumReviews + (double) 1);

                newAvgRating = Math.round(newAvgRating * 100);
                newAvgRating = newAvgRating / 100;
                details[0][1] = Double.toString(newAvgRating);
                details[1][1] = Integer.toString(currentNumReviews + 1);
            }

            FileIOHandler.writeToCsv2D(dir, details);
            String reviewContent = "default" + "," + review + "," + Integer.toString(rating);
            FileIOHandler.appendToFile(dir, reviewContent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to get the reviews of a movie from the database given the movie name.
     * 
     * @param movieName
     */
    public static void printMovieReviews(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/reviews.csv";
        String[][] details = FileIOHandler.readTo2DArray(dir);

        System.out.println("Total reviews: " + details[1][1]);
        System.out.println("Average rating: " + details[0][1] + "/5");

        int listCount = 0;
        for (int i = 3; i < details.length; i++) {
            listCount++;
            System.out.printf("%-4s", listCount + ". ");
            System.out.println("Rating: " + details[i][2] + "/5");
            System.out.printf("%11s %s", "Review:", details[i][1] + "\n\n");
        }
    }

    /**
     * Method to get the movie synopsis from the database given the movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieStatus(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieStatus");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMovieStatus Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the movie synopsis from the database given the movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieRuntime(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieRuntime");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMovieRuntime Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the age rating of a movie from the database given the movie
     * name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieAgeRating(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "PGRating");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMoviePGRating Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the movie director from the database given the movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieDirector(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieDirector");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMovieDirector Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the movie cast from the database, returns a string array
     * 
     * @param movieName
     * @return String[]
     */
    public static String[] getMovieCast(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];
        Boolean endOfCastColumn = false;

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieCast");
            String[] cast = new String[lineString.length - 1];
            for (int i = 1; i < lineString.length; i++) {
                if (lineString[i].equals("")) {
                    endOfCastColumn = true;
                }
                if (!endOfCastColumn) {
                    cast[i - 1] = lineString[i];
                }
            }
            // return cast;

            for (int i = 0; i < cast.length; i++) {
                // remove space from first char of string
                if (cast[i].charAt(0) == ' ') {
                    cast[i] = cast[i].substring(1);
                }
                // append a "|" char infront of the string
                cast[i] = "|" + cast[i];
                System.out.println(cast[i]);
            }
        } catch (Exception e) {
            System.out.println("getMovieCast Error: " + e.getMessage());
        }

        return null;
    }

    /**
     * Method to get the movie synopsis from the database given the movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieSynopsis(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieSynopsis", ";");
            // strip last char from string
            // String synopsis = lineString[1].substring(0, lineString[1].length() - 1);
            String synopsis = lineString[1];

            return synopsis;
        } catch (Exception e) {
            System.out.println("getMovieSynopsis Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the movie sales from the database given the movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieSales(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieSales");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMovieSales Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the movie release date from the database given the movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieType(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "movieType");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMovieType Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to get the movie's blockbuster status from the database given the
     * movie name.
     * 
     * @param movieName
     * @return String
     */
    public static String getMovieBlockbuster(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        String lineString[];

        try {
            lineString = FileIOHandler.readAttribute(dir, "BlockBuster");
            return lineString[1];
        } catch (Exception e) {
            System.out.println("getMovieBlockbuster Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to print the movie details.
     * 
     * @param movieName
     */
    public static void viewMovieDetails(String movieName) {
        System.out.println("Movie Name: " + movieName.toUpperCase() + "\n");
        System.out.println("Movie Runtime: " + getMovieRuntime(movieName) + "\n");
        System.out.println("Movie Status: " + getMovieStatus(movieName) + "\n");
        System.out.println("Movie Age Rating: " + getMovieAgeRating(movieName) + "\n");
        System.out.println("Movie Synopsis: " + getMovieSynopsis(movieName) + "\n");
        System.out.println("Movie Director: " + getMovieDirector(movieName) + "\n");
        System.out.println("Movie Type: " + getMovieType(movieName) + "\n");
        System.out.println("Movie Blockbuster: " + getMovieBlockbuster(movieName) + "\n");
        System.out.println("Movie Cast: ");
        getMovieCast(movieName);
        System.out.println("\nMovie Reviews: ");
        printMovieReviews(movieName);
    }

    /**
     * Method to update sales of the movie given the movie name.
     * 
     * @param movieName
     */
    public static void updateSales(String movieName) {
        String dir = moviesDBDir + "/" + movieName + "/details.csv";
        System.out.println("Current movie sales: " + getMovieSales(movieName));
        System.out.print("Enter new movie sales: ");
        int newMovieSales = sc.nextInt();
        String[][] details = FileIOHandler.readTo2DArray(dir);
        details[6][1] = Integer.toString(newMovieSales);

        if (FileIOHandler.writeToCsv2D(dir, details)) {
            System.out.println("Movie sales updated successfully!");
        } else {
            System.out.println("Movie sales update failed!");
        }
    }

    /**
     * Method to list all movies in the database. Parameter is used to determine
     * which type of status to show. 1 = Showing/Preview, 2 = Lists all movies, 3 =
     * END_OF_SHOW
     * Showing
     * 
     * @param showStatus
     */
    public static void listMovies(int showStatus) {

        // only shows the movies where the movieStatus is "now showing"
        String dir = "./database/Movies";
        int movieListCounter = 1;

        try {
            File[] movieFolder = new File(dir).listFiles();

            for (File movie : movieFolder) {
                if (movie.getName().equals(".DS_Store")) {
                    continue;
                }

                if (showStatus == 1 && (getMovieStatus(movie.getName()).equals("Showing")
                        || getMovieStatus(movie.getName()).equals("Preview"))) {
                    System.out.println(movieListCounter + ". " + movie.getName());
                    movieListCounter++;
                } else if (showStatus == 2) { // lists all movies
                    System.out.println(movieListCounter + ". " + movie.getName());
                    movieListCounter++;
                } else if (showStatus == 3 && getMovieStatus(movie.getName()).equals("Coming Soon")) {
                    System.out.println(movieListCounter + ". " + movie.getName());
                    movieListCounter++;
                } else if (showStatus == 4 && getMovieStatus(movie.getName()).equals("END_OF_SHOW")) {
                    System.out.println(movieListCounter + ". " + movie.getName());
                    movieListCounter++;
                }

            }
        } catch (

        Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

        movieListCounter -= 1;
        System.out.println("Listed " + movieListCounter + " movies.");
    }

    /**
     * Method to check if a movie exists in the database, given the movie name.
     * Returns true if movie exists, false if not.
     * 
     * @param movieName
     * @return Boolean
     */
    public static Boolean searchMovie(String movieName) {
        String dir = "./database/Movies";

        try {
            File[] movieFolder = new File(dir).listFiles();

            for (File movie : movieFolder) {

                String workingMovieName = movie.getName();
                if (workingMovieName.equals(".DS_Store")) {
                    continue;
                }

                workingMovieName = workingMovieName.toLowerCase();

                if (workingMovieName.toLowerCase().equals(movieName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
        return false;

    }
}

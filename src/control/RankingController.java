package control;

import java.io.File;

public class RankingController {
    /**
     * method to extract the movies and details from the details.csv
     * and puts them into a 2D Array
     * 
     * @param dir
     * @param length
     * @return String[][]
     */
    public static String[][] extractDetails(String dir, int length) {
        String[][] movieslist = new String[length][2];
        int i = 0;

        try {
            File[] movieFolder = new File(dir).listFiles();

            for (File movie : movieFolder) {
                if (movie.getName().equals(".DS_Store")) {
                    continue;
                }

                if (MovieController.getMovieStatus(movie.getName()).equals("Showing")) {
                    // System.out.println("DEBUGGING");
                    String movietoinsert = dir + "/" + movie.getName() + "/details.csv";

                    String dummymoviearray[][] = FileIOHandler.readTo2DArray(movietoinsert); // put the current
                                                                                             // iteration of the movie
                                                                                             // in a dummymovierray

                    movieslist[i][0] = movie.getName(); // insert the name
                    // if new movie with no sales, default ticket sales to 0

                    // System.out.println(movieslist[i][0]);
                    if (dummymoviearray[6].length == 2 && dummymoviearray[6][1] != null
                            && NumberChecker.isDouble(dummymoviearray[6][1])) {
                        movieslist[i][1] = dummymoviearray[6][1]; // insert the sales into the movieslist
                    } else {

                        movieslist[i][1] = "0";
                    }

                    // System.out.println("The ticket sales for " + movieslist[i][0] + " is " +
                    // movieslist[i][1]); //debugging statement
                    i++; // increment the moviecount by 1
                }

            }

        } catch (Exception e) {
            System.out.println("Error: BOIS" + e);
            System.out.println("Error: " + e);
            System.exit(0);
        }
        return movieslist;

    }

    /**
     * method to extract the movies and reviews from the reviews.csv
     * and puts them into a 2D Array
     * 
     * @param dir
     * @param length
     * @return String[][]
     */
    public static String[][] extractReviews(String dir, int length) {
        String[][] reviewslist = new String[length][2];
        int i = 0;
        try {
            File[] movieFolder = new File(dir).listFiles();

            for (File movie : movieFolder) {
                if (movie.getName().equals(".DS_Store")) {
                    continue;
                }

                if (MovieController.getMovieStatus(movie.getName()).equals("Showing")) {
                    String movietoinsert = dir + "/" + movie.getName() + "/reviews.csv";

                    String dummymoviearray[][] = FileIOHandler.readTo2DArray(movietoinsert); // put the current
                                                                                             // iteration of the movie
                                                                                             // in a dummymovierray

                    reviewslist[i][0] = movie.getName(); // insert the name
                    if (dummymoviearray[0].length != 2 || dummymoviearray[0][1] == null
                            || !NumberChecker.isDouble(dummymoviearray[0][1]))// if no rating, default rating is 0.0
                        reviewslist[i][1] = "0.0";
                    else
                        reviewslist[i][1] = dummymoviearray[0][1];// insert the reviews into the movieslist
                    // System.out.println("The review score of " + reviewslist[i][0] + " is " +
                    // reviewslist[i][1]); //debugging statement
                    i++; // increment the moviecount by 1

                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);

        }

        return reviewslist;

    }

}

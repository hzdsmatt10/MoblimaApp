package boundary;

import java.io.*;
import java.util.Scanner;

import control.MovieController;
import control.RankingController;
import control.UIController;

/**
 * Represents the class of Ranking, which handles the top 5 rankings of the
 * movies sales
 * and movie reviews, also used to lock the ranking methods when needed
 * 
 * @author Matthias
 * @version 1.0
 * @since 1.0
 */

public class RankingUI extends UIController {
    // LOCK MECHANISM

    private static int revolvingLock;

    /**
     * The getter method for the revolvingLock attribute
     * 
     * @return int
     */
    public int getRevolvingLock() {
        return revolvingLock;
    }

    /**
     * The setter method for the revolvingLock attribute
     * 
     * @param int
     */
    public void setRevolvingLock(int revolvingLock) {
        this.revolvingLock = revolvingLock;
    }

    /**
     * This method is to unlock the ranking of reviews and unlock the ranking of
     * sales
     * 
     * 
     */
    public void unlockReviewsUnlockSales() {
        revolvingLock = 0;
    }

    /**
     * This method is to unlock the ranking of reviews and lock the ranking of sales
     * 
     * 
     */
    public void lockSalesUnlockReviews() {
        revolvingLock = 1;
    }

    /**
     * This method is to unlock the ranking of sales and lock the reviews
     * 
     *
     * 
     */
    public void unlockSalesLockReviews() {
        revolvingLock = 2;
    }

    /**
     * This method is to lock the ranking of review and sales
     * 
     * 
     */
    public void lockReviewslockSales() {
        revolvingLock = 3;
    }

    /**
     * This method is to show the main menu to allow the users to choose
     * which method of rankings they want to choose: reviews or sales
     * This method in turn calls other methods from this class
     *
     * 
     */
    public void rank_movies() {
        RankingUI UI = new RankingUI();
        String dir = "./database/movies";
        Scanner sc = new Scanner(System.in);
        int i = 0;

        // find the number of movies
        try {
            File[] movieFolder = new File(dir).listFiles();

            for (File movie : movieFolder) {
                if (movie.getName().equals(".DS_Store")) {
                    continue;
                }

                if (MovieController.getMovieStatus(movie.getName()).equals("Showing")
                        || MovieController.getMovieStatus(movie.getName()).equals("Preview")) {
                    // System.out.println(i + ". " + movie.getName());
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

        // System.out.println("The total number of movies is "+i);

        String[][] movieslist = new String[i][2];
        String[][] reviewslist = new String[i][2];
        int userChoice;
        do {
            UI.printHeader("RANKING");
            UI.printBody("""
                    Please select sorting type:
                    1. Sort by Ratings
                    2. Sort by Ticket sales
                    3. Exit to Main Menu
                    """);
            System.out.print("Choice: ");
            userChoice = sc.nextInt(); // input on whether to sort by rating or sales

            if (userChoice == 1) {
                // sorting by rating
                if (revolvingLock == 0 || revolvingLock == 1) {
                    reviewslist = RankingController.extractReviews(dir, i);
                    printSortByRatings(reviewslist);
                } else
                    System.out.println(UI.colorText("\nThe option to rank by ratings is disabled\n", "red"));

            } else if (userChoice == 2) {
                // sort by sales
                if (revolvingLock == 0 || revolvingLock == 2) {
                    movieslist = RankingController.extractDetails(dir, i); // extract details.csv into movieslist
                    printSortBySales(movieslist); // sort the movies by sales numbers

                } else
                    System.out.println(UI.colorText("\nThe option to rank by sales is disabled\n", "red"));
            } else if (userChoice == 3)
                return;
            else
                System.out.println(UI.colorText("\nInvalid Option! Please try again...\n", "red"));
            UI.printBanner(31);
        } while (userChoice != 3);
    }

    /**
     * This method takes in the movies with their sales and ranks the top 5 movie in
     * sales and prints them out
     * 
     * @param toSort
     * 
     */
    private void printSortBySales(String[][] toSort) {
        RankingUI UI = new RankingUI();
        // insertionSort
        for (int i = 0; i < toSort.length; i++) {

            for (int j = i; j > 0; j--) {

                if (Double.parseDouble(toSort[j][1]) < Double.parseDouble(toSort[j - 1][1])) {
                    // swap
                    String[] swapHolder = toSort[j - 1];
                    toSort[j - 1] = toSort[j];
                    toSort[j] = swapHolder;
                } else {
                    break;
                }

            }
        }

        UI.printHeader("SORTED BY SALES");
        if (toSort.length >= 1)
            System.out.println(
                    "1: " + toSort[toSort.length - 1][0] + " with " + toSort[toSort.length - 1][1] + " ticket sales");
        if (toSort.length >= 2)
            System.out.println(
                    "2: " + toSort[toSort.length - 2][0] + " with " + toSort[toSort.length - 2][1] + " ticket sales");
        if (toSort.length >= 3)
            System.out.println(
                    "3: " + toSort[toSort.length - 3][0] + " with " + toSort[toSort.length - 3][1] + " ticket sales");
        if (toSort.length >= 4)
            System.out.println(
                    "4: " + toSort[toSort.length - 4][0] + " with " + toSort[toSort.length - 4][1] + " ticket sales");
        if (toSort.length >= 5)
            System.out.println(
                    "5: " + toSort[toSort.length - 5][0] + " with " + toSort[toSort.length - 5][1] + " ticket sales");

    }

    /**
     * This method takes in the movies with their sales and ranks the top 5 movie in
     * ratings/reviews and prints them out
     * 
     * @param toSort
     * 
     */
    private void printSortByRatings(String[][] toSort) {
        RankingUI UIs = new RankingUI();

        // insertionSort
        for (int i = 0; i < toSort.length; i++) {
            for (int j = i; j > 0; j--) {
                if (Double.parseDouble(toSort[j][1]) < Double.parseDouble(toSort[j - 1][1])) {
                    // swap
                    String[] swapHolder = toSort[j - 1];
                    toSort[j - 1] = toSort[j];
                    toSort[j] = swapHolder;
                } else {
                    break;
                }
            }
        }

        UIs.printHeader("SORTED BY RATINGS");
        if (toSort.length >= 1)
            System.out
                    .println(
                            "1: " + toSort[toSort.length - 1][0] + " with a rating of " + toSort[toSort.length - 1][1]);
        if (toSort.length >= 2)
            System.out
                    .println(
                            "2: " + toSort[toSort.length - 2][0] + " with a rating of " + toSort[toSort.length - 2][1]);
        if (toSort.length >= 3)
            System.out
                    .println(
                            "3: " + toSort[toSort.length - 3][0] + " with a rating of " + toSort[toSort.length - 3][1]);
        if (toSort.length >= 4)
            System.out
                    .println(
                            "4: " + toSort[toSort.length - 4][0] + " with a rating of " + toSort[toSort.length - 4][1]);
        if (toSort.length >= 5)
            System.out
                    .println(
                            "5: " + toSort[toSort.length - 5][0] + " with a rating of " + toSort[toSort.length - 5][1]);

    }

}

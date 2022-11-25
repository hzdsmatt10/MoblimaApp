package boundary;

import java.util.ArrayList;
import java.util.Scanner;

import control.UIController;
import entity.Movie;
import control.MovieController;

/**
 * Represents the UI class that handles creation of movie listing.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class AdminCreationUI extends UIController {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Displays the admin creation menu.
     */
    public static void displayCreationUI() {
        System.out.println("Please enter the following details:");

        System.out.print("Movie Title: ");
        String movieTitle = sc.nextLine();

        System.out.print("Movie runtime: ");
        int movieRuntime = sc.nextInt();

        String movieStatusString = null;
        do {
            System.out.println("Movie Status: ");
            System.out.println("1. Coming Soon");
            System.out.println("2. Showing");
            System.out.println("3. Preview");
            System.out.println("4. END_OF_SHOW");
            System.out.print("Choice: ");
            int movieStatus = sc.nextInt();

            if (movieStatus == 1) {
                movieStatusString = "Coming Soon";
            } else if (movieStatus == 2) {
                movieStatusString = "Showing";
            } else if (movieStatus == 3) {
                movieStatusString = "Preview";
            } else if (movieStatus == 4) {
                movieStatusString = "END_OF_SHOW";
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (movieStatusString == null);

        String movieAgeRatingString = null;
        do {
            System.out.println("\nAge Rating: ");
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
                    movieAgeRatingString = "G";
                    break;
                case 2:
                    movieAgeRatingString = "PG";
                    break;
                case 3:
                    movieAgeRatingString = "PG13";
                    break;
                case 4:
                    movieAgeRatingString = "NC16";
                    break;
                case 5:
                    movieAgeRatingString = "M18";
                    break;
                case 6:
                    movieAgeRatingString = "R21";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }

        } while (movieAgeRatingString == null);

        sc.nextLine();
        System.out.println("\nDirector: ");
        String movieDirector = sc.nextLine();

        System.out.println("\nCast: ");
        ArrayList<String> movieCast = new ArrayList<String>();
        while (true) {
            System.out.println("Enter cast member name (enter '-' to finish): ");
            String castMember = sc.nextLine();
            if (castMember.equals("-")) {
                break;
            } else {
                movieCast.add(castMember);
            }
        }

        System.out.println("\nSynopsis: ");
        String movieSynopsis = sc.nextLine();

        System.out.println("\nSales: ");
        String movieSales = sc.nextLine();

        String movieType = null;
        do {
            System.out.println("\nMovie Type: ");
            System.out.println("1. 2D");
            System.out.println("2. 3D");
            System.out.print("Choice: ");
            int typeChoice = sc.nextInt();

            if (typeChoice == 1) {
                movieType = "2D";
            } else if (typeChoice == 2) {
                movieType = "3D";
            } else {
                System.out.println("Invalid choice, try again.");
            }
        } while (movieType == null);

        sc.nextLine();
        String movieBlockbuster;
        Boolean movieBlockbusterBool = false;
        do {
            System.out.println("\nIs it a Blockbuster? (Y/N): ");
            movieBlockbuster = sc.nextLine();
            movieBlockbuster = movieBlockbuster.toLowerCase();
            if (movieBlockbuster.equals("y")) {
                movieBlockbusterBool = true;
                break;
            } else if (movieBlockbuster.equals("n")) {
                movieBlockbusterBool = false;
                break;
            } else {
                System.out.println("Invalid choice, try again.");
                movieBlockbuster = "";
            }
        } while (movieBlockbuster != "y" || movieBlockbuster != "n");

        Movie newMovie = new Movie(movieTitle, movieRuntime, movieStatusString,
                movieAgeRatingString,
                movieDirector, movieCast, movieSynopsis, movieSales, movieType,
                movieBlockbusterBool);

        MovieController.addMovie(newMovie);
        return;
    }
}
package boundary;

import java.util.Scanner;

import control.MovieController;
import control.UIController;

/**
 * Represents the UI Class that manages movie reviews.
 * 
 * @author Mattias
 * @version 1.0
 * @since 1.0
 */
public class ReviewUI extends UIController {

    /**
     * Guides user to adding a review.
     * 
     * @return boolean
     */
    public static boolean addNewReview() {
        Scanner sc = new Scanner(System.in);
        ReviewUI UI = new ReviewUI();
        String movieName;
        int movieRating;

        // list all movie names
        UI.printHeader("RATE MOVIE");
        MovieController.listMovies(2);

        do {
            System.out.print("\nEnter name of movie to review: ");
            movieName = sc.nextLine();
            movieName = movieName.toLowerCase();
            if (movieName.equals("-")) {
                return false;
            }

            if (!MovieController.searchMovie(movieName)) {
                System.out.println(UI.colorText("Movie not found. Please try again, or press '-' to return.", "red"));
                movieName = "";
            }

        } while (movieName == "");

        System.out.print("Enter your review: ");
        String review = sc.nextLine();

        do {
            System.out.print("Enter your rating (0-5): ");
            movieRating = sc.nextInt();
            if (movieRating < 0 || movieRating > 5) {
                System.out.println("Invalid rating. Please try again.");
            }
        } while (movieRating < 0 || movieRating > 5);

        sc.nextLine();

        if (MovieController.addReview(movieName, review, movieRating)) {
            System.out.println(UI.colorText("Review added successfully!", "green"));
            UI.printBanner(31);
            return true;
        } else {
            System.out.println(UI.colorText("Review failed to add!", "red"));
            UI.printBanner(31);
            return false;
        }

    }
}

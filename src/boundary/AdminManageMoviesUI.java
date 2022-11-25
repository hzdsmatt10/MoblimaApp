package boundary;

import control.MovieController;
import control.UIController;

import java.util.Scanner;

/**
 * Represents the UI Class that manages movie listings.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class AdminManageMoviesUI extends UIController {

    /**
     * Method handler to handle managment of movie listing, depending on the user's
     * choice, it will guide the user through the setup/modification of the movie
     * listing
     * 
     * @param userChoice
     */
    public static void manageMovieListing(int userChoice) {
        AdminManageMoviesUI UI = new AdminManageMoviesUI();
        Scanner sc = new Scanner(System.in);

        switch (userChoice) {
            case 1:
                UI.printHeader("CREATING NEW MOVIE LISTING");
                AdminCreationUI.displayCreationUI();
                break;

            case 2:
                UI.printHeader("UPDATING MOVIE LISTING");
                MovieController.listMovies(2);
                System.out.print("Enter name of movie to edit: ");
                String movieName = sc.nextLine().toLowerCase();
                if (MovieController.searchMovie(movieName)) {
                    System.out.println(UI.colorText("Movie found!", "green"));
                    AdminUpdateUI.displayUpdateUI(movieName);
                } else {
                    System.out.println(UI.colorText("Movie not found.", "red"));
                }
                break;

            case 3:
                UI.printHeader("DELETING MOVIE LISTING");
                MovieController.listMovies(2);
                System.out.print("\nEnter name of movie to be deleted ('-' to cancel): ");
                String movieTitleToDelete = sc.nextLine();
                if (movieTitleToDelete.equals("-")) {
                    System.out.println("Deletion cancelled.");
                } else if (MovieController.deleteMovie(movieTitleToDelete)) {
                    System.out.println(UI.colorText("Movie deleted successfully!", "green"));
                } else {
                    System.out.println(UI.colorText("Movie not found!", "red"));
                }
                break;
            case 4:
                UI.printBody("Returning to Admin Panel...");
                return;
            default:
                System.out.println(UI.colorText("Invalid choice. Please try again.", "red"));
                break;
        }
    }
}

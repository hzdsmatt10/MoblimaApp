package boundary;

import java.util.Scanner;
import control.MovieController;
import control.UIController;

/**
 * Represents the class UI that handles movie listing and searching.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class ListSearchUI extends UIController {

    /**
     * Lists all movies and allows user to search for a movie.
     */
    public static void listsearch() {
        ListSearchUI UI = new ListSearchUI();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            UI.printHeader("List/Search");
            UI.printBody("""
                    Please select your choice:
                    1. List all Movies
                    2. Search for a Movie
                    3. Return to Main menu
                    """);
            System.out.print("Choice: ");

            option = sc.nextInt();
            sc.nextLine(); // clear out the left over /n

            switch (option) {
                case 1:
                    UI.printHeader("MOVIES");
                    UI.printHeader("NOW SHOWING/PREVIEW");
                    MovieController.listMovies(1);
                    UI.printHeader("COMING SOON");
                    MovieController.listMovies(3);
                    UI.printHeader("END_OF_SHOW");
                    MovieController.listMovies(4);
                    break;

                case 2:
                    UI.printHeader("MOVIE SEARCH");
                    System.out.print("Enter name of movie: ");
                    String movie = sc.nextLine();
                    movie = movie.toLowerCase(); // converts to lowercase, helps with string
                    // matching
                    UI.printHeader("SEARCH RESULTS");
                    if (MovieController.searchMovie(movie)) {
                        System.out.println(UI.colorText("Movie Found!", "green"));
                        MovieController.viewMovieDetails(movie);
                    } else {
                        System.out.println(UI.colorText("Movie does not exist!", "red"));
                    }
                    break;
            }

            UI.printBanner(31);
        } while (option != 3);

        // sc.close();
    }
}

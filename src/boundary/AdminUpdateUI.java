package boundary;

import control.UIController;

import java.util.ArrayList;
import java.util.Scanner;
import control.MovieUpdateController;

/**
 * Represents the UI class that handles guiding user through the update of
 * movie.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class AdminUpdateUI extends UIController {
    static Scanner sc = new Scanner(System.in);

    /**
     * Main method which displays the menu to give
     * the user the option to choose which attribute
     * of the movie they want to update.
     * 
     * @param movieName
     */
    public static void displayUpdateUI(String movieName) {
        System.out.println("\nPlease enter the following detail you'd want to edit:");
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
        System.out.println("'-' to exit");
        System.out.print("Choice: ");

        String choice = sc.nextLine();

        switch (choice) {

            case "1":
                System.out.print("Enter new movie name: ");
                String newMovieName = sc.next();
                if (MovieUpdateController.updateMovieName(movieName, newMovieName)) {
                    System.out.println("Movie name updated successfully!");
                } else {
                    System.out.println("Movie name update failed!");
                }

                break;

            case "2":
                System.out.println("Current movie runtime: " + MovieUpdateController.getMovieRuntime(movieName));
                System.out.print("Enter new movie runtime: ");
                int newMovieRuntime = sc.nextInt();

                if (MovieUpdateController.updateMovieRuntime(movieName, newMovieRuntime)) {
                    System.out.println("Movie runtime updated successfully!");
                } else {
                    System.out.println("Movie runtime update failed!");
                }
                break;

            case "3":
                System.out.println("Current movie status: " + MovieUpdateController.getMovieStatus(movieName));

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
                        newMovieStatus = "Preview";
                    } else if (statusChoice == 4) {
                        newMovieStatus = "END_OF_SHOW";
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } while (newMovieStatus == null);

                if (MovieUpdateController.updateMovieStatus(movieName, newMovieStatus)) {
                    System.out.println("Movie status updated successfully!");
                } else {
                    System.out.println("Movie status update failed!");
                }
                break;

            case "4":
                System.out.println("Current movie age rating: " + MovieUpdateController.getMovieAgeRating(movieName));

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

                if (MovieUpdateController.updateMovieAgeRating(movieName, newAgeRatingString)) {
                    System.out.println("Movie age rating updated successfully!");
                } else {
                    System.out.println("Movie age rating update failed!");
                }
                break;

            case "5":
                sc.nextLine();
                System.out.println("Current movie director: " + MovieUpdateController.getMovieDirector(movieName));
                System.out.print("Enter new movie director: ");
                String newMovieDirector = sc.nextLine();

                if (MovieUpdateController.updateMovieDirector(movieName, newMovieDirector)) {
                    System.out.println("Movie director updated successfully!");
                } else {
                    System.out.println("Movie director update failed!");
                }
                break;

            case "6":
                System.out.println("Current movie cast: ");
                MovieUpdateController.getMovieCast(movieName);
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

                if (MovieUpdateController.updateMovieCast(movieName, newMovieCast)) {
                    System.out.println("Movie cast updated successfully!");
                } else {
                    System.out.println("Movie cast update failed!");
                }
                break;

            case "7":
                sc.nextLine();
                System.out.println("Current movie synopsis: " + MovieUpdateController.getMovieSynopsis(movieName));
                System.out.print("Enter new movie synopsis: ");
                String newMovieSynopsis = sc.nextLine();

                if (MovieUpdateController.updateMovieSynopsis(movieName, newMovieSynopsis)) {
                    System.out.println("Movie synopsis updated successfully!");
                } else {
                    System.out.println("Movie synopsis update failed!");
                }
                break;

            case "8":
                System.out.println("Current movie sales: " + MovieUpdateController.getMovieSales(movieName));
                System.out.print("Enter new movie sales: ");
                int newMovieSales = sc.nextInt();

                if (MovieUpdateController.updateSales(movieName, newMovieSales)) {
                    System.out.println("Movie sales updated successfully!");
                } else {
                    System.out.println("Movie sales update failed!");
                }
                break;

            case "9":
                System.out.println("Current movie type: " + MovieUpdateController.getMovieType(movieName));

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

                if (MovieUpdateController.updateMovieType(movieName, newMovieType)) {
                    System.out.println("Movie details updated successfully!");
                } else {
                    System.out.println("Movie details update failed!");
                }
                break;

            case "0":
                sc.nextLine();
                System.out
                        .println("Current movie blockbuster: " + MovieUpdateController.getMovieBlockbuster(movieName));

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

                if (MovieUpdateController.updateMovieBlockbuster(movieName, newMovieBlockbusterBool)) {
                    System.out.println("Movie blockbuster updated successfully!");
                } else {
                    System.out.println("Movie blockbuster update failed!");
                }
                break;
            case "-":
                System.out.println("Returning...");
                return;
        }
    }

}
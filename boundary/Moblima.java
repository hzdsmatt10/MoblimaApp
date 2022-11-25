package boundary;

import java.util.Scanner;
import entity.MovieGoer;
import control.Booking;
import control.Seats;
import control.UIController;
import boundary.RankingUI;

/**
 * Entry class for the MOBLIMA applicaton.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class Moblima extends UIController {
    static Scanner sc = new Scanner(System.in);

    /**
     * Main function to start the MOBIAMA application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Moblima UI = new Moblima();
        MovieGoer user = new MovieGoer();
        int userChoice = 0;

        // RankingUI r = new RankingUI();

        while (userChoice != 8) {
            UI.printHeader("Welcome to MOBLIMA!");
            UI.printBody("""
                    Please select your choice:
                    1. Search/List Movie and View Movie Details
                    2. Check seat availability and selection of seats
                    3. Make booking and purchase ticket
                    4. Check booking history
                    5. List the Top 5 rankings by ticket sales OR by overall reviewers' ratings
                    6. Rate Movie
                    7. Adminstration Login
                    8. Exit
                    """);
            System.out.print("Choice: ");

            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    ListSearchUI.listsearch();
                    break;
                case 2:
                    // Check seat availability and selection of seats
                    Seats.manager(user);
                    break;
                case 3:
                    user = Booking.makeBooking(user);
                    break;
                case 4:
                    // Check booking history
                    Booking.checkHist();
                    break;
                case 5:
                    RankingUI r = new RankingUI();
                    r.rank_movies();
                    break;
                case 6:
                    ReviewUI.addNewReview();
                    break;
                case 7:
                    LoginHandlerUI.logIn();
                    break;

                case 8:
                    // Exit
                    System.out.println("Thank you for using MOBLIMA!");
                    System.exit(1);

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
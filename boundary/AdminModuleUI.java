package boundary;

import java.util.Scanner;
import control.*;
import entity.Admin;

/**
 * Represents the class of AdminModuleUI, which is the boundary class of the
 * admin
 * module
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */

public class AdminModuleUI extends UIController {
    static Scanner sc = new Scanner(System.in);

    /**
     * This method is used to display the admin menu
     * 
     * @param admin The Admin object
     */
    public static void adminMenu(Admin admin) {
        int userChoice = 0;
        AdminModuleUI UI = new AdminModuleUI();

        // user should already be verified as at admin at this point
        while (userChoice != 8 && admin.getVerified()) {
            UI.printHeader("ADMIN PANEL");
            UI.printBody("""
                    Please select your choice:
                    1. Create/Update/Remove movie listing (Manage movie listing)
                    2. Create/Update/Remove cinema showtimes and the movies to be shown (Manage movie showtimes)
                    3. Configure system settings
                    4. Back/Logout
                    """);
            System.out.print("Choice: ");
            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    // Create/Update/Remove movie listing (Manage movie listings
                    UI.printHeader("MANAGE MOVIE LISTING");
                    UI.printBody("""
                            Please select your choice:
                            1. Create movie listing
                            2. Update movie listing
                            3. Remove movie listing
                            4. Back
                            """);
                    System.out.print("Choice: ");
                    userChoice = sc.nextInt();

                    AdminManageMoviesUI.manageMovieListing(userChoice);

                    break;

                ///////////////////////////////////////////////////////////// SHOWTIME
                ///////////////////////////////////////////////////////////// MODULES/////////////////////////////////////////////////
                case 2:
                    UI.printHeader("MANAGE CINEMA SHOWTIMES"); // cinemaChoice //suite //movie //day //timing
                    UI.printBody("""
                            Please select your choice:
                            1. Create movie listing
                            2. Update movie listing
                            3. Remove movie listing
                            4. Back
                            """);
                    System.out.print("Choice: ");
                    userChoice = sc.nextInt();
                    
                    switch (userChoice) {
                        case 1:
                            AdminShowtimesUI.createShowtime();
                            break;
                        case 2:
                            AdminShowtimesUI.updateShowtime();
                            break;
                        case 3:
                            AdminShowtimesUI.deleteShowtime();
                            break;
                        case 4:
                            System.out.println("Returning to Admin Panel...");
                            return;
                        default:
                            System.out.println(UI.colorText("Invalid choice. Please try again.", "red"));
                            break;
                    }

                    break;
                case 3:
                    // system config
                    int option;
                    do {
                        UI.printHeader("System Configurations");
                        UI.printBody("""
                                Enter one of the available options:
                                1: Configure Rankings feature
                                2: Exit

                                """);

                        option = sc.nextInt();

                        switch (option) {
                            case 1:
                                RankingUI r = new RankingUI();
                                UI.printHeader("Configuration of Ranking feature");
                                System.out.println();

                                int x;
                                do {
                                    UI.printBody("""
                                            Enter one of the available options:
                                            1: unlock both sales and reviews rankings
                                            2: lock sales ranking but unlock reviews ranking
                                            3: lock reviews ranking but unlock sales ranking
                                            4. Lock both sales and reviews options
                                            5. View Top 5 in movies via Reviews/Sales
                                            6. Exit to System configurations
                                            """);

                                    x = sc.nextInt();
                                    if (x == 1)

                                        r.unlockReviewsUnlockSales();

                                    else if (x == 2)

                                        r.lockSalesUnlockReviews();

                                    else if (x == 3)

                                        r.unlockSalesLockReviews();

                                    else if (x == 4)

                                        r.lockReviewslockSales();
                                    else if (x == 5) {
                                        int currentlock = r.getRevolvingLock();
                                        //System.out.println("value is " + currentlock);
                                        r.unlockReviewsUnlockSales();
                                        r.rank_movies();
                                        r.setRevolvingLock(currentlock);

                                    } else if (x == 6)
                                        break;
                                    else
                                        System.out.println(UI.colorText("Invalid choice. Please try again.", "red"));
                                } while (x != 6);
                                break;
                            case 2:
                                System.out.println("Returning to main menu...");
                                break;
                            default:
                                System.out.println(UI.colorText("Invalid choice. Please try again.", "red"));
                                break;
                        }

                    } while (option != 2);
                case 4:
                    // Back
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println(UI.colorText("Invalid choice. Please try again.\n", "red"));
                    break;
            }
        }
        UI.printBanner(31);
    }
}

package boundary;

import java.util.Scanner;

import control.MovieController;
import control.NumberChecker;
import control.Showtimes;
import control.ShowtimesController;
import control.UIController;

/**
 * Represents the class of AdminShowtimes, which contains methods
 * to either create, update or delete a particular showtime
 * in the cineplex folder
 * 
 * @author Matthias
 * @version 1.0
 * @since 1.0
 */

public class AdminShowtimesUI extends UIController {
    /**
     * This method is used to create a ScreeningDetails.csv and
     * seating.csv through a Showtimes object,
     * getting input from the admin user on
     * the attributes of the Showtime object
     * 
     */
    public static void createShowtime() {
        AdminShowtimesUI UI = new AdminShowtimesUI();
        Scanner sc = new Scanner(System.in);
        UI.printHeader("CREATING NEW CINEMA SHOWTIME");
        System.out.println("Please choose a cineplex to add:");

        ////////////////////////////////////////////////////////////////////////// locationof
        ////////////////////////////////////////////////////////////////////////// choice///////////////////////////////////////////////////////////////
        String locationChoiceString = null;
        do {
            System.out.print("""
                    1) LIDO
                    2) NEX
                    3) PLQ
                    """);
            System.out.print("Choice: ");
            int locationChoice = sc.nextInt();

            switch (locationChoice) {
                case 1:
                    locationChoiceString = "LIDO";
                    break;
                case 2:
                locationChoiceString = "NEX";
               
                    break;
                case 3:
                locationChoiceString = "PLQ";
                
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (locationChoiceString == null);
      //  System.out.println("the location is "+ locationChoiceString);
        ////////////////////////////////////////////////////////////////////////// SUITE
        ////////////////////////////////////////////////////////////////////////// of
        ////////////////////////////////////////////////////////////////////////// choice///////////////////////////////////////////////////////////////
        System.out.println("\nPlease choose a suite to add:");
        String suiteChoiceString = null;
        do {
            System.out.print("""
                    1) Regular
                    2) Platinum
                    3) Gold
                    """);
            System.out.print("Choice: ");
            int suiteChoice = sc.nextInt();

            switch (suiteChoice) {
                case 1:
                    suiteChoiceString = "Regular";
                    break;
                case 2:
                    suiteChoiceString = "Platinum";
                    break;
                case 3:
                    suiteChoiceString = "Gold";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (suiteChoiceString == null);
        ////////////////////////////////////////////////////////////////////////// Hall
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        String hallchoice = null;
       

        System.out.print("""
                Please input the hall from 1-9
                 """);

        do {
            sc.nextLine();

            hallchoice = sc.nextLine();

            if (NumberChecker.isNumeric(hallchoice) && Integer.parseInt(hallchoice) > 0 && Integer.parseInt(hallchoice) < 10) {

                hallchoice = "Hall" + hallchoice;

                break;
            } else {
                System.out.println("Please enter a valid hall");
                hallchoice = null;
            }

        } while (hallchoice == null);
        ////////////////////////////////////////////////////////////////////////// MOVIE
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        String movieTitle = null;

        System.out.println("\nPlease choose a movie to add:");
        MovieController.listMovies(2);
        do {
            System.out.print("\nMovie Title: ");

            movieTitle = sc.nextLine();
            if (MovieController.searchMovie(movieTitle.toLowerCase()))
                break;
            else
                System.out.println("You have entered an invalid movie! Please enter another movie");

        } while (!MovieController.searchMovie(movieTitle) || movieTitle == null);
        movieTitle = movieTitle.toLowerCase();

        ////////////////////////////////////////////////////////////////////////// Day
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        System.out.println("\nPlease choose a day to add:");
        String DayChoiceString = null;
        do {
            System.out.print("""
                    1) 14/11/22,Monday
                    2) 15/11/22,Tuesday
                    3) 16/11/22,Wednesday
                    4) 17/11/22,Thursday
                    5) 18/11/22,Friday
                    6) 19/11/22,Saturday
                    7) 20/11/22,Sunday
                    """);
            System.out.print("Choice: ");
            int dayChoice = sc.nextInt();

            switch (dayChoice) {
                case 1:
                    DayChoiceString = "1~Monday";
                    break;
                case 2:
                    DayChoiceString = "2~Tuesday";
                    break;
                case 3:
                    DayChoiceString = "3~Wednesday";
                    break;
                case 4:
                    DayChoiceString = "4~Thursday";
                    break;
                case 5:
                    DayChoiceString = "5~Friday";
                    break;
                case 6:
                    DayChoiceString = "6~Saturday";
                    break;
                case 7:
                    DayChoiceString = "7~Sunday";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (DayChoiceString == null);
        ////////////////////////////////////////////////////////////////////////// Timing
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        /*
         * String timing =null;
         * do{
         * System.out.print("""
         * 1) 1500
         * 2) 1900
         * 
         * """);
         * int time = sc.nextInt();
         * 
         * switch (time) {
         * case 1:
         * timing="1500";
         * break;
         * case 2:
         * timing="1900";
         * break;
         * default:
         * System.out.println("Invalid choice, try again.");
         * }
         * } while(DayChoiceString==null);
         */
        // OLD TIMING METHOD

        String timing = null;
       
        System.out.print("\nPlease input the timing in 24hr format: ");
        sc.nextLine();
        do {

            timing = sc.nextLine();
            if (NumberChecker.isNumeric(timing) && Integer.parseInt(timing) > 0 && Integer.parseInt(timing) < 2400)
                break;
            else {
                System.out.println("Please enter a valid timing");
                timing = null;
            }

        } while (timing == null);

        // creation of showtime object
        Showtimes showtime = new Showtimes(locationChoiceString, suiteChoiceString, movieTitle, hallchoice,
                DayChoiceString, timing);

        if (ShowtimesController.addShowtime(showtime)) {
            System.out.println(UI.colorText("Showtime successfully added!", "green"));
        } else {
            System.out.println(UI.colorText("Showtime already exists!", "red"));
        }
    }

    /**
     * This method is used to update ScreeningDetails.csv and
     * seating.csv in the cineplexfolder,
     * getting input from the admin user on
     * the attributes of the Showtime object
     * 
     */
    public static void updateShowtime() {
        AdminShowtimesUI UI = new AdminShowtimesUI();
        Scanner sc = new Scanner(System.in);
        UI.printHeader("UPDATING CINEMA SHOWTIME");
        ////////////////////////////////////////////////////////////////////////// location of
        ////////////////////////////////////////////////////////////////////////// choice///////////////////////////////////////////////////////////////
        String locationChoiceString = null;
        do {
            System.out.print("""
                    1) LIDO
                    2) NEX
                    3) PLQ
                    """);
            System.out.print("Choice: ");
            int locationChoice = sc.nextInt();

            switch (locationChoice) {
                case 1:
                    locationChoiceString = "LIDO";
                    break;
                case 2:
                locationChoiceString = "NEX";
               
                    break;
                case 3:
                locationChoiceString = "PLQ";
                
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (locationChoiceString == null);
        ////////////////////////////////////////////////////////////////////////// SUITE
        ////////////////////////////////////////////////////////////////////////// of
        ////////////////////////////////////////////////////////////////////////// choice///////////////////////////////////////////////////////////////
        System.out.println("\nPlease choose a suite to update:");
        String suiteChoiceString = null;
        do {
            System.out.print("""
                    1) Regular
                    2) Platinum
                    3) Gold
                    """);
            System.out.print("Choice: ");
            int suiteChoice = sc.nextInt();

            switch (suiteChoice) {
                case 1:
                    suiteChoiceString = "Regular";
                    break;
                case 2:
                    suiteChoiceString = "Platinum";
                    break;
                case 3:
                    suiteChoiceString = "Gold";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (suiteChoiceString == null);
        ////////////////////////////////////////////////////////////////////////// Hall
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        String hallchoice = null;
        
        String hDir = "./database/Shaw" + "/" + locationChoiceString + "/" + suiteChoiceString;

        System.out.println("\nPlease input the hall number: ");
        ShowtimesController.listHalls(hDir);

        System.out.print("Choice: ");
        do {
            sc.nextLine();

            hallchoice = sc.nextLine();

            if (NumberChecker.isNumeric(hallchoice) && Integer.parseInt(hallchoice) > 0 && Integer.parseInt(hallchoice) < 10) {

                hallchoice = "Hall" + hallchoice;

                break;
            } else {
                System.out.println("Please enter a valid hall");
                hallchoice = null;
            }

        } while (hallchoice == null);
        ////////////////////////////////////////////////////////////////////////// Day
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        System.out.println("\nPlease choose a day to update:");
        String DayChoiceString = null;
        do {
            System.out.print("""
                    1) 14/11/22,Monday
                    2) 15/11/22,Tuesday
                    3) 16/11/22,Wednesday
                    4) 17/11/22,Thursday
                    5) 18/11/22,Friday
                    6) 19/11/22,Saturday
                    7) 20/11/22,Sunday
                    """);
            System.out.print("Choice: ");
            int dayChoice = sc.nextInt();

            switch (dayChoice) {
                case 1:
                    DayChoiceString = "1~Monday";
                    break;
                case 2:
                    DayChoiceString = "2~Tuesday";
                    break;
                case 3:
                    DayChoiceString = "3~Wednesday";
                    break;
                case 4:
                    DayChoiceString = "4~Thursday";
                    break;
                case 5:
                    DayChoiceString = "5~Friday";
                    break;
                case 6:
                    DayChoiceString = "6~Saturday";
                    break;
                case 7:
                    DayChoiceString = "7~Sunday";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (DayChoiceString == null);
        String dir = "./database/Shaw" + "/" + locationChoiceString + "/" + suiteChoiceString
                + "/" + hallchoice + "/" + DayChoiceString;

        ////////////////////////////////// listing showtimes////////////////////////////
        System.out.println(dir);
        System.out.println("\nPlease choose a showtime to update:");
        ShowtimesController.listShowtimes(dir);
        String timing = null; // which one to edit

        sc.nextLine();
        do {
            System.out.print("\nPlease enter the timing to update (in 24 hour format): ");
            timing = sc.nextLine();
            // change input into 24hour format

            if (NumberChecker.isNumeric(timing) && Integer.parseInt(timing) > 0 && Integer.parseInt(timing) < 2400
                    && timing.length() == 4)
                break;
            else {
                System.out.println("Please enter a valid timing!");
                timing = null;
            }

        } while (timing == null);

        if (ShowtimesController.searchTiming(timing, dir)) {
            System.out.println(UI.colorText("Timing found!", "green"));
            ShowtimesController.updateTiming(timing, dir);
        } else {
            System.out.println(UI.colorText("Timing not found.", "red"));
        }

    }

    ////////////////////////////////// deleting
    ////////////////////////////////// showtimes////////////////////////////
    /**
     * This method is used to delete the ScreeningDetails.csv and
     * seating.csv in the cineplex folder,
     * getting input from the admin user on
     * the attributes of the Showtime object,
     * deleting the timing folder at the end
     * 
     */
    public static void deleteShowtime() {
        AdminShowtimesUI UI = new AdminShowtimesUI();
        Scanner sc = new Scanner(System.in);
        UI.printHeader("REMOVING CINEMA SHOWTIME");
        ////////////////////////////////////////////////////////////////////////// location of
        ////////////////////////////////////////////////////////////////////////// choice///////////////////////////////////////////////////////////////
        String locationChoiceString = null;
        do {
            System.out.print("""
                    1) LIDO
                    2) NEX
                    3) PLQ
                    """);
            System.out.print("Choice: ");
            int locationChoice = sc.nextInt();

            switch (locationChoice) {
                case 1:
                    locationChoiceString = "LIDO";
                    break;
                case 2:
                locationChoiceString = "NEX";
               
                    break;
                case 3:
                locationChoiceString = "PLQ";
                
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (locationChoiceString == null);
        ////////////////////////////////////////////////////////////////////////// SUITE
        ////////////////////////////////////////////////////////////////////////// of
        ////////////////////////////////////////////////////////////////////////// choice///////////////////////////////////////////////////////////////
        String suiteChoiceString = null;
        do {
            System.out.print("""
                    1) Regular
                    2) Platinum
                    3) Gold
                    """);
            int suiteChoice = sc.nextInt();

            switch (suiteChoice) {
                case 1:
                    suiteChoiceString = "Regular";
                    break;
                case 2:
                    suiteChoiceString = "Platinum";
                    break;
                case 3:
                    suiteChoiceString = "Gold";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (suiteChoiceString == null);
        ////////////////////////////////////////////////////////////////////////// Hall
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        String hallchoice = null;
        
        String hDir = "./database/Shaw" + "/" + locationChoiceString + "/" + suiteChoiceString;

        System.out.println("\nPlease input the hall number: ");
        ShowtimesController.listHalls(hDir);

        System.out.print("Choice: ");
        do {
            sc.nextLine();

            hallchoice = sc.nextLine();

            if (NumberChecker.isNumeric(hallchoice) && Integer.parseInt(hallchoice) > 0 && Integer.parseInt(hallchoice) < 10) {

                hallchoice = "Hall" + hallchoice;

                break;
            } else {
                System.out.println("Please enter a valid hall");
                hallchoice = null;
            }

        } while (hallchoice == null);
        ////////////////////////////////////////////////////////////////////////// Day
        ////////////////////////////////////////////////////////////////////////// OF
        ////////////////////////////////////////////////////////////////////////// CHOICE///////////////////////////////////////////////////////////////
        String DayChoiceString = null;
        do {
            System.out.print("""
                    1) 14/11/22,Monday
                    2) 15/11/22,Tuesday
                    3) 16/11/22,Wednesday
                    4) 17/11/22,Thursday
                    5) 18/11/22,Friday
                    6) 19/11/22,Saturday
                    7) 20/11/22,Sunday
                    """);
            int dayChoice = sc.nextInt();

            switch (dayChoice) {
                case 1:
                    DayChoiceString = "1~Monday";
                    break;
                case 2:
                    DayChoiceString = "2~Tuesday";
                    break;
                case 3:
                    DayChoiceString = "3~Wednesday";
                    break;
                case 4:
                    DayChoiceString = "4~Thursday";
                    break;
                case 5:
                    DayChoiceString = "5~Friday";
                    break;
                case 6:
                    DayChoiceString = "6~Saturday";
                    break;
                case 7:
                    DayChoiceString = "7~Sunday";
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (DayChoiceString == null);
        String dir = "./database/Shaw" + "/" + locationChoiceString + "/" + suiteChoiceString
                + "/" + hallchoice + "/" + DayChoiceString;

        ////////////////////////////////// listing showtimes////////////////////////////

        ShowtimesController.listShowtimes(dir);

        String timing = null; // which one to edit

        System.out.print("""
                Enter timing to delete
                 """);
        sc.nextLine();
        do {

            timing = sc.nextLine();
            if (NumberChecker.isNumeric(timing) && Integer.parseInt(timing) > 0 && Integer.parseInt(timing) < 2400)
                break;
            else {
                System.out.println("Please enter a valid timing");
                timing = null;
            }

        } while (timing == null);
        if (ShowtimesController.searchTiming(timing, dir)) {
            System.out.println(UI.colorText("Timing found!", "green"));
            if (ShowtimesController.deleteTiming(dir, timing))
                System.out.println(UI.colorText("Timing deleted successfully!", "green"));
            else
                System.out.println(UI.colorText("Timing not deleted successfully!", "red"));
        } else {
            System.out.println(UI.colorText("Timing not found.", "red"));
        }
    }

}

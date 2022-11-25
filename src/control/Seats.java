package control;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import entity.MovieGoer;

/**
 * Represents the class of Seats, which is the control class of the seats,
 * prints out cinemas, suites,
 * showtimes, and seat selection. Lets the user choose their seats.
 * module
 * /**
 * 
 * @author Matthew
 * @version 1.0
 * @since 1.0
 */
public class Seats {
    
    protected static String designation = "./database/movies";
    static Scanner sc = new Scanner(System.in);
    private static SeatsUI UI = new SeatsUI();

    /**
     * this is the constructor for the Seats class
     */
    public Seats() {
        // constructor
    }

    /**
     * this method is a helper function to control the process of the booking.
     * 
     * @param args
     */
    public static void manager(MovieGoer user) {
        UI.printHeader("SEAT AVAILABILITY & SELECTION OF SEATS");
        String movie = ""; // define an empty string
        // int CinemaNumber = 0;
        movie = movieLookUp(movie);
        if (movie.equals("")) {
            // do nothing, movie not found
            System.out.println();
            System.out.printf("%s", UI.colorText("Movie not found.\n", "red"));
            System.out.println("Returning to main menu.");
        } else {
            // movie is found, print available cinemas
            user.setMovieChoice(movie);
            String timing = "nf";
            String cineplex = "";
            String suite = "";

            while ((timing.equals("nf"))) {
                cineplex = listCineplex(movie);
                user.setCineplexChoice(cineplex);
                // print available dates
                suite = listSuites(cineplex);
                user.setCinemaClass(suite);
                timing = listShowDateAndTimes(suite, cineplex, movie);
                if (timing.equals("nf")) {
                    continue;
                } else {
                    // there is available timing for that movie in that cineplex, suite.
                    printSeats(user, timing, cineplex, suite, movie, "");
                }
            }
        }
    }

    /**
     * this method checks whether the movie exists in the database, and returns and
     * stores the true value.
     * 
     * @param movie
     * @return String
     */
    public static String movieLookUp(String movie) {
        String movdesignation = "./database/Movies";
        UI.printHeader("MOVIES");
        MovieController.listMovies(1);
        String input = "";
        System.out.printf("%s", UI.colorText("\nEnter the name of the movie you would like to watch:\n", "yellow"));
        input = sc.nextLine();
        while (input.equals("") || (NumberChecker.isNumeric(input))) {
            System.out.println();
            System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
            System.out.printf("%s", UI.colorText("Enter the name of the movie you would like to watch:\n", "yellow"));
            input = sc.nextLine();
        }

        input = input.toLowerCase();
        File[] movieFolder = new File(movdesignation).listFiles();
        for (File folder : movieFolder) {
            if (input.equals(folder.getName().toLowerCase())) {
                if ("Showing".equals(MovieController.getMovieStatus(folder.getName()))
                        || "Preview".equals(MovieController.getMovieStatus(folder.getName()))) {
                    System.out.println(UI.colorText("\nMovie found!", "green"));
                    return folder.getName().toLowerCase();
                }
                // System.out.println(folder.getName());
            } else {
                // System.out.println("Movie not found or showing.");
                // break;
                // System.out.println(folder.getName());
            }
        }
        return "";
    }

    /**
     * this method lists out cineplexes , for the user to choose.
     * 
     * @param movie
     * @return String
     */
    public static String listCineplex(String movie) {
        // Check for cinemas that are showing the movie
        System.out.printf("%s", UI.colorText("\nSelect choice of Cinema location: \n", "yellow"));
        String designation = "./database/Shaw";
        File[] cinemaFolder = new File(designation).listFiles();
        int count = 0;
        String[] Cineplex = { "", "", "" };
        // print available cinema locations
        for (File folder : cinemaFolder) {
            count++;
            System.out.println(count + ") " + folder.getName());
            Cineplex[count - 1] = folder.getName();
        }
        // prompt user for input
        boolean acceptinput = false;
        while (!acceptinput) {
            System.out.print("Choice: ");
            String input = sc.nextLine();
            if (input.equals("") || !(NumberChecker.isNumeric(input))) {
                System.out.println();
                System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                continue;
            }
            int cinemaChoice = Integer.parseInt(input);
            if ((cinemaChoice < 1) || (cinemaChoice > count)) {
                System.out.println();
                System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                continue;
            } else {
                acceptinput = true;
                // System.out.println("Debug:" + cinemaChoice + " choice selected.");
                return Cineplex[cinemaChoice - 1];
            }
        }
        return "Error"; // shouldnt come here
    }

    /**
     * this method lets the user choose the suite they wish to watch the movie in.
     * 
     * @param cinemaChoice
     * @return String
     */
    public static String listSuites(String cinemaChoice) {
        // to implement
        String SuiteFolder = "./database/Shaw/" + cinemaChoice;
        File[] cinemaFolder = new File(SuiteFolder).listFiles();
        int count = 0;
        String[] Suites = { "", "", "" };
        System.out.printf("%s", UI.colorText("\nSelect suite: \n", "yellow"));
        // print available Suites
        for (File folder : cinemaFolder) {
            if (folder.getName().equals(".DS_Store"))
                continue;
            count++;
            System.out.println(count + ") " + folder.getName());
            Suites[count - 1] = folder.getName();
        }
        boolean acceptinput = false;
        while (!acceptinput) {
            System.out.print("Choice: ");
            String input = sc.nextLine();
            if (input.equals("") || !(NumberChecker.isNumeric(input))) {
                System.out.println();
                System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                continue;
            }
            int SuiteChoice = Integer.parseInt(input);
            // sc1.close();
            if ((SuiteChoice < 1) || (SuiteChoice > count)) {
                System.out.println();
                System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                continue;
            } else {
                acceptinput = true;
                // System.out.println("Debug:" + SuiteChoice + " choice selected.");
                return Suites[SuiteChoice - 1];
            }
        }
        return "Error"; // shouldnt reach here
    }

    /**
     * this method lists all available screenings for that movie , for that
     * cineplex, for that suite.
     * 
     * @param suite
     * @param cinemaChoice
     * @param movie
     * @return String
     */
    public static String listShowDateAndTimes(String suite, String cinemaChoice, String movie) {
        String directory = "./database/Shaw/" + cinemaChoice + "/" + suite;
        File[] hallFolders = new File(directory).listFiles();
        Arrays.sort(hallFolders);
        // String[] availableDays = new String[21];
        String[] availableTimings = new String[42];
        String[] hallDayTime = new String[3];
        int counter = 0;
        for (File hall : hallFolders) {
            if (hall.getName().equals(".DS_Store")) {
                continue;
            }
            String hallDirectory = directory + "/" + hall.getName();
            // System.out.println(hallDirectory);
            File[] dayFolders = new File(hallDirectory).listFiles();
            for (File folder : dayFolders) {
                if (folder.getName().equals(".DS_Store")) {
                    continue;
                }
                String day = hallDirectory + "/" + folder.getName();
                // System.out.println(day);
                File[] newFolder = new File(day).listFiles();
                for (File time : newFolder) {
                    if (time.getName().equals(".DS_Store")) {
                        continue;
                    }
                    String timing = day + "/" + time.getName() + "/ScreeningDetails.csv";
                    // String[] timeString = FileIOHandler.readAttribute(timing, "timing");
                    String[] movieString = FileIOHandler.readAttribute(timing, "movie");
                    if (movieString[1].toLowerCase().equals(movie)) {
                        // System.out.println(hall.getName() + "-" + folder.getName() + "-" +
                        // time.getName());
                        // availableDays[counter] = folder.getName();
                        availableTimings[counter] = hall.getName() + "-" + folder.getName() + "-" + time.getName();
                        counter++;
                    }
                }
            }
        }
        int choice = -1;
        if (counter != 0) {
            do {
                System.out.println();
                System.out.printf("%s", UI.colorText("Select a hall, day and time to watch your movie.\n", "blue"));
                for (int i = 0; i < counter; i++) {
                    hallDayTime = availableTimings[i].split("-");
                    String[] indiv_day = hallDayTime[1].split("~");
                    String hall_no = "Hall ";
                    hall_no = hall_no + hallDayTime[0].charAt(4);
                    if (hallDayTime[0].length() == 6)
                        hall_no = hall_no + hallDayTime[0].charAt(5);
                    System.out.println((i + 1) + ") " + hall_no + ", " + indiv_day[1] + ", " + hallDayTime[2]);
                }
                System.out.print("Choice: ");
                String input = sc.nextLine();
                if (input.equals("") || !(NumberChecker.isNumeric(input))) {
                    System.out.println();
                    System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                    continue;
                } else {
                    choice = Integer.parseInt(input);
                    if ((choice < 1) || (choice > counter)) {
                        System.out.println();
                        System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                        continue;
                    }
                }
            } while ((choice < 1) || (choice > counter));

            // String combined = availableDays[choice - 1] + "," + availableTimings[choice -
            // 1];
            return availableTimings[choice - 1];
        } else {
            System.out.println();
            System.out.println(
                    "Sorry. " + movie + " is not available for " + suite + " class for " + cinemaChoice + " SHAW");
            System.out.println("Please try another cinema.");
            return "nf";
        }
    }

    /**
     * this method prints out the seating arrangement for the screening.
     * 
     * @param hallDayTime
     * @param cinemaChoice
     * @param suite
     * @param movie
     * @param recall
     */
    public static void printSeats(MovieGoer user, String hallDayTime, String cinemaChoice, String suite, String movie,
            String recall) {
        String path = "";
        String layoutPath = "";
        String[] movieDetails = new String[10];
        if (recall.equals("")) {
            String[] hallDayTimeSplit = hallDayTime.split("-");
            System.out.println();
            String[] indiv_day = hallDayTimeSplit[1].split("~");
            String hall_no = "Hall ";
            hall_no = hall_no + hallDayTimeSplit[0].charAt(4);
            if (hallDayTimeSplit[0].length() == 6)
                hall_no = hall_no + hallDayTimeSplit[0].charAt(5);
            System.out.printf("%s", UI.colorText("Your selected timing to watch ", "blue"));
            // System.out.print("Your selected timing to watch ");
            System.out.printf("%s", UI.colorText(movie, "green"));
            System.out.printf("%s", UI.colorText(" is on\n", "blue"));
            System.out.printf("%s",
                    UI.colorText(indiv_day[1] + ", " + hallDayTimeSplit[2] + " at " + hall_no, "yellow"));
            System.out.println();

            int time = Integer.parseInt(hallDayTimeSplit[2]);
            String shift = "";
            if (time >= 1800) {
                shift = "Evening";
            } else {
                shift = "Afternoon";
            }
            path = "./database/Shaw/" + cinemaChoice + "/" + suite + "/" + hallDayTimeSplit[0] + "/"
                    + hallDayTimeSplit[1]
                    + "/" + hallDayTimeSplit[2] + "/ScreeningDetails.csv";
            layoutPath = "./database/Shaw/" + cinemaChoice + "/" + suite + "/" + hallDayTimeSplit[0] + "/"
                    + hallDayTimeSplit[1]
                    + "/" + hallDayTimeSplit[2] + "/seating.csv";
            user.setCinemaLocPath(path);
            user.setCinemaSeatPath(layoutPath);
            String moviePath = "./database/Movies/" + movie + "/" + "details.csv";
            String[] type = FileIOHandler.readAttribute(moviePath, "movieType");
            String[] blockbuster = FileIOHandler.readAttribute(moviePath, "BlockBuster");
            String[] screenDate = FileIOHandler.readAttribute(path, "date");

            user.setDay(indiv_day[1]);
            user.setTimeOfDay(shift);
            user.setMovieTiming(hallDayTimeSplit[2]);
            user.setDimension(type[1]);
            user.setBlockBuster(Boolean.parseBoolean(blockbuster[1]));
            user.setExactDay(screenDate[1]);
            user.setHall(hallDayTimeSplit[0]);
            String[] copymovieDetails = { movie, cinemaChoice, suite, indiv_day[1], shift, hallDayTimeSplit[2], type[1],
                    blockbuster[1], screenDate[1], hallDayTimeSplit[0] };
            movieDetails = copymovieDetails;
        } else {
            layoutPath = recall;
        }
        // String[][] layout = FileIOHandler.readTo2DArray(layoutPath);
        String[] availSeats = new String[200];
        int availSeatsCount = 0;
        // String inputLine;
        String rowLetter = "";
        System.out.println();
        try {
            String[][] lo = FileIOHandler.readTo2DArray(layoutPath);
            // print 2d array
            System.out.print(" ");
            for (int i = 0; i < lo[0].length; i++) {
                if (lo[1][i].equals("-1")) {
                    System.out.printf("%-2s", "-");
                }
                System.out.printf("%3s", lo[0][i]);
            }
            System.out.println("");
            int column;
            for (int i = 1; i < lo.length; i++) {
                column = 0;
                for (int j = 0; j < lo[i].length; j++) {
                    switch (lo[i][j]) {
                        case "-1":
                            System.out.printf("%2s", "  | |");
                            break;
                        case "O":
                            System.out.printf("%12s", UI.colorText("O", "white"));
                            if (j != 0)
                                availSeats[availSeatsCount++] = rowLetter + Integer.toString(column);
                            column++;
                            break;
                        case "X":
                            System.out.printf("%12s", UI.colorText("X", "red"));
                            column++;
                            break;
                        case " ":
                            // System.out.printf("%9s", "");
                            break;
                        default:
                            System.out.print("" + lo[i][j]);
                            rowLetter = lo[i][j];
                            column++;
                            break;
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // for (int z = 0;z<availSeatsCount;z++){
        // System.out.println(availSeats[z]);
        // }
        String choice = "test";
        System.out.println();
        if (recall.equals("")) {
            System.out.printf("%12s", UI.colorText("Would you like to make a reservation (Y/N)?\n", "yellow"));
            System.out.print("Choice: ");
            choice = sc.nextLine();
            choice = choice.toLowerCase();
            while (!(choice.equals("y") || choice.equals("n")) || (NumberChecker.isNumeric(choice))) {
                System.out.println("");
                System.out.printf("%s", UI.colorText("Invalid input. Please try again.\n", "red"));
                System.out.printf("%s", UI.colorText("Would you like to make a reservation (Y/N)?\n", "yellow"));
                System.out.print("Choice: ");
                choice = sc.nextLine();
                choice = choice.toLowerCase();
            }
            if (choice.equals("n")) {
                // bring back to moblima?
            } else {
                // call a booking function
                seatSelection(user, availSeats, availSeatsCount, movieDetails);
            }
        } else {
            // do nothing
        }
    }

    /**
     * this method lets the user select their seats for the screening, and rejects
     * if they are invalid inputs.
     * 
     * @param availSeats
     * @param availSeatsCount
     * @param movieDetails
     */
    public static void seatSelection(MovieGoer user, String[] availSeats, int availSeatsCount, String[] movieDetails) {
        // for (int k = 0; k<availSeats.length;k++){
        // System.out.print(availSeats[k]+ ", ");
        // }
        System.out.println();
        int flag = 0;
        String[] seats = new String[10]; // maximum number of seats they book is 10
        while (flag == 0) {
            flag = 1;
            // System.out.println("Enter the seats you would to book.\nExample: A1 or
            // A1,A2,A3,B2,B4");
            System.out.printf("%s", UI.colorText("Enter the seats you would to book.\n", "blue"));
            System.out.printf("%s", UI.colorText("Example: A1 or A1,A2,A3,B2,B4\n", "yellow"));
            System.out.print("Choice: ");
            String input = sc.nextLine();
            seats = input.split(",");
            if (seats.length > 10 || seats.length < 0) {
                System.out.println("Invalid number of seats chosen. Maximum is 10.");
                continue;
            } else {
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < availSeatsCount; j++) {
                        if (seats[i].equals(availSeats[j])) {
                            flag = 1;
                            break;
                        } else {
                            // the seat is not matching
                            flag = 0;
                        }
                    }
                    // the seat is not in the whole array of available seats
                    if (flag == 0) {
                        // System.out.println(seats[i] + " is not a valid seat!\nPlease re-enter.");
                        System.out.printf("%s", UI.colorText("Invalid reservation. Please re-enter.\n", "red"));
                        System.out.println();
                        break;
                    }
                }
            }
        }
        System.out.println();
        System.out
                .println(
                        "Reservation accepted.\nYour seats have been reserved. Please make booking to confirm purchase.");
        // pass cinemaLocpath into makebooking also
        user.setSelectedSeats(seats);
        // updateSeats(seats, "no", cinemaSeatPath);
        // System.out.println("confirm?");
        // String confirm = "";
        // confirm = sc.nextLine();
        // if(confirm.equals("yes"))
        // updateSeats(seats, "yes", cinemaSeatPath);
        // MovieGoer.makeBooking(movieDetails,seats);
        // updateSeats(seats);
    }

    /**
     * this method prints out the seating, with reserved seating , and the new
     * confirmed seating arrangement for the user to see.
     * 
     * @param seatsSelection
     * @param confirmbooking
     * @param cinemaSeatPath
     */
    public static void updateSeats(MovieGoer user, String confirmBooking) {
        String layout[][] = FileIOHandler.readTo2DArray(user.getCinemaSeatPath());
        boolean seatfound = false;
        String rowletter = "";
        int column = 0;
        String seat = "";
        for (String selected_seat : user.getSelectedSeats()) {
            seatfound = false;
            for (int i = 1; i < layout.length; i++) {
                rowletter = layout[i][0];
                column = 0;
                for (int j = 0; j < layout[i].length; j++) {
                    if (layout[i][j].equals("O")) {
                        column++;
                        seat = rowletter + Integer.toString(column);
                        if (selected_seat.equals(seat)) {
                            seatfound = true;
                            layout[i][j] = "X";
                        }
                    } else if (layout[i][j].equals("X")) {
                        column++;
                    }
                }
                if (seatfound == true) {
                    break; // breaks out and looks at next seat choice
                }
            }
        }
        System.out.println("Your seat selection:\n ");
        // String[][] lo = FileIOHandler.readTo2DArray(layoutPath);
        // print 2d array
        System.out.print(" ");
        for (int i = 0; i < layout[0].length; i++) {
            if (layout[1][i].equals("-1")) {
                System.out.printf("%-2s", "-");
            }
            System.out.printf("%3s", layout[0][i]);
        }
        System.out.println("");
        column = 0;
        String rowLetter = "";
        int selectSeatIndex = 0;
        for (int i = 1; i < layout.length; i++) {
            column = 0;
            for (int j = 0; j < layout[i].length; j++) {
                switch (layout[i][j]) {
                    case "-1":
                        System.out.printf("%2s", "  | |");
                        break;
                    case "O":
                        System.out.printf("%12s", UI.colorText("O", "white"));
                        // if (j != 0)
                        // availSeats[availSeatsCount++] = rowLetter + Integer.toString(column);
                        column++;
                        break;
                    case "X":
                        if (selectSeatIndex < user.getSelectedSeats().length) {
                            if ((rowLetter + Integer.toString(column))
                                    .equals(user.getSelectedSeats()[selectSeatIndex++])) {
                                if (confirmBooking.toLowerCase().equals("yes")) {
                                    System.out.printf("%12s", UI.colorText("X", "green"));
                                } else {
                                    System.out.printf("%12s", UI.colorText("X", "yellow"));
                                }
                            }
                        } else {
                            System.out.printf("%12s", UI.colorText("X", "red"));
                        }
                        column++;
                        break;
                    case " ":
                        // System.out.printf("%9s", "");
                        break;
                    default:
                        System.out.print("" + layout[i][j]);
                        rowLetter = layout[i][j];
                        column++;
                        break;
                }
            }
            System.out.println();
        }
        if (confirmBooking.toLowerCase().equals("yes")) {
            FileIOHandler.writeToCsv2D(user.getCinemaSeatPath(), layout);
            // printSeats(null, null, null, null, cinemaLocPath);
        } else {
            // do nothing
            // System.out.println("Your seat selection:\n ");
        }
        // to see if it worked.
    }
}
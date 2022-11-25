package control;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.*;
import control.TicketController;

/**
 * Handles the new booking of
 * movies by the user and subsequent updating of csvs
 * 
 * @author Noel
 * @version 1.0
 * @since 1.0
 */

public class Booking extends FileIOHandler {

    protected static String bookingHistPath = "./database/Bookings.csv";
    protected static String moviesDBDir = "./database/Movies";
    private static BookingUI UI = new BookingUI();

    protected static Scanner sc = new Scanner(System.in);

    /**
     * Writes the new booking made by the user to Bookings.csv where all movie
     * booking detalis are stored
     * 
     * @param MovieGoer
     */
    public static void updateBooking(MovieGoer MovieGoer) {

        String booked_movie = MovieGoer.getMovieChoice();

        String booked_movie_lower = booked_movie.toLowerCase();

        // Adding new booking to the array and Booking.csv

        String[][] retrieved_array = readTo2DArray(bookingHistPath); // reads the arrays from booking.csv
        // System.out.println(Arrays.deepToString(new_array))

        String[][] edited_array = insertNewBooking(retrieved_array, retrieved_array.length, MovieGoer);
        writeToCsv2D(bookingHistPath, edited_array); // writes the new array to csv

        // Updates the sales of the movie that was booked
        String dir = moviesDBDir + "/" + booked_movie + "/details.csv";
        // System.out.println("Debug : path directory :" + dir);
        if (MovieController.searchMovie(booked_movie_lower)) {
            String oldsales = MovieController.getMovieSales(booked_movie);
            double oldsales_double = Double.valueOf(oldsales);
            double user_totalprice = MovieGoer.getTotalPrice();

            double updated_sales = oldsales_double + user_totalprice;
            String string_updated_sales = String.valueOf(updated_sales);
            String[][] details = FileIOHandler.readTo2DArray(dir);
            details[6][1] = string_updated_sales;
            FileIOHandler.writeToCsv2D(dir, details);
        }

        else {
            System.out.println("Error! Movie not found!");
        }
    }

    /**
     * checkHist allows the user to check any past booking history
     * 
     */

    public static void checkHist() {
        String confirmEmail;
        String email;

        do {
            System.out.println();
            System.out.println("Enter email: ");
            email = sc.next();
            email = email.toLowerCase();

            System.out.println("Your email is: " + email);
            System.out.println("Confirm email? [y/n]");
            confirmEmail = sc.next();

        } while (confirmEmail.equals("n"));

        try {
            BufferedReader brStream = FileIOHandler.openFile(bookingHistPath);
            String inputLine;
            int count = 0;
            System.out.println("------------------------------");
            while ((inputLine = brStream.readLine()) != null) {
                String[] values = inputLine.split(",");
                if (email.equals(values[0].toLowerCase())) {

                    if (count == 0) {
                        System.out.println("\nBookings for " + email);
                        System.out.println();
                    }
                    count++;

                    // System.out.println("\n" + email + " matched with " + values[0]);
                    System.out.println();
                    System.out.println("Ticket ID: " + values[1]);
                    System.out.println("Movie: " + values[5]);
                    char val1 = values[1].charAt(0);
                    char val2 = values[1].charAt(1);
                    char val3 = values[1].charAt(2);
                    switch (val1) {
                        case ('C'):
                            System.out.println(("Cineplex: Cathay"));
                            break;
                        case ('G'):
                            System.out.println(("Cineplex: Golden Village"));
                            break;
                        case ('S'):
                            System.out.println(("Cineplex: Shaw"));
                            break;
                    }
                    switch (val2) {
                        case 'R':

                            System.out.println(("Suite: Regular"));
                            break;
                        case 'P':

                            System.out.println(("Suite: Premium"));
                            break;
                        case 'G':

                            System.out.println(("Suite: Gold"));
                            break;
                    }
                    System.out.println("Location: Hall " + val3);

                    System.out.println("Date of Movie: " + values[6]);

                    System.out.println("Timing of Movie: " + values[7]);

                    System.out.println("Total Ticket Price " + values[8]);
                    // how to get timing from ur TID

                    System.out.print("Seats booked: ");
                    for (int i = 9; i < values.length; i++) {
                        System.out.print(values[i]);
                        if (values.length - i - 1 > 0) {
                            System.out.print(",");
                        }
                    }
                    System.out.print(".");
                    System.out.println();
                }

            }
            System.out.println();
            System.out.println(count + " bookings found for " + email);
            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            brStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * insertNewBooking takes the new booking and appends it into an array of past
     * bookings, which were extracted from Bookings.csv
     * such that Bookings.csv can now be updated
     * 
     * @param m
     * @param r
     * @param MovieGoer
     * @return String[][]
     */

    public static String[][] insertNewBooking(String[][] m, int r, MovieGoer MovieGoer) {
        String[][] out = new String[m.length + 1][];

        String name = MovieGoer.getName();
        int Phone = MovieGoer.getPhone();
        String email = MovieGoer.getEmail();
        String movie = MovieGoer.getMovieChoice();
        String TID = MovieGoer.getTID();
        double price = MovieGoer.getTotalPrice();
        int age = MovieGoer.getAge();
        String screeningTime = MovieGoer.getMovieTiming();
        String dayOfMovie = MovieGoer.getExactDay();
        double totalPrice = MovieGoer.getTotalPrice();
        String string_totalPrice = String.valueOf(totalPrice);
        String string_age = String.valueOf(age);
        String string_phone = String.valueOf(Phone);
        String string_price = String.valueOf(price);
        // String stringprice_formatted = String.format("%.2d",string_price);

        String[] booked_seats = MovieGoer.getSelectedSeats();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < booked_seats.length; i++) {
            sb.append(booked_seats[i]);
            if (i != booked_seats.length - 1) {
                sb.append(",");
            }

        }
        String booked_seats_formatted = sb.toString();

        String[] booking_data = { email, TID, name, string_phone, string_age, movie, dayOfMovie, screeningTime,
                string_totalPrice, booked_seats_formatted };

        for (int i = 0; i < r; i++) {
            out[i] = m[i];
        }
        out[r] = booking_data;
        for (int i = r + 1; i < out.length; i++) {
            out[i] = m[i - 1];
        }
        return out;
    }

    /**
     * Function allows the user to check the booking details
     * 
     * @param user
     */
    public static MovieGoer makeBooking(MovieGoer user) {
        // String[] movieDetails =
        // [movie,cinema,class,day,timeOfDay,timing,2D/3D,blockbuster,date];
        if (user.getSelectedSeats() == null) {
            System.out.println("You do not have any seats in your cart. Please select seats.");
        } else {
            String time, tid, code;
            double ticketPrice = 0;
            int purchase = 3;
            String correctInput;

            UI.printHeader("MAKE BOOKING");
            System.out.print("What is your age?: ");

            boolean acceptInput = false;
            while (!acceptInput) {
                correctInput = sc.next();
                if (correctInput.equals("") || !(NumberChecker.isNumeric(correctInput))) { // check if empty or not
                                                                                           // number
                    System.out.println("Invalid input.");
                    System.out.println("Please re-enter your age. ");
                    continue;
                } else {
                    int age = Integer.parseInt(correctInput);
                    if (age < 100 || age > 10) {
                        acceptInput = true;
                        user.setAge(age);
                        break;
                    }
                    System.out.println("Invalid input.");
                    System.out.println("Please re-enter your age. ");
                    continue;
                }
            }

            String[] seats = user.getSelectedSeats();
            UI.printHeader("BOOKING CONFIRMATION");
            System.out.printf("Movie: %s, in %s.\n", user.getMovieChoice(), user.getDimension());
            // System.out.println("Your booking is for the" + (user.getBlockBuster() ? "
            // BlockBuster" : "") + " movie,");
            // System.out.println(user.getMovieChoice() + ", in " + user.getDimension() +
            // ".");
            System.out
                    .println("At " + user.getCineplexChoice() + ", on " + user.getExactDay() + "," + user.getDay()
                            + " at "
                            + user.getMovieTiming() + "hrs.");
            System.out.println("Cineplex: " + user.getCineplexChoice());
            // System.out.printf("Date: %s, at %s\n", user.getExactDay(), user.getDay());

            System.out.println("Seat(s):");
            for (int i = 0; i < seats.length; i++) {
                System.out.print(seats[i]);
                if (i == seats.length - 1) {
                    System.out.print(".");
                } else {
                    System.out.print(",");
                }
            }

            System.out.println();

            Seats.updateSeats(user, "no");

            System.out.println();
            // getTicketPrice(2D/3D, blockbuster(?), day of the week, afternoon/evening,
            // age)
            // ticketPrice = Ticket.getTicketPrice(user.getDimension(),
            // user.getBlockBuster(), user.getDay(),
            // user.getTimeOfDay(),
            // user.getAge(), user.getCinemaClass());
            String[] HOLIDAYS = {
            };

            // format of the date 15/11/2022
            for (int i = 0; i < HOLIDAYS.length; i++) {
                if (HOLIDAYS[i].equals(user.getExactDay())) {
                    // set boolean
                    user.setHoliday(true);
                    break;
                } else {
                    user.setHoliday(false);
                }
            }
            int dayOfWeek = 0;
            if ((user.getDay().equals("Saturday")) || user.getDay().equals("Sunday")) {
                dayOfWeek = 6; // weekend regardless
            }

            ticketPrice = TicketController.tickPrice1(user.getAge(), user.getCinemaClass(), user.getTimeOfDay(),
                    dayOfWeek);
            ticketPrice = ticketPrice
                    + TicketController.tickPrice2(user.getBlockBuster(), user.getDimension(), user.getHoliday());
            // we assuming all the tickets are the same? regardless of age? is it
            // YES

            if (ticketPrice > 0) {
                // System.out.println("Ticket Price is : " + ticketPrice * seats.length);
                System.out.printf("Total Ticket Price: %.2f\n", ticketPrice * seats.length);
                System.out.print("""
                        1. Confirm Booking
                        2. Cancel Booking
                        """);

                System.out.print("Choice: ");
                // purchase = sc.nextInt();
                // while ((purchase != 1) || (purchase != 2)) {
                // System.out.println("Invalid input.");
                // System.out.print("Please re-enter choice: ");
                // purchase = sc.nextInt();
                // }
                String inputPurchase;
                inputPurchase = sc.next();
                while (inputPurchase.equals("") || !(NumberChecker.isNumeric(inputPurchase))) {
                    // System.out.println();
                    if (!(inputPurchase.equals("1")) || !(inputPurchase.equals("2"))) {
                        System.out.printf("%s", UI.colorText("Invalid input.\n", "red"));
                        System.out.printf("%s", UI.colorText("Please re-enter choice: ", "yellow"));
                        inputPurchase = sc.next();
                    }
                }
                if (inputPurchase.equals("1")) {
                    purchase = 1;
                } else {
                    purchase = 2;
                }

                if (purchase == 1) {
                    user.setTotalPrice(ticketPrice * seats.length);

                    System.out.printf("%s", UI.colorText("Please enter your name: ", "yellow"));
                    String nameInput = sc.next();
                    while (nameInput.equals("") || (NumberChecker.isNumeric(nameInput))) {
                        // System.out.println();
                        System.out.printf("%s", UI.colorText("Invalid input. Please try again.\n", "red"));
                        System.out.printf("%s", UI.colorText("Please re-enter your name: ", "yellow"));
                        nameInput = sc.next();
                    }
                    user.setName(nameInput);

                    System.out.printf("%s", UI.colorText("\nPlease enter your email: ", "yellow"));
                    Boolean match = false;
                    String emailInput = sc.next();
                    while (!match) {
                        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(emailInput);
                        if (matcher.matches()) {
                            match = true;
                        } else {
                            System.out.printf("%s", UI.colorText("Invalid input. Please try again.\n", "red"));
                            System.out.printf("%s", UI.colorText("Please re-enter your Email:", "yellow"));
                            emailInput = sc.next();
                        }
                    }
                    user.setEmail(emailInput);

                    match = false;

                    System.out.printf("%s", UI.colorText("\nPlease enter your phone number: ", "yellow"));
                    String phoneNumber = sc.next();
                    while (!match) {
                        String regex = "(8|9)[0-9]{0,7}";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(phoneNumber);
                        if (matcher.matches() && phoneNumber.length() == 8
                                && (phoneNumber.charAt(0) == '9' || phoneNumber.charAt(0) == '8')) {
                            match = true;
                        } else {
                            System.out.printf("%s", UI.colorText("Invalid option. Please try again.\n", "red"));
                            System.out.printf("%s", UI.colorText("Enter your phone number:", "yellow"));
                            phoneNumber = sc.next();
                        }
                    }
                    user.setPhone(Integer.parseInt(phoneNumber));

                    time = user.getTiming();
                    String choice = user.getCineplexChoice();
                    String[] a = choice.split("");
                    choice = user.getCinemaClass();
                    String[] b = choice.split("");

                    code = a[0] + b[0] + user.getHall().toString().charAt(4);
                    tid = code + time;
                    user.setTID(tid);
                    Seats.updateSeats(user, "yes");
                    Booking.updateBooking(user);
                    user = new MovieGoer(); // reset

                    // Seats.printSeats(user.getMovieTiming(), user.getCinemaChoice(),
                }

            } else {
                System.out.println("Booking cancelled. Going back to main menu");
                Seats.updateSeats(user, "no");
                user = new MovieGoer(); // reset
            }
        }
        return (user);
    }
}

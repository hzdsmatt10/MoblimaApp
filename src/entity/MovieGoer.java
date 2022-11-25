package entity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The entity class of the person that using this application
 * 
 * @author Kai Boon
 * @version 1.0
 * @since 1.0
 */

public class MovieGoer extends Person {

    protected int mobileNo, age;
    protected String name, email, movieChoice, cineplexChoice,
            classChoice, date, timeOfDay, movieTiming, dimension,
            exactDay, tid, hallNo, day;
    // protected String[] movieDetails;
    protected String[] selectedSeats;
    protected boolean block, holiday;
    protected double ticketPrice;
    protected static Scanner sc = new Scanner(System.in);
    protected String cinemaSeatPath;
    protected String cinemaLocPath;

    // Constructor
    public MovieGoer() {

    }

    /**
     * Gets the age of the user
     * 
     * @return int
     */
    // print Rating function
    // add review or remove a review

    public String getCinemaSeatPath() {
        return this.cinemaSeatPath;
    }

    public void setCinemaSeatPath(String path) {
        this.cinemaSeatPath = path;
    }

    public String getCinemaLocPath() {
        return this.cinemaLocPath;
    }

    public void setCinemaLocPath(String path) {
        this.cinemaLocPath = path;
    }

    public int getAge() {
        return this.age;
    }

    /**
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the name of the user
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the time of day of the
     * movie that the user is booking
     * Afternoon/Evening
     * 
     * @return String
     */
    public String getTimeOfDay() {
        return this.timeOfDay;
    }

    /**
     * @param timeOfDay
     */
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    /**
     * Gets the email address of the user
     * 
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            this.email = email;
        } else {
            System.out.println("Invalid email or invalid email format");
        }
    }

    /**
     * Gets the phone number of the user
     * 
     * @return int
     */
    public int getPhone() {
        return this.mobileNo;
    }

    /**
     * @param phoneNo
     */
    public void setPhone(int phoneNo) {
        this.mobileNo = phoneNo;
    }

    /**
     * 
     * @param movieChoice
     */
    public void setMovieChoice(String movieChoice) {
        this.movieChoice = movieChoice;
    }

    /**
     * Gets the title of the movie that the user booked
     * 
     * @return String
     */
    public String getMovieChoice() {
        return movieChoice;
    }

    /**
     * 
     * 
     * @param classChoice
     */
    public void setCinemaClass(String classChoice) {
        this.classChoice = classChoice;
    }

    /**
     * Gets the cinema class that the user has chosen
     * 
     * @return String
     */
    public String getCinemaClass() {
        return this.classChoice;
    }

    /**
     * @param cinemaChoice
     */
    public void setCineplexChoice(String cineplexChoice) {
        this.cineplexChoice = cineplexChoice;
    }

    /**
     * Gets the cineplex that the user chose
     * 
     * @return String
     */
    public String getCineplexChoice() {
        return this.cineplexChoice;
    }

    /**
     * @param block
     */
    public void setBlockBuster(boolean block) {
        this.block = block;
    }

    /**
     * Check whether it is a blockbuster movie or not
     * 
     * @return boolean
     */
    public boolean getBlockBuster() {
        return this.block;
    }

    /**
     * @param day
     */
    public void setDay(String day) {
        this.day = day;

    }

    /**
     * Gets the day of the week
     * 
     * @return String
     */
    public String getDay() {
        return this.day;
    }

    /**
     * 
     * @param movieTiming
     */
    public void setMovieTiming(String movieTiming) {
        this.movieTiming = movieTiming;

    }

    /**
     * Gets the movie screening timing
     * 
     * @return String
     */
    public String getMovieTiming() {
        return this.movieTiming;
    }

    /**
     * @param dimension
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;

    }

    /**
     * Gets the movie's animation choice 2D or 3D
     * 
     * @return String
     */
    public String getDimension() {
        return this.dimension;
    }

    /**
     * @param exactDay
     */
    public void setExactDay(String exactDay) {
        this.exactDay = exactDay;

    }

    /**
     * Gets the day of the movie that the user intends to watch
     * 
     * @return String
     */
    public String getExactDay() {
        return this.exactDay;
    }

    /**
     * @param tid
     */
    public void setTID(String tid) {
        this.tid = tid;

    }

    /**
     * Get the transaction ID of the purchase
     * 
     * @return String
     */
    public String getTID() {
        return this.tid;
    }

    /**
     * @param ticketPrice
     */
    public void setTotalPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;

    }

    /**
     * Gets the total ticket price of the selected seats
     * 
     * @return double
     */
    public double getTotalPrice() {
        return this.ticketPrice;
    }

    /**
     * @param hallNo
     */
    public void setHall(String hallNo) {
        this.hallNo = hallNo;

    }

    /**
     * Gets the cinema hall number
     * 
     * @return String
     */
    public String getHall() {
        return this.hallNo;
    }

    /**
     * @param selectedSeats
     */
    public void setSelectedSeats(String[] selectedSeats) {
        this.selectedSeats = selectedSeats;

    }

    /**
     * Gets the seat(s) that the user chose for the movie
     * 
     * @return String[]
     */
    public String[] getSelectedSeats() {
        return this.selectedSeats;
    }

    public boolean getHoliday() {
        return this.holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

    /**
     * Gets the timing when the user confirms the
     * booking for the tickets
     * 
     * @return String
     */
    public static String getTiming() {
        String a = java.time.LocalDateTime.now().toString();
        String[] date = a.split("-");
        String[] time = date[2].split(":");
        String[] hour = time[0].split("T");
        return date[0] + date[1] + hour[0] + hour[1] + time[1];
    }

    /**
     * @param cart
     */

}
package control;

/**
 * Represents the entity class that creates an instance of a Showtimes object.
 * Aids in the creation of a showtimes object, and stores the showtimes'
 * attributes.
 * 
 * @author Matthias
 * @version 1.0
 * @since 1.0
 */
public class Showtimes {
    // Properties
    private String location;
    private String suite;
    private String movieTitle;
    private String hall;
    private String day;
    private String timing;

    // Constructor
    /**
     * Constructor for the Showtimes class, creates an instance of a Showtimes
     * object.
     * 
     * @param cineplex
     * @param suite
     * @param movieTitle
     * @param hall
     * @param day
     * @param timing
     */
    public Showtimes(String location, String suite, String movieTitle, String hall, String day, String timing) {
        this.movieTitle = movieTitle;
        this.location = location;
        this.suite = suite;
        this.day = day;
        this.timing = timing;
        this.hall = hall;
    }

    // getters and setters
    /**
     * Setter for the location attribute of the Showtimes object.
     * 
     * @param cineplex
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for the location attribute of the Showtimes object.
     * 
     * @return String
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for the suite attribute of the Showtimes object.
     * 
     * @param suite
     */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /**
     * Getter for the suite attribute of the Showtimes object.
     * 
     * @return String
     */
    public String getSuite() {
        return suite;
    }

    /**
     * Getter for the movieTitle attribute of the Showtimes object.
     * 
     * @return String
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Setter for the hall attribute of the Showtimes object.
     * 
     * @param hall
     */
    public void setHall(String hall) {
        this.hall = hall;
    }

    /**
     * Getter for the hall attribute of the Showtimes object.
     * 
     * @return String
     */
    public String getHall() {
        return hall;
    }

    /**
     * Setter for the day attribute of the Showtimes object.
     * 
     * @param day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Getter for the day attribute of the Showtimes object.
     * 
     * @return String
     */
    public String getDay() {
        return day;
    }

    /**
     * Setter for the timing attribute of the Showtimes object.
     * 
     * @param timing
     */
    public void setTiming(String timing) {
        this.timing = timing;
    }

    /**
     * Getter for the timing attribute of the Showtimes object.
     * 
     * @return String
     */
    public String getTiming() {
        return timing;
    }

    /**
     * Getter for the blockbuster attribute of the movie.
     * 
     * @return String
     */
    public String getBlockBuster() {
        return MovieController.getMovieBlockbuster(getMovieTitle());
    }

    /**
     * Getter for the type attribute of the movie.
     * 
     * @return String
     */
    public String getType() {
        return MovieController.getMovieType(getMovieTitle());
    }

    /**
     * Gets the date of the movie based on the day attribute of the Showtimes object
     * 
     * @return String
     */
    public String getDate() {
        if (getDay() == "1~Monday") {
            return "14/11/2022";
        } else if (getDay() == "2~Tuesday") {
            return "15/11/2022";
        } else if (getDay() == "3~Wednesday") {
            return "16/11/2022";
        } else if (getDay() == "4~Thursday") {
            return "17/11/2022";
        } else if (getDay() == "5~Friday") {
            return "18/11/2022";
        } else if (getDay() == "6~Saturday") {
            return "19/11/2022";
        } else if (getDay() == "7~Sunday") {
            return "20/11/2022";
        } else {
            return "Error";
        }
    }

    /**
     * Gets the layout of the cinema depending on the suite attribute of the
     * Showtimes object
     * 
     * @return String
     */
    public String getLayout() {
        if (getSuite() == "Regular") {
            return """
                    1,2,3,4,5,6,-,7,8,9,1O,11,12,13,14,15,16,17,18,19,2O,-,21,22,23,24,25,26
                    A,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                    B,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                    C,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                    D,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                    E,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                    F,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                    G,O,O,O,O,O,O,-1,O,O,O,O,O,O,O,O,O,O,O,O,O,O,-1,O,O,O,O,O,O
                                """;
        } else if (getSuite() == "Platinum") {
            return """
                    1,2,3,4,-,5,6,7,8,-,9,10,11,12
                    A,O,O,O,O,-1,O,O,O,O,-1,O,O,O,O
                    B,O,O,O,O,-1,O,O,O,O,-1,O,O,O,O
                    C,O,O,O,O,-1,O,O,O,O,-1,O,O,O,O
                    D,O,O,O,O,-1,O,O,O,O,-1,O,O,O,O
                    E,O,O,O,O,-1,O,O,O,O,-1,O,O,O,O
                                """;
        } else if (getSuite() == "Gold") {
            return """
                    1,2,-,3,4,-,5,6
                    A,O,O,-1,O,O,-1,O,O
                    B,O,O,-1,O,O,-1,O,O
                    C,O,O,-1,O,O,-1,O,O
                    D,O,O,-1,O,O,-1,O,O
                    E,O,O,-1,O,O,-1,O,O
                                """;
        } else
            return "error";
    }
}

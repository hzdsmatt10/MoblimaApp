package entity;

import java.util.ArrayList;

/**
 * Represents the entity class that creates an instance of a Movie object.
 * Aids in the creation of a movie object, and stores the movie's attributes.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class Movie {

    // Properties
    private String movieTitle;
    private int movieRuntime;
    private String movieStatus;
    private String movieAgeRating;
    private String movieDirector;
    private ArrayList<String> movieCast;
    private String movieSynopsis;
    private String movieSales;
    private String movieType;
    private boolean movieBlockBuster;

    // Constructor
    /**
     * Constructor for the Movie class, creates an instance of a Movie object.
     * 
     * @param movieTitle
     * @param movieRuntime
     * @param movieStatus
     * @param movieAgeRating
     * @param movieDirector
     * @param movieCast
     * @param movieSynopsis
     * @param movieSales
     * @param movieType
     * @param movieBlockBuster
     */
    public Movie(String movieTitle, int movieRuntime, String movieStatus, String movieAgeRating,
            String movieDirector,
            ArrayList<String> movieCast, String movieSynopsis, String movieSales, String movieType,
            boolean movieBlockBuster) {
        this.movieTitle = movieTitle;
        this.movieRuntime = movieRuntime;
        this.movieStatus = movieStatus;
        this.movieAgeRating = movieAgeRating;
        this.movieDirector = movieDirector;
        this.movieCast = movieCast;
        this.movieSynopsis = movieSynopsis;
        this.movieSales = movieSales;
        this.movieType = movieType;
        this.movieBlockBuster = movieBlockBuster;
    }

    /**
     * Getter for the movieTitle attribute of the Movie object.
     * 
     * @return String
     */
    // Getters and Setters
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Setter for the movieTitle attribute of the Movie object.
     * 
     * @param movieTitle
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * Getter for the movieRuntime attribute of the Movie object.
     * 
     * @return int
     */
    public int getMovieRunTime() {
        return movieRuntime;
    }

    /**
     * Setter for the movieRuntime attribute of the Movie object.
     * 
     * @param movieRuntime
     */
    public void setMovieRunTime(int movieRuntime) {
        this.movieRuntime = movieRuntime;
    }

    /**
     * Getter for the movieStatus attribute of the Movie object.
     * 
     * @return String
     */
    public String getMovieStatus() {
        return movieStatus;
    }

    /**
     * Setter for the movieStatus attribute of the Movie object.
     * 
     * @param movieStatus
     */
    public void setMovieStatus(String movieStatus) {
        this.movieStatus = movieStatus;
    }

    /**
     * Getter for the movieAgeRating attribute of the Movie object.
     * 
     * @return String
     */
    public String getMovieAgeRating() {
        return movieAgeRating;
    }

    /**
     * Setter for the movieAgeRating attribute of the Movie object.
     * 
     * @param movieAgeRating
     */
    public void setMovieAgeRating(String movieAgeRating) {
        this.movieAgeRating = movieAgeRating;
    }

    /**
     * Getter for the movieDirector attribute of the Movie object.
     * 
     * @return String
     */
    public String getMovieDirector() {
        return movieDirector;
    }

    /**
     * Setter for the movieDirector attribute of the Movie object.
     * 
     * @param movieDirector
     */
    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    /**
     * Getter for the movieCast attribute of the Movie object.
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> getMovieCast() {
        return movieCast;
    }

    /**
     * Setter for the movieCast attribute of the Movie object.
     * 
     * @param movieCast
     */
    public void setMovieCast(ArrayList<String> movieCast) {
        this.movieCast = movieCast;
    }

    /**
     * Getter for the movieSynopsis attribute of the Movie object.
     * 
     * @return String
     */
    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    /**
     * Setter for the movieSynopsis attribute of the Movie object.
     * 
     * @param movieSynopsis
     */
    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }

    /**
     * Getter for the movieSales attribute of the Movie object.
     * 
     * @return String
     */
    public String getMovieSales() {
        return movieSales;
    }

    /**
     * Setter for the movieSales attribute of the Movie object.
     * 
     * @param movieSales
     */
    public void setMovieSales(String movieSales) {
        this.movieSales = movieSales;
    }

    /**
     * Getter for the movieType attribute of the Movie object.
     * 
     * @return String
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     * Setter for the movieType attribute of the Movie object.
     * 
     * @param movieType
     */
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    /**
     * Getter for the movieBlockBuster attribute of the Movie object.
     * 
     * @return boolean
     */
    public boolean getMovieBlockbuster() {
        return movieBlockBuster;
    }

    /**
     * Setter for the movieBlockBuster attribute of the Movie object.
     * 
     * @param movieBlockbuster
     */
    public void setMovieBlockbuster(boolean movieBlockbuster) {
        this.movieBlockBuster = movieBlockbuster;
    }

}
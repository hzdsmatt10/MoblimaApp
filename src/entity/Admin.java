package entity;

/**
 * Represents the entity class that creates an instance of an Admin object.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
public class Admin {
    private String username;
    private String password;
    private Boolean isVerified;

    /**
     * Constructor for the Admin class, creates an instance of an Admin object.
     * 
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.isVerified = false;
    }

    /**
     * Getter for the username of the Admin object.
     * 
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of the Admin object.
     * 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for the isVerified attribute of the Admin object.
     * 
     * @param verified
     */
    public void setVerified(Boolean verified) {
        // set object to verified
        this.isVerified = verified;
    }

    /**
     * Getter for the isVerified attribute of the Admin object.
     * 
     * @return Boolean
     */
    public Boolean getVerified() {
        return isVerified;
    }
}

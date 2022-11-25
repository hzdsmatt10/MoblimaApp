package control;

/**
 * Interface class containing the attributes const for the colors used in the
 * application, and the methods to print the header and banner.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */

interface UIControllerInterface {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public abstract String printHeader(String header);

    public abstract String printBanner(int length);

    public abstract String printBody(String uiContent);

}

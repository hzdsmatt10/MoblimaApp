package control;

/**
 * Represents the class which checks whether the strings can be
 * converted to numbers(doubles or integers)
 * 
 * @author Matthias
 * @version 1.0
 * @since 1.0
 */
public class NumberChecker {
    /**
     * Validates whether the input string can be converted
     * into an integer
     * 
     * @param string
     * @return boolean
     */
    public static boolean isNumeric(String string) {
        int intValue;

        // System.out.println(String.format("Parsing string: \"%s\"", string));

        if (string == null || string.equals("")) {
            // System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            // System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    /**
     * Validates whether the input string can be converted
     * into a double
     * 
     * @param string
     * @return boolean
     */
    public static boolean isDouble(String string) {
        double dubValue;

        // System.out.println(String.format("Parsing string: \"%s\"", string));

        if (string == null || string.equals("")) {
            // System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            dubValue = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            // System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

}

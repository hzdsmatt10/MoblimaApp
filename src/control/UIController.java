package control;

/**
 * Represents the abstract class that handles the UI of the application.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */
abstract public class UIController implements UIControllerInterface {
    private String headerStartBanner = "\n-=-= ";
    private String headerEndBanner = " =-=-";

    /**
     * Appends the decoration of the header to the header text, and tints it blue.
     * 
     * @param header
     * @return String
     */
    public String printHeader(String header) {
        String appendedHeader = ANSI_BLUE + headerStartBanner + header + headerEndBanner + ANSI_RESET;
        System.out.println(appendedHeader);
        return appendedHeader;
    }

    /**
     * Prints the banner/seperateors for the body of the UI.
     * 
     * @param length
     * @return String
     */
    public String printBanner(int length) {
        boolean toggle = false;
        String banner = ANSI_BLUE;
        for (int i = 0; i < length; i++) {
            if (toggle) {
                banner += "=";
                toggle = !toggle;
            } else {
                banner += "-";
                toggle = !toggle;
            }
        }
        banner += ANSI_RESET;
        System.out.println(banner);
        return banner;
    }

    /**
     * Helper method to color/tint text. Returns the colored text.
     * 
     * @param body
     * @param color
     * @return String
     */
    public String colorText(String body, String color) {
        String coloredText = "";

        switch (color) {
            case "red":
                coloredText = ANSI_RED + body + ANSI_RESET;
                break;
            case "green":
                coloredText = ANSI_GREEN + body + ANSI_RESET;
                break;
            case "yellow":
                coloredText = ANSI_YELLOW + body + ANSI_RESET;
                break;
            case "blue":
                coloredText = ANSI_BLUE + body + ANSI_RESET;
                break;
            case "purple":
                coloredText = ANSI_PURPLE + body + ANSI_RESET;
                break;
            case "cyan":
                coloredText = ANSI_CYAN + body + ANSI_RESET;
                break;
            case "white":
                coloredText = ANSI_WHITE + body + ANSI_RESET;
                break;
            default:
                coloredText = ANSI_RESET + body + ANSI_RESET;
                break;
        }

        return coloredText;
    }

    // abstract method to print header
    public String printBody(String uiContent) {
        System.out.println(uiContent);
        return uiContent;
    }

}

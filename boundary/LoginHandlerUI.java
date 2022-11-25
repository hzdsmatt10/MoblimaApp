package boundary;

import java.util.Scanner;
// uses Admin.java
// uses LoginHandler.java

import control.LoginValidationController;
import control.UIController;
import entity.Admin;

/**
 * Represents the class of UI that handles admin login.
 * 
 * @author Owen
 * @version 1.0
 * @since 1.0
 */

public class LoginHandlerUI extends UIController {
    static Scanner sc = new Scanner(System.in);
    static LoginHandlerUI UI = new LoginHandlerUI();

    /**
     * Handles admin login, and checks if the admin is valid.
     */

    public static void logIn() {
        Admin admin;
        UI.printHeader("ADMIN LOGIN");

        do {
            System.out.print("Username: ");
            String username = sc.nextLine();

            // condition to exit/stop the log in process
            if (username.equals("-")) {
                System.out.println("Returning to main menu...");
                return;
            }

            System.out.print("Password: ");
            String password = sc.nextLine();

            // create a new admin object to handle the login
            admin = new Admin(username, password);
            // validate the login using LoginValidation class
            admin.setVerified(LoginValidationController.validateLogin(admin));

            // if login is verified, go to AdminModule
            if (admin.getVerified()) {
                System.out.println(UI.colorText("\nVerified! Welcome, " + admin.getUsername(), "green"));
                // once verified, go to admin page, passing in the verified admin object
                AdminModuleUI.adminMenu(admin);
            } else {
                System.out.println(UI.colorText("Invalid username or password. Please try again.", "red"));
                System.out.println("Press '-' to go back to main menu");
            }

        } while (!admin.getVerified());
    }
}

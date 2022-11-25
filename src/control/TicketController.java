package control;

/**
 * Represents the class of TicketController, which handles the prices of
 * tickets.
 * 
 * @author Kai Boon
 * @version 1.0
 * @since 1.0
 */
public class TicketController {

    /**
     * Calculates prices of tickets based on age, movie class, time of day and day
     * of week. Returns the price of the ticket.
     * 
     * @param age
     * @param mClass
     * @param timeOfDay
     * @param DayOfWeek
     * @return double
     */
    public static double tickPrice1(int age, String mClass, String timeOfDay, int DayOfWeek) {

        double price = 0;
        String ageStatus = "";
        if (age <= 22)
            ageStatus = "STUDENT";
        else if (age >= 55)
            ageStatus = "SENIOR";
        else
            ageStatus = "ADULT";

        if (mClass.equals("Gold")) {
            price = price + 30;
            if (DayOfWeek <= 5) {
                price = price + 4;
                if (timeOfDay.equals("Afternoon")) {
                    price = statusPrice(ageStatus, price);
                } else { // evening
                    price = price + 2;
                    price = statusPrice(ageStatus, price);
                }

            } else {
                price = price + 8;
                if (timeOfDay.equals("Afternoon")) {
                    price = statusPrice(ageStatus, price);
                } else { // evening
                    price = price + 2;
                    price = statusPrice(ageStatus, price);
                }
            }

        } else if (mClass.equals("Platinum")) {
            price = price + 20;
            if (DayOfWeek <= 5) {
                price = price + 3;
                if (timeOfDay.equals("Afternoon")) {
                    price = statusPrice(ageStatus, price);
                } else { // evening
                    price = price + 2;
                    price = statusPrice(ageStatus, price);
                }
            } else {
                price = price + 6;
                if (timeOfDay.equals("Afternoon")) {
                    price = statusPrice(ageStatus, price);
                } else { // evening
                    price = price + 2;
                    price = statusPrice(ageStatus, price);
                }
            }

        } else { // regular
            price = price + 7;
            if (DayOfWeek <= 5) {
                price = price + 1;
                if (timeOfDay.equals("Afternoon")) {
                    price = statusPrice(ageStatus, price);
                } else { // evening
                    price = price + 2;
                    price = statusPrice(ageStatus, price);
                }

            } else {
                price = price + 4;
                if (timeOfDay.equals("Afternoon")) {
                    price = statusPrice(ageStatus, price);
                } else { // evening
                    price = price + 2;
                    price = statusPrice(ageStatus, price);
                }
            }
        }
        return price;
    }

    /**
     * Calculates prices of tickets based on the person's age category. Returns the
     * price
     * 
     * @param status
     * @param price
     * @return double
     */
    public static double statusPrice(String status, double price) {
        switch (status) {
            case "STUDENT":
                price = price + 2;
                break;
            case "SENIOR":
                break;
            case "ADULT":
                price = price + 4;
                break;
        }
        return price;
    }

    /**
     * Calculates prices of tickets based on the movie's blockbuster type, dimension
     * and if it is a holiday. Returns the price.
     * 
     * @param block
     * @param dim
     * @param holi
     * @return double
     */
    public static double tickPrice2(boolean block, String dim, boolean holi) {
        double price = 0;
        if (block == true) {
            price = price + 1;
            if (dim.equals("3D")) {
                price = price + 5;
            } else {

            }
        } else { // not blockbuster
            if (dim.equals("3D")) {
                price = price + 5;
            } else {

            }
        }

        if (holi == true) {
            price = price + 5;
        }
        return price;
    }
}

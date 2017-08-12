//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Helper {

    private static Scanner input = new Scanner(System.in);

    static int userSelection() {
        try {
            System.out.print("\nEnter your option:  ");
            int userChoice = input.nextInt();
            input.nextLine();

            if (userChoice > 6) {
                System.out.println("\nYou did not enter valid range from the menu.");
                System.out.println("Please select again with an integer shown in the menu...");
                input.nextLine();
                return userSelection(); // recursively run again until input correct
            } else {
                return userChoice;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nYou did not enter an integer from the menu.");
            System.out.println("Please select again with an integer (i.e. 1)...");
            input.nextLine(); // discard previous input
            return userSelection();
        }
    }

    static int confirm(String message) {

        System.out.print("\n" + message);

        String confirm = input.nextLine();

        if (confirm.isEmpty() || confirm.equalsIgnoreCase("y")) {
            return 1;
        } else if (confirm.equalsIgnoreCase("n")){
            return 0;
        } else {
            System.out.print("\nPlease only input y or no [press enter for default(Y)]: ");
            return confirm(message);
        }
    }

    static void printMenu() {
        System.out.println("\n******************************");
        System.out.println("********* Main Menu **********");
        System.out.println("******************************");
        System.out.printf("\nPlease choose from below:\n" +
                "[ 0 ] Exit\n" +
                "[ 1 ] Create Customer Categories\n" +
                "[ 2 ] Show All Cards\n" +
                "[ 3 ] Show All Purchases\n" +
                "[ 4 ] Add Purchase\n" +
                "[ 5 ] Show Total Purchases\n" +
                "[ 6 ] Show Customer Points\n");
        System.out.println("\n******************************");
    }

    static void createCardMenu() {
        System.out.printf("%nPlease select a Card Type choice from below:%n" +
                "[ 1 ] Anon Card%n" +
                "[ 2 ] Basic Card%n" +
                "[ 3 ] Premium Card%n" +
                "[ 0 ] Exit%n");
    }

    static String cardSelection() {
        createCardMenu();

        int selection = userSelection();

        switch (selection) {
            case 0: return "";
            case 1: return "AnonCard";
            case 2: return "BasicCard";
            case 3: return "PremiumCard";
            default: return "";
        }
    }

    static ArrayList<String> userCategories(boolean auto) {
        ArrayList<String> categoriesList = new ArrayList<>();
        String option;

        // TODO CHANGE THIS to show something that represents a prompt
        if (auto) {
            /*If the auto flag is true, the categories list will automatically
            * populate with the default*/
            categoriesList.add("Motors");
            categoriesList.add("Electronics");
            categoriesList.add("Fashion");
            categoriesList.add("Toys");
            categoriesList.add("Sporting Goods");
            categoriesList.add("Deals");

            return categoriesList;
        } else {
            System.out.printf("%s%n%s%n%s%n%s%n%n",
                    "Please type the names for a category.",
                    "Type [ default ] to use a template list.",
                    "Template: Deals, Electronics, Toys, Sporting Goods, Fashion, Motors",
                    "***** Type [ finished ] or [ x ] to save and quit *****");

            while (input.hasNextLine()) {
                option = input.nextLine();

                if (option.equalsIgnoreCase("x") ||
                        option.equalsIgnoreCase("finished")) {
                    break;
                } else if (option.equalsIgnoreCase("default")) {
                    categoriesList.add("Motors");
                    categoriesList.add("Electronics");
                    categoriesList.add("Fashion");
                    categoriesList.add("Toys");
                    categoriesList.add("Sporting Goods");
                    categoriesList.add("Deals");
                    break;
                } else {
                    categoriesList.add(option);
                }
            }

            System.out.println("\nYou have typed the following list:");

            for (String item : categoriesList)
                System.out.println(item);

            if (confirm("Do you wish to continue? [Y/n]: ") == 1) {
                return categoriesList;
            } else {
                return userCategories(false);
            }
        }
    }
}

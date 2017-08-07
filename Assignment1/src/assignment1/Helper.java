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

            if (userChoice > 5) {
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
                "[ 1 ] Show All Cards\n" +
                "[ 2 ] Show All Purchases\n" +
                "[ 3 ] Add Purchase\n" +
                "[ 4 ] Show Total Purchases\n" +
                "[ 5 ] Show Customer Points\n" +
                "[ 0 ] Exit\n");
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

    static ArrayList<String> createCategories() {
        ArrayList<String> categoriesList = new ArrayList<>();
        String option;

        System.out.printf("%s%n%s%n%s%n%s%n%n",
                "Please type the Names for Categories",
                "Type [ default ] to use a template list.",
                "Template: Deals, Electronics, Toys, Sporting Goods, Fashion, Motors",
                "***** Type [ exit ] or [ x ] to quit *****");

        // TODO CHANGE THIS to show something that represents a prompt
        while (input.hasNextLine()) {
            option = input.nextLine();

            if (option.equalsIgnoreCase("x") ||
                    option.equalsIgnoreCase("exit")) {
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
            return createCategories();
        }
    }
}

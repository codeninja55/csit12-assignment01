//package assignment1;
import java.util.Scanner;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Helper {

    private Scanner input = new Scanner(System.in);

    public int userSelection() {
        System.out.print("\nEnter your option:  ");
        return input.nextInt();
    }

    public void printMenu() {
        System.out.println("\n******************************");
        System.out.println("********* Main Menu **********");
        System.out.println("******************************");
        System.out.printf("\nPlease choose from below:\n" +
                "[ 1 ] Show All Cards\n" +
                "[ 2 ] Show All Purchases\n" +
                "[ 3 ] Add Purchase\n" +
                "[ 4 ] Show Total Purchases\n" +
                "[ 0 ] Exit\n");
        System.out.println("\n******************************");
    }

    public void createCardMenu() {
        System.out.printf("%nPlease select a Card Type choice from below:%n" +
                "[ 1 ] Anon Card%n" +
                "[ 2 ] Basic Card%n" +
                "[ 3 ] Premium Card%n" +
                "[ 0 ] Exit%n");
    }

    public String cardSelection() {
        createCardMenu();

        int selection = this.userSelection();

        switch (selection) {
            case 0: return "";
            case 1: return "AnonCard";
            case 2: return "BasicCard";
            case 3: return "PremiumCard";
            default: return "";
        } // TODO add thrown exception

    }

    public void categoriesMenu() {

        /* TODO Use a dynamic array to create this menu with a loop
           TODO Use an array method to pop off menu items */

        System.out.printf("%nPlease select Purchase Category from below to add amount:%n" +
                "[ 1 ] Systems%n" +
                "[ 2 ] Laptops%n" +
                "[ 3 ] Peripherals%n" +
                "[ 4 ] Multimedia%n" +
                "[ 5 ] Accessories%n" +
                "[ 0 ] Finished%n");
    }

    public String categoriesSelection() {
        categoriesMenu();

        int selection = this.userSelection();

        switch(selection) {
            case 0: return "";
            case 1: return "Systems";
            case 2: return "Laptops";
            case 3: return "Peripherals";
            case 4: return "Multimedia";
            case 5: return "Accessories";
            default: return "";
        } // TODO add thrown exception
    }
}

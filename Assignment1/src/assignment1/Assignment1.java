//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Assignment1 {

    static private ArrayList<Card> cards = new ArrayList<Card>();
    static private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

    public static void main(String[] args) {
        int menuChoice;
        Boolean change = true;

        System.out.printf("%n%nWelcome to Card Analytics%n%n");

        do {
            printMenu();
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter your option: ");

            menuChoice = input.nextInt();
            // TODO need to fix InputMismatchException

            switch (menuChoice) {
                case 0: System.exit(0);
                case 1: showCards();
                        break;
                case 2: showPurchases();
                        break;
                case 3: addCard();
                        break;
                case 4: makePurchase();
                        break;
            }
        } while (change);

        //System.out.println(testBasic.getDetails());
        //makePurchase();
        //System.out.println(purchases.get(0).toString());
    } // end of main method

    public static void printMenu() {
        System.out.printf("\nPlease choose from below:\n" +
        "[ 1 ] Show All Cards\n" +
        "[ 2 ] Show All Purchases\n" +
        "[ 3 ] Add Card\n" +
        "[ 4 ] Add Purchase\n" +
        "[ 0 ] Exit\n");
    }

    public static void showCards() {
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }

    public static void showPurchases() {
            for (Purchase purchase : purchases) {
                System.out.println(purchase.toString())
            }
    }

    /****** SETTERS ******/

    public static void makePurchase() {
        purchases.add(new Purchase(11111, 1234, "Laptops"));
    }

    public static void addCard() {
        //BasicCard testBasic = new BasicCard("1234", 1000.0,
        //        "Andrew Che", "andrew@codeninja55.me", 100.0);
        cards.add(new BasicCard("1235", 1000.0,
                "Scarlett Johansson", "scarlett@marvel.com", 100.0));
        cards.add(new BasicCard("1234", 1000.0,
                "Andrew Che", "andrew@codeninja55.me", 100.0));
    }

} // end of Assignment1 class

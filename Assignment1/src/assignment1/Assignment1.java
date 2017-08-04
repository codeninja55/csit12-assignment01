//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

// TODO Question: Do you make the card first or the card based on a purchase?

public class Assignment1 {

    static private ArrayList<Card> cards = new ArrayList<Card>();
    static private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    static private Scanner input = new Scanner(System.in);
    static private Helper helper = new Helper(); // helper class to print menu's to console

    /* BETA | Purchase Details List */
    static private final double laptops = 0;
    static private final double systems = 0;
    static private final double peripherals = 0;
    static private final double multimedia = 0;
    static private final double accessories = 0;

    public static void main(String[] args) {

        System.out.printf("%n%nWelcome to Card Analytics%n%n");

        while (true) {
            helper.printMenu();

            int menuChoice = helper.userSelection();
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
        }
    } // end of main method

    /****** SETTERS ******/

    private static void makePurchase() {
        //purchases.add(new Purchase(11111, 1234, "Laptops"));

        

        purchases.add(new Purchase());

    }

    // Unfinished
    private static void addCard() {
        /*cards.add(new BasicCard("1235", 1000.0,
                "Scarlett Johansson", "scarlett@marvel.com", 100.0));
        cards.add(new BasicCard("1234", 1000.0,
                "Andrew Che", "andrew@codeninja55.me", 100.0));*/
        helper.addCardMenu();
        int cardChoice = helper.userSelection();

        switch (cardChoice) {
            case 0: break;
            case 1:
                System.out.print("Enter the Card ID:  ");
                String cardID = input.nextLine();
                cards.add(new AnonCard(cardID));
                break;
            case 2:
                System.out.print("Enter the Card ID:  ");
                String cardID = input.nextLine();
                System.out.print("Enter Customer's Name:  ");
                String name = input.nextLine();
                System.out.print("Enter Customer's Email:  ");
                String email = input.nextLine();

                break;
            case 3:
                break;
        }
    }

    /****** GETTERS ******/

    public static void showCards() {
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }

    public static void showPurchases() {
            for (Purchase purchase : purchases) {
                System.out.println(purchase.toString());
            }
    }

} // end of Assignment1 class

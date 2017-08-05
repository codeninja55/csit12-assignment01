//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

// TODO Question: Do you make the card first or the card based on a purchase?
// TODO Add a method for creating a card that can be used both from makePurchase method and on its own
// TODO Use BigDecimal for monetary calculations
// TODO Change PurchaseAmount to the value of the category they're using

public class Assignment1 {

    static private ArrayList<Card> cards = new ArrayList<Card>();
    static private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    static private Scanner input = new Scanner(System.in);
    static private Helper helper = new Helper(); // helper class to print menu's to console

    public static void main(String[] args) {

        /* BETA | Purchase Details List with HashMap/Dictionary */
        //Map purchaseDetails = new HashMap();
        //purchaseDetails.put("Laptops", 0);
        //purchaseDetails.put("Systems", 0);
        //purchaseDetails.put("Peripherals", 0);
        //purchaseDetails.put("Multimedia", Double(0));
        //purchaseDetails.put("Accessories", Double(0));

        //addCard();
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
                case 3: makePurchase();
                        break;
                case 4: showTotalPurchases();
                        break;
            }
        } // end while loop
    } // end of main method

    /****** SETTERS ******/

    private static void makePurchase() {
        //purchases.add(new Purchase(11111, 1234, "Laptops"));

        // get the receipt ID
        System.out.print("\nEnter Receipt ID:  ");
        int receiptID = input.nextInt();
        input.nextLine();

        // TODO this purchase amount should go into categories

        /*// get the purchase amount
        System.out.print("Enter Purchase Amount:  ");
        double purchaseAmount = input.nextDouble();*/

        // give the user a menu to select categories from
        /*helper.purchaseMenu();
        int categoryChoice = helper.userSelection();
        input.nextLine(); // consume the newline character from use of previous nextInt()

        String purchaseCategories;

        switch (categoryChoice) {
            case 0: break;
            case 1: purchaseCategories = "Systems";
                    break;
            case 2: purchaseCategories = "Laptops";
                    break;
            case 3: purchaseCategories = "Peripherals";
                    break;
            case 4: purchaseCategories = "Multimedia";
                    break;
            case 5: purchaseCategories = "Accessories";
                    break;
            default: purchaseCategories = "Error";
        }*/

        System.out.print("Enter Card ID [or Cash]:  ");
        String cardID = input.nextLine();

        String cardType = "BasicCard";

        //purchases.add(new Purchase(receiptID, cardID, cardType));

        Purchase testP = new Purchase(receiptID, cardID, cardType);

        testP.displayMap();


        /*if (cardID.equalsIgnoreCase("Cash")) {
            purchases.add(new Purchase(receiptID, purchaseCategories, purchaseAmount));

        } else {
            // loop through cards ArrayList to validate for existing cards
            // if the card does not exist, prompt user to make one
            for (Card card : cards) {
                if (card.id.equalsIgnoreCase(cardID)) {
                    cardType = card.getCardType();
                    //card.setPoints(purchaseAmount);

                    // TODO Add purchase amount to Basic or Premium card balance

                    purchases.add(new Purchase(receiptID, purchaseCategories,
                                    card.id, cardType));
                } else {
                    System.out.print("\nPlease create a new card for this purchase\n");

                    //addCard();
                }
            }
        }*/
    } // end of makePurchase method

    // Unfinished
    /*private static void addCard(String cardID, double purchaseAmount) {
        cards.add(new BasicCard("12345", "Scarlett Johansson", "scarlett@marvel.com"));
        cards.add(new BasicCard("12355", "Andrew Che", "andrew@codeninja55.me"));

        helper.addCardMenu();
        int cardChoice = helper.userSelection();

        *//*switch (cardChoice) {
            case 0: break;
            case 1: // Anon Card
                System.out.println("Creating an Anon Card");

                cards.add(new AnonCard(cardID));

                break;

            case 2: // Basic Card
                System.out.println("Creating a Basic Card");

                System.out.print("\nEnter Customer Name:  ");
                String name = input.nextLine();

                System.out.print("\nEnter Customer Email:  ");
                String email = input.nextLine();

                cards.add(new BasicCard(cardID, name, email, purchaseAmount));

                break;

            case 3: // Premium Card
                System.out.println("Creating a Premium Card");
                System.out.println("Please note there is a $25.0 fee to sign up.");
                System.out.println("This will be added to your purchase.");

                System.out.print("\nEnter Customer Name:  ");
                String name = input.nextLine();

                System.out.print("\nEnter Customer Email:  ");
                String email = input.nextLine();

                cards.add(new PremiumCard());

                break;
        }*//*

    } // end of addCard method*/

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

    public static void showTotalPurchases() {
        double total = 0;
        for (Purchase purchase : purchases) {
            total += purchase.getAmount();
        }

        System.out.printf("%nTotal Purchases:  %.2f%n", total);
    }

    // Need a getter to output categories

} // end of Assignment1 class

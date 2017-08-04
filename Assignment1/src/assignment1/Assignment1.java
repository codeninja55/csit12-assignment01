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

    public static void main(String[] args) {

        /* BETA | Purchase Details List with HashMap/Dictionary */
        //Map purchaseDetails = new HashMap();
        //purchaseDetails.put("Laptops", 0);
        //purchaseDetails.put("Systems", 0);
        //purchaseDetails.put("Peripherals", 0);
        //purchaseDetails.put("Multimedia", Double(0));
        //purchaseDetails.put("Accessories", Double(0));

        addCard();
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
                case 4: addCard();
                        break;
                case 5: showTotalPurchases();
                        break;
            }
        }
    } // end of main method

    /****** SETTERS ******/

    private static void makePurchase() {
        //purchases.add(new Purchase(11111, 1234, "Laptops"));

        System.out.print("Enter Receipt ID:  ");
        int receiptID = input.nextInt();
        System.out.print("Enter Purchase Amount:  ");
        double purchaseAmount = input.nextDouble();

        helper.purchaseMenu();
        int purchaseCat = helper.userSelection();

        String purchaseDetails = "";

        switch (purchaseCat) {
            case 1: purchaseDetails = "Systems";
                break;
            case 2: purchaseDetails = "Laptops";
                break;
            case 3: purchaseDetails = "Peripherals";
                break;
            case 4: purchaseDetails = "Multimedia";
                break;
            case 5: purchaseDetails = "Accessories";
                break;
        }

        System.out.print("Enter Card ID [0 for Cash]:  ");
        int cardID = input.nextInt();

        String purchaseType;
        if (cardID == 0) {
            purchaseDetails = "Cash";
            purchases.add(new Purchase(receiptID, purchaseDetails, purchaseAmount));
        } else {

            // loop through cards ArrayList to validate for existing cards
            // if the card does not exist, prompt user to make one
            for (Card card : cards) {
                if (card.id == cardID) {
                    purchaseType = card.getCardType();
                    card.setPoints(purchaseAmount);
                    purchases.add(new Purchase(receiptID, purchaseAmount, purchaseDetails,
                                    card.id, purchaseType));
                }
            }
        }
    } // end of makePurchase method

    // Unfinished
    private static void addCard() {
        cards.add(new BasicCard(12345, "Scarlett Johansson", "scarlett@marvel.com"));
        cards.add(new BasicCard(12355, "Andrew Che", "andrew@codeninja55.me"));

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

    public static void showTotalPurchases() {
        double total = 0;
        for (Purchase purchase : purchases) {
            total += purchase.getAmount();
        }

        System.out.printf("%nTotal Purchases:  %.2f%n", total);
    }

} // end of Assignment1 class

//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

// TODO Add a method for creating a card that can be used both from makePurchase method and on its own
// TODO Use BigDecimal for monetary calculations

public class Assignment1 {

    static private ArrayList<Card> cards = new ArrayList<Card>();
    static private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    static private Scanner input = new Scanner(System.in);
    static private Helper helper = new Helper(); // helper class to print menu's to console
    static private Map<String, Double> categories = new HashMap<>();

    public static void main(String[] args) {

        /****** TESTING CODE ******/

        cards.add(new AnonCard("1234"));
        cards.add(new BasicCard("12345", "Scarlett Johansson", "scarlett@marvel.com", 100.0));
        cards.add(new PremiumCard("12355", "Andrew Che", "andrew@codeninja55.me",1000D));

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
                case 4: //showTotalPurchases();
                        break;
            }
        } // end while loop
    } // end of main method

    /****** SETTERS ******/

    private static void makePurchase() {

        System.out.print("\nEnter Receipt ID:  ");
        int receiptID = input.nextInt();
        input.nextLine();

        System.out.print("Enter Card ID [or Cash]:  ");
        String cardID = input.nextLine();

        // TODO Put this into its own method
        categories = new HashMap<>();

        categories.put("Systems", 0D);
        categories.put("Laptops", 0D);
        categories.put("Peripherals", 0D);
        categories.put("Multimedia", 0D);
        categories.put("Accessories", 0D);

        while (true) {
            helper.categoriesMenu();
            String selection = helper.categoriesSelection();

            if (selection.isEmpty()) {
                break;
            } else {
                System.out.printf("Enter Total Amount for %s Category:  ", selection);
                double categoryAmount = input.nextDouble();
                categories.put(selection, categoryAmount);
            }
        }

        //System.out.println(categories.entrySet()); // TESTING

        if (cardID.equalsIgnoreCase("cash")) {
            purchases.add(new Purchase(receiptID, categories));
        } else {

            int counter = 1; // check when for loops reaches end
            /* Loop through cards ArrayList to validate for existing cards
             * if the card does not exist, prompt user to make one. */
            for (Card card : cards) {
                if (cardID.equalsIgnoreCase(card.id)) {
                    String cardType = card.cardType;
                    Purchase newPurchase = new Purchase(receiptID, cardID, cardType, categories);
                    card.setPoints(newPurchase.getCategoriesTotal());

                    if (!cardType.equalsIgnoreCase("AnonCard")) {
                        card.setBalance(newPurchase.getCategoriesTotal());
                    }

                    purchases.add(newPurchase);
                    counter++;
                } else if (cards.size() < counter){
                    System.out.print("\nPlease create a new card for this purchase\n");

                    //addCard();
                } else {
                    counter++;
                }
            }
        }

    } // end of makePurchase method

    // TODO Unfinished
    private static void addCard(String cardID, double purchaseAmount) {

        helper.addCardMenu();
        int cardChoice = helper.userSelection();

        /*switch (cardChoice) {
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
        }*/

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

    //public static void showTotalPurchases() { }

    // Need a getter to output categories

} // end of Assignment1 class

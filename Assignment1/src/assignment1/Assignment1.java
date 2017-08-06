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
        cards.add(new BasicCard("12345", "Scarlett Johansson",
                "scarlett@marvel.com", 100.0));
        cards.add(new PremiumCard("12355", "Andrew Che",
                "andrew@codeninja55.me",1000D));

        //createCard();

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

        Map<String, Double> categories = createCategories();

        /*
        * NOTE: Regarding ConcurrentModificationError when iterating over ArrayList
        * There are 2 options available:
        * 1. Create a copy of cards ArrayList using ArrayList(Collection<? extends E> c)
        *    to avoid Modifying the original cards list
                * 2. Iterating over original ArrayList to find index of existing cards and making
        *    modifications to any existing card objects  using its index in the ArrayList
        * */

        ArrayList<Card> cardsCopy = new ArrayList<>(cards);

        if (cardID.equalsIgnoreCase("cash")) {
            /* If it just a cash purchase, not updates required to card */
            purchases.add(new Purchase(receiptID, categories));
        } else {
            int counter = 1; // check when for loops reaches end

            /* Loop through cards ArrayList to validate for existing cards
             * if the card does not exist, prompt user to make one. */
            for (Card card : cardsCopy) {
                if (cardID.equalsIgnoreCase(card.id)) {
                    String cardType = card.cardType;
                    Purchase newPurchase = new Purchase(receiptID, cardID, cardType, categories);
                    card.setPoints(newPurchase.getCategoriesTotal());

                    if (!cardType.equalsIgnoreCase("AnonCard")) {
                        card.setBalance(newPurchase.getCategoriesTotal());
                    }

                    purchases.add(newPurchase);
                    counter++;
                } else if (cardsCopy.size() != counter){
                    counter++;

                } else {

                    System.out.print("\nPlease create a new card for this purchase\n");

                    createCard(receiptID, cardID, categories);
                }
            }
        }

    } // end of makePurchase method

    private static Map<String, Double> createCategories() {
        categories = new HashMap<>();

        categories.put("Systems", 0D);
        categories.put("Laptops", 0D);
        categories.put("Peripherals", 0D);
        categories.put("Multimedia", 0D);
        categories.put("Accessories", 0D);

        while (true) {
            String selection = helper.categoriesSelection();

            if (selection.isEmpty()) {
                break;
            } else {
                System.out.printf("Enter Total Amount for %s Category:  ", selection);
                double categoryAmount = input.nextDouble();
                categories.put(selection, categoryAmount);
            }
        }

        return categories;
    }

    private static void createCard(int ReceiptID, String cardID, Map<String, Double> categories) {
        String name, email;
        Card newCard;

        String cardChoice = helper.cardSelection();

        Purchase newPurchase = new Purchase(ReceiptID, cardID, cardChoice, categories);
        double totalAmount = newPurchase.getCategoriesTotal();

        if (cardChoice.equalsIgnoreCase("AnnonCard")) {
            System.out.println("\nCreating an Anon Card");

            newCard = new AnonCard(cardID);

            newCard.setPoints(totalAmount);

        } else {

            if (cardChoice.equalsIgnoreCase("BasicCard")) {
                System.out.println("\nCreating a Basic Card");
            } else {
                System.out.println("Creating a Premium Card");
                System.out.println("Please note there is a $25.0 fee to sign up.");
                System.out.println("This will be added to your purchase.");
            }

            System.out.print("\nEnter Customer Name:  ");
            name = input.nextLine();

            System.out.print("\nEnter Customer Email:  ");
            email = input.nextLine();

            if (cardChoice.equalsIgnoreCase("BasicCard")) {
                newCard = new BasicCard(cardID, name, email, totalAmount);
            } else {
                newCard = new PremiumCard(cardID, name, email, totalAmount);
            }

            newCard.setPoints(totalAmount);

        }

        cards.add(newCard);
        purchases.add(newPurchase);

    } // end of createCard method*/

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

    //public static void showTotalPurchases() { } // TODO

    /* TODO
    * prints (i) the total points earned by all customers and
    * (ii) prints the number of customers with low (<500), medium (>= 500 and < 2000)
    * and large (>= 2000) points.*/

    //public static void printPoints() { }

    // Need a getter to output categories

} // end of Assignment1 class

//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

// TODO Use BigDecimal for monetary calculations

public class Assignment1 {

    static private ArrayList<Card> cards = new ArrayList<Card>();
    static private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    static private Scanner input = new Scanner(System.in);
    static private Helper helper = new Helper(); // helper class to print menu's to console
    static private Map<String, Double> categories = new HashMap<>();

    public static void main(String[] args) {

        createTestCode();

        System.out.println("\n\nWELCOME TO CARD ANALYTICS\n\n");

        while (true) {
            helper.printMenu();

            int menuChoice = helper.userSelection();

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
                case 5: printPoints();
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

        boolean newCard = true; // flag to see if new card required

        if (cardID.equalsIgnoreCase("cash")) {
            /* If it just a cash purchase, no updates required to card */
            purchases.add(new Purchase(receiptID, categories));
        } else {
            /* Loop through cards ArrayList to validate for existing cards
             * if the card does not exist, prompt user to make one. */
            for (Card card : cardsCopy) {

                if (card.id.equals(cardID)) {
                    String cardType = card.cardType;
                    Purchase newPurchase = new Purchase(receiptID, cardID, cardType, categories);
                    card.setPoints(newPurchase.calcCategoriesTotal());

                    if (!cardType.equalsIgnoreCase("AnonCard"))
                        card.setBalance(newPurchase.calcCategoriesTotal());

                    purchases.add(newPurchase);
                    newCard = false; // set flag so new card not created
                    break;
                }
            }

            if (newCard) {
                System.out.print("\nPlease create a new card for this purchase\n");

                createCard(receiptID, cardID, categories);
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
        input.nextLine(); // consume newline character leftover from nextInt()

        Purchase newPurchase = new Purchase(ReceiptID, cardID, cardChoice, categories);
        double totalAmount = newPurchase.calcCategoriesTotal();

        if (cardChoice.isEmpty()) {
            System.out.println("\nExiting from creating card...");
        } else if (cardChoice.equalsIgnoreCase("AnonCard")) {
            System.out.println("\nCreating an Anon Card");

            newCard = new AnonCard(cardID);

            newCard.setPoints(totalAmount);
            cards.add(newCard);

        } else {

            if (cardChoice.equalsIgnoreCase("BasicCard")) {
                System.out.println("\nCreating a Basic Card");
            } else {
                System.out.println("\nCreating a Premium Card");
                System.out.println("Please note there is a $25.0 fee to sign up.");
                System.out.println("This will be added to your purchase.");
            }

            System.out.print("\nEnter Customer Name:  ");
            name = input.nextLine();

            System.out.print("\nEnter Customer Email:  ");
            email = input.nextLine();

            if (cardChoice.equalsIgnoreCase("BasicCard"))
                newCard = new BasicCard(cardID, name, email, totalAmount);
            else
                newCard = new PremiumCard(cardID, name, email, totalAmount);

            newCard.setPoints(totalAmount);

            cards.add(newCard);
        }

        purchases.add(newPurchase);
    } // end of createCard method*/

    private static void createTestCode() {
        /****** TESTING CODE ******/

        // Cash purchase test
        Map<String, Double> cat1 = new HashMap<>();
        cat1.put("Systems", 800D);
        cat1.put("Laptops", 0D);
        cat1.put("Peripherals", 100D);
        cat1.put("Multimedia", 0D);
        cat1.put("Accessories", 0D);
        purchases.add(new Purchase(1,cat1));

        // AnonCard Test
        cards.add(new AnonCard("111"));

        Map<String, Double> cat2 = new HashMap<>();
        cat2.put("Systems", 0D);
        cat2.put("Laptops", 0D);
        cat2.put("Peripherals", 80D);
        cat2.put("Multimedia", 0D);
        cat2.put("Accessories", 100D);

        Purchase anonTest = new Purchase(100,"111","AnonCard",cat2);
        cards.get(0).setPoints(anonTest.calcCategoriesTotal());
        purchases.add(anonTest);

        // BasicCard Test
        cards.add(new BasicCard("69", "Scarlett Johansson",
                "scarlett@marvel.com", 0));

        Map<String, Double> cat3 = new HashMap<>();
        cat3.put("Systems", 3000D);
        cat3.put("Laptops", 5000D);
        cat3.put("Peripherals", 500D);
        cat3.put("Multimedia", 0D);
        cat3.put("Accessories", 1000D);

        Purchase basicTest = new Purchase(1000, "69", "BasicCard",cat3);
        cards.get(1).setPoints(basicTest.calcCategoriesTotal());
        cards.get(1).setBalance(basicTest.calcCategoriesTotal());
        purchases.add(basicTest);

        // PremiumCard Test
        cards.add(new PremiumCard("55", "Andrew Che",
                "andrew@codeninja55.me",0));

        Map<String, Double> cat4 = new HashMap<>();
        cat4.put("Systems", 100000D);
        cat4.put("Laptops", 4500D);
        cat4.put("Peripherals", 300D);
        cat4.put("Multimedia", 10000D);
        cat4.put("Accessories", 500D);

        Purchase premiumTest = new Purchase(2000,"55","PremiumCard",cat4);
        cards.get(2).setPoints(premiumTest.calcCategoriesTotal());
        cards.get(2).setBalance(premiumTest.calcCategoriesTotal());
        purchases.add(premiumTest);

        cards.add(new PremiumCard("75", "Tony Stark",
                "ironman@avengers.team",1000000D));
        cards.get(3).setPoints(9000D);

        cards.add(new BasicCard("1", "Steve Rogers",
                "captain_a@avengers.team",500D));
        cards.get(4).setPoints(100000D);
    }

    /****** GETTERS ******/

    public static void showCards() {
        System.out.printf("%n%n%-12s %-10s %-10s %-15s %-20s %-20s%n",
                "Card Type","Card ID","Points","Balance", "Name", "Email");
        for (Card card : cards) {
            System.out.println(card.toString());
        }
        System.out.println();
    }

    public static void showPurchases() {
            for (Purchase purchase : purchases) {
                System.out.println(purchase.toString());
            }
    }

    public static void showTotalPurchases() {
        System.out.printf("%n%n%-20s %s","Category","Total");

        Map<String, Double> categoryTotal = new HashMap<>();
        categoryTotal.put("Systems", 0D);
        categoryTotal.put("Laptops", 0D);
        categoryTotal.put("Peripherals", 0D);
        categoryTotal.put("Multimedia", 0D);
        categoryTotal.put("Accessories", 0D);

        for (Purchase purchase : purchases) {
            Map<String, Double> map = purchase.getCatMap();

            double systemsVal = categoryTotal.get("Systems") + map.get("Systems");
            categoryTotal.replace("Systems", systemsVal);

            double laptopsVal = categoryTotal.get("Laptops") + map.get("Laptops");
            categoryTotal.replace("Laptops", laptopsVal);

            double peripheralsVal = categoryTotal.get("Peripherals") + map.get("Peripherals");
            categoryTotal.replace("Peripherals", peripheralsVal);

            double multimediaVal = categoryTotal.get("Multimedia") + map.get("Multimedia");
            categoryTotal.replace("Multimedia", multimediaVal);

            double accessoriesVal = categoryTotal.get("Accessories") + map.get("Accessories");
            categoryTotal.replace("Accessories", accessoriesVal);
        }

        for (Map.Entry<String, Double> item : categoryTotal.entrySet()) {
            System.out.printf("%n%-20s $%.2f", (item.getKey() + ":"), item.getValue());
        }
        System.out.println("\n\n");
    }

    public static void printPoints() {

        // Total points by all customers
        double totalPoints = 0;

        // Points groupings by customer
        int low = 0;
        int medium = 0;
        int high = 0;

        for (Card card : cards) {
            totalPoints += card.getPoints();

            if (card.points < 500D) {
                low++;
            } else if (card.points > 500D && card.points < 2000D) {
                medium++;
            } else {
                high++;
            }
        }

        System.out.printf("%n%nTotal Points for All Customers: %.2f%n%n", totalPoints);

        System.out.println("Customers by Groupings");
        System.out.printf("Low (less than 500): %d%nMedium (500 to 2000): %d%n" +
                        "High (more than 2000): %d%n%n", low, medium, high);
    }
} // end of Assignment1 class

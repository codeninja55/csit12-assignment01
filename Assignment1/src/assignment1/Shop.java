//package assignment1;
import java.lang.reflect.Array;
import java.util.*;

/*
 *
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 *
 */

public class Shop {

    private ArrayList<Card> cards;
    private ArrayList<Purchase> purchases;
    private ArrayList<String> categoriesList; // store a String list of category keys
    private Map<String, Double> categories;
    private Scanner input = new Scanner(System.in);
    //private Helper Helper = new Helper(); // Helper class to print menu's to console

    /******************************************************************/
    /************************** CONSTRUCTORS **************************/
    /******************************************************************/

    // default
    public Shop() {
        this.purchases = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    // constructor to create categories list
    public Shop(ArrayList<String> categoriesList) {
        this.purchases = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.categoriesList = categoriesList;
        this.categories = createCategories(categoriesList);
    }

    /*************************************************************/
    /************************** SETTERS **************************/
    /*************************************************************/

    public void makePurchase() {

        System.out.print("\nEnter Receipt ID:  ");
        int receiptID = input.nextInt();
        input.nextLine();

        System.out.print("Enter Card ID [or Cash]:  ");
        String cardID = input.nextLine();

        // TODO Fix this
        //Map<String, Double> categories = setCategories();
        setCategories();

        /*
        * NOTE: Regarding ConcurrentModificationError when
        * iterating over ArrayList
        * There are 2 options available:
        * 1. Create a copy of cards ArrayList using
        *    ArrayList(Collection<? extends E> c)
        *    to avoid Modifying the original cards list
        * 2. Iterating over original ArrayList to find
        *    index of existing cards and making
        *    modifications to any existing card objects
        *    using its index in the ArrayList
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

    private Map<String, Double> createCategories(ArrayList<String> categoriesList) {
        Map<String, Double> categories = new HashMap<>();

        for (String item : categoriesList)
            categories.put(item, 0D);

        return categories;
    }

    private void setCategories() {

        /*Creates a new HashMap for the Menu
        * Loop through the categories HashMap, retrieve the keys and place them in
        * a new HashMap with an int as the key. This way the menu option has an int
        * to print to the screen. */
        Map<Integer, String> categoriesMenu = new HashMap<>();
        int counter = 1;

        for (Map.Entry<String, Double> item : categories.entrySet()) {
            categoriesMenu.put(counter,item.getKey());
            counter++;
        }

        /*Displays the menu to the screen and through options of categories for user
        * to select with an int input. It will then remove than item form the menu
        * HashMap so the user cannot input a value twice. Exits loop when user enters 0
        * or presses enter on a blank newline character */
        while (true) {
            System.out.printf("%nPlease select Purchase Category from below to add amount:%n");
            System.out.printf("[ 0 ] %s%n", "Finished");

            for (Map.Entry<Integer, String> item : categoriesMenu.entrySet())
                System.out.printf("[ %d ] %s%n", item.getKey(), item.getValue());

            int choice = Helper.userSelection();
            String selection = "";
            boolean sentinel = true;

            for (Map.Entry<Integer, String> item : categoriesMenu.entrySet()) {
                if (choice == item.getKey()) {
                    selection = item.getValue();
                    sentinel = false;
                }
            }

            if (sentinel) {
                selection = "";
            }

            if (selection.isEmpty()) {
                break;
            } else {
                System.out.printf("Enter Total Amount for %s Category:  ", selection);
                double categoryAmount = input.nextDouble();
                categories.put(selection, categoryAmount);
                categoriesMenu.remove(choice);
            }
        }
    } // end of setCategories method

    private void createCard(int ReceiptID, String cardID, Map<String, Double> categories) {

        String name, email;
        Card newCard;

        String cardChoice = Helper.cardSelection();
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
    } // end of createCard method


    /*##### PLACEHOLDER FOR SETPOINTSTHRESHOLDER METHOD #####*/

    /*************************************************************/
    /************************** GETTERS **************************/
    /*************************************************************/
    public ArrayList<Card> getCards() { return cards; }

    public ArrayList<Purchase> getPurchases() { return purchases; }

    /*************************************************************/
    /************************** HELPERS **************************/
    /*************************************************************/

    public void showCards() {
        System.out.printf("%n%n%-12s %-10s %-10s %-15s %-20s %-20s%n",
                "Card Type","Card ID","Points","Balance", "Name", "Email");

        for (Card card : cards)
            System.out.println(card.toString());

        System.out.println();
    }

    public void showPurchases() {
        for (Purchase purchase : purchases)
            System.out.println(purchase.toString());
    }

    public void showTotalPurchases() {
        System.out.printf("%n%n%-20s %s","Category","Total");

        Map<String, Double> categoryTotal = new HashMap<>();
        categoryTotal.put("Systems", 0D);
        categoryTotal.put("Laptops", 0D);
        categoryTotal.put("Peripherals", 0D);
        categoryTotal.put("Multimedia", 0D);
        categoryTotal.put("Accessories", 0D);

        // TODO Change this to loop through the categories
        for (Purchase purchase : purchases) {
            Map<String, Double> map = purchase.getCategoriesMap();

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

        for (Map.Entry<String, Double> item : categoryTotal.entrySet())
            System.out.printf("%n%-20s $%.2f", (item.getKey() + ":"), item.getValue());

        System.out.println("\n\n");
    }

    /* TODO 2
    * The user can enter via the console an arbitrary number of thresholds
    * (instead of the three required in standard deliverable number 4),
    * then these thresholds will be used when reporting the number of customers
    * in each of these point 'bands'.
    * */

    private Map<String, int[]> createThresholdContainer() {

        System.out.printf("%n%s%n%s%n%s%n%s%n%s",
                "You can set the Points Threshold as follows:",
                "1. Give a number of thresholds.",
                "2. Give the minimum value for a threshold (For threshold 1, 0 for no minimum).",
                "3. Give the maximum value for a threshold (For final threshold, 0 for no maximum).",
                "4. Repeat until number of thresholds have a min and max value.");

        System.out.print("\n\nInput the number of thresholds:  ");
        int thresholdNumber = input.nextInt();

        Map<String, int[]> thresholdList = new HashMap<>();

        // Grab input from user for a min and max and store them as elements in int[]
        for (int counter = 1 ; counter <= thresholdNumber ; counter++ ) {
            int[] valArr = new int[2];
            String name = "Threshold " + Integer.toString(counter);

            System.out.printf("%nInput %s minimum value:  ", name);
            int min = input.nextInt();

            System.out.printf("Input %s maximum value:  ", name);
            int max = input.nextInt();
            input.nextLine(); // consume dangling newline char

            // TODO Need to do some validation checking if numbers are fine
            valArr[0] = min;
            valArr[1] = max;

            thresholdList.put(name, valArr);
        }

        return thresholdList;
    }

    private Map<String, Integer> setPointsThreshold(Map<String, int[]> thresholdList) {

        Map<String, Integer> thresholdResults = new HashMap<>();
        int count = 0;

        for (Card card : cards) {
            for (Map.Entry<String, int[]> item : thresholdList.entrySet()) {
                if (card.points >= item.getValue()[0] && card.points < item.getValue()[1]) {
                    thresholdResults.put(item.getKey(), count++);
                }
            }
        }
        return thresholdResults;
    }

    public void showPoints() {

        double totalPoints = 0;

        // prompt user if they would like to make a new threshold <-- put this in Helper class
        // otherwise default to the ones already created below

        System.out.printf("%n%s%n%s%n%s%n%s","Default Points Threshold Levelsl:",
                "Low (less than 500", "Medium (between 500 and 2000", "High (more than 2000)");

        int confirm = Helper.confirm("Do you wish to change the points threshold levels? [Y/n]: ");
        input.nextLine();

        if (confirm == 1) {

            Map<String, int[]> thresholdList = createThresholdContainer();
            Map<String, Integer> thresholdResults = setPointsThreshold(thresholdList);

            for (Card card : cards)
                totalPoints += card.getPoints();

            System.out.printf("%n%nTotal Points for All Customers: %.2f%n%n", totalPoints);
            System.out.println("Customers by Thresholds");

            for (Map.Entry<String, Integer> item : thresholdResults.entrySet()) {
                System.out.printf("%n%s (Min: %d; Max: %d): %d",
                        item.getKey(), thresholdList.get(item.getKey())[0],
                        thresholdList.get(item.getKey())[1], item.getValue());
            }

        } else if (confirm == 0) {
            // Default points thresholds by customer
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

            System.out.println("Customers by Thresholds");
            System.out.printf("Low (less than 500): %d%nMedium (500 to 2000): %d%n" +
                    "High (more than 2000): %d%n%n", low, medium, high);
        }
    }
}

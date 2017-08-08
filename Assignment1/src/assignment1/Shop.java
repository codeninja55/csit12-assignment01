//package assignment1;
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
        this.categories = new HashMap<>();
        this.createCategories(Helper.userCategories(true));
    }

    /*************************************************************/
    /************************** SETTERS **************************/
    /*************************************************************/

    public void makePurchase() {

        System.out.print("\n\nEnter Card ID [or Cash]:  ");
        String cardID = input.nextLine();

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
            purchases.add(new Purchase(categories));
        } else {
            /* Loop through cards ArrayList to validate for existing cards
             * if the card does not exist, prompt user to make one. */
            for (Card card : cardsCopy) {

                if (card.id.equals(cardID)) {
                    String cardType = card.cardType;
                    Purchase newPurchase = new Purchase(cardID, cardType, categories);
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
                createCard(cardID, categories);
            }
        }
    } // end of makePurchase method

    public void createCategories(ArrayList<String> categoriesList) {
        for (String item : categoriesList)
            this.categories.put(item, 0D);
    }

    private void setCategories() {

        /*Creates a new HashMap for the Menu
        * Loop through the categories HashMap, retrieve the keys and place them in
        * a new HashMap with an int as the key. This way the menu option has an int
        * to print to the screen. */
        Map<Integer, String> categoriesMenu = new HashMap<>();
        int counter = 1;

        for (Map.Entry<String, Double> item : this.categories.entrySet()) {
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

            if (sentinel)
                selection = "";

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

    private void createCard(String cardID, Map<String, Double> categories) {

        String name, email;
        Card newCard;

        String cardChoice = Helper.cardSelection();
        input.nextLine(); // consume newline character leftover from nextInt()

        Purchase newPurchase = new Purchase(cardID, cardChoice, categories);
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
        System.out.printf("%n%n%-20s %s%n","Category","Total");

        /*Create a Map of the default categories map with the same keys
        * and the value being an array. */
        Map<String, ArrayList<Double>> categoryTotal = new HashMap<>();

        for (Map.Entry<String, Double> item : categories.entrySet())
            categoryTotal.put(item.getKey(), new ArrayList<>());

        for (Purchase purchase : purchases) {
            Map<String, Double> categoriesMap = purchase.getCategoriesMap();

            for (Map.Entry<String, Double> item : categoriesMap.entrySet())
                categoryTotal.get(item.getKey()).add(item.getValue());
        }

        /*Loop through each List and sum them together to print*/

        double sum = 0;
        for (Map.Entry<String, ArrayList<Double>> item : categoryTotal.entrySet()) {
            for (Double values : item.getValue())
                sum += values;

            System.out.printf("%n%-20s $%.2f", (item.getKey() + ":"), sum);
        }

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

            /*Checks the last Threshold value if it is set to 0 and sets the value to
             *the MAX_VALUE allowed for an integer */
            if (counter == thresholdNumber && max == 0)
                valArr[1] = Integer.MAX_VALUE;
            else
                valArr[1] = max;

            thresholdList.put(name, valArr);
        }
        return thresholdList;
    }

    private Map<String, Integer> setPointsThreshold(Map<String, int[]> thresholdList) {

        Map<String, Integer> thresholdResults = new HashMap<>();

        /*Uses the thresholdLIst created as a Map container with key being String name
        * and the value being an array of min at index 0 and max at index 1*/

        for (Map.Entry<String, int[]> item : thresholdList.entrySet()) {
            int count = 1;
            for (Card card : cards) {
                if (card.points >= item.getValue()[0] && card.points < item.getValue()[1]) {
                    thresholdResults.put(item.getKey(), count++);
                } else {
                    thresholdResults.put(item.getKey(), 0);
                }
            }
        }
        return thresholdResults;
    }

    public void showPoints() {

        double totalPoints = 0;

        // prompt user if they would like to make a new threshold <-- put this in Helper class
        // otherwise default to the ones already created below

        System.out.printf("%n%s%n%s%n%s%n%s","Default Points Threshold Levels:",
                "Low (less than 500", "Medium (between 500 and 2000", "High (more than 2000)");

        int confirm = Helper.confirm("Do you wish to change the points threshold levels? [Y/n]: ");

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

            System.out.println("\n" + thresholdList);
            System.out.println(thresholdResults);

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

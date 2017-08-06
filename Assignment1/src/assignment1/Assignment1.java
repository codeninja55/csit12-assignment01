//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

/* TODO 1
* The purchase details are a flexible number of categories.
* For example cat-A, cat-B, cat-C, cat-D, cat-E, cat-F ...
* Do not use these names, think of a shop, and create your own
* meaningful category names. The purchase details that need to be stored are:
* for each category the purchase amount. You will also need to store the
* category names somewhere.
*
*/

/* TODO 3
* Reorganise your code so that there is an additional Shop class. The shop
* class has two attributes: (i) a list of cards and (ii) a list of purchases.
* The makePurchase method should be moved into the Shop class. New purchase
* obejcts should only be created in the Shop class. You'll need to create a
* shop in your main method. Testing should then be done via various Shop class
* methods.
* */

// TODO Use BigDecimal for monetary calculations

public class Assignment1 {

    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO CARD ANALYTICS\n");

        Shop shop = new Shop(Helper.createCategories());

        //createTestCode(shop);

        while (true) {
            Helper.printMenu();

            int menuChoice = Helper.userSelection();

            switch (menuChoice) {
                case 0: System.exit(0);
                case 1: shop.showCards();
                        break;
                case 2: shop.showPurchases();
                        break;
                case 3: shop.makePurchase();
                        break;
                case 4: shop.showTotalPurchases();
                        break;
                case 5: shop.showPoints();
                        break;
            }
        } // end while loop
    } // end of main method

    // TODO change these to using only Shop class methods
    private static void createTestCode(Shop shop) {
        /****** TESTING CODE ******/

        ArrayList<Card> cards = shop.getCards();
        ArrayList<Purchase> purchases = shop.getPurchases();

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

} // end of Assignment1 class

//package assignment1;
import java.util.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

// TODO Use BigDecimal for monetary calculations

public class Assignment1 {

    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO CARD ANALYTICS\n");

        Shop shop = new Shop();

        createTestCode(shop);

        while (true) {
            Helper.printMenu();

            int menuChoice = Helper.userSelection();

            switch (menuChoice) {
                case 0: System.exit(0);
                case 1: shop.createCategories(shop.userCategories(false));
                        break;
                case 2: shop.showCards();
                        break;
                case 3: shop.showPurchases();
                        break;
                case 4: shop.makePurchase();
                        break;
                case 5: shop.showTotalPurchases();
                        break;
                case 6: shop.showPoints();
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
        cat1.put("Toys", 0D);
        cat1.put("Sporting Goods", 800D);
        cat1.put("Electronics", 0D);
        cat1.put("Motors", 100D);
        cat1.put("Fashion", 0D);
        cat1.put("Deals", 500D);

        shop.makePurchase("cash");

        // AnonCard Test

        Map<String, Double> cat2 = new HashMap<>();
        cat2.put("Deals", 0D);
        cat2.put("Electronics", 200D);
        cat2.put("Fashion", 80D);
        cat2.put("Sporting Goods", 0D);
        cat2.put("Toys", 100D);
        cat2.put("Motors", 0D);

        Purchase anonTest = new Purchase("111","AnonCard",cat2);
        cards.get(0).calcPoints(anonTest.calcCategoriesTotal());
        purchases.add(anonTest);

        shop.makePurchase("111");

        // BasicCard Test
        cards.add(new BasicCard("69", "Scarlett Johansson",
                "scarlett@marvel.com", 0));

        Map<String, Double> cat3 = new HashMap<>();
        cat3.put("Electronics", 3000D);
        cat3.put("Fashion", 5000D);
        cat3.put("Sporting Goods", 500D);
        cat3.put("Motors", 0D);
        cat3.put("Toys", 1000D);
        cat3.put("Deals", 2000D);

        Purchase basicTest = new Purchase("69", "BasicCard",cat3);
        cards.get(1).calcPoints(basicTest.calcCategoriesTotal());
        cards.get(1).calcBalance(basicTest.calcCategoriesTotal());
        purchases.add(basicTest);

        // PremiumCard Test
        cards.add(new PremiumCard("55", "Andrew Che",
                "andrew@codeninja55.me",0));

        Map<String, Double> cat4 = new HashMap<>();
        cat4.put("Electronics", 10000D);
        cat4.put("Deals", 4500D);
        cat4.put("Toys", 300D);
        cat4.put("Motors", 10000D);
        cat4.put("Sporting Goods", 500D);
        cat4.put("Fashion", 2000D);

        Purchase premiumTest = new Purchase("55","PremiumCard",cat4);
        cards.get(2).calcPoints(premiumTest.calcCategoriesTotal());
        cards.get(2).calcBalance(premiumTest.calcCategoriesTotal());
        purchases.add(premiumTest);

        cards.add(new PremiumCard("75", "Tony Stark",
                "ironman@avengers.team",1000000D));
        cards.get(3).calcPoints(9000D);

        cards.add(new BasicCard("1", "Steve Rogers",
                "captain_a@avengers.team",500D));
        cards.get(4).calcPoints(100000D);
    }

} // end of Assignment1 class

package assignment1;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Assignment1 {
    
    static private ArrayList<Card> cards = new ArrayList<Card>();
    static private ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        BasicCard testBasic = new BasicCard("1234", 1000.0,
                "Andrew Che", "andrew@codeninja55.me", 100.0);

        System.out.println(testBasic.getDetails());

        makePurchase();
        //Purchase purchase = new assignment1.Purchase(11111, 1234, "Cat-A");
        System.out.println(purchases.get(0).getDetails());

    }
    
    public static void makePurchase() {
        /* ISSUE: if this method is static with no return,
           then there will be no object to call the Purchase.getDetails()
           to print out the details.
           SOLUTION: solved by adding to the ArrayList and calling the list to
           return something instead.
         */

        purchases.add(new Purchase(11111, 1234, "Cat-A"));
    }


}
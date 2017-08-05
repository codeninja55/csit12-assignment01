//package assignment1;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Purchase {
    private int receiptID;
    private String cardID;
    private String purchaseCategories;
    private double purchaseAmount;
    private String cardType;

    private Date purchaseTime;

    private Map<String, Double> categories = new HashMap<String, Double>();

    /****** CONSTRUCTORS ******/

    // default constructor
    public Purchase() {

    }

    // constructor for cash purchases
    public Purchase(int receiptID) {

        this.receiptID = receiptID;
        this.cardID = null;
        this.cardType = "Cash";

        createCatMap();

        this.purchaseTime = setPurchaseTime();

    } // end of contructor for cash

    // constructor for card purchases
    public Purchase(int receiptID, String cardID, String cardType) {

        this.receiptID = receiptID;
        this.cardID = cardID;
        this.cardType = cardType;

        createCatMap();

        this.purchaseTime = setPurchaseTime();

    } // end of constructor for card

    /****** SETTERS ******/

    private Date setPurchaseTime() {
        // create a java calendar instance
        // REFERENCE: https://alvinalexander.com/java/java-timestamp-example-current-time-now
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        // a java current time (now) instance
        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        return now;
    }

    private void createCatMap() {
        this.categories.put("systems", 0D);
        this.categories.put("laptops", 0D);
        this.categories.put("peripherals", 0D);
        this.categories.put("multimedia", 0D);
        this.categories.put("accessories", 0D);
    }


    /****** GETTERS ******/

    public String toString() {
        return "\nReceipt ID: " + this.receiptID +
                "\nCard ID: " + this.cardID +
                "\nCard Type: " + this.cardType +
                "\nPurchase Time: " + this.purchaseTime +
                "\nPurchase Details: " + this.purchaseCategories +
                "\nPurchase Amount: " + this.purchaseAmount + "\n";
    }

    public double getAmount() { return this.purchaseAmount; }

    public void displayMap() {
        System.out.printf("%n%-12s %s","CATEGORY", "AMOUNT");
        for (Map.Entry<String, Double> item : this.categories.entrySet()) {
            System.out.printf("%n%-11s  $%.2f", item.getKey().toUpperCase(), item.getValue());
        }
        System.out.println();
    }
}

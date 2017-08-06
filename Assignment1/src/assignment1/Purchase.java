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
    private String cardType;

    private Date purchaseTime;

    private Map<String, Double> categories;

    /****** CONSTRUCTORS ******/

    // default constructor
    public Purchase() {
        this.receiptID = 0;
        this.cardID = "";
        this.cardType = "AnonCard";
        this.purchaseTime = setPurchaseTime();
        this.categories = new HashMap<>();
    }

    // constructor for cash purchases
    public Purchase(int receiptID, Map<String, Double> categories) {

        this.receiptID = receiptID;
        this.cardID = null;
        this.cardType = "Cash";
        this.categories = categories;

        this.purchaseTime = setPurchaseTime();

    } // end of constructor for cash

    // constructor for card purchases
    public Purchase(int receiptID, String cardID, String cardType,
                    Map<String, Double> categories) {

        this.receiptID = receiptID;
        this.cardID = cardID;
        this.cardType = cardType;
        this.categories = categories;

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

    /****** GETTERS ******/

    public String toString() {

        String output;
        String firstOutput;
        String secondOutput = "";

        firstOutput = String.format(
                "%n%-20s %s" +
                "%n%-20s %s" +
                "%n%-20s %s" +
                "%n%-20s %-20tc",
                "Receipt ID", this.receiptID,
                "Card ID:",this.cardID,
                "Card Type:",this.cardType,
                "Purchase Time:",this.purchaseTime);

        for (Map.Entry<String, Double> item : this.categories.entrySet()) {
            secondOutput += String.format("%n%-20s $%.2f", (item.getKey() + ":"),
                    item.getValue());
        }

        output = firstOutput + secondOutput;

        return output;
    }

    public double calcCategoriesTotal() {
        double total = 0;

        for (Map.Entry<String, Double> item : this.categories.entrySet()) {
            total += item.getValue();
        }

        return total;
    }

    public Map<String, Double> getCategoriesMap() { return this.categories; }

}

//package assignment1;
import java.util.Calendar;
import java.util.Date;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Purchase {
    private int receiptID;
    private String cardID;
    private String purchaseDetails;
    private double purchaseAmount;
    //private final String[] DETAILS_ARR = {"Laptops","Systems","Peripherals","Multimedia","Accessories"};
    private String purchaseType;

    private Date purchaseTime;

    /****** CONSTRUCTORS ******/

    // constructor for cash purchases
    public Purchase(int receiptID, String purchaseDetails,
                    double purchaseAmount) {
        this.receiptID = receiptID;
        this.cardID = null;
        this.purchaseType = "Cash";
        this.purchaseDetails = purchaseDetails;
        this.purchaseAmount = purchaseAmount;

        this.purchaseTime = setPurchaseTime();
    }

    public Purchase(int receiptID, double purchaseAmount, String purchaseDetails,
                    String cardID, String purchaseType) {

        this.receiptID = receiptID;
        this.cardID = cardID;
        this.purchaseAmount = purchaseAmount;
        this.purchaseDetails = purchaseDetails;
        this.purchaseTime = setPurchaseTime();
        this.purchaseType = purchaseType;

    } // end of constructor

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
        return "\nReceipt ID: " + this.receiptID +
                "\nCard ID: " + this.cardID +
                "\nCard Type: " + this.purchaseType +
                "\nPurchase Time: " + this.purchaseTime +
                "\nPurchase Details: " + this.purchaseDetails +
                "\nPurchase Amount: " + this.purchaseAmount + "\n";
    }

    public double getAmount() { return this.purchaseAmount; }

}

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
    private int cardID;
    private String purchaseDetails;
    private final String[] DETAILS_ARR = {"Laptops","Systems","Peripherals","Multimedia","Accessories"};
    private final String[] PURCHASE_TYPE = {"Cash","Anon Card","Basic Card","Premium Card"};

    private Date purchaseTime;

    /****** CONSTRUCTORS ******/

    public Purchase(int receiptID, int cardID, String purchaseDetails) {
        this.receiptID = receiptID;
        this.cardID = cardID;
        this.purchaseTime = setPurchaseTime();


        /*for (String cat : DETAILS_ARR) {
            if (purchaseDetails.equalsIgnoreCase(cat)) {
                this.purchaseDetails = cat;
                break;
            } else {
                System.out.println("Wrong category!");
            }
        }*/
    } // end of constructor

    /****** GETTERS ******/

    public String toString() {
        return "\nReceipt ID: " + this.receiptID + "\nCard ID: " + this.cardID +
                "\nPurchase Time: " + this.purchaseTime + "\nPurchase Details: " +
                this.purchaseDetails;
    }

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

}

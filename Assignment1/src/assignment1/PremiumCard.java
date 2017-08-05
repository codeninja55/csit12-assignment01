//package assignment1;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class PremiumCard extends Card {
    private static final double POINTS_RATE_LOW = 0.025;
    private static final double POINTS_RATE_HIGH = 0.03;
    private static final double SIGNUP_FEE = 25.0;

    private String name;
    private String email;
    private double balance;
    private final String CARD_TYPE = "PremiumCard";

    /****** CONSTRUCTORS ******/

    // default constructor
    public PremiumCard() {
        super.id = null;
        super.points = 0;
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public PremiumCard(String id, double points, String name,
                     String email, double balance) {

        super.id = id;
        super.points = points;
        this.name = name;
        this.email = email;

        // validate to make sure entered balance is not negative
        if (balance > 0) {
            this.balance = balance;
        }else {
            this.balance = 0;
        }
    }

    /****** SETTERS ******/

    /****** GETTERS ******/
    public String getDetails() {
        return "\nID: " + super.id +
                "\nCard Type: " + this.CARD_TYPE +
                "\nName: " + this.name +
                "\nEmail: " + this.email +
                "\nBalance: " + this.balance +
                "\nPoints: " + super.points + "\n";
    }

    public String getCardType() { return this.CARD_TYPE; }
}

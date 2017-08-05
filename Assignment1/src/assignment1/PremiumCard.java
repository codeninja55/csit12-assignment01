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

    /****** CONSTRUCTORS ******/

    // default constructor
    public PremiumCard() {
        super.id = null;
        super.points = 0;
        super.cardType = "PremiumCard";
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public PremiumCard(String id, String name, String email, double balance) {

        super.id = id;
        super.points = 0;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    /****** SETTERS ******/

    /****** GETTERS ******/
    public String getDetails() {
        return "\nID: " + super.id +
                "\nCard Type: " + this.cardType +
                "\nName: " + this.name +
                "\nEmail: " + this.email +
                "\nBalance: " + this.balance +
                "\nPoints: " + super.points + "\n";
    }
}

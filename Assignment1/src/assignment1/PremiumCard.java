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
        super.cardType = "PremiumCard";
        super.points = 0;
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public PremiumCard(String id, String name, String email, double totalAmount) {

        super.id = id;
        super.cardType = "PremiumCard";
        super.points = 0;
        this.name = name;
        this.email = email;
        this.balance = totalAmount - SIGNUP_FEE;
    }

    /****** SETTERS ******/
    public void setPoints(double totalAmount) {
        if (totalAmount < 40 && this.balance < 1000) {
            this.points = totalAmount * POINTS_RATE_LOW;
        } else {
            this.points = totalAmount * POINTS_RATE_HIGH;
        }
    }

    public void setBalance(double totalAmount) { this.balance += totalAmount; }

    /****** GETTERS ******/
    public String toString() {
        return "\nCard ID: " + this.id +
                "\nCard Type: " + this.cardType +
                "\nName: " + this.name +
                "\nEmail: " + this.email +
                "\nBalance: " + this.balance +
                "\nPoints: " + this.points + "\n";
    }
}

//package assignment1;

/*
 *
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 *
 */

public class BasicCard extends Card {
    private static final double POINTS_RATE = 0.015;

    private String name;
    private String email;
    private double balance;

    /****** CONSTRUCTORS ******/

    // default constructor
    public BasicCard() {
        super.cardType = "BasicCard";
        super.id = null;
        super.points = 0;
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public BasicCard(String id, String name, String email) {
        super.cardType = "BasicCard";
        super.id = id;
        super.points = 0;
        this.name = name;
        this.email = email;
        this.balance = 0;

    }

    /****** SETTERS ******/
    public void setPoints(double purchaseAmount) { super.points = POINTS_RATE * purchaseAmount; }

    /****** GETTERS ******/
    public String toString() {
        return "\nCard ID: " + super.id +
                "\nCard Type: " + super.cardType +
                "\nName: " + this.name +
                "\nEmail: " + this.email +
                "\nBalance: " + this.balance +
                "\nPoints: " + super.points + "\n";
    }

    public String getCardType() {
        return this.cardType;
    }
}

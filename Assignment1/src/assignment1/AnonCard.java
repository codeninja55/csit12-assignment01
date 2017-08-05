//package assignment1;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class AnonCard extends Card {
    private static final double POINTS_RATE = 0.01;

    /****** CONSTRUCTORS ******/

    // default constructor
    public AnonCard() {
        super.id = null;
        super.points = 0;
        super.cardType = "AnonCard";
    }

    public AnonCard(String id) {
        super.cardType = "AnonCard";
        super.id = id;
        super.points = 0; // TODO change
    }

    /****** SETTERS ******/
    public void setPoints(double purchaseAmount) { super.points = POINTS_RATE * purchaseAmount; }

    /****** GETTERS ******/
    public String toString() {
        return "\nCard Type: " + super.cardType +
                "\nCard ID: " + super.id +
                "\nPoints: " + super.points + "\n";
    }
}

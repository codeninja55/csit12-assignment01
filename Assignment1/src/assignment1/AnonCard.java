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
        super.id = 0;
        super.points = 0;
        super.cardType = "AnonCard";
    }

    public AnonCard(int id) {
        super.cardType = "AnonCard";
        super.id = id;
        super.points = 0;
    }

    /****** SETTERS ******/

    /****** GETTERS ******/
    public String toString() {
        return "\nCard Type: " + super.cardType +
                "\nCard ID: " + super.id +
                "\nPoints: " + super.points + "\n";
    }

    public String getCardType() {
        return super.cardType;
    }
}

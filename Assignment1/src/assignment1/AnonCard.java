//package assignment1;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class AnonCard extends Card {
    private static final double POINTS_RATE = 0.01;
    private final String CARD_TYPE = "Anon Card";

    /****** CONSTRUCTORS ******/

    // default constructor
    public AnonCard() {
        super.id = "";
        super.points = 0;
    }

    public AnonCard(String id) {
        super.id = id;
        super.points = 0;
    }

    /****** SETTERS ******/

    /****** GETTERS ******/
    public String toString() {
        return "\nCard Type: " + this.CARD_TYPE + "\nCard ID: " + super.id +
                "\nPoints: " + super.points + "\n";
    }

}

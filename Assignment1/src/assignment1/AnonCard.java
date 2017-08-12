//package assignment1;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class AnonCard extends Card {
    private static final double POINTS_RATE = 0.01;

    /*########## CONSTRUCTORS ##########*/

    // default constructor
    public AnonCard() {
        super.id = null;
        super.cardType = "AnonCard";
        super.points = 0;
    }

    public AnonCard(String id) {
        super.cardType = "AnonCard";
        super.id = id;
        super.points = 0; // TODO change
    }

    /*########## SETTERS ##########*/
    public void calcPoints(double totalAmount) { this.points += POINTS_RATE * totalAmount; }

    /*########## GETTERS ##########*/
    public String toString() {
        String output = String.format("%-12s %-10s %-10.2f",
                this.cardType, this.id, this.points);

        return output;
    }
}

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
    private final String CARD_TYPE = "Basic Card";


    /****** CONSTRUCTORS ******/

    // default constructor
    public BasicCard() {
        super.id = "";
        super.points = 0;
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public BasicCard(String id, String name, String email) {

        super.id = id;
        super.points = 0;
        this.name = name;
        this.email = email;

        // validate to make sure entered balance is not negative
        // Consider when creating a card for the first time the balance would have to be zero
        /*if (balance > 0) {
            this.balance = balance;
        }else {
            this.balance = 0;
        }*/
    }

    /****** GETTERS ******/
    public String toString() {
        return "\nCard ID: " + super.id +
                "\nCard Type: " + this.CARD_TYPE +
                "\nName: " + this.name +
                "\nEmail: " + this.email +
                "\nBalance: " + this.balance +
                "\nPoints: " + super.points + "\n";
    }

}

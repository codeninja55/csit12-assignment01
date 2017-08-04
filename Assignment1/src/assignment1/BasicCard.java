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
        super.id = "";
        super.points = 0;
        this.name = "";
        this.email = "";
        this.balance = 0;
    }

    // constructor with details
    public BasicCard(String id, double points, String name,
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

    /****** GETTERS ******/
    public String toString() {
        return "\nCard ID: " + super.id + "\nName: " + this.name +
                "\nEmail: " + this.email + "\nBalance: " + this.balance +
                "\nPoints: " + super.points;
    }

}

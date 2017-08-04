//package assignment1;

/*
 *
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 *
 */

public class Card {
    protected int id;
    protected double points;
    protected String cardType;
    /****** CONSTRUCTORS ******/

    // default constructor
    public Card() {
        this.id = 0;
        this.points = 0;
        this.cardType = "";
    }

    // constructor that initializes card properly
    public Card(int id) {
        this.id = id;
    }

    /****** SETTERS ******/
    public void setPoints(double points) {
        this.points = points;
    }

    /****** GETTERS ******/
    public String getCardType() {
        return this.cardType;
    }
}

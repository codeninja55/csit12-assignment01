//package assignment1;

/*
 *
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 *
 */

public abstract class Card {
    protected String id;
    protected double points;
    protected String cardType;

    /****** CONSTRUCTORS ******/

    // default constructor
    public Card() {
        this.id = null;
        this.points = 0;
        this.cardType = "Card";
    }

    // constructor that initializes card properly
    public Card(String id) { this.id = id; }

    /****** SETTERS ******/
    public void setPoints(double totalAmount) { }
    public void setBalance(double totalAmount) { }

    /****** GETTERS ******/
    public String getCardType() { return this.cardType; }
}

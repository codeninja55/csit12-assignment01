package assignment1;

/*
 *
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 *
 */

public class Card {
    protected String id;
    protected double points;

    /****** CONSTRUCTORS ******/

    // default constructor
    public Card() {
        this.id = "";
        this.points = 0;
    }

    // constructor that initializes card properly
    public Card(String id, double points) {
        this.id = id;
        this.points = points;
    }


    
}

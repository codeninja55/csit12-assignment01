//package assignment1;
import java.util.Scanner;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Helper {

    private Scanner input = new Scanner(System.in);

    public int userSelection() {
        System.out.print("\nEnter your option: ");
        return input.nextInt();
    }

    public void printMenu() {
        System.out.printf("\nPlease choose from below:\n" +
                "[ 1 ] Show All Cards\n" +
                "[ 2 ] Show All Purchases\n" +
                "[ 3 ] Add Purchase\n" +
                "[ 4 ] Add Card\n" +
                "[ 5 ] Show Total Purchases\n" +
                "[ 0 ] Exit\n");
    }

    public void addCardMenu() {
        System.out.printf("%nPlease choose from below:%n" +
                "[ 1 ] Anon Card%n" +
                "[ 2 ] Basic Card%n" +
                "[ 3 ] Premium Card%n" +
                "[ 0 ] Exit%n");
    }

    public void purchaseMenu() {
        System.out.printf("%nPlease Purchase Category from Below choose from below:%n" +
                "[ 1 ] Laptops%n" +
                "[ 2 ] Systems%n" +
                "[ 3 ] Peripherals%n" +
                "[ 4 ] Multimedia%n" +
                "[ 5 ] Accessories%n" +
                "[ 0 ] Exit%n");
    }
}

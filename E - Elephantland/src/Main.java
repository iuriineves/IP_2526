import java.util.Scanner;

void main() {
    Scanner input = new Scanner(System.in);
    int hannoAvenue = input.nextInt();
    int hannoStreet = input.nextInt();
    input.nextLine();

    int kandulaAvenue = input.nextInt();
    int kandulaStreet = input.nextInt();
    input.close();

    /*
    moving straight or diagonally is the exact same - only
    changing the order in which the steps are taken
    */
    int avenueDistance = Math.abs(hannoAvenue - kandulaAvenue);
    int streetDistance = Math.abs(hannoStreet - kandulaStreet);
    int distance = avenueDistance + streetDistance;

    // present the data to the user
    System.out.println(distance);

}
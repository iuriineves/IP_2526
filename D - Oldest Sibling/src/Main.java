import java.util.Scanner;

void main() {

    // get user input
    Scanner input = new Scanner(System.in);
    int firstAge = input.nextInt();
    int secondAge = input.nextInt();
    input.close();

    // get the biggest age
    System.out.println(Math.max(firstAge, secondAge));
}
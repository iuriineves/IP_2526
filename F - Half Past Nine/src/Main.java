import java.util.Scanner;

void main() {

    // get user input
    Scanner input = new Scanner(System.in);

    int ruiAge = input.nextInt();
    input.nextLine();
    int momAge = input.nextInt();
    input.close();

    /*
    calculate desired age - half of momAge - by calculating the age gap
    between them
    */
    int ageGap = momAge - ruiAge;
    int ageUntilHalf = ageGap - ruiAge;

    System.out.println(ageUntilHalf);
}
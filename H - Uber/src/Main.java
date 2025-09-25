import java.util.Scanner;

final int UBERX_MIN = 250;
final int UBERX_PER_MINUTE = 10;
final int UBERX_PER_KILOMETER = 80;
final int UBERX_BASE = 100;

final int UBERXL_MIN = 350;
final int UBERXL_PER_MINUTE = 15;
final int UBERXL_PER_KILOMETER = 120;
final int UBERXL_BASE = 150;

int getQuote(int type, int time, int distance) {
    if (type == 1) {
        return Math.max(UBERX_MIN, time * UBERX_PER_MINUTE + distance * UBERX_PER_KILOMETER + UBERX_BASE);
    } else if (type == 2) {
        return Math.max(UBERXL_MIN, time * UBERXL_PER_MINUTE + distance * UBERXL_PER_KILOMETER + UBERXL_BASE);
    }
    return 0;
}

int getPriceForRide(Scanner input) {
    int type = input.nextInt();
    int time = input.nextInt();
    int distance = input.nextInt();
    input.nextLine();

    return getQuote(type, time, distance);
}

void main() {
    Scanner input = new Scanner(System.in);

    int firstCase = getPriceForRide(input);
    int secondCase = getPriceForRide(input);
    int thirdCase = getPriceForRide(input);
    input.close();

    System.out.println(firstCase);
    System.out.println(secondCase);
    System.out.println(thirdCase);
}
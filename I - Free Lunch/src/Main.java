import java.util.Scanner;

final String NO_LUNCH = "No free lunch";

int winner;
int secret;

void init(int secretVal) {
    secret = secretVal;
    winner = 0;
}

int readNum(Scanner input) {
    int num = input.nextInt();
    input.nextLine();
    return num;
}

void getsFreeLunch() {
    if (winner == 0) {
        System.out.println(NO_LUNCH);
    } else {
        System.out.println(winner);
    }
}

void main() {
    Scanner input = new Scanner(System.in);
    int fixedNum = readNum(input);
    init(fixedNum);
    int friendsCount = readNum(input);
    for (int i = 0; i < friendsCount; i++) {
        int current = readNum(input);
        if (current > winner && current < secret) {
            winner = current;
        }
    }
    input.close();
    getsFreeLunch();
}
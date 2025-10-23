import java.util.Scanner;

final int CARDS_IN_HAND = 5;

int winners;
int highestScore;

void init() {
    winners = 0;
    highestScore = 0;
}

void update(int score) {
    if (highestScore < score) {
        winners = 1;
        highestScore = score;
    } else if (highestScore == score) {
        winners++;
    }
}

int[] bubbleSort(int[] array) {
   for (int i = 0; i < array.length; i++) {
       for (int j = 0; j < array.length ; j++) {
           if (array[i] > array[j]) {
               int temp = array[i];
               array[i] = array[j];
               array[j] = temp;
           }
       }
   }
   return array;
}

int calculatePoints(int occurrences, int cardValue) {
    int points = 0;
    switch (occurrences) {
        case 2 -> points += 3 * cardValue;
        case 3 -> points += 5 * cardValue;
        case 4 -> points += 10 * cardValue;
        default -> points += cardValue;
    }
    return points;
}

int readInt(Scanner in) {
    int num = in.nextInt();
    in.nextLine();
    return num;
}

int[] readHand(Scanner input) {
    int[] cardsInHand = new int[CARDS_IN_HAND];
    for (int card = 0; card < CARDS_IN_HAND; card++) {
        cardsInHand[card] = input.nextInt();
    }
    input.nextLine();
    return cardsInHand;
}

void registerHand(int[] cardsInHand) {
    int[] sorted = bubbleSort(cardsInHand);
    int lastCard = sorted[0];
    int occurrences = 0;
    int points = 0;

    for (int card = 0; card < CARDS_IN_HAND; card++) {
        if (sorted[card] == lastCard) {
            occurrences++;
        } else {
            points += calculatePoints(occurrences, lastCard);
            lastCard = sorted[card];
            occurrences = 1;
        }
    }

    points += calculatePoints(occurrences, lastCard);
    update(points);

}

void main() {
    Scanner input = new Scanner(System.in);
    int players = readInt(input);

    for (int player = 0; player < players; player++) {
        int[] cardsInHand = readHand(input);
        registerHand(cardsInHand);
    }
    System.out.println(winners + " " + highestScore);
}
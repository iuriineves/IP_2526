import java.util.Scanner;

// generic method to check if a certain time is within a set time range
boolean isWithinTimeRange(int startHour, int startMin, int endHour, int endMin, int targetHour, int targetMin) {
    boolean isAboveLowerLimit = (startHour < targetHour) || (startHour == targetHour && startMin <= targetMin);
    boolean isBelowUpperLimit = (endHour > targetHour) || (endHour == targetHour && endMin >= targetMin);
    return isAboveLowerLimit && isBelowUpperLimit;
}

// get user input and return if it is within the time range
boolean findEachOther(Scanner input) {
    int startHour = input.nextInt();
    int startMin = input.nextInt();
    input.nextLine();
    int endHour = input.nextInt();
    int endMin = input.nextInt();
    input.nextLine();
    int targetHour = input.nextInt();
    int targetMin = input.nextInt();
    input.nextLine();

    return isWithinTimeRange(startHour, startMin, endHour, endMin, targetHour, targetMin);
}

// print line based on if they found each other or not
void announceInformation(boolean didFindEachOther) {
    if (didFindEachOther) {
        System.out.println("Encontram-se");
    } else {
        System.out.println("Desencontram-se");
    }
}

// main method
void main() {
    Scanner input = new Scanner(System.in);
    boolean firstCase = findEachOther(input);
    boolean secondCase = findEachOther(input);
    input.close();

    announceInformation(firstCase);
    announceInformation(secondCase);
}
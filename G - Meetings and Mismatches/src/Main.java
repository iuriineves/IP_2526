import java.util.Scanner;

final int MINS_PER_HOUR = 60;

final String FOUND_MSG = "Encontram-se";
final String NOT_FOUND_MSG = "Desencontram-se";

int timeToMinutes(int hours, int mins) {
    return mins + hours * MINS_PER_HOUR;
}

// generic method to check if a certain time is within a set time range
boolean isWithinTimeRange(int start, int end, int target) {
    return start <= target && target <= end;
}

String getMessage(boolean didFindEachOther) {
    if (didFindEachOther) return FOUND_MSG;
    return NOT_FOUND_MSG;
}

int readTime(Scanner input) {
    int hours = input.nextInt();
    int mins = input.nextInt();
    input.nextLine();
    return timeToMinutes(hours, mins);
}

// get user input and return if it is within the time range
boolean findEachOther(Scanner input) {
    int startTime = readTime(input);
    int endTime = readTime(input);
    int targetTime = readTime(input);

    return isWithinTimeRange(startTime, endTime, targetTime);
}

// print line based on if they found each other or not
void announceInformation(boolean didFindEachOther) {
    final String MSG = getMessage(didFindEachOther);
    System.out.println(MSG);
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
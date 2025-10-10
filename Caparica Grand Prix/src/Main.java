import java.util.Scanner;

char[] track;
int raceLaps;
int maxSpeed;
int numOpponents;

void init(char[] initTrack, int raceLapsInit, int maxSpeedInit, int numOpponentsInit) {
    track = initTrack;
    raceLaps = raceLapsInit;
    maxSpeed = maxSpeedInit;
    numOpponents = numOpponentsInit;
}

/*
    reads the next integer and skips the new line character
 */
int readIntLn(Scanner in) {
    int num = in.nextInt();
    in.nextLine();
    return num;
}

char[] defineTrack(String trackStr) {
    return trackStr.toCharArray();
}

void main() {
    Scanner input = new Scanner(System.in);
    init(defineTrack(input.nextLine()), readIntLn(input), readIntLn(input), readIntLn(input));

}

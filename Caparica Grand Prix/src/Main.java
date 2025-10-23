/**
 * Caparica Grand Prix is a CLI game where the player races against AI controlled cars. The first to get the required laps wins.
 * @author Iúri Neves
 */
import java.util.Scanner;

// track components
final char POLE = 'S';
final char BOOST = '+';
final char DRAG = '-';
final char OIL = '!';

// user commands
final String ACCEL_CMD = "accel";
final String SHOW_CMD = "show";
final String STATUS_CMD = "status";
final String QUIT_CMD = "quit";

// messages and templates
final String INVALID_MSG = "Invalid command";
final String ENDED_MSG = "ended";
final String ONGOING_MSG = "ongoing";
final String RACE_OVER_MSG = "The race is not over yet!";
final String RACE_ENDED_TEMPLATE = "Race ended: %s won the race!\n";
final String SHOW_TEMPLATE = "%s (%s)\n";
final String PLAYER_WON_TEMPLATE = "Player %s won the race!\n";
final String CAR_STATUS_TEMPLATE = "Player %s: cell %d, laps %d!\n";

// misc constants
final int PLAYER_CAR_ID = 0;
final int DEFAULT_SPEED = 1;
final int OPPONENT_VISION = 3;
final char NO_WINNER = ' ';

// cars data
char[] carsNames;
char[] possibleCarNames = "Pabcdefghi".toCharArray();
int[] carsPositions;
int[] lastCarsPositions;
int[] carsSpeeds;
int[] carsLaps;

// user defined variables
char[] track;
int raceLaps;
int maxSpeed;
int players;

// game state
char winner;
boolean running;
boolean hasYellowFlag;
int rounds;

/**
 * Initializes every game-state related variables
 * @param initTrack a character array containing track pieces
 * @param raceLapsInit amount of laps to win
 * @param maxSpeedInit maximum allowed speed, must be between {@code 0} and {@code initTrack.length - 1}
 * @param numOpponentsInit number of AI opponents
 */
void init(char[] initTrack, int raceLapsInit, int maxSpeedInit, int numOpponentsInit) {
    track = initTrack;
    raceLaps = raceLapsInit;
    maxSpeed = maxSpeedInit;
    players = numOpponentsInit + 1;
    winner = ' ';
    rounds = 0;
    initCarsData();
    running = true;
}

/**
 * Initializes the cars' data
 */
void initCarsData() {
    carsNames = new char[players];
    carsPositions = new int[players];
    lastCarsPositions = new int[players];
    carsSpeeds = new int[players];
    carsLaps = new int[players];

    for (int car = 0; car < players; car++) {
       carsNames[car] = possibleCarNames[car];
       carsLaps[car] = -1;
       setCarPosition(car, getElementPosition(POLE, track) - car - 1);
       lastCarsPositions[car] = carsPositions[car];
       setCarSpeed(car, DEFAULT_SPEED);
    }
}

/**
 * Determines the next AI's move based on current state and predefined rules
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 */
void handleOpponentMovement(int carID) {
    int carPosition = carsPositions[carID];
    int carSpeed = carsSpeeds[carID];
    int i = 0;
    int modifier = 0;
    do {
        carPosition = getWrapAroundPosition(carPosition + DEFAULT_SPEED);
        if (track[carPosition] == BOOST && carSpeed < maxSpeed) modifier = DEFAULT_SPEED;
        else if (track[carPosition] == OIL && carSpeed > 0) modifier = -DEFAULT_SPEED;

        i++;
    } while(i < OPPONENT_VISION && modifier < 1);
    accelerate(carID, modifier);
}

/**
 * Handles car-track, such as powerups
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 */
void handleInteractions(int carID) {
    switch (track[carsPositions[carID]]) {
        case BOOST -> addCarSpeed(carID, DEFAULT_SPEED);
        case DRAG -> addCarSpeed(carID, -DEFAULT_SPEED);
        case OIL -> setCarSpeed(carID, 0);
    }

    // laps logic
    if (track[carsPositions[carID]] == POLE
            && lastCarsPositions[carID] != carsPositions[carID])
        carsLaps[carID]++;

    if (carsPositions[carID] < lastCarsPositions[carID]
            && getElementPosition(POLE, track) < carsPositions[carID])
        carsLaps[carID]++;

    if (lastCarsPositions[carID] < getElementPosition(POLE, track)
            && getElementPosition(POLE, track) < carsPositions[carID])
        carsLaps[carID]++;
}

/**
 * Handles collisions between cars
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 */
void handleCollisions(int carID) {
    for (int car = 0; car < carsNames.length; car++) {
        if (carsPositions[carID] == carsPositions[car]
                && carsNames[car] != carsNames[carID]) {
            int correctedPosition = getWrapAroundPosition(carsPositions[carID] - 1);
            carsPositions[carID] = correctedPosition;
            hasYellowFlag = true;
            handleCollisions(carID);
        }
    }
}

/**
 * Sets the car's position
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 * @param position position value to set
 */
void setCarPosition(int carID, int position) {
    if (hasYellowFlag) position = Math.min(position, carsPositions[carID] + DEFAULT_SPEED);
    position = getWrapAroundPosition(position);
    lastCarsPositions[carID] = carsPositions[carID];
    carsPositions[carID] = position;

}

/**
 * Sets the car's speed
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 * @param speed speed value to set
 */
void setCarSpeed(int carID, int speed) {
    carsSpeeds[carID] = Math.clamp(speed, 0, maxSpeed);
}

/**
 * Adds {@code speed} to the car's speed
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 * @param speed speed value to add
 */
void addCarSpeed(int carID, int speed) {
    setCarSpeed(carID, carsSpeeds[carID] + speed);
}

/**
 * Retrieves the {@code character}'s position on the provided {@code array}
 * @param character element to look for
 * @param array array to inspect
 * @return index of {@code character} in {@code array} or {@code 0} if none is found
 */
int getElementPosition(char character, char[] array) {
    for (int element = 0; element < array.length; element++)
        if (array[element] == character) return element;
    return -1;
}

/** Retrieves the race status
 * @return race status as string
 */
String getRaceStatus() {
    if (winner != NO_WINNER) return ENDED_MSG; else return ONGOING_MSG;
}

/**
 * Turns a string track into a character array track
 * @param trackStr string track
 * @return character array track
 */
char[] defineTrack(String trackStr) {
    return trackStr.toCharArray();
}

/**
 * Corrects a position if it is outside the track
 * @param position position to correct
 * @return corrected position, or itself if not outside the array
 */
int getWrapAroundPosition(int position) {
    if (position >= track.length) position -= track.length;
    if (position < 0) position += track.length;
    return position;
}

/**
 * Reads and runs user commands
 * @param in {@code Scanner} to read user input
 */
void readCommand(Scanner in) {
    String cmd = in.next();
    switch (cmd) {
        case ACCEL_CMD -> {
            int accel = in.nextInt();
            accelCommand(accel);
            int i = 1;
            while (i < players && winner == NO_WINNER) handleOpponentMovement(i++);

            if (winner == NO_WINNER) printCarStatus(carsNames[PLAYER_CAR_ID]);
            hasYellowFlag = false;
            rounds++;
        }
        case SHOW_CMD -> System.out.printf(SHOW_TEMPLATE, getCharArrayAsString(getTrackWithPlayers()), getRaceStatus());
        case STATUS_CMD -> {
            char carName = in.next().toCharArray()[0];
            printCarStatus(carName);
        }
        case QUIT_CMD -> quitCommand();
        default -> {
            in.nextLine();
            System.out.println(INVALID_MSG);
        }
    }
}

/**
 * Turns a char array into a string
 * @param charArray array of characters
 * @return a string containing every character
 */
String getCharArrayAsString(char[] charArray) {
    String string = "";
    for (int c = 0; c < track.length; c++) {
        string += charArray[c];
    }

    return string;
}

/**
 * Clones a character array
 * @param array character array to clone
 * @return cloned character array
 */
char[] cloneCharArray(char[] array) {
    char[] clonedArray = new char[array.length];
    for (int i = 0; i < array.length; i++)
        clonedArray[i] = array[i];
    return clonedArray;
}

/**
 * Retrieves the track including player cars
 * @return character array including track pieces and player cars
 */
char[] getTrackWithPlayers() {
    char[] tempTrack = cloneCharArray(track);

    for (int i = 0; i < players; i++) {
        int carPos = carsPositions[i];
        tempTrack[carPos] = carsNames[i];
    }
    return tempTrack;
}

/**
 * General function for car acceleration
 * @param carID car index on the data arrays (P is always 0, a is always 1, etc...)
 * @param acceleration acceleration value to add to the car
 */
void accelerate(int carID, int acceleration) {
    addCarSpeed(carID, acceleration);
    setCarPosition(carID, carsSpeeds[carID] + carsPositions[carID]);

    handleCollisions(carID);
    handleInteractions(carID);

    if (carsLaps[carID] >= raceLaps) {
        winner = carsNames[carID];
        System.out.printf(PLAYER_WON_TEMPLATE, winner);
    }
}

/**
 * Prints {@code carID}'s cell position and current laps
 * @param carName car name (P, a, etc...)
 */
void printCarStatus(char carName) {
    int carID = getElementPosition(carName, carsNames);
    if (carID == -1) {
        System.out.printf("Player %s does not exist!\n", carName);
    } else {
        if (winner != carName) System.out.printf(CAR_STATUS_TEMPLATE, carName, carsPositions[carID], Math.max(0, carsLaps[carID]));
        else System.out.printf(RACE_ENDED_TEMPLATE, winner);
    }

}

/**
 * User command for acceleration
 * @param accel acceleration value to add to the user's car
 */
void accelCommand(int accel) {
    if (winner == NO_WINNER) {
        accelerate(PLAYER_CAR_ID, accel);
    } else System.out.printf(RACE_ENDED_TEMPLATE, winner);

}

/**
 * User command for quiting
 */
void quitCommand() {
    running = false;
    if (winner != NO_WINNER) System.out.printf(RACE_ENDED_TEMPLATE, winner);
    else System.out.println(RACE_OVER_MSG);
}

void main() {
    Scanner input = new Scanner(System.in);
    init(defineTrack(input.nextLine()), input.nextInt(), input.nextInt(), input.nextInt());
    input.nextLine();

    do {
        readCommand(input);
    } while (running);

}

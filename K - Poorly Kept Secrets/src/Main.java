import java.util.Objects;
import java.util.Scanner;

final String LOCKED = "#";

int drawers;
int inaccessibleDrawers;
String lastDrawerState;

void init(int nDrawers) {
    drawers = nDrawers;
    inaccessibleDrawers = 0;
    lastDrawerState = LOCKED;
}

int readInt(Scanner input) {
    int val = input.nextInt();
    input.nextLine();
    return val;
}

String readStr(Scanner input) {
    String val = input.next();
    input.nextLine();
    return val;
}

boolean canOpen(String drawerState) {
    if (!Objects.equals(drawerState, LOCKED) || !Objects.equals(lastDrawerState, LOCKED)) {
       lastDrawerState = drawerState;
       return true;
    }
    return false;
}

void main() {
    Scanner input = new Scanner(System.in);
    init(readInt(input));
    for (int i = 0; i < drawers; i++) {
       String state = readStr(input);
       if (!canOpen(state)) inaccessibleDrawers++;
    }
    System.out.println(inaccessibleDrawers);
}
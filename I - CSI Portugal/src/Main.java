import java.util.Scanner;

final String ALIBI_MSG = "Com alibi";
final String NO_ALIBI_MSG = "Sem alibi";

boolean fitsTimeFrame(int crimeStart, int crimeEnd, int alibiStart, int alibiEnd) {
    return (crimeStart >= alibiStart) && (crimeEnd <= alibiEnd);
}

String getAlibiMessage(boolean hasAlibi) {
    if (hasAlibi) return ALIBI_MSG;
    return NO_ALIBI_MSG;
}

boolean hasAlibi(Scanner input) {
    int crimeStart = input.nextInt();
    int crimeEnd = input.nextInt();
    input.nextLine();
    int alibiStart = input.nextInt();
    int alibiEnd = input.nextInt();
    input.nextLine();
    return fitsTimeFrame(crimeStart, crimeEnd, alibiStart, alibiEnd);
}

void announceAlibi(boolean hasAlibi) {
    final String MSG = getAlibiMessage(hasAlibi);
    System.out.println(MSG);
}

void main() {
    Scanner input = new Scanner(System.in);
    boolean firstCase = hasAlibi(input);
    boolean secondCase = hasAlibi(input);
    boolean thirdCase = hasAlibi(input);
    boolean forthCase = hasAlibi(input);

    announceAlibi(firstCase);
    announceAlibi(secondCase);
    announceAlibi(thirdCase);
    announceAlibi(forthCase);
}
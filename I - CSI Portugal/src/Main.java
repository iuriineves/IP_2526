import java.util.Scanner;

boolean fitsTimeFrame(int crimeStart, int crimeEnd, int alibiStart, int alibiEnd) {
    return (crimeStart >= alibiStart) && (crimeEnd <= alibiEnd);
}

boolean hasAlibi(Scanner input) {
    int crimeStart = input.nextInt();
    int crimeEnd = input.nextInt();
    input.nextLine();
    int alibiStart = input.nextInt();
    int alibiEnd = input.nextInt();
    return fitsTimeFrame(crimeStart, crimeEnd, alibiStart, alibiEnd);
}

void announceAlibi(boolean hasAlibi) {
    if (hasAlibi) {
        System.out.println("Com alibi");
    } else {
        System.out.println("Sem alibi");
    }
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
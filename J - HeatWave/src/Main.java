import java.util.Scanner;

int avgTemp;
int days;
int consecutiveDays;

final String HEATWAVE_MSG = "WAVE";
final String FLAT_MSG = "FLAT";
final int CONSECUTIVE_DAYS_FOR_HEATWAVE = 6;
final int TEMP_DIFF = 5;

void init(int avgTempInit, int daysInit) {
    avgTemp = avgTempInit;
    days = daysInit;
    consecutiveDays = 0;
}

void registerTemp(int temp) {
    if (temp >= avgTemp + TEMP_DIFF) consecutiveDays++;
    else consecutiveDays = 0;
}

int readTemp(Scanner in) {
    int temp = in.nextInt();
    in.nextLine();
    return temp;
}
void predictHeatwave() {
    if (consecutiveDays >= CONSECUTIVE_DAYS_FOR_HEATWAVE) System.out.println(HEATWAVE_MSG);
    else System.out.println(FLAT_MSG);
}
void main() {
    Scanner input = new Scanner(System.in);

    init(readTemp(input), readTemp(input));
    do {
        registerTemp(readTemp(input));
    } while(consecutiveDays < 6);
    predictHeatwave();
}
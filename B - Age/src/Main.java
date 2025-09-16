import java.util.Scanner;

void main() {

    Scanner input = new Scanner(System.in);
    String data = input.nextLine();
    input.close();

    int age = Integer.parseInt(data.split(" ")[2]);

    System.out.println(age);

}
import java.io.File;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Menu MENU = new Menu();

    public static void main(String[] args) {
        MENU.printWelcome();
        String fileName = SCANNER.nextLine();
        String filepath = "src/main/resources/" + fileName + ".txt";
        File file = new File(filepath);
        DataReadFromFile dataReadFromFile = new Solution(file);
        MENU.printMenu();
        int userInput = SCANNER.nextInt();
        while (userInput != 0) {
            switch (userInput) {
                case 1:
                    System.out.println(dataReadFromFile.getLongestWord());
                    break;
                case 2:
                    System.out.println(dataReadFromFile.getSecondLongestWord());
                    break;
                case 3:
                    System.out.println(dataReadFromFile.getAmountOfConcatWords());
                    break;
                default:
                    System.out.println("Wrong entered data, try again");
            }
            MENU.printMenu();
            userInput = SCANNER.nextInt();
        }
    }
}

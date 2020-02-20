public class Menu {

    public void printWelcome() {
        System.out.println("Welcome!");
        System.out.println("To start please enter name of your file"
                + " (it should be located in \"resources\" folder).");
        System.out.println("File name: ");
    }

    public void printMenu() {
        System.out.println("##################################");
        System.out.println("1. Get longest concatenated word");
        System.out.println("2. Get second longest concatenated word");
        System.out.println("3. Get total amount of concat words");
        System.out.println("0. Exit");
        System.out.println("##################################");
    }
}

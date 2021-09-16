import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    public static int inputInt(int minValue, int maxValue, String prompt) {
        boolean valideInput = false;
        String userInput;
        int numberToReturn = 0;

        do {
            System.out.print(prompt);
            userInput = scanner.nextLine();

            try {
                numberToReturn = Integer.parseInt(userInput);
                if(numberToReturn >= minValue && numberToReturn <= maxValue) valideInput = true;
            } catch (Exception ignored){}

            if(!valideInput) System.out.println("Erreur de saisie, vous devez saisir un nombre entre " + minValue + " et " + maxValue);
        } while (!valideInput);

        return numberToReturn;
    }

    public static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}

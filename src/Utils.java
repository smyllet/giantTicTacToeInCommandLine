import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);
    public static final ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));

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

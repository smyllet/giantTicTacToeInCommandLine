import java.util.*;

public class Game {
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> tokens = new ArrayList<>();
    static ArrayList<Integer> points = new ArrayList<>();

    static ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    static ArrayList<ArrayList<Integer>> grid;

    private static void generateGrid(int sizeOfGrid) {
        if(sizeOfGrid > 20) sizeOfGrid = 20;
        if(sizeOfGrid < 4) sizeOfGrid = 4;

        grid = new ArrayList<>();

        for(int i = 0; i < sizeOfGrid; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for(int j = 0; j < sizeOfGrid; j++) {
                column.add(0);
            }
            grid.add(column);
        }
    }

    private static String getStringCharForBoxMap(Integer box) {
        if(box == 0) return " ";
        else {
            if(box - (getPlayerOfBox(box)*10000) > 0) {
                return "\u001B[36m" + tokens.get(getPlayerOfBox(box)-1) + "\u001B[0m";
            } else return "\u001B[31m" + tokens.get(getPlayerOfBox(box)-1) + "\u001B[0m";
        }
    }

    private static void printGrid(int player) {
        ArrayList<String> stringLineArray = new ArrayList<>();
        ArrayList<String> scoreBoardArray = new ArrayList<>();

        for (int i = 0; i <= grid.size(); i++) {
            if(i == 0) {
                StringBuilder firstLine = new StringBuilder("   ");
                for(int j = 1; j <= grid.size(); j++) {
                    if(j < 10) firstLine.append(" ").append(j).append("  ");
                    else firstLine.append(" ").append(j).append(" ");
                }
                stringLineArray.add(firstLine.toString());
            } else stringLineArray.add(alphabet.get(i-1) + " |");
        }

        for(ArrayList<Integer> column : grid) {
            for(int i = 0; i < column.size() ; i++) {
                stringLineArray.set(i+1, stringLineArray.get(i+1) + " " + getStringCharForBoxMap(column.get(i)) + " |");
            }
        }

        scoreBoardArray.add("Tableau des scores");
        scoreBoardArray.add("");
        for(int i = 0; i < points.size(); i++) {
            scoreBoardArray.add(((player == i+1) ? "\u001B[32m" : "") + "Joueur " + (i+1) + " (" + tokens.get(i) + ") : " + points.get(i) + "\u001B[0m");
        }

        int spaceUp = (stringLineArray.size()-scoreBoardArray.size())/2;
        for(int i = 0; i < stringLineArray.size(); i++) {
            String lineToPrint = stringLineArray.get(i);

            if(i >= spaceUp && i < (scoreBoardArray.size()+spaceUp)) lineToPrint += "          " + scoreBoardArray.get(i-spaceUp);

            System.out.println(lineToPrint);
        }
    }

    private static Map<String, Integer> getColumnAndLineFromUserInput(String userInput) {
        Map<String, Integer> columnAndLine = new HashMap<>();

        columnAndLine.put("line", alphabet.indexOf(userInput.substring(0,1)));
        columnAndLine.put("column", Integer.parseInt(userInput.substring(1))-1);

        return columnAndLine;
    }

    private static boolean addToken(int columnNumber, int lineNumber, int player) {
        boolean tokenPlayed = false;

        if(columnNumber < grid.size() && columnNumber >= 0 && lineNumber < grid.size() && lineNumber >= 0) {
            if(grid.get(columnNumber).get(lineNumber) == 0) {
                grid.get(columnNumber).set(lineNumber, player*10000);
                tokenPlayed = true;
            }
        }

        return tokenPlayed;
    }

    private static int getPlayerOfBox(Integer box) {
        if(box >= 10000) return Integer.parseInt(box.toString().substring(0,1));
        else return 0;
    }

    private static int getColumnConsumationOfBox(Integer box) {
        if(box >= 10000) return Integer.parseInt(box.toString().substring(4,5));
        else return 0;
    }

    private static int getLineConsumationOfBox(Integer box) {
        if(box >= 10000) return Integer.parseInt(box.toString().substring(3,4));
        else return 0;
    }

    private static int getDiagUpDownConsumationOfBox(Integer box) {
        if(box >= 10000) return Integer.parseInt(box.toString().substring(2,3));
        else return 0;
    }

    private static int getDiagDownUpConsumationOfBox(Integer box) {
        if(box >= 10000) return Integer.parseInt(box.toString().substring(1,2));
        else return 0;
    }

    private static void checkWin(int columnNumber, int lineNumber) {
        int player = getPlayerOfBox(grid.get(columnNumber).get(lineNumber));

        // check colonne
        StringBuilder stringChecker = new StringBuilder();
        for(Integer box : grid.get(columnNumber)) {
            if(getPlayerOfBox(box) == player && getColumnConsumationOfBox(box) == 0) stringChecker.append("#");
            else stringChecker.append(" ");
        }
        stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
        for(int i = 0; i < grid.size(); i++) {
            if(stringChecker.charAt(i) == 'X') grid.get(columnNumber).set(i, grid.get(columnNumber).get(i)+1);
        }
        if(stringChecker.toString().contains("X")) points.set(player-1, points.get(player-1)+1);

        // Check ligne
        stringChecker = new StringBuilder();
        for(ArrayList<Integer> column : grid) {
            for(int i = 0; i < grid.size(); i++) {
                if(i == lineNumber) {
                    Integer box = column.get(i);
                    if(getPlayerOfBox(box) == player && getLineConsumationOfBox(box) == 0) stringChecker.append("#");
                    else stringChecker.append(" ");
                }
            }
        }
        stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid.size(); j++) {
                if(j == lineNumber) {
                    if(stringChecker.charAt(i) == 'X') grid.get(i).set(j, grid.get(i).get(j)+10);
                }
            }
        }
        if(stringChecker.toString().contains("X")) points.set(player-1, points.get(player-1)+1);

        // Check Diagonale Haut Bas
        stringChecker = new StringBuilder();
        for(int i = 0; i < grid.size(); i++) {
            stringChecker.append(" ");
            for(int j = 0; j < grid.size(); j++) {
                if(j == (lineNumber+(i-columnNumber)) || j == (lineNumber-(columnNumber-i)) || ((i == columnNumber) && (j == lineNumber))) {
                    Integer box = grid.get(i).get(j);
                    if(getPlayerOfBox(box) == player && getDiagUpDownConsumationOfBox(box) == 0) stringChecker.setCharAt(i, '#');
                }
            }
        }
        stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid.size(); j++) {
                if(((i < columnNumber) && (j == (lineNumber+(i-columnNumber)))) || ((i > columnNumber) && (j == lineNumber-(columnNumber-i))) || ((i == columnNumber) && (j == lineNumber))) {
                    if(stringChecker.charAt(i) == 'X') grid.get(i).set(j, grid.get(i).get(j)+100);
                }
            }
        }
        if(stringChecker.toString().contains("X")) points.set(player-1, points.get(player-1)+1);

        // Check Diagonale Bas Haut
        stringChecker = new StringBuilder();
        for(int i = 0; i < grid.size(); i++) {
            stringChecker.append(" ");
            for(int j = 0; j < grid.size(); j++) {
                if(j == (lineNumber-(i-columnNumber)) || j == (lineNumber+(columnNumber-i)) || ((i == columnNumber) && (j == lineNumber))) {
                    Integer box = grid.get(i).get(j);
                    if(getPlayerOfBox(box) == player && getDiagDownUpConsumationOfBox(box) == 0) stringChecker.setCharAt(i,'#');
                }
            }
        }
        stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid.size(); j++) {
                if(j == (lineNumber-(i-columnNumber)) || j == (lineNumber+(columnNumber-i)) || ((i == columnNumber) && (j == lineNumber))) {
                    if(stringChecker.charAt(i) == 'X') grid.get(i).set(j, grid.get(i).get(j)+100);
                }
            }
        }
        if(stringChecker.toString().contains("X")) points.set(player-1, points.get(player-1)+1);
    }

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

    public static void main(String[] args) {
        System.out.println("Bonjour, bienvenue au jeu du Morpion pour les étudiants qui s'ennuie vraiment en amphi");

        int numberOfPlayer = inputInt(2, 6, "Veuillez saisir le nombre de joueur (2-6) : ");


        for(int i = 1; i <= numberOfPlayer; i++) {
            String token;
            boolean valideInput = false;

            System.out.println("Saisie des jetons des joueurs");

            do {
                System.out.print("Caractère joueur " + i + " : ");
                token = scanner.nextLine();
                if(token.length() == 1) {
                    if(!tokens.contains(token)) {
                        tokens.add(token);
                        points.add(0);
                        valideInput = true;
                    }
                    else System.out.println("Ce caractère est déjà utilisé par un autre joueur");
                } else System.out.println("Veuillez saisir un seul caractère");
            } while (!valideInput);
        }

        int gridSizeInput = inputInt(numberOfPlayer+2, 20, "Veuillez definer la taille de la grille (" + (numberOfPlayer+2) + "-20) : ");

        generateGrid(gridSizeInput);

        int player = 1;
        boolean end = false;
        String playerInput;

        do {
            printGrid(player);

            System.out.print("Joueur " + (player) + " (" + tokens.get(player-1) + ") > ");
            playerInput = scanner.nextLine().toUpperCase();
            if(playerInput.equalsIgnoreCase("fin")) end = true;
            else {
                Map<String, Integer> playerPosition = getColumnAndLineFromUserInput(playerInput);
                if(addToken(playerPosition.get("column"), playerPosition.get("line"), player)) {
                    checkWin(playerPosition.get("column"), playerPosition.get("line"));
                    if(player == tokens.size()) player = 1;
                    else player++;
                } else System.out.println("Case déjà occupé, veuillez saisir une autres case");
            }
        } while (!end);
    }
}

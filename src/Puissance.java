import java.util.*;

public class Puissance {
    static ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    static ArrayList<ArrayList<String>> grid;

    private static void generateGrid(int sizeOfGrid) {
        if(sizeOfGrid > 20) sizeOfGrid = 20;
        if(sizeOfGrid < 4) sizeOfGrid = 4;

        grid = new ArrayList<>();

        for(int i = 0; i < sizeOfGrid; i++) {
            ArrayList<String> column = new ArrayList<>();
            for(int j = 0; j < sizeOfGrid; j++) {
                column.add(" ");
            }
            grid.add(column);
        }
    }

    private static void printGrid() {
        ArrayList<String> stringLineArray = new ArrayList<>();

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

        for(ArrayList<String> column : grid) {
            for(int i = 0; i < column.size() ; i++) {
                stringLineArray.set(i+1, stringLineArray.get(i+1) + " " + column.get(i) + " |");
            }
        }

        for(String lineToPrint : stringLineArray) {
            System.out.println(lineToPrint);
        }
    }

    private static Map<String, Integer> getColumnAndLineFromUserInput(String userInput) {
        Map<String, Integer> columnAndLine = new HashMap<>();

        columnAndLine.put("line", alphabet.indexOf(userInput.substring(0,1)));
        columnAndLine.put("column", Integer.parseInt(userInput.substring(1))-1);

        return columnAndLine;
    }

    private static boolean addToken(int columnNumber, int lineNumber, String token) {
        boolean tokenPlayed = false;

        if(columnNumber < grid.size() && columnNumber >= 0 && lineNumber < grid.size() && lineNumber >= 0) {
            if(grid.get(columnNumber).get(lineNumber).equals(" ")) {
                grid.get(columnNumber).set(lineNumber, token);
                tokenPlayed = true;
            }
        }

        return tokenPlayed;
    }

    public static void main(String[] args) {
        generateGrid(20);

        Map<String, Integer> temp = getColumnAndLineFromUserInput("E5");

        addToken(temp.get("column"), temp.get("line"), "O");
        printGrid();
    }
}

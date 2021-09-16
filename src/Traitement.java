import java.util.ArrayList;

public class Traitement {
    public static int getXFromUserInput(String userInput) throws Exception {
        if(userInput.length() > 1) {
            try {
                return Integer.parseInt(userInput.substring(1))-1;
            } catch (Exception e) {
                throw new Exception();
            }
        } else throw new Exception();
    }

    public static int getYFromUserInput(String userInput) throws Exception {
        if(userInput.length() > 1) {
            try {
                int y = Utils.alphabet.indexOf(userInput.substring(0,1));
                if(y != -1) return y;
                else throw new Exception();
            } catch (Exception e) {
                throw new Exception();
            }
        } else throw new Exception();
    }

    public static String getStringGrid(Grid grid, ArrayList<Player> playerList, Player highLightPlayer, boolean win) {
        ArrayList<String> stringLineArray = new ArrayList<>();
        ArrayList<String> scoreBoardArray = new ArrayList<>();

        for (int i = 0; i <= grid.getSize(); i++) {
            if(i == 0) {
                StringBuilder firstLine = new StringBuilder("   ");
                for(int j = 1; j <= grid.getSize(); j++) {
                    if(j < 10) firstLine.append(" ").append(j).append("  ");
                    else firstLine.append(" ").append(j).append(" ");
                }
                stringLineArray.add(firstLine.toString());
            } else stringLineArray.add(Utils.alphabet.get(i-1) + " |");
        }

        for(ArrayList<Box> column : grid.getBoxList()) {
            for(int i = 0; i < column.size() ; i++) {
                stringLineArray.set(i+1, stringLineArray.get(i+1) + " " + ((column.get(i).getPlayer() != null) ? column.get(i).toString() : " ") + " |");
            }
        }

        scoreBoardArray.add("Tableau des scores");
        scoreBoardArray.add("");
        for(Player player : playerList) {
            scoreBoardArray.add(((player == highLightPlayer) ? ((win) ? "\u001B[35m" : "\u001B[32m") : "") + "Joueur " + (player.getNumber()) + " (" + player.getToken() + ") : " + player.getPoint() + "\u001B[0m");
        }
        int spaceUp = (stringLineArray.size()-scoreBoardArray.size())/2;

        for(int i = 0; i < stringLineArray.size(); i++) {
            String lineToPrint = stringLineArray.get(i);

            if(i >= spaceUp && i < (scoreBoardArray.size()+spaceUp)) stringLineArray.set(i, lineToPrint + "          " + scoreBoardArray.get(i-spaceUp));
        }

        return String.join("\n", stringLineArray);
    }

    public static String getStringGrid(Grid grid, ArrayList<Player> playerList) {
        return getStringGrid(grid, playerList, null, false);
    }
}
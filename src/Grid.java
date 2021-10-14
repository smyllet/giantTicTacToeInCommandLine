import exception.*;

import java.util.ArrayList;

public class Grid {
    protected ArrayList<ArrayList<Box>> boxList;

    Grid(int size) {
        if(size > 20) size = 20;
        if(size < 4) size = 4;

        boxList = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            ArrayList<Box> column = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                column.add(new Box());
            }
            boxList.add(column);
        }
    }

    public void play(Player player, int x, int y) throws BoxOutOfRangeException, BoxAlreadyPlayedException, NegativePointParameterException, CheckWinInvalideDataException {
        if(x < boxList.size() && x >= 0 && y < boxList.size() && y >= 0) {
            if(boxList.get(x).get(y).getPlayer() == null) {
                boxList.get(x).get(y).setPlayer(player);
                int point = checkWinAndConsumes(x, y);
                player.addPoint(point);
            } else throw new BoxAlreadyPlayedException();
        } else throw new BoxOutOfRangeException();
    }

    public int getTotalNbBox() {
        return (int) (Math.pow(boxList.size(),2));
    }

    private int checkWinAndConsumes(int x, int y) throws BoxOutOfRangeException, CheckWinInvalideDataException {
        Player player;

        try {
            player = this.boxList.get(x).get(y).getPlayer();
        } catch (IndexOutOfBoundsException e) {
            throw new BoxOutOfRangeException();
        }
        int points = 0;

        try {
            // check colonne
            StringBuilder stringChecker = new StringBuilder();
            for(Box box : this.boxList.get(x)) {
                if(box.getPlayer() == player && !box.isColumnConsommation()) stringChecker.append("#");
                else stringChecker.append(" ");
            }
            stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
            for(int i = 0; i < this.boxList.size(); i++) {
                if(stringChecker.charAt(i) == 'X') this.boxList.get(x).get(i).consumeColumn();
            }
            if(stringChecker.toString().contains("X")) points++;

            // Check ligne
            stringChecker = new StringBuilder();
            for(ArrayList<Box> column : this.boxList) {
                for(int i = 0; i < this.boxList.size(); i++) {
                    if(i == y) {
                        Box box = column.get(i);
                        if(box.getPlayer() == player && !box.isLineConsommation()) stringChecker.append("#");
                        else stringChecker.append(" ");
                    }
                }
            }
            stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
            for(int i = 0; i < this.boxList.size(); i++) {
                for(int j = 0; j < this.boxList.size(); j++) {
                    if(j == y) {
                        if(stringChecker.charAt(i) == 'X') this.boxList.get(i).get(j).consumeLine();
                    }
                }
            }
            if(stringChecker.toString().contains("X")) points++;

            // Check Diagonale Haut Bas
            stringChecker = new StringBuilder();
            for(int i = 0; i < this.boxList.size(); i++) {
                stringChecker.append(" ");
                for(int j = 0; j < this.boxList.size(); j++) {
                    if(j == (y+(i-x)) || j == (y-(x-i)) || ((i == x) && (j == y))) {
                        Box box = this.boxList.get(i).get(j);
                        if(box.getPlayer() == player && !box.isDiagUpDownConsommation()) stringChecker.setCharAt(i, '#');
                    }
                }
            }
            stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
            for(int i = 0; i < this.boxList.size(); i++) {
                for(int j = 0; j < this.boxList.size(); j++) {
                    if(((i < x) && (j == (y+(i-x)))) || ((i > x) && (j == y-(x-i))) || ((i == x) && (j == y))) {
                        if(stringChecker.charAt(i) == 'X') this.boxList.get(i).get(j).consumeDiagUpDown();
                    }
                }
            }
            if(stringChecker.toString().contains("X")) points++;

            // Check Diagonale Bas Haut
            stringChecker = new StringBuilder();
            for(int i = 0; i < this.boxList.size(); i++) {
                stringChecker.append(" ");
                for(int j = 0; j < this.boxList.size(); j++) {
                    if(j == (y-(i-x)) || j == (y+(x-i)) || ((i == x) && (j == y))) {
                        Box box = this.boxList.get(i).get(j);
                        if(box.getPlayer() == player && !box.isDiagDownUpConsommation()) stringChecker.setCharAt(i,'#');
                    }
                }
            }
            stringChecker = new StringBuilder(stringChecker.toString().replace("####", "XXXX"));
            for(int i = 0; i < this.boxList.size(); i++) {
                for(int j = 0; j < this.boxList.size(); j++) {
                    if(j == (y-(i-x)) || j == (y+(x-i)) || ((i == x) && (j == y))) {
                        if(stringChecker.charAt(i) == 'X') this.boxList.get(i).get(j).consumeDiagDownUp();
                    }
                }
            }
            if(stringChecker.toString().contains("X")) points++;

            return points;
        } catch (Exception e) {
            throw new CheckWinInvalideDataException();
        }
    }

    public int getSize() {
        return boxList.size();
    }

    public ArrayList<ArrayList<Box>> getBoxList() {
        return boxList;
    }
}
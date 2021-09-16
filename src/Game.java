import java.util.*;

public class Game {
    static Grid grid;

    static ArrayList<Player> playerList;

    public static void main(String[] args) {
        System.out.println("Bonjour, bienvenue au jeu du Morpion pour les étudiants qui s'ennuie vraiment en amphi");

        int numberOfPlayer = Utils.inputInt(2, 6, "Veuillez saisir le nombre de joueur (2-6) : ");
        playerList = new ArrayList<>();

        int gridSizeInput = Utils.inputInt(numberOfPlayer+2, 20, "Veuillez definer la taille de la grille (" + (numberOfPlayer+2) + "-20) : ");

        grid = new Grid(gridSizeInput);

        for(int i = 1; i <= numberOfPlayer; i++) {
            String token;
            boolean valideInput = false;

            System.out.println("Saisie des jetons des joueurs");

            do {
                token = Utils.inputString("Caractère joueur " + i + " : ");
                if(token.length() == 1) {
                    Player player = new Player(i, token.charAt(0));
                    if(playerList.stream().filter(p -> p.getToken() == player.getToken()).toArray().length < 1) {
                        playerList.add(player);
                        valideInput = true;
                    }
                    else System.out.println("Ce caractère est déjà utilisé par un autre joueur");
                } else System.out.println("Veuillez saisir un seul caractère");
            } while (!valideInput);
        }

        int player = 1;
        boolean end = false;
        String playerInput;
        int totalPlayerTurn = 0;

        do {
            Player actualPlayer = playerList.get(player-1);

            System.out.println(Utils.getStringGrid(grid, playerList, actualPlayer, false));

            playerInput = Utils.inputString("Joueur " + (player) + " (" + actualPlayer.getToken() + ") > ").toUpperCase();

            if(playerInput.equalsIgnoreCase("fin")) end = true;
            else {
                try {
                    int x = Utils.getXFromUserInput(playerInput);
                    int y = Utils.getYFromUserInput(playerInput);

                    if(grid.play(actualPlayer, x, y)) {
                        totalPlayerTurn++;

                        if(player == playerList.size()) player = 1;
                        else player++;
                    } else System.out.println("Case déjà occupé, veuillez saisir une autres case");
                } catch (Exception e) {
                    System.out.println("Saisie incorrect");
                }
            }
        } while (!end && totalPlayerTurn < grid.getTotalNbBox());

        Player winner = playerList.get(0);

        for(Player p : playerList) {
            if(p.getPoint() >= winner.getPoint()) winner = p;
        }

        System.out.println(" - - - - - Fin de partie - - - - - ");
        if(winner.getPoint() > 0) {
            System.out.println(Utils.getStringGrid(grid, playerList, winner, true));
            System.out.println("Félicitation au joueur " + (winner.getNumber()) + " (" + winner.getToken() + ") qui gagne avec " + winner.getPoint() + " point(s)");
        } else System.out.println(Utils.getStringGrid(grid, playerList));
    }
}

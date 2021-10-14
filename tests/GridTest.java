import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GridOfTest extends Grid {
    GridOfTest(int size) {
        super(size);
    }

    @Override
    public String toString() {
        ArrayList<String> stringLineArray = new ArrayList<>();

        for(ArrayList<Box> column : super.getBoxList()) {
            for(int i = 0; i < column.size() ; i++) {
                if(stringLineArray.size() <= i) stringLineArray.add("");
                char token;
                if(column.get(i).getPlayer() == null) token = '.';
                else token = column.get(i).getPlayer().getToken();
                stringLineArray.set(i, stringLineArray.get(i) + token);
            }
        }

        return String.join("\n", stringLineArray);
    }

    public void parse(String stringGrid, ArrayList<Player> playerList) {
        ArrayList<String> stringLineArray = new ArrayList<>(Arrays.asList(stringGrid.split("\n")));

        super.boxList = new ArrayList<>();

        for(String line : stringLineArray) {
            for(int i = 0; i < line.length(); i++) {
                if(super.boxList.size() <= i) super.boxList.add(new ArrayList<>());
                Box box = new Box();
                for(Player player : playerList) {
                    char lineToken = line.charAt(i);
                    if(player.getToken() == lineToken) {
                        box.setPlayer(player);
                    }
                }
                super.boxList.get(i).add(box);
            }
        }
    }
}

public class GridTest {
    GridOfTest gridOfSize8;
    Player player1, player2;

    @BeforeEach
    void setUp() {
        gridOfSize8 = new GridOfTest(8);
        player1 = new Player(1, 'X');
        player2 = new Player(2, 'O');
    }

    @Test
    final void constructor() {
        GridOfTest gridOfSize3 = new GridOfTest(3);
        assertEquals(4, gridOfSize3.getSize());

        GridOfTest gridOfSize4 = new GridOfTest(4);
        assertEquals(4, gridOfSize4.getSize());

        GridOfTest gridOfSizeMoins1 = new GridOfTest(-1);
        assertEquals(4, gridOfSizeMoins1.getSize());

        GridOfTest gridOfSize20 = new GridOfTest(20);
        assertEquals(20, gridOfSize20.getSize());

        GridOfTest gridOfSize21 = new GridOfTest(21);
        assertEquals(20, gridOfSize21.getSize());
    }

    @Test
    final void getTotalNbBox() {
        assertEquals(64, gridOfSize8.getTotalNbBox());
    }

    @Test
    final void play() {
        try {
            assertEquals("........\n........\n........\n........\n........\n........\n........\n........", gridOfSize8.toString());

            gridOfSize8.play(player1, 2, 3);
            assertEquals("........\n........\n........\n..X.....\n........\n........\n........\n........", gridOfSize8.toString());

            gridOfSize8.play(player2, 5, 6);
            assertEquals("........\n........\n........\n..X.....\n........\n........\n.....O..\n........", gridOfSize8.toString());

            gridOfSize8.parse("...X...O\n...O....\n........\n..XXX...\n........\n........\n.....O..\n........", new ArrayList<>(Arrays.asList(player1, player2)));
            assertEquals("...X...O\n...O....\n........\n..XXX...\n........\n........\n.....O..\n........", gridOfSize8.toString());

            gridOfSize8.parse("........\n........\n........\n..XXX...\n........\n........\n........\n........", new ArrayList<>(Arrays.asList(player1, player2)));
            gridOfSize8.play(player1, 5, 3);
            assertEquals("........\n........\n........\n..XXXX..\n........\n........\n........\n........", gridOfSize8.toString());

        } catch (Exception e) {
            fail();
        }
    }
}

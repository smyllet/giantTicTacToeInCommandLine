import exception.NegativePointParameterException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    static Player player1, player2, player3, player4;

    @BeforeAll
    static void setUpAll() {
        player1 = new Player(1, 'X');
        player2 = new Player(2, 'O');
        player3 = new Player(3, 'H');
        player4 = new Player(4, 'E');
    }

    @BeforeEach
    void setUp() {
        try {
            player1.setPoint(3);
            player2.setPoint(3);
            player3.setPoint(0);
            player4.setPoint(4);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    final void addPoint() {
        try {
            player1.addPoint(1);
            assertEquals(4, player1.getPoint());

            player2.addPoint(3);
            assertEquals(6, player2.getPoint());

            player3.addPoint(1);
            assertEquals(1, player3.getPoint());
            player3.addPoint(1);
            assertEquals(2, player3.getPoint());
        } catch (Exception e) {
            fail();
        }

        assertThrows(NegativePointParameterException.class, () -> player4.addPoint(-3));
        assertEquals(4, player4.getPoint());
    }

    @Test
    final void removePoint() {
        try {
            player1.removePoint(1);
            assertEquals(2, player1.getPoint());
            player1.removePoint(1);
            assertEquals(1, player1.getPoint());

            player2.removePoint(2);
            assertEquals(1, player2.getPoint());

            player3.removePoint(1);
            assertEquals(0, player3.getPoint());

            player4.removePoint(4);
            assertEquals(0, player4.getPoint());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    final void getNumber() {
        assertEquals(1, player1.getNumber());
        assertEquals(2, player2.getNumber());
        assertEquals(3, player3.getNumber());
        assertEquals(4, player4.getNumber());
    }

    @Test
    final void getToken() {
        assertEquals('X', player1.getToken());
        assertEquals('O', player2.getToken());
        assertEquals('H', player3.getToken());
        assertEquals('E', player4.getToken());
    }
}

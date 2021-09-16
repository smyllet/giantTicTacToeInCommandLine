import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {
    Player player1;
    Box box1, box2;

    @BeforeEach
    void setUp() {
        player1 = new Player(1, 'X');

        box1 = new Box();
        box1.setPlayer(player1);
        box2 = new Box();
    }

    @Test
    final void constructValue() {
        assertFalse(box1.isLineConsommation());
        assertFalse(box1.isColumnConsommation());
        assertFalse(box1.isDiagDownUpConsommation());
        assertFalse(box1.isDiagUpDownConsommation());
        assertEquals(player1, box1.getPlayer());

        assertFalse(box2.isLineConsommation());
        assertFalse(box2.isColumnConsommation());
        assertFalse(box2.isDiagDownUpConsommation());
        assertFalse(box2.isDiagUpDownConsommation());
        assertNull(box2.getPlayer());
    }

    @Test
    final void consumeLine() {
        box1.consumeLine();
        assertTrue(box1.isLineConsommation());
        assertFalse(box1.isColumnConsommation());
        assertFalse(box1.isDiagDownUpConsommation());
        assertFalse(box1.isDiagUpDownConsommation());

        box2.consumeLine();
        assertFalse(box2.isLineConsommation());
        assertFalse(box2.isColumnConsommation());
        assertFalse(box2.isDiagDownUpConsommation());
        assertFalse(box2.isDiagUpDownConsommation());
    }

    @Test
    final void consumeColumn() {
        box1.consumeColumn();
        assertFalse(box1.isLineConsommation());
        assertTrue(box1.isColumnConsommation());
        assertFalse(box1.isDiagDownUpConsommation());
        assertFalse(box1.isDiagUpDownConsommation());

        box2.consumeColumn();
        assertFalse(box2.isLineConsommation());
        assertFalse(box2.isColumnConsommation());
        assertFalse(box2.isDiagDownUpConsommation());
        assertFalse(box2.isDiagUpDownConsommation());
    }

    @Test
    final void consumeDiagUpDown() {
        box1.consumeDiagUpDown();
        assertFalse(box1.isLineConsommation());
        assertFalse(box1.isColumnConsommation());
        assertFalse(box1.isDiagDownUpConsommation());
        assertTrue(box1.isDiagUpDownConsommation());

        box2.consumeDiagUpDown();
        assertFalse(box2.isLineConsommation());
        assertFalse(box2.isColumnConsommation());
        assertFalse(box2.isDiagDownUpConsommation());
        assertFalse(box2.isDiagUpDownConsommation());
    }

    @Test
    final void consumeDiagDownUp() {
        box1.consumeDiagDownUp();
        assertFalse(box1.isLineConsommation());
        assertFalse(box1.isColumnConsommation());
        assertTrue(box1.isDiagDownUpConsommation());
        assertFalse(box1.isDiagUpDownConsommation());

        box2.consumeDiagDownUp();
        assertFalse(box2.isLineConsommation());
        assertFalse(box2.isColumnConsommation());
        assertFalse(box2.isDiagDownUpConsommation());
        assertFalse(box2.isDiagUpDownConsommation());
    }
}

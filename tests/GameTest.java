import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    int testVal;

    @BeforeEach
    void setUp() {
        testVal = 1;
    }

    @Test
    final void test() {

        assertEquals(1, testVal, "");
    }
}

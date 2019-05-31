import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    JPanel testPanel;
    Vector line;

    @BeforeEach
    public void Setup() {
        testPanel = new JPanel();
        line = null;

    }

    /*
    Test 1: Line Vector Output
     */
    @Test
    public void TestLineOutput() {
        line = new LineVector(0,0,1,1, testPanel);
        assertEquals("LINE 0.00 0.00 1.00 1.00",line.VectorOutputFormatted());

    }
}
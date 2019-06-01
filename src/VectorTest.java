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
        testPanel.setSize(100, 100);
        line = null;

    }

    /*
    Test 1: Line Vector Output
     */
    @Test
    public void TestLineOutput() {
        line = new LineVector(0,0,50,50, testPanel);
        assertEquals("LINE 0.00 0.00 0.50 0.50",line.VectorOutputFormatted());

    }
}
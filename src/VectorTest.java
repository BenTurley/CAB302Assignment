import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    JPanel testPanel;
    Vector line;
    Vector rectangle;
    Vector ellipse;
    Vector plot;
    Vector polygon;

    @BeforeEach
    public void Setup() {
        testPanel = new JPanel();
        testPanel.setSize(100, 100);
        line = null;
        rectangle = null;
        ellipse = null;
        plot = null;
        polygon = null;
    }

    /*
    Test 1: Line Vector Output
     */
    @Test
    public void TestLineOutput() {
        line = new LineVector(0,0,50,50, testPanel);
        assertEquals("LINE 0.00 0.00 0.50 0.50",line.VectorOutputFormatted());
    }

    /*
    Test 2: Rectangle Vector Output
    */
    @Test
    public void TestRectangleOutput() {
        rectangle = new RectangleVector(0,0,50,50, testPanel);
        assertEquals("RECTANGLE 0.00 0.00 0.50 0.50",rectangle.VectorOutputFormatted());
    }

    /*
    Test 3: Ellipse Vector Output
    */
    @Test
    public void TestEllipseOutput() {
        ellipse = new EllipseVector(0,0,50,50, testPanel);
        assertEquals("ELLIPSE 0.00 0.00 0.50 0.50",ellipse.VectorOutputFormatted());
    }

    /*
    Test 4: Plot Vector Output
    */
    @Test
    public void TestPlotOutput() {
        plot = new PlotVector(50,50, testPanel);
        assertEquals("PLOT 0.50 0.50",plot.VectorOutputFormatted());
    }

    /*
    Test 5: Polygon Vector Output
    */
    @Test
    public void TestPolygonOutput() {
        polygon = new PolygonVector(testPanel);
        ((PolygonVector) polygon).PolygonInitialise(10,10);
        ((PolygonVector) polygon).TestAddSide(50, 30);
        ((PolygonVector) polygon).TestAddSide(10, 50);
        ((PolygonVector) polygon).TestAddSide(5, 75);
        ((PolygonVector) polygon).TestAddSide(10, 10);
        assertEquals("POLYGON 0.10 0.10 0.50 0.30 0.10 0.50 0.05 0.75 0.10 0.10",polygon.VectorOutputFormatted());
    }

    /*
    Test 6: Line Vector for when x and y input is off screen
    */
    @Test
    public void TestLineBoundsLimit() {
        line = new LineVector(10,10,-100,-50, testPanel);
        assertEquals("LINE 0.10 0.10 0.00 0.00",line.VectorOutputFormatted());
    }

    /*
    Test 7: Rectangle Vector for when x and y input is off screen
    */
    @Test
    public void TestRectangleBoundsLimit() {
        rectangle = new RectangleVector(50,50,-50,-50, testPanel);
        assertEquals("RECTANGLE 0.50 0.50 0.00 0.00",rectangle.VectorOutputFormatted());
    }

    /*
    Test 8: Ellipse Vector for when x and y input is off screen
    */
    @Test
    public void TestEllipseBoundsLimit() {
        ellipse = new EllipseVector(50,50,-50,-50, testPanel);
        assertEquals("ELLIPSE 0.50 0.50 0.00 0.00",ellipse.VectorOutputFormatted());
    }


}
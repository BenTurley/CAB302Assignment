import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Handles initialization and drawing PlotVector on panels
 */
public class PlotVector implements Vector {
    private double x1;
    private double y1;
    private JPanel panel;
    private Color localColour;

    /**
     * Initializes PlotVector from x and y coordinates and drawing panel
     * @param x1
     * @param y1
     * @param panel
     */
    public PlotVector(double x1, double y1, JPanel panel) {
        this.x1 = x1;
        this.y1 = y1;
        this.panel = panel;
    }

    /**
     * Changes the outline colour of the Plot for when it's drawn
     * @param colour    Colour of outline
     */
    public void SetColour(Color colour) {
        this.localColour = colour;
    }

    /**
     * Draws the Plot
     */
    public void DrawVector() {
        Graphics g = panel.getGraphics();
        g.setColor(localColour);
        g.drawLine((int)x1,(int)y1,(int)x1,(int)y1);
    }

    /**
     * Formats the Rectangles's Vector coordinates in VEC language format
     * @return String of formatted coordinates of Rectangle Vector
     */
    public String VectorOutputFormatted() {
        double panelHeight = panel.getSize().height;
        double panelWidth = panel.getSize().width;

        DecimalFormat df = new DecimalFormat("0.00");

        double convertedX1 = (x1/panelWidth);
        double convertedY1 = (y1/panelHeight);

        String formattedX1 = df.format(convertedX1);
        String formattedY1 = df.format(convertedY1);

        return "PLOT " + formattedX1 + " " + formattedY1;
    }
}

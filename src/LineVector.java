import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Represents a line object
 */

public class LineVector implements Vector {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;
    private Color localColour;

    /**
     * Initialises LineVector
     * @param x1    x1 coordinate for line
     * @param y1    y1 coordinate for line
     * @param x2    x2 coordinate for line
     * @param y2    y2 coordinate for line
     * @param panel panel to draw line onto
     */
    public LineVector(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
    }

    /**
     * Sets LineVector colour
     * @param colour colour to set line
     */
    public void SetColour(Color colour) {
        this.localColour = colour;
    }

    /**
     * Draws LineVector to panel
     */
    public void DrawVector() {
        Graphics g = panel.getGraphics();
        g.setColor(localColour);
        g.drawLine((int) x1,(int)y1,(int)x2,(int)y2);
        boundsLimiter();
    }

    private void boundsLimiter(){
        double panelHeight = panel.getSize().height;
        double panelWidth = panel.getSize().width;

        double convertedX1 = (x1/panelWidth);
        double convertedY1 = (y1/panelHeight);
        double convertedX2 = (x2/panelWidth);
        double convertedY2 = (y2/panelHeight);


        if(convertedX1 < 0){
            x1 = 0;
        }
        if(convertedY1 < 0){
            y1 = 0;
        }
        if(convertedX2 < 0){
            x2 = 0;
        }
        if(convertedY2 < 0){
            y2 = 0;
        }
    }

    /**
     * Returns a line string formatted with line coordinates
     * @return formatted line command
     */
    @Override
    public String VectorOutputFormatted() {
        double panelHeight = panel.getSize().height;
        double panelWidth = panel.getSize().width;

        DecimalFormat df = new DecimalFormat("0.00");

        double convertedX1 = (x1/panelWidth);
        double convertedY1 = (y1/panelHeight);
        double convertedX2 = (x2/panelWidth);
        double convertedY2 = (y2/panelHeight);

        String formattedX1 = df.format(convertedX1);
        String formattedY1 = df.format(convertedY1);
        String formattedX2 = df.format(convertedX2);
        String formattedY2 = df.format(convertedY2);
        return "LINE " + formattedX1 + " " + formattedY1 + " " + formattedX2 + " " + formattedY2;
    }
}

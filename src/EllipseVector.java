import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Handles initialization and drawing EllipseVectors on panels
 */
public class EllipseVector implements Vector{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;
    private Color localColour;


    /**
     *Initializes Ellipse Vector from x and y coordinates and drawing panel
     * @param x1    x1 coordinate for Ellipse
     * @param y1    y1 coordinate for Ellipse
     * @param x2    x2 coordinate for Ellipse
     * @param y2    y2 coordinate for Ellipse
     * @param panel panel to draw Ellipse onto
     */
    public EllipseVector(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
        BoundsLimiter();
    }

    /**
     * Changes x or y coordinates that are initialized off the drawing panel to appearing on the edge of drawing panel
     */
    private void BoundsLimiter(){
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
     * Swaps x1 and x2 when called
     */
    private void xSwap(){
        int temp = (int) this.x2;
        this.x2 = this.x1;
        this.x1 = temp;
    }

    /**
     * Swaps y1 and y2 when called
     */
    private void ySwap(){
        int temp = (int) this.y2;
        this.y2 = this.y1;
        this.y1 = temp;
    }

    /**
     * Changes the outline colour of the Ellipse for when it is drawn
     * @param colour    Colour of outline
     */
    public void SetColour(Color colour) {
        this.localColour = colour;
    }

    /**
     * Draws the Ellipse
     */
    public void DrawVector() {
        int xDiff = (int) Math.abs(x2-x1);
        int yDiff = (int) Math.abs(y2-y1);

        if((this.x1 > this.x2) && (this.y1 <= this.y2)){
            xSwap();
        }

        else if((this.x1 <= this.x2) && (this.y1 > this.y2)){
            ySwap();
        }
        else if((this.x1 > this.x2) && (this.y1 > this.y2)){
            ySwap();
            xSwap();
        }

        Graphics g = panel.getGraphics();
        g.setColor(localColour);
        g.drawOval((int) x1,(int)y1,xDiff,yDiff);
    }

    /**
     * Draws the Ellipse with colour fill
     */
    public void FillVector(String colour) {
        int xDiff = (int) Math.abs(x2-x1);
        int yDiff = (int) Math.abs(y2-y1);

        if((this.x1 > this.x2) && (this.y1 <= this.y2)){
            xSwap();
        }

        else if((this.x1 <= this.x2) && (this.y1 > this.y2)){
            ySwap();
        }
        else if((this.x1 > this.x2) && (this.y1 > this.y2)){
            ySwap();
            xSwap();
        }

        System.out.println("FillVector set colour to: " + colour);
        Graphics g = panel.getGraphics();
        g.setColor(Color.decode(colour));
        System.out.println("FillVector set colour to: " + colour);

        g.fillOval((int) x1,(int)y1,xDiff,yDiff);
        //Draw outline
        g.setColor(localColour);
        g.drawOval((int) x1,(int)y1,xDiff,yDiff);
    }

    /**
     * Formats the Ellipse's Vector coordinates in VEC language format
     * @return String of formatted coordinates for Ellipse Vector
     */
    public String VectorOutputFormatted() {
        /* Make a class to handle this*/
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

        //System.out.println(convertedX1);
        //System.out.println(convertedY1);
        //System.out.println(convertedX2);
        //System.out.println(convertedY2);

        //System.out.println(formattedX1);
        //System.out.println(formattedY1);
        //System.out.println(formattedX2);
        //System.out.println(formattedY2);
        return "ELLIPSE " + formattedX1 + " " + formattedY1 + " " + formattedX2 + " " + formattedY2;
    }
}

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class LineVector implements Vector {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;
    private Color localColour;

    public LineVector(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
    }


    public void SetColour(Color colour) {
        this.localColour = colour;
    }

    public void DrawVector() {
        Graphics g = panel.getGraphics();
        g.setColor(localColour);
        g.drawLine((int) x1,(int)y1,(int)x2,(int)y2);

    }

    @Override
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
        return "LINE " + formattedX1 + " " + formattedY1 + " " + formattedX2 + " " + formattedY2;
    }
}

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class PlotVector implements Vector {
    private double x1;
    private double y1;
    private JPanel panel;

    public PlotVector(double x1, double y1, JPanel panel) {
        this.x1 = x1;
        this.y1 = y1;
        this.panel = panel;
    }

    public void DrawVector() {
        Graphics g = panel.getGraphics();
        g.drawLine((int)x1,(int)y1,(int)x1,(int)y1);
    }

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

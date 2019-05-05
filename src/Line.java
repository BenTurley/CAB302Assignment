import javax.swing.*;
import java.awt.*;


public class Line {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private JPanel panel;

    public Line(double x1, double y1, double x2, double y2, JPanel panel) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.panel = panel;


    }

    public void DrawLine() {
        Graphics g = panel.getGraphics();
        g.drawLine((int) x1,(int)y1,(int)x2,(int)y2);

    }

    public String LineOutputFormatted() {
        return "LINE " + x1 + " " + y1 + " " + x2 + " " + y2;
    }
}

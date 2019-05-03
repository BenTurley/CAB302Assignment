import javax.swing.*;
import java.awt.*;


public class Rectangle {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private JPanel panel;

    public Rectangle(double x1, double y1, double x2, double y2, JPanel panel) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.panel = panel;
    }

    public void DrawRectangle() {
        Graphics g = panel.getGraphics();
        g.drawLine((int) x1,(int)y1,(int)x2,(int)y1);
        g.drawLine((int) x2,(int)y1,(int)x2,(int)y2);
        g.drawLine((int) x2,(int)y2,(int)x1,(int)y2);
        g.drawLine((int) x1,(int)y2,(int)x1,(int)y1);
    }

    public String RectangleOutputFormatted() {
        return "RECTANGLE " + x1 + " " + y1 + " " + x2 + " " + y2;
    }
}

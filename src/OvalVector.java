import javax.swing.*;
import java.awt.*;

public class OvalVector implements Vector{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;

    public OvalVector(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
    }

    @Override
    public void DrawVector() {
        int xDiff = (int) Math.abs(x2-x1);
        int yDiff = (int) Math.abs(y2-y1);

        Graphics g = panel.getGraphics();
        g.drawOval((int) x1,(int)y1,xDiff,yDiff);
    }


    @Override
    public String VectorOutputFormatted() {
        return null;
    }
}

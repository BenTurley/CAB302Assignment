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

    private void xSwap(){
        int temp = (int) this.x2;
        this.x2 = this.x1;
        this.x1 = temp;
    }

    private void ySwap(){
        int temp = (int) this.y2;
        this.y2 = this.y1;
        this.y1 = temp;
    }

    public void DrawRectangle() {
        int xDiff = (int) Math.abs(x2-x1);
        int yDiff = (int) Math.abs(y2-y1);

        if((this.x1 > this.x2) && (this.y1< this.y2)){
            xSwap();
        }
        else if((this.x1 < this.x2) && (this.y1 > this.y2)){
            ySwap();
        }
        else if((this.x1 > this.x2) && (this.y1 > this.y2)){
            ySwap();
            xSwap();
        }

        Graphics g = panel.getGraphics();
        g.drawRect((int) x1,(int)y1,xDiff,yDiff);
    }

    public String RectangleOutputFormatted() {
        return "RECTANGLE " + x1 + " " + y1 + " " + x2 + " " + y2;
    }
}

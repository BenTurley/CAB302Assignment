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

    @Override
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
        g.drawOval((int) x1,(int)y1,xDiff,yDiff);
    }


    @Override
    public String VectorOutputFormatted() {
        return null;
    }
}

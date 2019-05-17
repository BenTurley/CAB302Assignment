import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class RectangleVector implements Vector {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;

    public RectangleVector(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
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
        g.drawRect((int) x1,(int)y1,xDiff,yDiff);
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

        System.out.println(convertedX1);
        System.out.println(convertedY1);
        System.out.println(convertedX2);
        System.out.println(convertedY2);

        System.out.println(formattedX1);
        System.out.println(formattedY1);
        System.out.println(formattedX2);
        System.out.println(formattedY2);
        return "RECTANGLE " + formattedX1 + " " + formattedY1 + " " + formattedX2 + " " + formattedY2;
    }
}

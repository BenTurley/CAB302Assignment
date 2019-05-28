import javax.swing.*;

public class VectorScale {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;
    private double panelHeight;
    private double panelWidth;

    public VectorScale(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
        this.panelHeight = panel.getSize().height;
        this.panelWidth = panel.getSize().width;

    }

    public double x1(){
        return x1 * panelWidth;
    }

    public double y1(){
        return y1 * panelHeight;
    }

    public double x2(){
        return x2 * panelWidth;
    }

    public double y2(){
        return y2 * panelHeight;
    }

    public double customScaleX(double x) {
        return x * panelWidth;
    }

    public double customScaleY(double y) {
        return y * panelHeight;
    }
}

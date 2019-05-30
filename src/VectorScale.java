import javax.swing.*;

/**
 * Scales coordinates from 0.00-1.00 an appropriate size for the panel
 */
public class VectorScale {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private JPanel panel;
    private double panelHeight;
    private double panelWidth;

    /**
     * Initialises VectorScale
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param panel
     */
    public VectorScale(double x1, double y1, double x2, double y2, JPanel panel){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.panel = panel;
        this.panelHeight = panel.getSize().height;
        this.panelWidth = panel.getSize().width;

    }

    /**
     *
     * @return scaled x1
     */
    public double x1(){
        return x1 * panelWidth;
    }

    /**
     *
     * @return scaled y1
     */
    public double y1(){
        return y1 * panelHeight;
    }

    /**
     *
     * @return scaled x2
     */
    public double x2(){
        return x2 * panelWidth;
    }

    /**
     *
     * @return scaled y2
     */
    public double y2(){
        return y2 * panelHeight;
    }

    /**
     * Scales an inputted x coordinate
     * @param x x value to scale
     * @return scaled x
     */
    public double customScaleX(double x) {
        return x * panelWidth;
    }

    /**
     * Scales an inputted y coordinate
     * @param y
     * @return scaled y
     */
    public double customScaleY(double y) {
        return y * panelHeight;
    }
}

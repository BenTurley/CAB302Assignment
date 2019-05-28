import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PolygonVector implements Vector {
    private boolean initialed = false;
    private int totalPoints = 0;
    private ArrayList<Double> xPoints =  new ArrayList<>();
    private ArrayList<Double> yPoints =  new ArrayList<>();
    private JPanel panel;
    private Color localColour;


    public PolygonVector( JPanel panel){
        this.panel = panel;
    }

    public void PolygonInitialise(double x1, double y1){
        xPoints.add(x1 / panel.getSize().width);
        yPoints.add(y1 / panel.getSize().height);
        this.initialed = true;
        totalPoints = 1;
    }

    public boolean ValidSides(){
        if(totalPoints < 3){
            return false;
        }
        else{
            return true;
        }
    }

    private void Redraw(){
        for(int i = 1; i < this.xPoints.size() ; i++){
            int x1 = (int) (this.xPoints.get(i - 1) * panel.getSize().width);
            int y1 = (int) (this.yPoints.get(i - 1) * panel.getSize().height);
            int x2 = (int) (this.xPoints.get(i) * panel.getSize().width);
            int y2 = (int) (this.yPoints.get(i) * panel.getSize().height);
            LineVector line = new LineVector(x1,y1,x2,y2, panel);
            line.SetColour(localColour);
            line.DrawVector();
    }
}

    public void FillVector(String colour) {
        int[] xPoints = new int[totalPoints + 1];
        int[] yPoints = new int[totalPoints + 1];
        for(int i = 0; i < totalPoints; i++){
            int x = (int) (this.xPoints.get(i) * panel.getSize().width);
            int y = (int) (this.yPoints.get(i) * panel.getSize().height);
            xPoints[i] = x;
            yPoints[i] = y;
        }

        xPoints[totalPoints] = (int) (this.xPoints.get(0) * panel.getSize().width);
        yPoints[totalPoints] = (int) (this.yPoints.get(0) * panel.getSize().height);

        System.out.println(this.xPoints);
        System.out.println(this.yPoints);
        System.out.println(totalPoints);

        Polygon poly = new Polygon(xPoints, yPoints, totalPoints);

        Graphics g = panel.getGraphics();

        g.setColor(Color.decode(colour));
        g.fillPolygon(poly);

        g.setColor(localColour);
        g.drawPolygon(poly);
    }

    public void AddSide(double x2, double y2){
        //VectorScale line = new VectorScale(0, 0, x2, y2, panel);

        xPoints.add(x2 / panel.getSize().width);
        yPoints.add(y2 / panel.getSize().height);

        double x1 = x2;
        double y1 = y2;
        totalPoints += 1;
        Redraw();
    }

    public boolean PolygonInitialised(){
        return initialed;
    }

    @Override
    public void SetColour(Color colour) {
        this.localColour = colour;
    }

    @Override
    public void DrawVector() {
        int[] xPoints = new int[totalPoints + 1];
        int[] yPoints = new int[totalPoints + 1];
        for(int i = 0; i < totalPoints; i++){
            int x = (int) (this.xPoints.get(i) * panel.getSize().width);
            int y = (int) (this.yPoints.get(i) * panel.getSize().height);
            xPoints[i] = x;
            yPoints[i] = y;
        }

        xPoints[totalPoints] = (int) (this.xPoints.get(0) * panel.getSize().width);
        yPoints[totalPoints] = (int) (this.yPoints.get(0) * panel.getSize().height);

        System.out.println(this.xPoints);
        System.out.println(this.yPoints);
        System.out.println(totalPoints);

        Polygon poly = new Polygon(xPoints, yPoints, totalPoints);

        Graphics g = panel.getGraphics();
        g.setColor(localColour);
        g.drawPolygon(poly);
    }

    @Override
    public String VectorOutputFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        String polygonString = "POLYGON";

        for (int i = 0; i < totalPoints; i++){
            double convertedX1 = this.xPoints.get(i);
            double convertedY1 = this.yPoints.get(i);

            String formattedX1 = df.format(convertedX1);
            String formattedY1 = df.format(convertedY1);
            polygonString =  polygonString + " " + formattedX1 + " " + formattedY1;
        }
        return polygonString;
    }
}

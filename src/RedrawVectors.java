import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedrawVectors {
    private ArrayList<String> vectorArray;
    private ArrayList<Double> VectorCords = new ArrayList<>();
    private JPanel drawingPanel;


    public RedrawVectors(ArrayList<String> vectorArray, JPanel drawingPanel) {
        this.vectorArray = vectorArray;
        this.drawingPanel = drawingPanel;
    }

    private void VectorShapeScale(double x1, double y1, double x2, double y2){
        double panelHeight = drawingPanel.getSize().height;
        double panelWidth = drawingPanel.getSize().width;
        VectorCords.add(0, x1 * panelWidth);
        VectorCords.add(1, y1 * panelHeight);
        VectorCords.add(2, x2 * panelWidth);
        VectorCords.add(3, y2 * panelHeight);
    }

    public void redraw() {
        int panelHeight = drawingPanel.getSize().height;
        int panelWidth = drawingPanel.getSize().width;
        Graphics g = drawingPanel.getGraphics();
        g.clearRect(0,0,panelWidth,panelHeight);
        String regex = "(LINE |RECTANGLE )([0-9.]+) ([0-9.]+) ([0-9.]+) ([0-9.]+)";
        Pattern pattern = Pattern.compile(regex);
        for (int x = 0; x < vectorArray.size(); x++) {
            String str = vectorArray.get(x).toString();
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                System.out.println("Type: " + matcher.group(1));
                System.out.println(matcher.group(1));
                System.out.println("x1: " + matcher.group(2));
                double outx1 = Double.valueOf(matcher.group(2));
                System.out.println(outx1);
                System.out.println("y1: " + matcher.group(3));
                double outy1 = Double.valueOf(matcher.group(3));
                System.out.println(outy1);
                System.out.println("x2: " + matcher.group(4));
                double outx2 = Double.valueOf(matcher.group(4));
                System.out.println(outx2);
                System.out.println("y2: " + matcher.group(5));
                double outy2 = Double.valueOf(matcher.group(5));
                System.out.println(outy2);
                //If LINE

                if(matcher.group(1).equals("LINE ")) {
                    System.out.println("Detected LINE");
                    VectorScale lineScale = new VectorScale(outx1, outy1, outx2, outy2, drawingPanel);
                    Vector line = new LineVector(lineScale.x1(),lineScale.y1(), lineScale.x2(), lineScale.y2(), drawingPanel);
                    line.DrawVector();
                    System.out.println(line.VectorOutputFormatted());
                    System.out.println(line);
                    //drawnShapes.add(fileLine.LineOutputFormatted());
                }
                else if(matcher.group(1).equals("RECTANGLE ")) {
                    System.out.println("Detected RECTANGLE");
                    VectorScale rectangleScale = new VectorScale(outx1, outy1, outx2, outy2, drawingPanel);
                    Vector fileRectangle = new RectangleVector(rectangleScale.x1(), rectangleScale.y1(), rectangleScale.x2(),  rectangleScale.y2(), drawingPanel);
                    fileRectangle.DrawVector();
                    System.out.println(fileRectangle.VectorOutputFormatted());
                    System.out.println(fileRectangle);
                    //drawnShapes.add(fileRectangle.RectangleOutputFormatted());
                }
            }

        }
    }
}
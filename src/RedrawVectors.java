import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedrawVectors {
    private ArrayList<String> vectorArray;
    private ArrayList<Double> VectorCords = new ArrayList<>();
    private JPanel drawingPanel;
    private String penColour = "#000000";
    private Color currentColour = Color.black;
    private String fillColour = "";


    public RedrawVectors(ArrayList<String> vectorArray, JPanel drawingPanel) {
        this.vectorArray = vectorArray;
        this.drawingPanel = drawingPanel;
    }

    public void redraw() {

        int panelHeight = drawingPanel.getSize().height;
        int panelWidth = drawingPanel.getSize().width;

        Graphics g = drawingPanel.getGraphics();
        g.clearRect(0,0,panelWidth,panelHeight);

        String baseRegex = "(LINE|RECTANGLE|PLOT|OVAL|FILL|PEN) ([0-9. ]+|#[A-FO0-9]+|OFF)";
        Pattern basePattern = Pattern.compile(baseRegex);
        for(int x = 0; x < vectorArray.size(); x++) {
            System.out.println("Iteration: " + x);
            String str = vectorArray.get(x).toString();
            Matcher matcher = basePattern.matcher(str);
            if(matcher.find()) {
                System.out.println("Match 1: " + matcher.group(1));
                System.out.println("Match 2: " + matcher.group(2));

                String[] split = matcher.group(2).split(" ");
                //System.out.println("The split array x1 is: " + split[0]);
                //System.out.println("The split array y1 is: " + split[1]);

                String type = matcher.group(1);
                System.out.println("Assigned type as: " + type);
                /*
                if(!matcher.group(1).equals("PLOT")) {
                    System.out.println("Found not plot!");
                    System.out.println("The split array x2 is: " + split[2]);
                    System.out.println("The split array y2 is: " + split[3]);
                }
                */
                //Draw vectors
                if(matcher.group(1).equals("LINE")) {
                    VectorScale lineScale = new VectorScale(Double.valueOf(split[0]),Double.valueOf(split[1]), Double.valueOf(split[2]),Double.valueOf(split[3]), drawingPanel);
                    Vector line = new LineVector(lineScale.x1(), lineScale.y1(), lineScale.x2(), lineScale.y2(), drawingPanel);
                    line.SetColour(currentColour);
                    line.DrawVector();
                }
                else if(matcher.group(1).equals("RECTANGLE")) {
                    VectorScale rectangleScale = new VectorScale(Double.valueOf(split[0]),Double.valueOf(split[1]), Double.valueOf(split[2]),Double.valueOf(split[3]), drawingPanel);
                    Vector rectangle = new RectangleVector(rectangleScale.x1(), rectangleScale.y1(), rectangleScale.x2(), rectangleScale.y2(), drawingPanel);
                    //if(fillColour == ""){

                    if(fillColour.equals("")) {
                        rectangle.SetColour(currentColour);
                        rectangle.DrawVector();
                    }
                    else{
                        ((RectangleVector) rectangle).FillVector(fillColour);
                        rectangle.SetColour(currentColour);
                        rectangle.DrawVector();
                    }

                }
                else if(matcher.group(1).equals("OVAL")) {
                    VectorScale ovalScale = new VectorScale(Double.valueOf(split[0]),Double.valueOf(split[1]), Double.valueOf(split[2]),Double.valueOf(split[3]), drawingPanel);
                    Vector oval = new OvalVector(ovalScale.x1(), ovalScale.y1(), ovalScale.x2(), ovalScale.y2(), drawingPanel);
                    oval.SetColour(currentColour);
                    oval.DrawVector();
                }
                else if(matcher.group(1).equals("PLOT")) {
                    VectorScale plotScale = new VectorScale(Double.valueOf(split[0]),Double.valueOf(split[1]), Double.valueOf(split[0]), Double.valueOf(split[1]), drawingPanel);
                    Vector plot = new PlotVector(plotScale.x1(), plotScale.y1(), drawingPanel);
                    plot.SetColour(currentColour);
                    plot.DrawVector();
                }
                else if(matcher.group(1).equals("PEN")) {
                    penColour = split[0];
                    System.out.println("String penColour is: " + penColour);
                    if(penColour.equals("#000000")) {
                        System.out.println("Found pen: #000000");
                        this.currentColour = Color.black;
                    }
                    else if(penColour.equals("#FF0000")) {
                        System.out.println("Found pen: #FF0000");
                        this.currentColour = Color.red;
                    }
                    else if(penColour.equals("#0000FF")) {
                        System.out.println("Found pen: #0000FF");
                        this.currentColour = Color.blue;
                    }



                }
                else if(matcher.group(1).equals("FILL")) {
                    this.fillColour = split[0];
                    System.out.println(fillColour);
                    if(fillColour.equals("OFF")) {
                        System.out.println("Set fillColour to \"\"");
                        this.fillColour = "";
                    }
                    System.out.println("FILL: " + split[0]);
                }

            }
        }

        /*
        String regex = "(LINE |RECTANGLE |OVAL )([0-9.]+) ([0-9.]+) ([0-9.]+) ([0-9.]+)";
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
                    //System.out.println(line);
                    //drawnShapes.add(fileLine.LineOutputFormatted());
                }
                else if(matcher.group(1).equals("RECTANGLE ")) {
                    System.out.println("Detected RECTANGLE");
                    VectorScale rectangleScale = new VectorScale(outx1, outy1, outx2, outy2, drawingPanel);
                    Vector fileRectangle = new RectangleVector(rectangleScale.x1(), rectangleScale.y1(), rectangleScale.x2(),  rectangleScale.y2(), drawingPanel);
                    fileRectangle.DrawVector();
                    System.out.println(fileRectangle.VectorOutputFormatted());
                    //System.out.println(fileRectangle);
                    //drawnShapes.add(fileRectangle.RectangleOutputFormatted());
                }
                else if(matcher.group(1).equals("PLOT ")) {
                    System.out.println("Detected PLOT");
                    VectorScale plotScale = new VectorScale(outx1, outy1, outx1, outy1, drawingPanel);
                    Vector filePlot = new PlotVector(plotScale.x1(), plotScale.y1(), drawingPanel);
                    filePlot.DrawVector();
                    System.out.println(filePlot.VectorOutputFormatted());
                }
                else if(matcher.group(1).equals("OVAL ")) {
                    System.out.println("Detected OVAL");
                    VectorScale ovalScales = new VectorScale(outx1, outy1, outx2, outy2, drawingPanel);
                    Vector fileOval = new OvalVector(ovalScales.x1(), ovalScales.y1(), ovalScales.x2(),  ovalScales.y2(), drawingPanel);
                    fileOval.DrawVector();
                    System.out.println(fileOval.VectorOutputFormatted());
                    //System.out.println(fileRectangle);
                    //drawnShapes.add(fileRectangle.RectangleOutputFormatted());
                }
            }
        }

         */

    }
}
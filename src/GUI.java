import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends JFrame {
    private JPanel drawingPanel;
    private String tool;
    private String colour = "";

    public ArrayList<String> drawnShapes = new ArrayList<>();

    public GUI() {
        super("Vector Editing Software");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //New drawing panel
        drawingPanel = new JPanel();
        //drawingPanel.setBackground(Color.LIGHT_GRAY);
        drawingPanel.addMouseListener(new ButtonListener());
        this.add(drawingPanel);


        //Create menu bar
        JMenuBar menuBar = new JMenuBar();
        //Create file menu
        JMenu fileMenu = new JMenu("File");
        //Create file menu items
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        //Add functionality to menu items
        fileChooser vecFileChooser = new fileChooser();
        //Add action listeners to buttons
        newItem.addActionListener(new ButtonListener());
        openItem.addActionListener(vecFileChooser);
        saveItem.addActionListener(vecFileChooser);

        //Add file menu items to menu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        //Add file menu to menu bar
        menuBar.add(fileMenu);

        //Create edit menu
        JMenu editMenu = new JMenu("Edit");
        //Create edit menu items
        JMenuItem undoItem = new JMenuItem("Undo");
        //Add functionality to items
        undoItem.addActionListener(new ButtonListener());
        //Add edit menu items to menu
        editMenu.add(undoItem);
        //Add edit menu to menu bar
        menuBar.add(editMenu);

        //Create tool menu
        JMenu toolMenu = new JMenu("Tools");
        //Create tool menu items
        JMenuItem lineItem = new JMenuItem("Line");
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        JMenuItem plotItem = new JMenuItem("Plot");
        JMenuItem OvalItem = new JMenuItem("Oval");
        //Add functionality to tools
        lineItem.addActionListener(new ButtonListener());
        rectangleItem.addActionListener(new ButtonListener());
        plotItem.addActionListener(new ButtonListener());
        OvalItem.addActionListener(new ButtonListener());
        //Add tool menu items to menu
        toolMenu.add(lineItem);
        toolMenu.add(rectangleItem);
        toolMenu.add(plotItem);
        toolMenu.add(OvalItem);
        //Add tool menu to menu bar
        menuBar.add(toolMenu);

        //Create pen colour menu
        JMenuItem penColourMenu = new JMenu("Pen");
        //Create pen colour menu items
        JMenuItem blackPen = new JMenuItem("Black");
        JMenuItem redPen = new JMenuItem("Red");
        JMenuItem bluePen = new JMenuItem("Blue");
        JMenuItem customPen = new JMenuItem("Custom");
        //Add functionality to pen items
        blackPen.addActionListener(new PenButtonListener());
        redPen.addActionListener(new PenButtonListener());
        bluePen.addActionListener(new PenButtonListener());
        customPen.addActionListener(new PenButtonListener());
        //Add pen colour menu items to menu
        penColourMenu.add(blackPen);
        penColourMenu.add(redPen);
        penColourMenu.add(bluePen);
        penColourMenu.add(customPen);
        //Add pen colour menu to menu bar
        menuBar.add(penColourMenu);

        //Create pen input for hex values

        //Create fill colour menu
        JMenu fillColourMenu = new JMenu("Fill");
        //Create fill colour menu items
        JMenuItem noFill = new JMenuItem("None");
        JMenuItem gray = new JMenuItem("Gray");
        JMenuItem red = new JMenuItem("Red");
        //Add functionality to fill items
        noFill.addActionListener(new ButtonListener());
        gray.addActionListener(new ButtonListener());
        red.addActionListener(new ButtonListener());
        //Add fill colour menu items to menu
        fillColourMenu.add(noFill);
        fillColourMenu.add(gray);
        fillColourMenu.add(red);
        //Add fill colour menu to menu bar
        menuBar.add(fillColourMenu);



        this.setJMenuBar(menuBar);

        //Display window
        this.setPreferredSize(new Dimension(300,300));
        this.setLocation(new Point(300,300));
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }


    public class PenButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent f) {
            String buttonString = f.getActionCommand();

            //Pen
            if(buttonString.equals("Black")) {
                drawnShapes.add("PEN #000000");
            }
            else if(buttonString.equals("Red")) {
                drawnShapes.add("PEN #FF0000");
            }
            else if(buttonString.equals("Blue")) {
                drawnShapes.add("PEN #0000FF");
            }
            else if(buttonString.equals("Custom")){
                JOptionPane.showInputDialog(drawingPanel, "Enter hexadecimal value for Pen");
            }
        }
    }

    public class ButtonListener implements MouseListener, ActionListener {
        private double x1;
        private double y1;
        private double x2;
        private double y2;




        public void actionPerformed(ActionEvent f){
            String buttonString = f.getActionCommand();

            if (buttonString == "Rectangle") {
                tool = "Rectangle";
            }
            else if(buttonString.equals("Line")){
                tool = "Line";
            }
            else if(buttonString.equals("Oval")) {
                tool = "Oval";
            }
            else if(buttonString.equals("Plot")) {
                tool = "Plot";
            }
            else if(buttonString.equals("New")) {
                drawnShapes.clear();
                System.out.println(drawnShapes);
                int panelHeight = drawingPanel.getSize().height;
                int panelWidth = drawingPanel.getSize().width;
                Graphics g = drawingPanel.getGraphics();
                g.clearRect(0,0,panelWidth,panelHeight);
            }
            else if(buttonString.equals("Undo")) {
                drawnShapes.remove(drawnShapes.size()-1);
                RedrawVectors redrawPanel = new RedrawVectors(drawnShapes, drawingPanel);
                redrawPanel.redraw();
            }
            //Fill
            else if(buttonString.equals("None")) {
                drawnShapes.add("FILL OFF");
                colour = "";
            }
            else if(buttonString.equals("Gray")) {
                drawnShapes.add("FILL #808080");
                colour = "#808080";
            }
            else if(buttonString.equals("Red")) {
                drawnShapes.add("FILL #FF0000");
                colour = "#FF0000";
            }
        }

        public void mouseClicked(MouseEvent e) {
            //Do nothing
        }

        public void mousePressed(MouseEvent e) {
            this.x1 = (e.getX());
            this.y1 = (e.getY());
            if(tool == "Plot") {
                PlotVector plot = new PlotVector(this.x1, this.y1, drawingPanel);
                plot.DrawVector();
                drawnShapes.add(plot.VectorOutputFormatted());
                System.out.println(drawnShapes);

            }
        }

        public void mouseReleased(MouseEvent e) {
            this.x2 = (e.getX());
            this.y2 = (e.getY());

            if(tool == "Rectangle") {
                /* Vector */
                Vector rectangle = new RectangleVector(this.x1, this.y1, this.x2, this.y2, drawingPanel);
                System.out.println("mouseReleased colour is: " + colour);
                //if(colour != ""){
                if(!colour.equals("")) {
                    ((RectangleVector) rectangle).FillVector(colour);
                }
                else{
                    rectangle.DrawVector();
                }
                drawnShapes.add(rectangle.VectorOutputFormatted());
                System.out.println(drawnShapes);
            }
            else if(tool == "Line"){
                Vector line  = new LineVector(this.x1, this.y1, this.x2, this.y2, drawingPanel);
                line.DrawVector();
                drawnShapes.add(line.VectorOutputFormatted());
                System.out.println(drawnShapes);
            }
            else if(tool == "Oval"){
                Vector Oval  = new OvalVector(this.x1, this.y1, this.x2, this.y2, drawingPanel);

                Oval.DrawVector();
                if(!colour.equals("")) {
                    Oval.DrawVector();
                }
                else{
                    ((OvalVector) Oval).FillVector(colour);
                }
                drawnShapes.add(Oval.VectorOutputFormatted());
                System.out.println(drawnShapes);
            }
            RedrawVectors redrawPanel = new RedrawVectors(drawnShapes, drawingPanel);
            redrawPanel.redraw();
        }

        public void mouseEntered(MouseEvent e) {
            //Do nothing
        }

        public void mouseExited(MouseEvent e) {
            //Do nothing
        }
    }

    public class fileChooser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.equals("Save")) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Select a .vec file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter(".vec", "vec");
                fileChooser.addChoosableFileFilter(restrict);

                int choice = fileChooser.showSaveDialog(null);

                if(choice == JFileChooser.APPROVE_OPTION) {
                    System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                    FileReaderWriter saveFile = new FileReaderWriter(fileChooser.getSelectedFile().getAbsolutePath(), "");

                    //Generate file contents
                    String saveFileContents = "";
                    for(int x = 0; x < drawnShapes.size(); x++) {
                        saveFileContents += drawnShapes.get(x) + "\n";
                    }
                    System.out.println(saveFileContents);

                    //Write to file
                    saveFile.WriteFile(saveFileContents);
                }
                else {
                    //Set display label to say user cancelled
                }
            }
            else {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Select a .vec file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter(".vec", "vec");
                fileChooser.addChoosableFileFilter(restrict);

                int choice = fileChooser.showOpenDialog(null);

                if(choice == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Opened: " + fileChooser.getSelectedFile().getAbsolutePath());

                    //#Regular expressions
                    String regex = "(LINE |RECTANGLE )([0-9.]+) ([0-9.]+) ([0-9.]+) ([0-9.]+)";
                    Pattern pattern = Pattern.compile(regex);
                    //#

                    //!NOTE: This is poorly written, rewrite once FileReaderWriter has been restructured
                    FileReaderWriter openedFile = new FileReaderWriter(fileChooser.getSelectedFile().getAbsolutePath(), "");
                    ArrayList fileContents = openedFile.ReadFile();
                    System.out.println(fileContents);
                    for(int x = 0; x < fileContents.size(); x++ ){
                        String str = fileContents.get(x).toString();
                        System.out.println(str);
                        //#Regular expressions
                        Matcher matcher = pattern.matcher(str);
                        if(matcher.find()) {
                            //System.out.println("Type: " + matcher.group(1));
                            //System.out.println(matcher.group(1));
                            //System.out.println("x1: " + matcher.group(2));
                            double outx1 = Double.valueOf(matcher.group(2));
                            //System.out.println(outx1);
                            //System.out.println("y1: " + matcher.group(3));
                            double outy1 = Double.valueOf(matcher.group(3));
                            //System.out.println(outy1);
                            //System.out.println("x2: " + matcher.group(4));
                            double outx2 = Double.valueOf(matcher.group(4));
                            //System.out.println(outx2);
                            //System.out.println("y2: " + matcher.group(5));
                            double outy2 = Double.valueOf(matcher.group(5));
                            //System.out.println(outy2);

                            //If LINE
                            if(matcher.group(1).equals("LINE ")) {
                                System.out.println("Detected LINE");
                                VectorScale lineScale = new VectorScale(outx1, outy1, outx2, outy2, drawingPanel);
                                Vector fileLine = new LineVector(lineScale.x1(), lineScale.y1(), lineScale.x2(), lineScale.y2(), drawingPanel);
                                fileLine.DrawVector();
                                System.out.println(fileLine.VectorOutputFormatted());
                                System.out.println(fileLine);
                                drawnShapes.add(fileLine.VectorOutputFormatted());
                            }

                            //Else if RECTANGLE
                            else if(matcher.group(1).equals("RECTANGLE ")) {
                                System.out.println("Detected RECTANGLE");
                                VectorScale rectangleScale = new VectorScale(outx1, outy1, outx2, outy2, drawingPanel);
                                RectangleVector fileRectangle = new RectangleVector(rectangleScale.x1(), rectangleScale.y1(), rectangleScale.x2(), rectangleScale.y2(), drawingPanel);
                                fileRectangle.DrawVector();
                                System.out.println(fileRectangle.VectorOutputFormatted());
                                System.out.println(fileRectangle);
                                drawnShapes.add(fileRectangle.VectorOutputFormatted());
                            }
                        }
                    }
                }
                else {
                    //Set display label to say user cancelled
                }
            }
        }
    }
}

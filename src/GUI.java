import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends JFrame {
    private JPanel drawingPanel;
    private String tool;
    private String colour = "";

    public ArrayList<String> drawnShapes = new ArrayList<>();
    public ArrayList<String> historyShapes = new ArrayList<>();

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
        JMenuItem saveBMP = new JMenuItem("Save as BMP");
        //Add functionality to menu items
        fileChooser vecFileChooser = new fileChooser();
        //Add action listeners to buttons
        newItem.addActionListener(new ButtonListener());
        openItem.addActionListener(vecFileChooser);
        saveItem.addActionListener(vecFileChooser);
        saveBMP.addActionListener(new ButtonListener());

        //Add file menu items to menu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveBMP);
        //Add file menu to menu bar
        menuBar.add(fileMenu);

        //Create edit menu
        JMenu editMenu = new JMenu("Edit");
        //Create edit menu items
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem historyItem = new JMenuItem("History");
        //Add functionality to items
        undoItem.addActionListener(new ButtonListener());
        historyItem.addActionListener(new ButtonListener());
        //Add edit menu items to menu
        editMenu.add(undoItem);
        editMenu.add(historyItem);
        //Add edit menu to menu bar
        menuBar.add(editMenu);

        //Create tool menu
        JMenu toolMenu = new JMenu("Tools");
        //Create tool menu items
        JMenuItem lineItem = new JMenuItem("Line");
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        JMenuItem plotItem = new JMenuItem("Plot");
        JMenuItem EllipseItem = new JMenuItem("Ellipse");
        JMenuItem PolygonItem = new JMenuItem("Polygon");
        //Add functionality to tools
        lineItem.addActionListener(new ButtonListener());
        rectangleItem.addActionListener(new ButtonListener());
        plotItem.addActionListener(new ButtonListener());
        EllipseItem.addActionListener(new ButtonListener());
        PolygonItem.addActionListener(new ButtonListener());
        //Add tool menu items to menu
        toolMenu.add(lineItem);
        toolMenu.add(rectangleItem);
        toolMenu.add(plotItem);
        toolMenu.add(EllipseItem);
        toolMenu.add(PolygonItem);
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
        JMenuItem customFill = new JMenuItem("Custom");
        //Add functionality to fill items
        noFill.addActionListener(new ButtonListener());
        gray.addActionListener(new ButtonListener());
        red.addActionListener(new ButtonListener());
        customFill.addActionListener(new ButtonListener());
        //Add fill colour menu items to menu
        fillColourMenu.add(noFill);
        fillColourMenu.add(gray);
        fillColourMenu.add(red);
        fillColourMenu.add(customFill);
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
                try {
                    String userColourInput = JOptionPane.showInputDialog(drawingPanel, "Enter hexadecimal value for Pen");
                    System.out.println(userColourInput);
                    //Validate string is valid hexadecimal colour
                    String regex = "([#]?)([A-F0-9]{6}|[a-f0-9]{6})";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(userColourInput);
                    //If a valid hex colour value is found
                    if(matcher.find()) {
                        drawnShapes.add("PEN #" + matcher.group(2));
                    }
                    else {
                        JOptionPane.showMessageDialog(drawingPanel, "Invalid hexadecimal value");
                    }
                }
                catch (NullPointerException e) {
                    System.out.println("Caught NullPointerException reading hex colour code");
                    JOptionPane.showMessageDialog(drawingPanel, "Invalid hexadecimal value");
                }

            }
        }
    }

    public class HistoryButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent f) {
            String buttonString = f.getActionCommand();

            System.out.println(buttonString);
            //ArrayList historyShapes = new ArrayList(drawnShapes);
            historyShapes = new ArrayList(drawnShapes);
            System.out.println(historyShapes);
            System.out.println(historyShapes.size());
            for (int i = drawnShapes.size()-1; Integer.parseInt(buttonString) < i; i--) {
                historyShapes.remove(i);
            }
            System.out.println(historyShapes);
            RedrawVectors redrawPanel = new RedrawVectors(historyShapes, drawingPanel);
            redrawPanel.redraw();



        }
    }

    public class ButtonListener implements MouseListener, ActionListener {
        private double x1;
        private double y1;
        private double x2;
        private double y2;
        private PolygonVector polygon = new PolygonVector(drawingPanel);



        public void actionPerformed(ActionEvent f){
            String buttonString = f.getActionCommand();

            if (buttonString == "Rectangle") {
                tool = "Rectangle";
            }
            else if(buttonString.equals("Line")){
                tool = "Line";
            }
            else if(buttonString.equals("Ellipse")) {
                tool = "Ellipse";
            }
            else if(buttonString.equals("Plot")) {
                tool = "Plot";
            }
            else if(buttonString.equals("Polygon")) {
                tool = "Polygon";
                JOptionPane.showMessageDialog(drawingPanel, "Left mouse click to place a point.\nRight mouse click to finish polygon.");
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
                if(drawnShapes.size() > 0) {
                    drawnShapes.remove(drawnShapes.size()-1);
                }

                RedrawVectors redrawPanel = new RedrawVectors(drawnShapes, drawingPanel);
                redrawPanel.redraw();
            }
            else if(buttonString.equals("History")) {
                JPanel historyMenu = new JPanel();
                JLabel infoLabel = new JLabel("Select to view drawing up until command");
                JLabel blankLabel = new JLabel("");
                historyMenu.add(infoLabel);
                historyMenu.add(blankLabel);
                historyMenu.setLayout(new GridLayout(0,2));
                for(int x = 0; x < drawnShapes.size(); x++) {

                    historyMenu.add(new JLabel(drawnShapes.get(x)));
                    //Add button next to each label to preview and revert to that location
                    //JButton previewButton = new JButton("Preview");
                    //previewButton.addActionListener(new HistoryButtonListener());
                    //historyMenu.add(previewButton);
                    JButton confirmButton = new JButton(Integer.toString(x));
                    confirmButton.addActionListener(new HistoryButtonListener());
                    historyMenu.add(confirmButton);

                }
                int result = JOptionPane.showConfirmDialog(drawingPanel, historyMenu, "History", JOptionPane.OK_CANCEL_OPTION);


                if(result == JOptionPane.OK_OPTION) {
                    //Change drawnShapes
                    drawnShapes = new ArrayList<>(historyShapes);

                    RedrawVectors redrawPanel = new RedrawVectors(drawnShapes, drawingPanel);
                    redrawPanel.redraw();
                }
                else{
                    RedrawVectors redrawPanel = new RedrawVectors(drawnShapes, drawingPanel);
                    redrawPanel.redraw();
                }


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
            else if(buttonString.equals("Custom")){
                try {
                    String userColourInput = JOptionPane.showInputDialog(drawingPanel, "Enter hexadecimal value for Fill");
                    System.out.println(userColourInput);
                    //Validate string is valid hexadecimal colour
                    String regex = "([#]?)([A-F0-9]{6}|[a-f0-9]{6})";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(userColourInput);
                    //If a valid hex colour value is found
                    if(matcher.find()) {
                        drawnShapes.add("FILL #" + matcher.group(2));
                        colour = "#" +  matcher.group(2);
                    }
                    else {
                        JOptionPane.showMessageDialog(drawingPanel, "Invalid hexadecimal value");
                    }
                }
                catch (NullPointerException e) {
                    System.out.println("Caught NullPointerException reading hex colour code");
                    JOptionPane.showMessageDialog(drawingPanel, "Invalid hexadecimal value");
                }
            }
            else if(buttonString.equals("Save as BMP")) {
                int xDimension = 25;
                int yDimension = 25;
                JTextField xInput = new JTextField(5);
                JTextField yInput = new JTextField(5);
                JPanel optionMenu = new JPanel();
                optionMenu.add(new JLabel("x: "));
                optionMenu.add(xInput);
                //optionMenu.add(Box.createHorizontalStrut(15));
                optionMenu.add(new JLabel("y: "));
                optionMenu.add(yInput);
                int result = JOptionPane.showConfirmDialog(drawingPanel, optionMenu, "Enter X and Y dimensions for BMP", JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.OK_OPTION) {
                    System.out.println("X: " + xInput.getText());
                    System.out.println("Y: " + yInput.getText());
                    xDimension = Integer.parseInt(xInput.getText());
                    yDimension = Integer.parseInt(yInput.getText());
                }

                BufferedImage image = new BufferedImage(xDimension, yDimension, BufferedImage.TYPE_INT_ARGB);
                drawingPanel.setVisible(true);

                RedrawVectors redrawPanel = new RedrawVectors(drawnShapes, drawingPanel);
                redrawPanel.redraw();
                Graphics g = image.getGraphics();

                redrawPanel.redraw();
                drawingPanel.paint(g);
                try {
                    ImageIO.write(image, "png", new File("test.bmp"));
                } catch (IOException exception) {
                    System.out.println("IOException creating BMP");
                }

            }
        }

        public void mouseClicked(MouseEvent e) {
            //Do nothing

            if(tool == "Polygon"){
                if(e.getButton() == MouseEvent.BUTTON1){
                    if(polygon.PolygonInitialised() == false){
                        this.x1 = (e.getX());
                        this.y1 = (e.getY());
                        polygon.PolygonInitialise(x1, y1);
                    }
                    else{
                        this.x2 = (e.getX());
                        this.y2 = (e.getY());
                        polygon.AddSide(x2, y2);
                    }
                }
                else if(e.getButton() == MouseEvent.BUTTON3){
                    try{
                        if(polygon.ValidSides() == false){
                            throw new Exception("Sides must be greater than 2");
                        }
                        if(!colour.equals("")) {
                            polygon.FillVector(colour);
                        }
                        else{
                            polygon.DrawVector();
                        }
                        System.out.println(polygon.VectorOutputFormatted());
                        drawnShapes.add(polygon.VectorOutputFormatted());
                        this.polygon = new PolygonVector(drawingPanel);
                    }
                    catch (Exception f){
                        JOptionPane.showMessageDialog(drawingPanel, "Invalid amount of polygon sides");
                        this.polygon = new PolygonVector(drawingPanel);
                        //System.out.println("Error");
                        System.out.println(f.getMessage());
                    }


                }
            }
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
                //System.out.println("mouseReleased colour is: " + colour);
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
            else if(tool == "Ellipse"){
                Vector Ellipse  = new EllipseVector(this.x1, this.y1, this.x2, this.y2, drawingPanel);

                Ellipse.DrawVector();
                if(!colour.equals("")) {
                    ((EllipseVector) Ellipse).FillVector(colour);
                }
                else{
                    Ellipse.DrawVector();
                }
                drawnShapes.add(Ellipse.VectorOutputFormatted());
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

                    drawnShapes.clear();
                    FileReaderWriter openedFile = new FileReaderWriter(fileChooser.getSelectedFile().getAbsolutePath(), "");
                    ArrayList fileContents = openedFile.ReadFile();
                    drawnShapes = fileContents;
                    System.out.println(fileContents);
                    RedrawVectors drawPanel = new RedrawVectors(fileContents, drawingPanel);
                    drawPanel.redraw();


                    /*
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
                     */
                }
                else {
                    //Set display label to say user cancelled
                }
            }
        }
    }
}

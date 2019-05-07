import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends JFrame {
    private JPanel panel;
    private JPanel buttonPanel;
    private JPanel drawingPanel;
    private String tool;

    public ArrayList<String> drawnShapes = new ArrayList<>();

    public GUI() {
        super("Vector Editing Software");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        panel = new JPanel();
        panel.addMouseListener(new ButtonListener());
        panel.setLayout(new GridLayout(1, 3));


        drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.LIGHT_GRAY);
        panel.add(drawingPanel);

        buttonPanel = new JPanel(new GridLayout(5, 0));
        buttonPanel.setBackground(Color.GRAY);
        panel.add(buttonPanel);

        add(buttonPanel, BorderLayout.WEST);

        //File chooser buttons
        JButton chooserButton1 = new JButton("Save");
        JButton chooserButton2 = new JButton("Open");
        //Create FileChooser object
        fileChooser vecFileChooser = new fileChooser();
        //Add action listeners to buttons
        chooserButton1.addActionListener(vecFileChooser);
        chooserButton2.addActionListener(vecFileChooser);
        //Add buttons to frame
        buttonPanel.add(chooserButton1);
        buttonPanel.add(chooserButton2);


        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(new ButtonListener());
        buttonPanel.add(rectangleButton);

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(new ButtonListener());
        buttonPanel.add(lineButton);

        this.getContentPane().add(panel);

        //File chooser
        /*
        JFileChooser chooser = new JFileChooser(new File("C:"));
        chooser.showSaveDialog(null);
        */


        //Display window
        this.setPreferredSize(new Dimension(300,300));
        this.setLocation(new Point(300,300));
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
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
                //System.out.println("Rectangle");
            }
            else if(buttonString.equals("Line")){
                tool = "Line";
                //System.out.println("Line");
            }
        }

        public void mouseClicked(MouseEvent e) {
            //Do nothing
        }

        public void mousePressed(MouseEvent e) {
            //double x1 = (e.getX());      //!Change to divide based on dimension of window, scaling from 0 - 1
            //double y1 = (e.getY());      //!Change to divide based on dimension of window, scaling from 0 - 1
            this.x1 = (e.getX());
            this.y1 = (e.getY());
            //System.out.println("Click!");
            //System.out.println("X co-ord: " + x1 + " Y co-ord: " + y1);
        }

        public void mouseReleased(MouseEvent e) {
            //System.out.println(tool);
            //double x2 = (e.getX());      //!Change to divide based on dimension of window, scaling from 0 - 1
            //double y2 = (e.getY());      //!Change to divide based on dimension of window, scaling from 0 - 1
            this.x2 = (e.getX());
            this.y2 = (e.getY());

            //Rectangle rectangle = new Rectangle(x1, y1, x2, y2, panel);
            //rectangle.DrawRectangle();

            if(tool == "Rectangle") {
                Rectangle rectangle = new Rectangle(this.x1, this.y1, this.x2, this.y2, panel);
                rectangle.DrawRectangle();
                System.out.println(rectangle.RectangleOutputFormatted());
            }
            else if(tool == "Line"){
                Line line = new Line(this.x1, this.y1, this.x2, this.y2, panel);
                line.DrawLine();
                System.out.println(line.LineOutputFormatted());
                //Add line to array of objects
                drawnShapes.add(line.LineOutputFormatted());
                System.out.println(drawnShapes);

            }
            //System.out.println("X co-ord: " + x2 + " Y co-ord: " + y2);
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
                            System.out.println("Type: " + matcher.group(1));
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
                            //Move output variables back into GUI class to draw to panel
                            Line fileLine = new Line(outx1, outy1, outx2, outy2, panel);
                            fileLine.DrawLine();
                            System.out.println(fileLine.LineOutputFormatted());
                            System.out.println(fileLine);
                            drawnShapes.add(fileLine.LineOutputFormatted());
                        }
                        //#
                    }





                    /*
                    Line line = new Line(this.x1, this.y1, this.x2, this.y2, panel);
                    line.DrawLine();
                     */
                    //!
                }
                else {
                    //Set display label to say user cancelled
                }
            }
        }
    }
}

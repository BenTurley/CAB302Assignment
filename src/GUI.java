import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class GUI extends JFrame {
    private JPanel panel;
    private JPanel buttonPanel;
    private JPanel drawingPanel;
    private String tool;

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
                fileChooser.setDialogTitle("Select a .vec or .txt file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .vec & .txt files", "vec", "txt");
                fileChooser.addChoosableFileFilter(restrict);

                int render = fileChooser.showSaveDialog(null);

                if(render == JFileChooser.APPROVE_OPTION) {
                    //Set display label to that of path
                }
                else {
                    //Set display label to say user cancelled
                }
            }
            else {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Select a .vec or .txt file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .vec & .txt files", "vec", "txt");
                fileChooser.addChoosableFileFilter(restrict);

                int render = fileChooser.showOpenDialog(null);

                if(render == JFileChooser.APPROVE_OPTION) {
                    //Set display label to that of path
                }
                else {
                    //Set display label to say user cancelled
                }
            }
        }
    }
}

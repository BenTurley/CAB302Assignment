import javax.swing.*;
import java.awt.*;

public class GUITest {
    //Creating a GUI
    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and setup window
        JFrame frame = new JFrame("Vector Editing Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add label
        JLabel label = new JLabel("Vector editing goes here");
        frame.getContentPane().add(label);

        //Display window
        frame.setPreferredSize(new Dimension(300,100));
        frame.setLocation(new Point(200, 200));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GUITest hello = new GUITest();
        GUITest.createAndShowGUI();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends JFrame {
    public GUI() {
        super("Vector Editing Software");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton();
        button.addMouseListener(new ButtonListener());

        this.getContentPane().add(button);

        //Display window
        this.setPreferredSize(new Dimension(300,300));
        this.setLocation(new Point(300,300));
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }

    public class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            //Do nothing
        }

        public void mousePressed(MouseEvent e) {
            double x = (e.getX());      //!Change to divide based on dimension of window, scaling from 0 - 1
            double y = (e.getY());      //!Change to divide based on dimension of window, scaling from 0 - 1
            System.out.println("Click!");
            System.out.println("X co-ord: " + x);
            System.out.println("Y co-ord: " + y);
        }

        public void mouseReleased(MouseEvent e) {
            //Do nothing
        }

        public void mouseEntered(MouseEvent e) {
            //Do nothing
        }

        public void mouseExited(MouseEvent e) {
            //Do nothing
        }
    }
}

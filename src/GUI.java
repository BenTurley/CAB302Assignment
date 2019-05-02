import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends JFrame {
    private JPanel panel;

    public GUI() {
        super("Vector Editing Software");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.addMouseListener(new ButtonListener());

        this.getContentPane().add(panel);

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
        private double x1;
        private double y1;
        private double x2;
        private double y2;

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
            //double x2 = (e.getX());      //!Change to divide based on dimension of window, scaling from 0 - 1
            //double y2 = (e.getY());      //!Change to divide based on dimension of window, scaling from 0 - 1
            this.x2 = (e.getX());
            this.y2 = (e.getY());
            //System.out.println("X co-ord: " + x2 + " Y co-ord: " + y2);

            Line line = new Line(x1,y1,x2,y2, panel);
            line.DrawLine();

            System.out.println(line.LineOutputFormatted());
        }

        public void mouseEntered(MouseEvent e) {
            //Do nothing
        }

        public void mouseExited(MouseEvent e) {
            //Do nothing
        }
    }
}

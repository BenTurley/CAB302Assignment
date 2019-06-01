import java.awt.*;

/**
 * Interface class for Vector Shapes
 */
public interface Vector {
    //void Vector();

    /**
     * Changes the outline colour of the Shape for when it's drawn
     * @param colour Colour of outline
     */
    void SetColour(Color colour);

    /**
     * Draws the Shape
     */
    void DrawVector(); //Alter to include Color variable input for pen colour
    //void FillVector(String colour);

    /**
     * Formats the Shapes's Vector coordinates in VEC language format
     * @return String of formatted coordinates of Shape Vector
     */
    String VectorOutputFormatted();
}

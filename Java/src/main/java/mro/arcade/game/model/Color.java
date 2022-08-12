package mro.arcade.game.model;

/**
 * Implementation of the color class. A color is defined by the RGB model where every part of the color (R means read, G means green, B means blue) has a value between 0 and 255.
 *
 * @author Noel Masur, Leon Federau
 * @since 2022-08-12
 */
public class Color {

    /**
     * Black color (0,0,0)
     */
    public static Color BLACK = new Color(0, 0, 0);
    public static Color RED = new Color(255, 0, 0);
    public static Color GREEN = new Color(0, 255, 0);
    public static Color BLUE = new Color(0, 0, 255);
    public static Color YELLOW = new Color(255, 255, 0);


    private int red;
    private int blue;
    private int green;

    /**
     * Creates a new color instance.
     *
     * @param red   the red part of the color
     * @param green the green part of the color
     * @param blue  the blue part of the color
     */
    public Color(int red, int green, int blue) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    /**
     * Returns the red part of the color.
     *
     * @return the red part of the color from 0 to 255.
     */
    public int getRed() {
        return red;
    }

    /**
     * Returns the blue part of the color.
     *
     * @return the blue part of the color from 0 to 255.Get blue
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Returns the green part of the color.
     *
     * @return the green part of the color from 0 to 255.
     */
    public int getGreen() {
        return green;
    }

    public String getHexString() {
        ;
        return String.format("%02X", red) + String.format("%02X", green) + String.format("%02X", blue);
    }
}

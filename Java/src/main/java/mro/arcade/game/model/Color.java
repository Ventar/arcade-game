package mro.arcade.game.model;

import java.util.Objects;

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
    public static final Color COLOR_BLACK = new Color(0, 0, 0);

    /**
     * Red color (255,0,0)
     */
    public static final Color COLOR_RED = new Color(255, 0, 0);

    /**
     * Red green (0,255,0)
     */
    public static final Color COLOR_GREEN = new Color(0, 255, 0);

    /**
     * Red blue (0,0,255)
     */
    public static final Color COLOR_BLUE = new Color(0, 0, 255);

    /**
     * Red yellow (255,255,255)
     */
    public static final Color COLOR_YELLOW = new Color(255, 255, 0);


    /**
     * Red.
     */
    private final int red;

    /**
     * Green.
     */
    private final int blue;

    /**
     * Blue.
     */
    private final int green;

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
        return String.format("%02X", red) + String.format("%02X", green) + String.format("%02X", blue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return red == color.red && blue == color.blue && green == color.green;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, blue, green);
    }
}

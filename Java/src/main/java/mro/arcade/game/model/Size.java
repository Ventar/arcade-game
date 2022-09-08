package mro.arcade.game.model;

/**
 * Implementation of the size class.
 *
 * <p>
 * <img src="doc-files/Example.png"/>
 * <p>
 *
 * @author Noel Masur, Leon Federau
 * @since 12.08.2022
 * <p>
 * Get the size from the field/board
 */

public class Size {

    private int height;
    private int width;

    public Size(int height, int width) {
        this.height = height;
        this.width = width;
    }


    /**
     * Get the height of the...
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the...
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "[" + width + "|" + height + "]";
    }
}

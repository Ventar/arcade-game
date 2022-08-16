package mro.arcade.game.model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Tiles to play the game Tetris
 */
public class Tile extends TileTemplate{
    private Color color;
    private Rotation rotation;

    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     * @param size   of the template
     */
    public Tile(List<Position> fields, Size size, Color color, Rotation rotation) {
        super(fields, size);
        this.color = color;
        this.rotation = rotation;
    }

    /**
     * Get the Color of the tile
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the color of a tile
     * @param color of the tile
     */
    public void setColor(Color color) {
        this.color = color;
    }
}

package mro.arcade.game.model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Tiles to play the game Tetris
 */
public class Tile extends TileTemplate {
    private Color color;

    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     */
    public Tile(List<Position> fields, Color color) {
        super(fields);
        this.color = color;
    }

    /**
     * Get the Color of the tile
     *
     * @return
     */
    public Color getColor() {
        return color;
    }


}

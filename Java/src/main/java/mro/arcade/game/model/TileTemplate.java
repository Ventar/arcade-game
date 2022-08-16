package mro.arcade.game.model;

import java.util.ArrayList;
import java.util.List;

public class TileTemplate {

    protected List<Position> fields = new ArrayList<>();

    private Size size;

    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     * @param size   of the template
     */
    public TileTemplate(List<Position> fields, Size size) {
        this.fields = fields;
        this.size = size;
    }

    /**
     * Return the size of the tile template for example 2x3
     *
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set the size of the tile template
     *
     * @param size of the template
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Get all fields where the tile ist set
     *
     * @return tile fields
     */
    public List<Position> getFields() {
        return fields;
    }

    /**
     * Set the fields where the tile should be set
     *
     * @param fields the tile is claiming
     */
    public void setFields(List<Position> fields) {
        this.fields = fields;
    }
}

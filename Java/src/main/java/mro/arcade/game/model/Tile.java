package mro.arcade.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tiles to play the game Tetris
 */
public class Tile {
    private Color color;
    protected List<Position> fields;

    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     */
    public Tile(List<Position> fields, Color color) {
        this.fields = fields;
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


    /**
     * Get all fields where the tile ist set
     *
     * @return tile fields
     */
    public List<Position> getPositions() {
        return fields;
    }

    public Tile rotate(Rotation rotation) {
        List<Position> newPositions = new ArrayList<>();
        Position anchor = fields.get(0);

        for (Position pos : fields) {
            Position referencePos = pos.sub(anchor);
            Position finalPos = referencePos.rotate(rotation).add(anchor);
            newPositions.add(finalPos);
        }

        Tile tile = new Tile(newPositions, this.color);

//        Tile tile = new Tile(
//                fields.stream()
//                        .map(pos -> pos.sub(anchor).rotate(rotation).add(anchor))
//                        .toList(),
//                this.color);


        return tile;
    }


}

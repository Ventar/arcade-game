package mro.arcade.game.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * A tile is a collection of multiple {@link Position}s which groups them together to manage them in the context of a {@link TileContainer} container.
 * <p>
 * Tiles can be added to the  container and be {@link #move(Direction)}d, {@link #translate(Position)}) or {@link #rotate(Rotation)}d.
 * When such an operation is called the positions of the tile are changed according to the operation.
 * <p>
 * For example: If you translate a tile with the position (0|0) and (0|1) by (2|2) the positions within the tile will change their values to (2|2) and
 * (2|3). Negative values would be allowed here.
 *
 * @author Noel Masur, Leon Federau
 * @since 2022-09-22
 */
public class Tile {

    /**
     * The color of the tile.
     */
    private Color color;

    /**
     * List of positions.
     */
    protected List<Position> fields;
    /**
     * The name of the tile, primary used to identify a tile in the logs and increase readability.
     */
    private String name;


    /**
     * Create a new instance of a tile
     *
     * @param name   of the tile
     * @param fields -> all positions of the tile
     * @param color  of the tile
     */
    public Tile(String name, List<Position> fields, Color color) {
        this.fields = new ArrayList<>(fields);
        this.color = color;
        this.name = name;
    }

    /**
     * Create a new instance of a tile
     *
     * @param name   of the tile
     * @param fields -> all positions of the tile
     */
    public Tile(String name, List<Position> fields) {
        this.fields = fields;
        this.color = Color.COLOR_GREEN;
        this.name = name;
    }

    /**
     * Get the Color of the tile
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    public Tile setColor(Color color){
        this.color = color;
        return this;
    }

    /**
     * Get the name of the tile
     *
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     * Get all fields where the tile ist set
     *
     * @return tile fields
     */
    public List<Position> getPositions() {
        return fields;
    }

    /**
     * let the tile rotate
     *
     * @param rotation
     * @return the rotated tile
     */
    public Tile rotate(Rotation rotation) {
        List<Position> newPositions = new ArrayList<>();
        Position anchor = fields.get(0);

        for (Position pos : fields) {
            Position referencePos = pos.sub(anchor);
            Position finalPos = referencePos.rotate(rotation).add(anchor);
            newPositions.add(finalPos);
        }

        return new Tile(getName(), newPositions, this.color);
    }

    /**
     * Calculate and moves the tile in the given direction
     *
     * @param direction to move the tile
     * @return the moved tile
     */
    public Tile move(Direction direction) {
        return switch (direction) {
            case DOWN -> translate(new Position(0, -1));
            case LEFT -> translate(new Position(-1, 0));
            case RIGHT -> translate(new Position(1, 0));
            case UP -> translate(new Position(0, 1));
        };
    }


    public Tile translate(Position position) {

        List<Position> newTilePositions = new ArrayList<>();

        for (Position tilePosition : this.getPositions()) {
            newTilePositions.add(tilePosition.add(position));
        }

        return new Tile(getName(), newTilePositions, this.getColor());
    }

    /**
     * Get the width/ columns of the field
     *
     * @return Integer
     */
    public int getWidth() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;


        for (Position position : fields) {
            if (min > position.getColumn()) {
                min = position.getColumn();

            }
            if (max < position.getColumn()) {
                max = position.getColumn();
            }
        }
        return max - min + 1;
    }

    /**
     * Get the height/ rows of the field
     *
     * @return
     */
    public int getHeight() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;


        for (Position position : fields) {
            if (min > position.getRow()) {
                min = position.getRow();

            }
            if (max < position.getRow()) {
                max = position.getRow();
            }
        }
        return max - min + 1;
    }

    public boolean hasFieldInRow(int row) {

        for (Position position : fields) {
            if (position.getRow() == row) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the row is full
     *
     * @param row to remove
     * @return boolean
     */
    public boolean removeRow(int row) {

        boolean removed = false;

        for (Iterator<Position> iterator = fields.iterator(); iterator.hasNext(); ) {
            Position position = iterator.next();

            if (position.getRow() == row) {
                removed = true;
                iterator.remove();

            }
        }
        if (removed) {
            List<Position> newFields = new ArrayList<>();
            for (Iterator<Position> iterator = fields.iterator(); iterator.hasNext(); ) {
                Position position = iterator.next();

                if (position.getRow() > row) {
                    Position newPosition = position.sub(new Position(0, 1));
                    newFields.add(newPosition);
                } else {
                    newFields.add(position);
                }

            }
            fields = newFields;
        }

        // Neue Liste NewFields
        // Iterator über alte Fields
        // Wenn Field Row > Übergebene Row, dann Row SUB (-1) und zu New Fields List hinzufügen
        // sonst einfach fields hinzufügen


        return removed;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "name='" + name + '\'' +
                ", fields=" + fields +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return fields.equals(tile.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }
}

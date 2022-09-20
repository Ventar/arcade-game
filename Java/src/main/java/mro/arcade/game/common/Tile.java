package mro.arcade.game.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Tiles to play the game Tetris
 */
public class Tile {

    private Color color;
    protected List<Position> fields;
    private String name;


    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     */
    public Tile(String name, List<Position> fields, Color color) {
        this.fields = fields;
        this.color = color;
        this.name = name;
    }

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

    public Tile move(Direction direction) {
        Position newPosition;
        List<Position> newTilePositions = new ArrayList<>();

        for (Position tilePosition : this.getPositions()) {
            if (direction == Direction.DOWN) {
                newPosition = new Position(tilePosition.getColumn(), tilePosition.getRow() - 1);
                newTilePositions.add(newPosition);
            } else if (direction == Direction.LEFT) {
                newPosition = new Position(tilePosition.getColumn() - 1, tilePosition.getRow());
                newTilePositions.add(newPosition);
            } else if (direction == Direction.RIGHT) {
                newPosition = new Position(tilePosition.getColumn() + 1, tilePosition.getRow());
                newTilePositions.add(newPosition);
            } else if (direction == Direction.UP) {
                newPosition = new Position(tilePosition.getColumn(), tilePosition.getRow() + 1);
                newTilePositions.add(newPosition);
            }
        }

        return new Tile(getName(), newTilePositions, this.getColor());
    }


    public Tile translate(Position position) {

        List<Position> newTilePositions = new ArrayList<>();

        for (Position tilePosition : this.getPositions()) {
            newTilePositions.add(tilePosition.add(position));
        }

        return new Tile(getName(), newTilePositions, this.getColor());
    }

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


    public boolean removeRow(int row) {

        boolean removed = false;

        for (Iterator<Position> iterator = fields.iterator(); iterator.hasNext(); ) {
            Position position = iterator.next();

            if (position.getRow() == row) {
                removed = true;
                iterator.remove();
            }
        }

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

package mro.arcade.game.model;

import java.util.Objects;

/**
 * Implementation of the position class
 *
 * @author Noel Masur, Leon Federau
 * @since 12.08.2022
 * <p>
 * Get the certain position from the field
 */
public class Position {

    private int row;
    private int column;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Get the position from the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the Position from the Column
     */
    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

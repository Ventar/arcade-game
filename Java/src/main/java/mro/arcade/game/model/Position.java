package mro.arcade.game.model;

import java.util.Objects;

/**
 * Implementation of the position class
 *
 * @author Noel Masur, Leon Federau
 * @since 12.08.2022
 * <p>
 *Get the certain position from the field
 */
public class Position {

    private final int row;
    private final int column;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Returns the row from the game field
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the game field
     * @return
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

    @Override
    public String toString() {

        String s = "("+ column + "|" + row + ")";

        return s;

    }

}

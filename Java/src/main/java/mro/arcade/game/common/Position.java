package mro.arcade.game.common;

import java.util.Objects;

/**
 * A position is a single field on the game board that is identified by a colum (x) and a row (y) value.
 *
 * @author Noel Masur, Leon Federau
 * @since  2022-08-12
 *  *
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Create a new instance of a position
     * @param column of the position
     * @param row of the position
     */
    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Returns the row from the game field
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the game field
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Add a value to a position to create a new one. Neither the current position nor the passed one are changed.
     * @param pos to add
     * @return Position
     */
    public Position add(Position pos) {
       return new Position(this.column + pos.getColumn(), this.row + pos.getRow());
    }

    /**
     * Subtract a value of a position to create a new one
     * @param pos to subtract
     * @return Position
     */
    public Position sub(Position pos) {
        return new Position(this.column - pos.getColumn(), this.row - pos.getRow());
    }

    /**
     * Rotates the position of something by certain degrees
     * @param rotation the degree to calculate the new rotated position
     * @return the rotated position
     */
    public Position rotate(Rotation rotation) {
        return switch (rotation) {
            case DEGREE_0 -> new Position(this.column, this.row);
            case DEGREE_90 -> new Position(this.row, -1 * this.column);
            case DEGREE_180 -> new Position(-1 * this.column, -1 * this.row);
            case DEGREE_270 -> new Position(-1 * this.row, this.column);
        };
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
        return "(" + column + "|" + row + ")";
    }

}

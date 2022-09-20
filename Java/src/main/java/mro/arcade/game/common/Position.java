package mro.arcade.game.common;

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

    private final int row;
    private final int column;

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
     * @return
     */
    public int getColumn() {
        return column;
    }

    public Position add(Position pos) {
        return new Position(this.column + pos.getColumn(), this.row + pos.getRow());
    }

    public Position sub(Position pos) {
        return new Position(this.column - pos.getColumn(), this.row - pos.getRow());
    }

    public Position rotate(Rotation rotation) {
        return switch (rotation) {
            case DEGREE_0 -> new Position(this.column, this.row);
            case DEGREE_90 -> new Position(this.row, -1 * this.column);
            case DEGREE_180 -> new Position(-1 * this.column, -1 * this.row);
            case DEGREE_270 -> new Position(-1 * this.row, this.column);
            default -> throw new IllegalArgumentException();
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

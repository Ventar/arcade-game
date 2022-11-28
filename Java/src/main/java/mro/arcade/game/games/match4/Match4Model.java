package mro.arcade.game.games.match4;


import mro.arcade.game.common.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model of the game board.
 */
public class Match4Model {

    public static final Logger LOG = LoggerFactory.getLogger(Match4Model.class);

    /**
     * The number of rows of the game board.
     */
    private int rows;

    /**
     * The number of columns of the game board.
     */
    private int columns;

    /**
     * The fields of the board.
     */
    private Color[][] fields;

    /**
     * Creates a new game board model.
     *
     * @param columns the number of columns between 4 and 10
     * @param rows    the number of rows between 4 and 10.
     */
    public Match4Model(int columns, int rows) {

        if (columns < 4) {
            throw new IllegalArgumentException("The number of columns must be larger than 3");
        }

        if (columns > 10) {
            throw new IllegalArgumentException("The number of columns must be smaller than 9");
        }

        if (rows < 4) {
            throw new IllegalArgumentException("The number of rows must be larger than 3");
        }

        if (rows > 10) {
            throw new IllegalArgumentException("The number of rows must be smaller than 9");
        }

        this.columns = columns;
        this.rows = rows;
        this.fields = new Color[columns][rows];

        clear();
    }

    /**
     * Copy Constructor
     *
     * @param model the original model
     */
    public Match4Model(Match4Model model) {
        this.columns = model.columns;
        this.rows = model.rows;
        this.fields = new Color[columns][rows];

        for (int column = 0; column < model.columns; column++) {
            for (int row = 0; row < model.rows; row++) {

                this.fields[column][row] = model.getFields()[column][row];
            }
        }
    }

    public Color getColor(int column, int row) {
        if (column < 0 || column > columns - 1 || row < 0 || row > rows - 1) {
            return Color.COLOR_BLACK;
        }

        return fields[column][row];
    }

    /**
     * Sets all colors of the game board field to {@link Color}.
     *
     * @return this model
     */
    public Match4Model clear() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                fields[x][y] = Color.COLOR_BLACK;
            }
        }

        return this;
    }

    /**
     * Sets the color of column and row to the passed color
     *
     * @param color  the color
     * @param column the column
     * @param row    the row
     *
     * @return this model
     */
    public Match4Model setColor(mro.arcade.game.common.Color color, int column, int row) {

        LOG.debug("Change field ::= ({}|{}) to color ::= [{}]", column, row, color);

        if (column < 0 || column > this.columns - 1) {
            throw new IllegalArgumentException("The column must be between ::= [0] and ::= [" + (this.columns - 1) + "].");
        }

        if (row < 0 || row > this.rows - 1) {
            throw new IllegalArgumentException("The row must be between ::= [0] and ::= [" + (this.rows - 1) + "].");
        }

        fields[column][row] = color;

        return this;

    }

    /**
     * The number of rows.
     *
     * @return rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * The number of columns
     *
     * @return columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * The field array.
     *
     * @return the fields
     */
    public mro.arcade.game.common.Color[][] getFields() {
        return fields;
    }

    @Override
    public String toString() {
        String s = "";
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {

                s += "[" + column + "|" + +row + "] - (" + this.fields[column][row].getRed() +
                             ", " + this.fields[column][row].getGreen() +
                             ", " + this.fields[column][row].getBlue() + ")\n";
            }
        }

        return s;

    }


}

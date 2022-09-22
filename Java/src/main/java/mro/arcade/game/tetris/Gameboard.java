package mro.arcade.game.tetris;

import mro.arcade.game.common.*;
import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * The Gameboard to play the Game, Including the Interface RenderData to render the Gameboard in different variations.
 * Render an Asci board or a Frame board
 */
public class Gameboard extends Basics implements RenderData  {

    public static final Logger LOG = LoggerFactory.getLogger(Gameboard.class);


    /**
     * Creates a new instance of the Gameboard with a specific size
     *
     * @param size of the Gameboard
     */
    public Gameboard(Size size, Position offsetPoint) {
        super(size,offsetPoint);

    }

    /**
     * Moving a Tile on the Gameboard, deleting its old position the new position
     *
     * @return the moved Tile
     */

    public Tile addTileToField(Tile tileTemplate) {

        Position position = new Position(size.getWidth() / 2, size.getHeight() - tileTemplate.getHeight());

        return addTileToField(tileTemplate,position);
    }

    /**
     * Rotate the tile in different rotations with key presses
     *
     * @param tile
     * @param rotation
     * @return
     */
    public synchronized Tile rotate(Tile tile, Rotation rotation) {

        Tile newTile = tile.rotate(rotation);

        this.tiles.remove(tile); // We need to remove the tile here because a collision would always happen otherwise

        if (!isTileOnBoard(newTile) || (collide(newTile))) {
            tiles.add(tile);
            return tile;
        } else {
            tiles.add(newTile);
            return newTile;
        }
    }

    /**
     * Moves the current Tile
     *
     * @param tile
     * @param direction
     * @return
     */

    public synchronized Tile moveTile(Tile tile, Direction direction) {
        Tile newTile = tile.move(direction);
        this.tiles.remove(tile);
        if (!isTileOnBoard(newTile) || (collide(newTile))) {
            this.tiles.add(tile);
            return tile;
        } else {
            this.tiles.add(newTile);
            return newTile;
        }
    }

    /**
     * Check if the current tile is able to move
     *
     * @param tile
     * @param direction
     * @return
     */
    public synchronized boolean canMove(Tile tile, Direction direction) {

        Tile newTile = tile.move(direction);

        this.tiles.remove(tile); // We need to remove the tile here because a collision would always happen otherwise
        boolean valid = isTileOnBoard(newTile) && !collide(newTile);
        this.tiles.add(tile);
        return valid;

    }

    /**
     * removed the whole row when its full
     * @return
     */
    public synchronized int removeFullRows() {
        int fullRowRemoved = 0;

        int rowsRemoved = 0;
        for (int row = 0; row < size.getHeight(); row++) {
            if (isRowFull(row)) {
                for (Tile tile : tiles) {
                    tile.removeRow(row);
                    rowsRemoved++;
                }
                fullRowRemoved++;
            }
        }

        for (int i = 0; i < rowsRemoved; i++) {
            for (Tile t : new ArrayList<>(tiles)) {
                moveTile(t, Direction.DOWN);
            }
        }
        return fullRowRemoved;
    }

    /**
     * checks if the row is already full
     * @param row
     * @return
     */
    private boolean isRowFull(int row) {
        int counter = 0;
        for (int column = 0; column < size.getWidth(); column++) {

            Position pos = new Position(column, row);
            if (detectCollision(pos)) {
                counter++;
            }
        }
        return counter == size.getWidth();
    }

    /**
     * Clears/remove the whole row, when its full
     */


    private boolean collide(Tile tile) {
        for (Position pos : tile.getPositions()) {
            if (detectCollision(pos)) {
                LOG.trace("Collision at position ::= [{}]", pos);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Gameboard{" +
                "tiles=" + tiles +
                ", size=" + size +
                '}';
    }


}



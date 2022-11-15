package mro.arcade.game.games.tetris;

import mro.arcade.game.common.*;
import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * The Gameboard to play the Game, Including the Interface RenderData to render the Gameboard in different variations.
 * Render an Asci board or a Frame board
 */
public class TetrisBoard extends TileContainer implements RenderData {

    public static final Logger LOG = LoggerFactory.getLogger(TetrisBoard.class);


    /**
     * Creates a new instance of the Gameboard with a specific size
     *
     * @param size of the Gameboard
     */
    public TetrisBoard(Size size, Position offsetPoint) {
        super(size, offsetPoint);

    }

    /**
     * Add a tile to the field
     *
     * @param tileTemplate to add
     * @return Tile
     */
    public Tile addTileToField(Tile tileTemplate) {

        Position position = new Position(size.getWidth() / 2, size.getHeight() - tileTemplate.getHeight());

        return addTileToField(tileTemplate, position);
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
     * Moves the current Tile in the given direction
     *
     * @param tile      to move
     * @param direction to move the tile
     * @return Tile
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
     * @param tile      to check
     * @param direction to move the tile
     * @return boolean
     */
    public synchronized boolean canMove(Tile tile, Direction direction) {

        Tile newTile = tile.move(direction);

        this.tiles.remove(tile); // We need to remove the tile here because a collision would always happen otherwise
        boolean valid = isTileOnBoard(newTile) && !collide(newTile);
        this.tiles.add(tile);
        return valid;

    }

    /**
     * removed the whole row when it is full
     *
     * @return int
     */
    public synchronized int removeFullRows() {
        for (int row = 0; row < size.getHeight(); row++) {

            if (isRowFull(row)) {
                List<Tile> modifiedTiles = new ArrayList<>();

                for (Tile tile : tiles) {
                    if (tile.removeRow(row)) {
                        modifiedTiles.add(tile);
                    }
                }

                for (Tile tile : new ArrayList<>(tiles)){
                    if (!modifiedTiles.contains(tile)){
                        moveTile(tile,Direction.DOWN);
                    }
                }
                return 1 + removeFullRows();
            }
        }
        return 0;
    }

    /**
     * checks if the row is already full
     *
     * @param row to check
     * @return boolean
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
     * Checks of a tile collides with something
     *
     * @param tile to check
     * @return boolean
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



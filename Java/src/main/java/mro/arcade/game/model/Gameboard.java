package mro.arcade.game.model;

import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * The Gameboard to play the Game, Including the Interface RenderData to render the Gameboard in different variations.
 * Render an Asci board or a Frame board
 */
public class Gameboard implements RenderData {

    private static final Logger LOG = LoggerFactory.getLogger(Gameboard.class);

    /**
     * The list contains all Tiles which are laid on the Gameboard
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * Indicates the size of the Gameboard
     */
    private Size size;


    private Position offsetPoint;



    /**
     * Creates a new instance of the Gameboard with a specific size
     *
     * @param size of the Gameboard
     */
    public Gameboard(Size size, Position offsetPoint) {
        this.size = size;
        this.offsetPoint = offsetPoint;

    }

    /**
     * Moving a Tile on the Gameboard, deleting its old position the new position
     *
     * @return the moved Tile
     */

    public Tile addTileToField(Tile tileTemplate) {

        Position position = new Position(size.getWidth() / 2, size.getHeight()- tileTemplate.getHeight());


        LOG.trace("Try to add tile ::= [{}] to position ::= [{}]", tileTemplate);

        // The apprentice solution
        // ----------------------------------------------------------------------------------------------------------------------

        // take the positions of the tile template which are (0|0), (0|1), (0|2), (1|0) fora regular L-Template for example
        List<Position> tileTemplatePositions = tileTemplate.getPositions();

        List<Position> tilePositions = new ArrayList<>();


        for (Position tilePosition : tileTemplatePositions) {
            Position pos = new Position(tilePosition.getColumn() +position.getColumn(), tilePosition.getRow() + position.getRow());

            if (isPositionOnBoard(pos)) {

                // all tiles on the field have a color, if no tile is available on a field the color black is returned, i.e. if a color
                // is returned for a given position the field was already set.
                if (checkIfSet(pos)) {
                    return null;
                }

                tilePositions.add(pos);
            }
        }
        Tile tile = new Tile(tileTemplate.getName(), tilePositions, tileTemplate.getColor());
        LOG.trace("Tile to add : " + tile);
        tiles.add(tile);

        LOG.debug("Added tile ::= [{}] to board", tile);

        return tile;
    }

    @Override
    public Color getFieldColor(Position position) {

        Position pos = new Position(position.getColumn() - offsetPoint.getColumn(), position.getRow() - offsetPoint.getRow());

        for (Tile tileInList : tiles) {
            for (Position positionInList : tileInList.getPositions()) {
                if (positionInList.equals(pos)) {
                    return tileInList.getColor();
                }
            }
        }

        return Color.COLOR_BLACK;
    }


    @Override
    public String toString() {
        return "Gameboard{" +
                "tiles=" + tiles +
                ", size=" + size +
                '}';
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

        if (!isOnBoard(newTile) || (collide(newTile))) {
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
        Tile newTile = tile.move(tile, direction);
        this.tiles.remove(tile);
        if (!isOnBoard(newTile) || (collide(newTile))) {
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

        Tile newTile = tile.move(tile, direction);

        this.tiles.remove(tile); // We need to remove the tile here because a collision would always happen otherwise
        boolean valid = isOnBoard(newTile) && !collide(newTile);
        this.tiles.add(tile);
        return valid;

    }

    public synchronized void removeFullRows() {

        int rowsRemoved = 0;
        for (int row = 0; row < size.getHeight(); row++) {
            if (isRowFull(row)) {
                for (Tile tile : tiles) {
                    tile.removeRow(row);
                    rowsRemoved++;
                }
            }
        }

        for (int i = 0; i < rowsRemoved; i++) {
            for (Tile t : new ArrayList<>(tiles)) {
                moveTile(t, Direction.DOWN);
            }
        }

    }

    /**
     * Checks if the tile is able to be placed
     *
     * @param position
     * @return
     */
   private boolean checkIfSet(Position position) {
        for (Tile tile : tiles) {
            for (Position pos : tile.getPositions()) {
                if (pos.equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param tile
     * @return
     */
    private boolean isOnBoard(Tile tile) {
        String s = "The Tile goes over the board, choose other direction or rotation";
        for (Position position : tile.getPositions()) {
            if (position.getColumn() > size.getWidth() - 1) {
                return false;
            }
            if (position.getRow() > size.getHeight() - 1) {
                return false;
            }
            if (position.getRow() < 0) {
                return false;
            }
            if (position.getColumn() < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPositionOnBoard(Position position) {
        String s = "The Tile goes over the board, choose other direction or rotation";
        if (position.getColumn() > size.getWidth() - 1) {
            return false;
        }
        if (position.getRow() > size.getHeight() - 1) {
            return false;
        }
        if (position.getRow() < 0) {
            return false;
        }
        if (position.getColumn() < 0) {
            return false;
        }

        return true;
    }

    private boolean isRowFull(int row) {
        int counter = 0;
        for (int column = 0; column < size.getWidth(); column++) {

            Position pos = new Position(column, row);
            if (checkIfSet(pos)) {
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
            if (checkIfSet(pos)) {
                LOG.trace("Collision at position ::= [{}]", pos);
                return true;
            }
        }
        return false;
    }


}



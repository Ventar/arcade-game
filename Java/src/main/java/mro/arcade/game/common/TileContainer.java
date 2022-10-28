package mro.arcade.game.common;

import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class        TileContainer implements RenderData {

    public static final Logger LOG = LoggerFactory.getLogger(TileContainer.class);

    protected final Position offsetPoint;

    protected final Size size;
    protected final List<Tile> tiles = new ArrayList<>();

    public TileContainer(Size size, Position offsetPoint) {
        this.size = size;
        this.offsetPoint = offsetPoint;
    }

    /**
     *
     * Checks if a tile collides with another tile or the end of the playing field
     *
     * @param position
     * @return that there are no collisions.
     */
    protected boolean detectCollision(Position position) {
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
     * Checks if the current position of a tile is already taken
     * @param position
     * @return if the the position is on board.
     */
    protected boolean isPositionOnBoard(Position position) {
        if (position.getColumn() > size.getWidth() - 1 ||
                position.getRow() > size.getHeight() - 1 ||
                position.getRow() < 0 ||
                position.getColumn() < 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the rotated or moved tile would still be in the field
     * @param tile to check
     * @return boolean
     */
    protected boolean isTileOnBoard(Tile tile) {
        for (Position position : tile.getPositions()) {
            if (!isPositionOnBoard(position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Places the current tile into the playfield.
     * @param tileTemplate
     * @param position
     * @return a tile.
     */
    public Tile addTileToField(Tile tileTemplate, Position position) {


        LOG.trace("Try to add tile ::= [{}] to position ::= [{}]", tileTemplate, position);

        Tile tile = tileTemplate.translate(position);

        for (Position pos : tile.getPositions()) {
            if (detectCollision(pos) || !isPositionOnBoard(pos)) {
                return null;
            }
        }

        tiles.add(tile);
        LOG.debug("Added tile ::= [{}] to board", tile);
        return tile;
    }

    /**
     * Get the color of a position on the field
     * @param position the position to fetch the color. If no color is set at that position {@link Color#COLOR_BLACK} is returned
     *
     * @return a color
     */
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

    /**
     * Remove a tile from the field
     * @param tile to remove
     */
    public void removeTile(Tile tile) {

        tiles.remove(tile);

    }

}

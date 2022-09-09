package mro.arcade.game.model;

import mro.arcade.game.view.RenderData;

import java.util.ArrayList;
import java.util.List;

import static mro.arcade.game.model.Gameboard.LOG;

public class Basics implements RenderData {


    protected final Position offsetPoint;

    protected final Size size;
    protected final List<Tile> tiles = new ArrayList<>();

    public Basics(Size size, Position offsetPoint) {
        this.size = size;
        this.offsetPoint = offsetPoint;
    }

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

    private boolean isPositionOnBoard(Position position) {
        if (position.getColumn() > size.getWidth() - 1 ||
                position.getRow() > size.getHeight() - 1 ||
                position.getRow() < 0 ||
                position.getColumn() < 0) {
            return false;
        }
        return true;
    }

    protected boolean isTileOnBoard(Tile tile) {
        for (Position position : tile.getPositions()) {
            if (!isPositionOnBoard(position)) {
                return false;
            }
        }
        return true;
    }

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

    public void removeTile(Tile tile){

        tiles.remove(tile);

    }

}

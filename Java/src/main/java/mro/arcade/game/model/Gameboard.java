package mro.arcade.game.model;

import mro.arcade.game.view.RenderData;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S106")
/**
 * The Gameboard to play the Game, Including the Interface RenderData to render the Gameboard in different variations.
 * Render an Asci board or a Frame board
 */
public class Gameboard implements RenderData {

    /**
     * The list contains all Tiles which are laid on the Gameboard
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * Indicates the size of the Gameboard
     */
    private Size size;

    /**
     * Creates a new instance of the Gameboard with a specific size
     * @param size of the Gameboard
     */
    public Gameboard(Size size) {
        this.size = size;

    }

    /**
     * Moving a Tile on the Gameboard, deleting its old position the new position
     * @param tile to move
     * @param direction to move the tile
     * @return the moved Tile
     */

    public Tile addTileToField(TileTemplate tileTemplate, Rotation rotation, Position boardPosition, Color color) {

        // The apprentice solution
        // ----------------------------------------------------------------------------------------------------------------------

        // take the positions of the tile template which are (0|0), (0|1), (0|2), (1|0) fora regular L-Template for example
        List<Position> tileTemplatePositions = tileTemplate.rotate(rotation).getPositions();
        List<Position> tilePositions = new ArrayList<>();

        for (Position tilePosition : tileTemplatePositions) {

            Position newBoardPosition = new Position(tilePosition.getColumn() + boardPosition.getColumn(), tilePosition.getRow() + boardPosition.getRow());

            // all tiles on the field have a color, if no tile is available on a field the color black is returned, i.e. if a color
            // is returned for a given position the field was already set.
            if (checkIfSet(newBoardPosition)) {
                throw new IllegalArgumentException("Position " + newBoardPosition + " is already filled");
            }

            tilePositions.add(newBoardPosition);
        }

        Tile tile = new Tile(tilePositions, color);
        System.out.println("Tile to add : " + tile);
        tiles.add(tile);


        return tile;
    }

    @Override
    public Color getFieldColor(Position position) {
        validatePosition(position);
        for (Tile tileInList : tiles) {
            for (Position positionInList : tileInList.getPositions()) {
                if (positionInList.equals(position)) {
                    return tileInList.getColor();
                }
            }
        }

        return Color.COLOR_BLACK;
    }

    @Override
    public Size getSize() {
        return size;
    }

    /**
     * The  method checks if the posititon is outside of the border. Or is already loaded with another tile.
     *
     * @param position the position to check
     * @throws InterruptedException if the check fails.
     */
    private void validatePosition(Position position) {

        String pos = "The position";

        if (position.getColumn() > size.getWidth() - 1) {
            throw new IllegalArgumentException(pos + position + " has an invalid column, max column is " + (size.getWidth() - 1));
        }
        if (position.getRow() > size.getHeight() - 1) {
            throw new IllegalArgumentException(pos + position + " has an invalid row, max row is " + (size.getHeight() - 1));
        }
        if (position.getRow() < 0) {
            throw new IllegalArgumentException(pos + position + " has an invalid row, min row is 0 ");
        }
        if (position.getColumn() < 0) {
            throw new IllegalArgumentException(pos + position + " has an invalid column, min column is 0 ");
        }

    }


    @Override
    public String toString() {
        return "Gameboard{" +
                "tiles=" + tiles +
                ", size=" + size +
                '}';
    }


    public Tile rotate(Tile t, Rotation rotation) {

        tiles.remove(t);

        Tile rotatedTile = t.rotate(rotation);


        for (Position tilePosition : rotatedTile.getPositions()) {
            if (checkIfSet(tilePosition)) {
                System.out.println("Collision: " + tilePosition + ", do not move, board = " + tiles);
                tiles.add(t);
                return t;
            }
        }

        tiles.add(rotatedTile);
        return rotatedTile;
    }


    public boolean checkIfSet(Position position) {
        for (Tile tile : tiles) {
            for (Position pos : tile.getPositions()) {
                if (pos.equals(position)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Tile moveTile(Tile tile, Direction direction) {

        this.tiles.remove(tile);

        Tile newTile = tile.move(tile, direction);

        for (Position tilePosition : newTile.getPositions()) {
            if (checkIfSet(tilePosition)) {
                System.out.println("Collision: " + tilePosition + ", do not move, board = " + tiles);
                this.tiles.add(tile);
                return tile;
            }
        }

        this.tiles.add(newTile);
        return newTile;
    }
}



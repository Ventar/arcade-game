package mro.arcade.game.model;

import mro.arcade.game.view.ASCIIRenderer;
import mro.arcade.game.view.RenderData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class Gameboard implements RenderData {

    private List<Tile> tiles = new ArrayList<>();

    private Size size;

    public Gameboard(Size size) {
        this.size = size;

    }

    public Tile moveTile(Tile tile, Direction direction) {

        this.tiles.remove(tile);

        Tile newTile = tile.move(tile, direction);

        for (Position tilePosition : newTile.getPositions()) {
            for (Tile boardTile : this.tiles) {
                for (Position boardTilePosition : boardTile.getPositions()) {
                    if (tilePosition.equals(boardTilePosition)) {
                        this.tiles.add(tile);
                        return null;
                    }
                }
            }
        }

        this.tiles.add(newTile);
        return newTile;
    }

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
            if (!getFieldColor(newBoardPosition).equals(Color.COLOR_BLACK)) {
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





    public void rotate(Tile t, Rotation rotation) {

        tiles.remove(t);
        Tile rotatedTile = t.rotate(rotation);
        for (Position rotatedTilePos : rotatedTile.getPositions()) {
            for (Tile tileCheck : tiles) {
                for (Position positioncheck : tileCheck.getPositions())
                    if (rotatedTilePos.equals(positioncheck)) {
                        tiles.add(t);
                        return;
                    }
            }
        }
        tiles.add(rotatedTile);
    }
}



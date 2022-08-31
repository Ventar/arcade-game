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
     *
     * @param size of the Gameboard
     */
    public Gameboard(Size size) {
        this.size = size;

    }

    /**
     * Moving a Tile on the Gameboard, deleting its old position the new position
     *
     * @return the moved Tile
     */

    public Tile addTileToField(Tile tileTemplate, Rotation rotation, Position boardPosition, Color color) {

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
        //validatePosition(position);
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


    public synchronized Tile rotate(Tile t, Rotation rotation) {


        Tile newTile = t.rotate(rotation);

        if (!validateTile(newTile)) {
            System.out.println("The Tile goes of the board");
            return t;
        }

        tiles.remove(t);

        for (Position tilePosition : newTile.getPositions()) {
            if (checkIfSet(tilePosition)) {
                ;
                System.out.println("Collision: " + tilePosition + ", do not move, board = " + tiles);
                tiles.add(t);
                return t;
            }
        }

        tiles.add(newTile);
        return newTile;
    }


    public synchronized Tile moveTile(Tile tile, Direction direction) {

        Tile newTile = tile.move(tile, direction);

        if (!validateTile(newTile)) {
            System.out.println("The Tile goes of the board");
            return tile;
        }

        this.tiles.remove(tile);
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

    public synchronized boolean canMove(Tile tile, Direction direction) {

        Tile newTile = tile.move(tile, direction);

        if (!validateTile(newTile)) {
            System.out.println("The Tile goes of the board");
            return false;
        }

        this.tiles.remove(tile);
        for (Position tilePosition : newTile.getPositions()) {
            if (checkIfSet(tilePosition)) {
                System.out.println("Collision: " + tilePosition + ", do not move, board = " + tiles);
                this.tiles.add(tile);
                return false;
            }
        }
        this.tiles.add(tile);

        return true;
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

    private boolean validateTile(Tile tile) {
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


    public boolean isRowFull(int row) {

        int counter = 0;
        for (int column = 0; column < size.getWidth(); column++) {

            Position pos = new Position(column, row);

            if (checkIfSet(pos)) {
                counter++;
            }


        }
        return counter == size.getWidth();
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


//
//    public boolean checkIfSet(Position position) {
//        for (Tile tile : tiles) {
//            for (Position pos : tile.getPositions()) {
//                if (pos.equals(position)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

}



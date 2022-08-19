package mro.arcade.game.model;

import java.util.ArrayList;
import java.util.List;

public class Gameboard {

    private List<Tile> tiles = new ArrayList<>();

    private Size size;

    public Gameboard(Size size) {
        this.size = size;

    }

    public Tile addTileToField(TileTemplate tileTemplate, Rotation rotation, Position boardPosition) {

        // The apprentice solution
        // ----------------------------------------------------------------------------------------------------------------------

        // take the positions of the tile template which are (0|0), (0|1), (0|2), (1|0) fora regular L-Template for example
        List<Position> tileTemplatePositions = tileTemplate.rotate(rotation).getPositions();
        int count = tileTemplatePositions.size(); // number of positions in the tile template, in L Template this is 4

        // we need to calculate the positions of the tile template when it is added to the passed position of the board.
        // The result is stored in this list to create a new tile from them.
        List<Position> tilePositions = new ArrayList<>();

        // iterate over all entries in the tileTemplatePositions and calculate the new position for every entry
        for (int i = 0; i < count; i++) {

            Position tilePosition = tileTemplatePositions.get(i);

            int column = tilePosition.getColumn() + boardPosition.getColumn();
            int row = tilePosition.getRow() + boardPosition.getRow();

            Position newBoardPosition = new Position(column, row);
            tilePositions.add(newBoardPosition);

        }

        Tile tile = new Tile(tilePositions, Color.COLOR_RED);

        System.out.println("Tile to add : " + tile);


        for (Position check : tile.getPositions()) {
            if (check.getColumn() > size.getWidth() - 1) {
                throw new IllegalArgumentException("The position " + check + " has an invalid column, max column is " + (size.getWidth() - 1));
            }
            if (check.getRow() > size.getHeight() - 1) {
                throw new IllegalArgumentException("The position " + check + " has an invalid row, max row is " + (size.getHeight() - 1));
            }
            if (check.getRow() < 0) {
                throw new IllegalArgumentException("The position " + check + " has an invalid row, min row is 0 ");

            }
            if (check.getColumn() < 0) {
                throw new IllegalArgumentException("The position " + check + " has an invalid column, min column is 0 ");
            }

            if (tiles.equals(1)){
                throw new IllegalArgumentException("This tile cannot fit because there is a other tile");
            }


        }
        tiles.add(tile);

        // The apprentice solution with for-each
        // ----------------------------------------------------------------------------------------------------------------------

        //        List<Position> tilePositions = new ArrayList<>();
        //
        //         for(Position tilePosition: tileTemplate.getTemplateFields()) {
        //             int column = tilePosition.getColumn() + boardPosition.getColumn();
        //             int row = tilePosition.getRow() + boardPosition.getRow();
        //
        //             Position newBoardPosition = new Position(column, row);
        //             tilePositions.add(newBoardPosition);
        //         }
        //
        //        Tile tile = new Tile(tilePositions, Color.COLOR_RED, Rotation.DEGREE_0);

        // The apprentice solution with for-each, calculations in constructor
        // ----------------------------------------------------------------------------------------------------------------------

        //        List<Position> tilePositions = new ArrayList<>();
        //
        //        for (Position tilePosition : tileTemplate.getTemplateFields()) {
        //            Position newBoardPosition = new Position(
        //                    tilePosition.getColumn() + boardPosition.getColumn(),
        //                    tilePosition.getRow() + boardPosition.getRow()
        //            );
        //            tilePositions.add(newBoardPosition);
        //        }
        //
        //        Tile tile = new Tile(tilePositions, Color.COLOR_RED, Rotation.DEGREE_0);

        // The apprentice solution with for-each, calculations in constructor, direct add
        // ----------------------------------------------------------------------------------------------------------------------

        //        List<Position> tilePositions = new ArrayList<>();
        //
        //        for (Position tilePosition : tileTemplate.getTemplateFields()) {
        //            tilePositions.add(new Position(
        //                    tilePosition.getColumn() + boardPosition.getColumn(),
        //                    tilePosition.getRow() + boardPosition.getRow()
        //            ));
        //        }
        //
        //        Tile tile = new Tile(tilePositions, Color.COLOR_RED, Rotation.DEGREE_0);

        // The apprentice solution with for-each, calculations in constructor, direct add, stream API
        // ----------------------------------------------------------------------------------------------------------------------

        //        List<Position> tilePositions = new ArrayList<>();
        //
        //        tileTemplate.getTemplateFields().forEach(tilePosition ->
        //                        tilePositions.add(new Position(
        //                                tilePosition.getColumn() + boardPosition.getColumn(),
        //                                tilePosition.getRow() + boardPosition.getRow()
        //                        ))
        //                );
        //
        //        Tile tile = new Tile(tilePositions, Color.COLOR_RED, Rotation.DEGREE_0);


        // The apprentice solution with for-each, calculations in constructor, direct add, stream API
        // ----------------------------------------------------------------------------------------------------------------------

        //        List<Position> tilePositions = tileTemplate.getTemplateFields().stream().map(tilePosition ->
        //                new Position(
        //                        tilePosition.getColumn() + boardPosition.getColumn(),
        //                        tilePosition.getRow() + boardPosition.getRow()
        //                )
        //        ).toList();
        //
        //        Tile tile = new Tile(tilePositions, Color.COLOR_RED, Rotation.DEGREE_0);

        // Stream API solution
        // ----------------------------------------------------------------------------------------------------------------------


        //        Tile tile = new Tile(tileTemplate.getTemplateFields().stream().map(
        //                ttf -> new Position(
        //                        ttf.getColumn() + boardPosition.getColumn(),
        //                        ttf.getRow() + boardPosition.getColumn())).toList(),
        //                Color.COLOR_RED,
        //                Rotation.DEGREE_0);


        return tile;
    }

    @Override
    public String toString() {
        return "Gameboard{" +
                "tiles=" + tiles +
                ", size=" + size +
                '}';
    }

    public boolean canAddTile(TileTemplate tileTemplate, Position position) {


        return true;
    }

    public static void main(String[] args) {

        Gameboard board = new Gameboard(new Size(3, 4));
        System.out.println(board);
        board.addTileToField(TileTemplate.L_TEMPLATE, Rotation.DEGREE_270, new Position(2, 0));
        System.out.println(board);
        board.addTileToField(TileTemplate.L_TEMPLATE, Rotation.DEGREE_270, new Position(2, 0));
        System.out.println(board);

    }

//    public Tile getTile(Position position){}
//
//    public Tile moveTile(Tile tileToMove){}
//
//    public Tile rotateTile(Tile tile, Rotation rotation){}
//
//    public void convertTileToBoardField(Tile tile){}
//
//    public void removeFulllines(){}
//
//    public Color getFieldColor(Position position){}
}

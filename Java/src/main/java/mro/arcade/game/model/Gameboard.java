package mro.arcade.game.model;

import java.util.List;

public class Gameboard {
    private Size size;
    private Tile tile;
    private BoardField[][] fields = new BoardField[size.getWidth()][size.getHeight()];

    public Tile addTileToField(TileTemplate tileTemplate, Rotation rotation, Position position, Color color){
        Tile tile = new Tile(tileTemplate.getTemplateFields(), tileTemplate.getTemplateSize(), color, rotation);
        //If(canAddTile == true){...}-------Noels part
        return tile;
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

package mro.arcade.game.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TileContainerTest {

    @Test
    public void testTileContainer() {

        Size size = new Size(3, 4);
        Position offsetPoint = new Position(3, 4);
        TileContainer tileContainer = new TileContainer(size, offsetPoint);

        Assertions.assertEquals(3, tileContainer.size.getHeight());
        Assertions.assertEquals(4, tileContainer.size.getWidth());
        Assertions.assertEquals(3, tileContainer.offsetPoint.getColumn());
        Assertions.assertEquals(4, tileContainer.offsetPoint.getRow());
    }

    @Test
    public void testDetectCollision() {
        TileContainer tileContainer = new TileContainer(new Size(3, 4), new Position(3, 4));
        Tile tile = tileContainer.addTileToField(TileLibary.O_TEMPLATE, new Position(0, 0));
        List<Position> fields = new ArrayList<>();
        fields.add(new Position(0, 0));
        fields.add(new Position(0, 1));
        fields.add(new Position(1, 0));
        fields.add(new Position(1, 1));

        Color color = new Color(3, 4, 5);
        String name = "Tile";
        Tile finalTile = new Tile(name, fields, color);

        Assertions.assertEquals(tile, finalTile);
        System.out.println(tile);

    }

    @Test
    public void testGetFieldColor() {


    }


    @Test
    public void testIsPositionOnBoard() {


    }

    @Test
    public void testIsTileOnBoard() {
        Position position = new Position(3, 4);

        List<Position> fields = new ArrayList<>();
        fields.add(new Position(3, 4));

        String name = "Tile";

        Tile tile = new Tile(name, fields);

        //Assertions.assertEquals(tile,position);

    }

}

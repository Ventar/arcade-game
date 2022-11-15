package mro.arcade.game.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TileTest {


    @Test
    public void testTile() {
        List<Position> fields = new ArrayList<>();
        fields.add(new Position(2, 3));
        fields.add(new Position(2, 4));
        fields.add(new Position(2, 5));
        fields.add(new Position(2, 6));


        Color color = new Color(3, 4, 5);
        String name = "Tile";
        Tile tile = new Tile(name, fields, color);
        Tile tileNoColor = new Tile(name, fields);
        tile.hashCode();

        Assertions.assertEquals("Tile", tile.getName());
        Assertions.assertEquals(32833, tile.getColor().hashCode());
        Assertions.assertEquals(fields, tile.getPositions());
        Assertions.assertEquals(fields, tileNoColor.getPositions());

    }

    @Test
    public void testRotate() {

        List<Position> fields = new ArrayList<>();
        fields.add(new Position(2, 3));
        fields.add(new Position(2, 4));
        fields.add(new Position(2, 5));
        fields.add(new Position(2, 6));

        Color color = new Color(3, 4, 5);
        String name = "Tile";
        Tile tile = new Tile(name, fields, color);

        Rotation rotation = Rotation.DEGREE_90;
        Tile rotatedTile = tile.rotate(rotation);

        List<Position> rotatedPositions = new ArrayList<>();
        rotatedPositions.add(new Position(2, 3));
        rotatedPositions.add(new Position(3, 3));
        rotatedPositions.add(new Position(4, 3));
        rotatedPositions.add(new Position(5, 3));

        Assertions.assertEquals(rotatedPositions, rotatedTile.getPositions());

//        Assertions.assertEquals(4,fields.get(0));
//        Assertions.assertEquals(rotatedPositions.get(0),fields.get(0));
//        Assertions.assertEquals(rotatedPositions.get(1),fields.get(1));
//        Assertions.assertEquals(rotatedPositions.get(2),fields.get(2));
//        Assertions.assertEquals(rotatedPositions.get(3),fields.get(3));
//
//        Assertions.assertEquals(fields, tile.rotate(rotation));


    }

    @Test
    public void testMove() {
        List<Position> fields = new ArrayList<>();
        fields.add(new Position(2, 3));
        fields.add(new Position(2, 4));
        fields.add(new Position(2, 5));
        fields.add(new Position(2, 6));

        Color color = new Color(3, 4, 5);
        String name = "Tile";
        Tile tile = new Tile(name, fields, color);

        List<Position> movedPositions = new ArrayList<>();
        movedPositions.add(new Position(2, 2));
        movedPositions.add(new Position(2, 3));
        movedPositions.add(new Position(2, 4));
        movedPositions.add(new Position(2, 5));

        Assertions.assertEquals(movedPositions, tile.move(Direction.DOWN).getPositions());

        movedPositions.clear();
        movedPositions.add(new Position(3, 3));
        movedPositions.add(new Position(3, 4));
        movedPositions.add(new Position(3, 5));
        movedPositions.add(new Position(3, 6));

        Assertions.assertEquals(movedPositions, tile.move(Direction.RIGHT).getPositions());


        movedPositions.clear();
        movedPositions.add(new Position(1, 3));
        movedPositions.add(new Position(1, 4));
        movedPositions.add(new Position(1, 5));
        movedPositions.add(new Position(1, 6));

        Assertions.assertEquals(movedPositions, tile.move(Direction.LEFT).getPositions());

        movedPositions.clear();
        movedPositions.add(new Position(2, 4));
        movedPositions.add(new Position(2, 5));
        movedPositions.add(new Position(2, 6));
        movedPositions.add(new Position(2, 7));

        Assertions.assertEquals(movedPositions, tile.move(Direction.UP).getPositions());
    }

    @Test
    public void testTranslate() {



        List<Position> newTilePositions = new ArrayList<>();
        newTilePositions.add(new Position(2, 3));
        newTilePositions.add(new Position(2, 4));
        newTilePositions.add(new Position(2, 5));
        newTilePositions.add(new Position(2, 6));

        Position position = new Position(2, 3);
        Tile tile = new Tile("1",newTilePositions,Color.COLOR_BLACK);

        Tile tile1 = tile.translate(position);

        List<Position> tileEqual = new ArrayList<>();
        tileEqual.add(new Position(4, 6));
        tileEqual.add(new Position(4, 7));
        tileEqual.add(new Position(4, 8));
        tileEqual.add(new Position(4, 9));

        Assertions.assertEquals(tileEqual,tile1.getPositions());
    }

}

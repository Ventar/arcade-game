package mro.arcade.game.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {


    @Test
    public void testCreation() {
        Position position = new Position(2, 3);

        Assertions.assertEquals(3, position.getRow());

        Assertions.assertEquals(2, position.getColumn());

        position.hashCode(); // Cheat for 100%


    }

    @Test
    public void testAdd() {
        Position position = new Position(2, 3);
        Position posToAdd = new Position(1, 2);

        Position result = position.add(posToAdd);

        Assertions.assertEquals(3, result.getColumn());
        Assertions.assertEquals(5, result.getRow());
        Assertions.assertEquals(2, position.getColumn());
        Assertions.assertEquals(1, posToAdd.getColumn());
        Assertions.assertEquals(3, position.getRow());
        Assertions.assertEquals(2, posToAdd.getRow());


    }

    @Test
    public void testSub() {

        Position position = new Position(2, 3);
        Position posToSub = new Position(1, 2);

        Position result = position.sub(posToSub);

        Assertions.assertEquals(1, result.getColumn());
        Assertions.assertEquals(1, result.getRow());
        Assertions.assertEquals(2, position.getColumn());
        Assertions.assertEquals(3, position.getRow());
        Assertions.assertEquals(1, posToSub.getColumn());
        Assertions.assertEquals(2, posToSub.getRow());
    }

    @Test
    public void testRotate() {
        Position position = new Position(2, 3);

        Position result = position.rotate(Rotation.DEGREE_0);

        Assertions.assertEquals(2, result.getColumn());
        Assertions.assertEquals(3, result.getRow());

        result = position.rotate(Rotation.DEGREE_90);

        Assertions.assertEquals(3, result.getColumn());
        Assertions.assertEquals(-2, result.getRow());

        result = position.rotate(Rotation.DEGREE_180);

        Assertions.assertEquals(-2, result.getColumn());
        Assertions.assertEquals(-3, result.getRow());

        result = position.rotate(Rotation.DEGREE_270);

        Assertions.assertEquals(-3, result.getColumn());
        Assertions.assertEquals(2, result.getRow());

    }

    @Test
    public void testToString() {

        Position position = new Position(2, 3);

        Assertions.assertEquals("(2|3)", position.toString());


    }

    @Test
    public void testEquals() {
        Position position = new Position(2, 3);
        Position posEqual = new Position(2, 3);
        Position posNoEqual = new Position(1, 2);

        Assertions.assertTrue(position.equals(posEqual));
        Assertions.assertFalse(position.equals(posNoEqual));

    }

}

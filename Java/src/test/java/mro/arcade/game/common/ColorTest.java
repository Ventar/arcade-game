package mro.arcade.game.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    public void testColor() {
        Color color = new Color(2, 3, 4);

        Assertions.assertEquals(2, color.getRed());
        Assertions.assertEquals(3, color.getGreen());
        Assertions.assertEquals(4, color.getBlue());
        Assertions.assertEquals(31840,color.hashCode());
    }

    @Test
    public void testToHexString() {
        Color color = new Color(2, 3, 4);

        Assertions.assertEquals("020304", color.getHexString());

    }

    @Test
    public void testToString() {

        Color color = new Color(2, 3, 4);
        Assertions.assertEquals("#020304", color.toString());
    }

    @Test
    public void testEquals(){
        Color color = new Color(2,3,4);
        Color colorEqual = new Color(2,3,4);
        Color colorNoEqual = new Color(4,6,8);

        Assertions.assertTrue(color.equals(colorEqual));
        Assertions.assertFalse(color.equals(colorNoEqual));

    }
}

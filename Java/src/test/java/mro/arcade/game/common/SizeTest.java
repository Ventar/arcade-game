package mro.arcade.game.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SizeTest {


    @Test
    public void testSize() {
        Size size = new Size(3, 4);
        Assertions.assertEquals(3, size.getHeight());
        Assertions.assertEquals(4, size.getWidth());
    }

    @Test
    public void testToString(){
        Size size = new Size(3, 4);
        Assertions.assertEquals("[4|3]", size.toString());
    }


}

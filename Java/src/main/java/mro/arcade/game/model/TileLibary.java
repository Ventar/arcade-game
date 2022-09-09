package mro.arcade.game.model;

import java.util.ArrayList;
import java.util.Arrays;

public class TileLibary {


    public static final Tile L_TEMPLATE = new Tile("L_TEMPLATE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(1, 0)
    ));

    public static final Tile O_TEMPLATE = new Tile("O_TEMPLATE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(1, 1)
    ));
    public static final Tile S_TEMPLATE = new Tile("S_TEMPLATE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(-1, 1),
            new Position(-1, 2)
    ));
    public static final Tile I_TEMPLATE = new Tile("I_TEMPLATE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(0, 3)
    ));

    public static final Tile T_TEMPLATE = new Tile("T_TEMPLATE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(1, 1)
    ));

    public static final Tile T_TEMPLATE_REVERSE = new Tile("T_TEMPLATE_REVERSE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(-1, 1)
    ));
    public static final Tile L_TEMPLATE_REVERSE = new Tile("L_TEMPLATE_REVERSE", Arrays.asList(
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2)
    ));

    public static final Tile S_TEMPLATE_REVERSE = new Tile("S_TEMPLATE_REVERSE", Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 1),
            new Position(1, 2)
    ));

    public static final Tile NUMBER_TEMPLATE_ZERO = new Tile("NUMBER_TEMPLATE_ZERO", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 0),
            p(1, 4),
            p(2, 0),
            p(2, 4),
            p(2, 3),
            p(2, 2),
            p(2, 1)


    ));
    public static final Tile NUMBER_TEMPLATE_ONE = new Tile("NUMBER_TEMPLATE_ONE", Arrays.asList(
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(2, 3),
            p(2, 4),
            p(1, 4),
            p(0, 3)

    ));
    public static final Tile NUMBER_TEMPLATE_TWO = new Tile("NUMBER_TEMPLATE_TWO", Arrays.asList(
            p(0, 0),
            p(1, 0),
            p(2, 0),
            p(0, 2),
            p(2, 0),
            p(1, 4),
            p(2, 4),
            p(2, 3),
            p(2, 2),
            p(1, 2),
            p(0, 1),
            p(0, 4)

    ));
    public static final Tile NUMBER_TEMPLATE_THREE = new Tile("NUMBER_TEMPLATE_THREE", Arrays.asList(
            p(0, 0),
            p(1, 0),
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(1, 2),
            p(0, 2),
            p(2, 4),
            p(1, 4),
            p(0, 4),
            p(2, 3)

    ));
    public static final Tile NUMBER_TEMPLATE_FOUR = new Tile("NUMBER_TEMPLATE_FOUR", Arrays.asList(
            p(0, 4),
            p(0, 3),
            p(0, 2),
            p(1, 2),
            p(2, 2),
            p(2, 1),
            p(2, 0),
            p(2, 4),
            p(2, 3)

    ));
    public static final Tile NUMBER_TEMPLATE_FIVE = new Tile("NUMBER_TEMPLATE_FIVE", Arrays.asList(
            p(0, 0),
            p(1, 0),
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(1, 2),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 4),
            p(2, 4)

    ));
    public static final Tile NUMBER_TEMPLATE_SIX = new Tile("NUMBER_TEMPLATE_SIX", Arrays.asList(
            p(0, 0),
            p(1, 0),
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(1, 2),
            p(0, 2),
            p(0, 1),
            p(0, 3),
            p(0, 4),
            p(1, 4),
            p(2, 4)


    ));
    public static final Tile NUMBER_TEMPLATE_SEVEN = new Tile("NUMBER_TEMPLATE_SEVEN", Arrays.asList(
            p(0, 4),
            p(1, 4),
            p(2, 4),
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(2, 3)

    ));
    public static final Tile NUMBER_TEMPLATE_EIGHT = new Tile("NUMBER_TEMPLATE_EIGHT", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 0),
            p(1, 2),
            p(1, 4),
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(2, 3),
            p(2, 4)

    ));
    public static final Tile NUMBER_TEMPLATE_NINE = new Tile("NUMBER_TEMPLATE_NINE", Arrays.asList(
            p(0, 0),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 0),
            p(1, 2),
            p(1, 4),
            p(2, 0),
            p(2, 1),
            p(2, 2),
            p(2, 3),
            p(2, 4)
    ));


//    public static final Tile NUMBER_TEMPLATE_FOUR = new Tile("NUMBER_TEMPLATE_FOUR", Arrays.asList(
//            new Position(23, 23),
//            new Position(21, 23),
//            new Position(23, 22),
//            new Position(21, 22),
//            new Position(22, 21),
//            new Position(23, 20),
//            new Position(23, 19),
//            new Position(23, 21),
//            new Position(21, 21)
//
//    ));
//
//    public static final Tile NUMBER_TEMPLATE_ONE = new Tile("NUMBER_TEMPLATE_ONE", Arrays.asList(
//            new Position(23, 23),
//            new Position(22, 23),
//            new Position(23, 22),
//            new Position(21, 22),
//            new Position(23, 20),
//            new Position(23, 19),
//            new Position(23, 21)
//
//
//    ));
//
//
//    public static final Tile NUMBER_TEMPLATE_NINE = new Tile("NUMBER_TEMPLATE_NINE", Arrays.asList(
//            p(23, 23), p(22, 23), p(21, 23),
//            new Position(23, 22),
//            new Position(21, 22),
//            new Position(22, 21),
//            new Position(23, 20),
//            new Position(22, 19),
//            new Position(23, 19),
//            new Position(21, 19),
//            new Position(23, 21),
//            new Position(21, 21)
//
//    ));
//
//
//    public static final Tile NUMBER_TEMPLATE_EIGHT = new Tile("NUMBER_TEMPLATE_EIGHT", Arrays.asList(
//
//            new Position(23, 23),
//            new Position(22, 23)));
//            new Position(21,23)
//            new Position(23,22)
//            new Position(21,22)
//           new Position(22,21)
//            new Position(23,20)
//            new Position(22,19)
//            new Position(23,19)
//            new Position(21,19)
//            || position.equals(new Position(21,20))
//            || position.equals(new Position(23,21))
//            || position.equals(new Position(21,21))


    public static final Tile[] TILE_TEMPLATES = new Tile[]{
            L_TEMPLATE,
            O_TEMPLATE,
            S_TEMPLATE,
            I_TEMPLATE,
            T_TEMPLATE,
            L_TEMPLATE_REVERSE,
            S_TEMPLATE_REVERSE,
            T_TEMPLATE_REVERSE,
    };


//    public static final Tile[] TILE_NUMBERS = new Tile[]{
//            NUMBER_TEMPLATE_ZERO,
//            NUMBER_TEMPLATE_ONE,
//
//
//            NUMBER_TEMPLATE_FOUR,
//
//
//            NUMBER_TEMPLATE_NINE,
//
//
//    };


    private static Position p(int x, int y) {
        return new Position(x, y);
    }


}

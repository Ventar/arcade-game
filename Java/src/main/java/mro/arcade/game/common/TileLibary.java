package mro.arcade.game.common;

import java.util.Arrays;

/**
 * Utility class that offers multiple predefined tiles to be used in other classes.
 * These tiles can be considered as templates  which are added to a Basics container
 *
 * @author Noel Masur, Leon Federau
 * @since 2022-09-22
 */
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
            new Position(-1, 0),
            new Position(0, 1),
            new Position(1, 1)
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
            new Position(-1, 0),
            new Position(1, 0)
    ));

//    public static final Tile T_TEMPLATE_REVERSE = new Tile("T_TEMPLATE_REVERSE", Arrays.asList(
//            new Position(0, 0),
//            new Position(0, 1),
//            new Position(0, 2),
//            new Position(-1, 1)
//    ));
//    public static final Tile L_TEMPLATE_REVERSE = new Tile("L_TEMPLATE_REVERSE", Arrays.asList(
//            new Position(0, 0),
//            new Position(1, 0),
//            new Position(1, 1),
//            new Position(1, 2)
//    ));
//
//    public static final Tile S_TEMPLATE_REVERSE = new Tile("S_TEMPLATE_REVERSE", Arrays.asList(
//            new Position(0, 0),
//            new Position(0, 1),
//            new Position(1, 1),
//            new Position(1, 2)
//    ));

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
            p(1, 3),
            p(0, 2)

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


    public static final Tile LETTER_TEMPLATE_G = new Tile("LETTER_TEMPLATE_G", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 4),
            p(2, 4),
            p(1, 0),
            p(2, 0),
            p(3, 0),
            p(3, 1),
            p(3, 2),
            p(2,2)
    ));

    public static final Tile LETTER_TEMPLATE_A = new Tile("LETTER_TEMPLATE_A", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 4),
            p(2, 4),
            p(2, 3),
            p(2, 2),
            p(2, 1),
            p(2, 0),
            p(1,2)
    ));

    public static final Tile LETTER_TEMPLATE_M = new Tile("LETTER_TEMPLATE_M", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 3),
            p(2, 2),
            p(3, 3),
            p(4, 4),
            p(4, 3),
            p(4, 2),
            p(4, 1),
            p(4, 0)
    ));

    public static final Tile LETTER_TEMPLATE_E = new Tile("LETTER_TEMPLATE_E", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 0),
            p(2, 0),
            p(1, 2),
            p(2, 2),
            p(1, 4),
            p(2, 4)
    ));

    public static final Tile LETTER_TEMPLATE_O = new Tile("LETTER_TEMPLATE_O", Arrays.asList(
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

    public static final Tile LETTER_TEMPLATE_V = new Tile("LETTER_TEMPLATE_V", Arrays.asList(
            p(0, 4),
            p(0, 3),
            p(0, 2),
            p(0, 1),
            p(1, 0),
            p(2, 1),
            p(2, 2),
            p(2, 3),
            p(2, 4)
    ));

    public static final Tile LETTER_TEMPLATE_R = new Tile("LETTER_TEMPLATE_R", Arrays.asList(
            p(0, 0),
            p(0, 1),
            p(0, 2),
            p(0, 3),
            p(0, 4),
            p(1, 4),
            p(2, 4),
            p(2, 3),
            p(2, 2),
            p(1, 2),
            p(1, 1),
            p(2, 0)
    ));

    public static final Tile[] TILE_TEMPLATES = new Tile[]{
            L_TEMPLATE,
            O_TEMPLATE,
            S_TEMPLATE,
            I_TEMPLATE,
            T_TEMPLATE,
//            L_TEMPLATE_REVERSE,
//            S_TEMPLATE_REVERSE,
//            T_TEMPLATE_REVERSE,
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

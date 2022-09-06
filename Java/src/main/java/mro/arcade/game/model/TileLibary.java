package mro.arcade.game.model;

import java.util.Arrays;

public class TileLibary {


    public static final Tile L_TEMPLATE = new Tile("L_TEMPLATE",Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(1, 0)
    ));

    public static final Tile O_TEMPLATE = new Tile("O_TEMPLATE",Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(1, 1)
    ));
    public static final Tile S_TEMPLATE = new Tile("S_TEMPLATE",Arrays.asList(
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

    public static final Tile S_TEMPLATE_REVERSE = new Tile("S_TEMPLATE_REVERSE",Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 1),
            new Position(1, 2)
    ));

    public static final Tile NUMBER_TEMPLATE_ZERO = new Tile("NUMBER_TEMPLATE_ZERO", Arrays.asList(
            new Position(0, 0),
            new Position(1, 0),
            new Position(-1, 0),
            new Position(1, 1),
            new Position(-1, 1),
            new Position(1, 2),
            new Position(-1, 2),
            new Position(1, 3),
            new Position(-1, 3),
            new Position(1, 4),
            new Position(-1, 4),
            new Position(0, 4)
    ));


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


}

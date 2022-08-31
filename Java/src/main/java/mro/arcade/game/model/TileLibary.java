package mro.arcade.game.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TileLibary {


    public static final Tile L_TEMPLATE = new Tile(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(1, 0)
    ));

    public static final Tile O_TEMPLATE = new Tile(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(1, 1)
    ));
    public static final Tile S_TEMPLATE = new Tile(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 1),
            new Position(1, 2)
    ));
    public static final Tile I_TEMPLATE = new Tile(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(0, 3)
    ));

    public static final Tile[] TILE_TEMPLATES = new Tile[]{
            L_TEMPLATE,
            O_TEMPLATE,
            S_TEMPLATE,
            I_TEMPLATE,
    };


}

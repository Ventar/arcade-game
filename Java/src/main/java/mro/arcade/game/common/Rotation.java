package mro.arcade.game.common;

/**
 *
 * Implementation of the boardrenderer interface
 *
 * @author Noel Masur, Leon Federau
 * @since 12.08.2022
 *
 *
 *
 */
public enum Rotation {
    /**
     * Enumirations of the position from the tiles
     */

    // L Tile
    //Represents a rotation by 0°
    DEGREE_0,

    //Represents a rotation by 90°
    DEGREE_90,

    // Represents a rotation by 180°
    DEGREE_180,

    // Represent a rotation by 270°
    DEGREE_270;



    public final static Rotation[] ROTATIONS = new Rotation[]{

            DEGREE_0,
            DEGREE_90,
            DEGREE_180,
            DEGREE_270,

    };





}


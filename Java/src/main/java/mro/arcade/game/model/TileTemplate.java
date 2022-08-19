package mro.arcade.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileTemplate {


    public static final TileTemplate L_TEMPLATE = new TileTemplate(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(1, 0)
    ));

    public static final TileTemplate O_TEMPLATE = new TileTemplate(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(1, 1)
    ));
    public static final TileTemplate S_TEMPLATE = new TileTemplate(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 1),
            new Position(1, 2)
    ));
    public static final TileTemplate I_TEMPLATE = new TileTemplate(Arrays.asList(
            new Position(0, 0),
            new Position(0, 1),
            new Position(0, 2),
            new Position(0, 3)
    ));

    protected List<Position> fields;

    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     */
    public TileTemplate(List<Position> fields) {
        this.fields = fields;
    }


    /**
     * Creates a new template with the fields of this template according to the passed rotation.
     * <p>
     * Example:
     * <p>
     * If the current template is an L-TEMPLATE with fields (0|0), (0|1), (0|2), (1|0) and a rotation of 90° is applied, the internal
     * field list of this tile will contain the fields (0|0), (1|0), (2|0), (0|-1).
     *
     * @param rotation the rotation
     * @return the new template
     */
    public TileTemplate rotate(Rotation rotation) {

        List<Position> tileRotations = new ArrayList<>();

        for (Position pos : fields) {

            switch (rotation) {
                case DEGREE_0:
                    Position p = new Position(pos.getColumn(),  pos.getRow());
                    tileRotations.add(p);
                    break;
                case DEGREE_90:
                    p = new Position(pos.getRow(), -1 * pos.getColumn());
                    tileRotations.add(p);


                    break;
                case DEGREE_180:
                    p = new Position(-1 * pos.getColumn(), -1 * pos.getRow());
                    tileRotations.add(p);


                    break;
                case DEGREE_270:
                    p = new Position(-1 * pos.getRow(), pos.getColumn());
                    tileRotations.add(p);


                    break;
                default:
                    throw new IllegalArgumentException("Passed rotation is not supported");
            }
        }
        TileTemplate tileTemplate = new TileTemplate(tileRotations);
        return tileTemplate;

    }


    /**
     * Get all fields where the tile ist set
     *
     * @return tile fields
     */
    public List<Position> getPositions() {
        return fields;
    }

    @Override
    public String toString() {
        return fields.toString();
    }
}


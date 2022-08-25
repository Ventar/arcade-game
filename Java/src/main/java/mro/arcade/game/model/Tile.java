package mro.arcade.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tiles to play the game Tetris
 */
public class Tile {
    private Color color;
    protected List<Position> fields;

    /**
     * Create a new instance of a tile template
     *
     * @param fields files the tile could take place
     */
    public Tile(List<Position> fields, Color color) {
        this.fields = fields;
        this.color = color;
    }

    /**
     * Get the Color of the tile
     *
     * @return
     */
    public Color getColor() {
        return color;
    }


    /**
     * Get all fields where the tile ist set
     *
     * @return tile fields
     */
    public List<Position> getPositions() {
        return fields;
    }

    public Tile rotate(Rotation rotation) {
        List<Position> newPositions = new ArrayList<>();
        Position anchor = fields.get(0);

        for (Position pos : fields) {
            Position referencePos = pos.sub(anchor);
            Position finalPos = referencePos.rotate(rotation).add(anchor);
            newPositions.add(finalPos);
        }






        return new Tile(newPositions, this.color) ;
    }

	public Tile move(Tile tile, Direction direction){
        Position newPosition;
        List<Position> newTilePositions = new ArrayList<>();

        for (Position tilePosition : tile.getPositions()) {
            if(direction == Direction.DOWN){

                newPosition = new Position(tilePosition.getColumn(), tilePosition.getRow() - 1);
                newTilePositions.add(newPosition);
            }else if(direction == Direction.LEFT){
                newPosition = new Position(tilePosition.getColumn() - 1, tilePosition.getRow());
                newTilePositions.add(newPosition);
            }else if(direction == Direction.RIGHT){
                newPosition = new Position(tilePosition.getColumn() + 1, tilePosition.getRow());
                newTilePositions.add(newPosition);
            }
        }

        return new Tile (newTilePositions, tile.getColor());
    }

    @Override
    public String toString() {
        return fields.toString();
    }
}

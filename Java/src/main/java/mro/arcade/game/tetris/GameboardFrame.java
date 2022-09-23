package mro.arcade.game.tetris;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.view.RenderData;

public class GameboardFrame implements RenderData {

    private Size size;

    private Color color;

    private Position offsetPoint;

    /**
     * Creates an instance of a game board frame.
     * @param size of the game board frame
     * @param offsetPoint of the game board frame
     * @param color of the frame
     */
    public GameboardFrame(Size size, Position offsetPoint, Color color) {
        this.size = size;
        this.offsetPoint = offsetPoint;
        this.color = color;
    }

    /**
     * Get the color of a position
     * @param position the position to fetch the color. If no color is set at that position {@link Color#COLOR_BLACK} is returned
     *
     * @return color
     */
    @Override
    public Color getFieldColor(Position position) {
        for (int i = offsetPoint.getColumn(); i < size.getWidth() + offsetPoint.getColumn(); i++) {
            if (position.equals(new Position(i, offsetPoint.getRow()))) {
                return color;
            }
        }
        for (int i = offsetPoint.getRow(); i < size.getHeight() + offsetPoint.getRow(); i++){
            if (position.equals(new Position(offsetPoint.getColumn(), i))) {
                return color;
            }
        }
        for (int i = size.getHeight() - 1 + offsetPoint.getRow(); i > offsetPoint.getRow(); i--){
            if (position.equals(new Position(size.getWidth() - 1 + offsetPoint.getColumn(), i))) {
                return color;
            }
        }
        return Color.COLOR_BLACK;
    }
}

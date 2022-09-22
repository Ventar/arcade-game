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
     * Creates the gameboard frame.
     * @param size
     * @param offsetPoint
     * @param color
     */
    public GameboardFrame(Size size, Position offsetPoint, Color color) {
        this.size = size;
        this.offsetPoint = offsetPoint;
        this.color = color;
    }

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

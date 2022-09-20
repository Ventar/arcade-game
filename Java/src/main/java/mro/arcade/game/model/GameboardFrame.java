package mro.arcade.game.model;

import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.view.renderer.SwingRenderer;

public class GameboardFrame implements RenderData {

    private Size size;

    private Color color;

    private Position offsetPoint;

    public GameboardFrame(Size size, Position offsetPoint, Color color) {
        this.size = size;
        this.offsetPoint = offsetPoint;
        this.color = color;
    }

    @Override
    public Color getFieldColor(Position position) {
        for (int i = 0; i < size.getWidth(); i++) {
            if (position.equals(new Position(i, 0))) {
                return new Color(0, 0, 200);
            }
        }
        for (int i = 0; i < size.getHeight(); i++){
            if (position.equals(new Position(0, i))) {
                return new Color(0, 0, 200);
            }
        }
        for (int i = size.getHeight() - 1; i > 0; i--){
            if (position.equals(new Position(size.getWidth() - 1, i))) {
                return new Color(0, 0, 200);
            }
        }
        return Color.COLOR_BLACK;
    }
}

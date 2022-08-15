package mro.arcade.game;

import mro.arcade.game.model.Color;
import mro.arcade.game.model.Position;
import mro.arcade.game.view.ASCIIRenderer;
import mro.arcade.game.view.RenderData;

public class ArcadeGameMain {

    public static void main(String[] args) {

        ASCIIRenderer renderer = new ASCIIRenderer();

        RenderData data = new RenderData() {
            @Override
            public Color getFieldColor(Position position) {
                if (position.equals(new Position(2, 2))) {
                    return Color.COLOR_RED;
                }
                return Color.COLOR_BLACK;
            }
        };

        renderer.render(data);

    }

}

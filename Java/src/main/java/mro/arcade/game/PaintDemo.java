package mro.arcade.game;

import mro.arcade.game.model.Color;
import mro.arcade.game.model.Position;
import mro.arcade.game.model.Size;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.view.renderer.SwingRenderer;

public class PaintDemo {

    public static void main(String[] args) {
        BoardRenderer renderer = new SwingRenderer(new Size(24, 24));

        renderer.render(new RenderData() {
            @Override
            public Color getFieldColor(Position position) {
                // 8

                if (position.equals(new Position(23, 23))
                            || position.equals(new Position(22, 23))
                            || position.equals(new Position(21, 23))
                            || position.equals(new Position(23, 22))
                            || position.equals(new Position(21, 22))
                            || position.equals(new Position(22, 21))
                            || position.equals(new Position(23, 20))
                            || position.equals(new Position(22, 19))
                            || position.equals(new Position(23, 19))
                            || position.equals(new Position(21, 19))
                            || position.equals(new Position(21, 20))
                            || position.equals(new Position(23, 21))
                            || position.equals(new Position(21, 21))
                ) {
                    return new Color(16, 0, 0);
                }

                if (position.getColumn() == 5 && position.getRow() < 20) {
                    return new Color(16, 16, 16);
                }

                if (position.getColumn() == 16 && position.getRow() < 20) {
                    return new Color(16, 16, 16);
                }

                if (position.getColumn() < 16 && position.getColumn() > 4 && position.getRow() == 0) {
                    return new Color(16, 16, 16);
                }


                return Color.COLOR_BLACK;
            }


        });

    }

}

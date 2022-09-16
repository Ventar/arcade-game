package mro.arcade.game.model;

import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.view.renderer.SwingRenderer;

public class GameboardFrame implements RenderData {

    public GameboardFrame() {
    Size size = new Size(7,7);

    }

    public static void main(String[] args) {


        BoardRenderer frame = new SwingRenderer(new Size(24,24));
        GameboardFrame gameboardFrame = new GameboardFrame();

        frame.render(gameboardFrame);



    }

    @Override
    public Color getFieldColor(Position position) {
        if (position.equals(new Position(0, 0))
                || position.equals(new Position(0, 1))
                || position.equals(new Position(0, 2))
                || position.equals(new Position(0, 3))
                || position.equals(new Position(0, 4))
                || position.equals(new Position(0, 5))
                || position.equals(new Position(0, 6))
                || position.equals(new Position(0, 7))
                || position.equals(new Position(0, 8))
                || position.equals(new Position(0, 9))
                || position.equals(new Position(0, 10))
                || position.equals(new Position(0, 11))
                || position.equals(new Position(0, 12))
                || position.equals(new Position(10, 1))
                || position.equals(new Position(10, 2))
                || position.equals(new Position(10, 3))
                || position.equals(new Position(10, 4))
                || position.equals(new Position(10, 5))
                || position.equals(new Position(10, 6))
                || position.equals(new Position(10, 7))
                || position.equals(new Position(10,8))
                || position.equals(new Position(10, 9))
                || position.equals(new Position(10, 10))
                || position.equals(new Position(10, 11))
                || position.equals(new Position(10, 12))
                || position.equals(new Position(1, 0))
                || position.equals(new Position(2, 0))
                || position.equals(new Position(3, 0))
                || position.equals(new Position(4, 0))
                || position.equals(new Position(5, 0))
                || position.equals(new Position(6, 0))
                || position.equals(new Position(7, 0))
                || position.equals(new Position(8, 0))
                || position.equals(new Position(9, 0))
                || position.equals(new Position(10, 0))
        ) {
            return new Color(0, 0, 200);
        }

        return Color.COLOR_BLACK;


    }
}

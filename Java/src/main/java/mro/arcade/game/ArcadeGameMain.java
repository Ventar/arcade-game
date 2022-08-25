package mro.arcade.game;


import mro.arcade.game.model.*;
import mro.arcade.game.view.ASCIIRenderer;

public class ArcadeGameMain {

    public static void main(String[] args) {

        Gameboard board = new Gameboard(new Size(8, 8));
        board.addTileToField(TileTemplate.L_TEMPLATE, Rotation.DEGREE_270, new Position(2, 0), Color.COLOR_RED);
        Tile t = board.addTileToField(TileTemplate.S_TEMPLATE, Rotation.DEGREE_90, new Position(2, 4), Color.COLOR_BLUE);
        board.rotate(t, Rotation.DEGREE_90);

        ASCIIRenderer renderer = new ASCIIRenderer();
        renderer.render(board);


    }

}

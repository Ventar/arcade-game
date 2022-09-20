package mro.arcade.game;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Size;
import mro.arcade.game.common.TileLibary;
import mro.arcade.game.tetris.Gameboard;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Tile;
import mro.arcade.game.view.renderer.ASCIIRenderer;

public class DebuggerDemo {

    public static void main(String[] args) {
        Gameboard gameboard = new Gameboard(new Size(6, 4), new Position(0, 0));
        ASCIIRenderer renderer = new ASCIIRenderer(new Size(6,4));

        gameboard.addTileToField(new Tile(TileLibary.L_TEMPLATE.getName(), TileLibary.L_TEMPLATE.getPositions(), Color.COLOR_GREEN));

        renderer.render(gameboard);
    }

}

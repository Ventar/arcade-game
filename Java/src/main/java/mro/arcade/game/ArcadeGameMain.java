package mro.arcade.game;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.model.*;
import mro.arcade.game.view.ASCIIRenderer;
import mro.arcade.game.view.ArduinoHTTPRenderer;
import mro.arcade.game.view.BoardRenderer;

public class ArcadeGameMain implements NativeKeyListener {


    private BoardRenderer renderer = new ArduinoHTTPRenderer("192.168.2.207");
    //private BoardRenderer renderer = new ASCIIRenderer();

    private Gameboard board = new Gameboard(new Size(12, 12));

    private Tile activeTile;

    public ArcadeGameMain() {
        board.addTileToField(TileTemplate.L_TEMPLATE, Rotation.DEGREE_270, new Position(2, 0), Color.COLOR_RED);
        activeTile = board.addTileToField(TileTemplate.S_TEMPLATE, Rotation.DEGREE_90, new Position(2, 4), Color.COLOR_BLUE);
        renderer.render(board);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        System.out.println(nativeEvent.getKeyCode());

        if (nativeEvent.getKeyCode() == 1) {
            renderer.clear();
            System.exit(0);
        }

        activeTile = switch (nativeEvent.getKeyCode()) {
            case 30 -> board.rotate(activeTile, Rotation.DEGREE_270);
            case 32 -> board.rotate(activeTile, Rotation.DEGREE_90);
            case 57421 -> board.moveTile(activeTile, Direction.RIGHT);
            case 57419 -> board.moveTile(activeTile, Direction.LEFT);
            case 57424 -> board.moveTile(activeTile, Direction.DOWN);
            default -> activeTile; // nothing to do
        };

        renderer.render(board);

    }

    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        ArcadeGameMain game = new ArcadeGameMain();
        GlobalScreen.addNativeKeyListener(game);

    }
}


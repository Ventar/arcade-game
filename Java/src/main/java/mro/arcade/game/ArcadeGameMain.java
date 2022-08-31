package mro.arcade.game;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.model.*;
import mro.arcade.game.view.ASCIIRenderer;
import mro.arcade.game.view.ArduinoHTTPRenderer;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.SwingRenderer;

import java.util.Random;

public class ArcadeGameMain implements NativeKeyListener {


    //private BoardRenderer renderer = new ArduinoHTTPRenderer("192.168.2.207");
    //private BoardRenderer renderer = new ASCIIRenderer();
    private BoardRenderer renderer = new SwingRenderer(new Size(24, 24));

    private Gameboard board = new Gameboard(new Size(12, 12));

    private Tile activeTile;

    private Tile nextTile;

    private Color nextColor;


    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        System.out.println(nativeEvent.getKeyCode());

        if (nativeEvent.getKeyCode() == 1) {
            renderer.clear();
            System.exit(0);
        }

        switch (nativeEvent.getKeyCode()) {
            case 30:
                activeTile = board.rotate(activeTile, Rotation.DEGREE_270);
                break;
            case 32:
                activeTile = board.rotate(activeTile, Rotation.DEGREE_90);
                break;
            case 57421:
                activeTile = board.moveTile(activeTile, Direction.RIGHT);
                break;
            case 57419:
                activeTile = board.moveTile(activeTile, Direction.LEFT);
                break;
            case 57424:
                while (board.canMove(activeTile, Direction.DOWN)) {
                    activeTile = board.moveTile(activeTile, Direction.DOWN);
                }
                break;
            case 57416:
                activeTile = board.moveTile(activeTile, Direction.UP);
                break;
            default:
                break;  // nothing to do
        }
        ;

        renderer.render(board);

    }

    public void generateNextTile() {
        Random random = new Random();
        int nextint = random.nextInt(4);
        nextTile = TileLibary.TILE_TEMPLATES[nextint];
        generateNextColor();

    }

    public void generateNextColor() {
        Random random = new Random();
        int nextint = random.nextInt(11);
        nextColor = Color.COLORS[nextint];
    }

    public void run() throws InterruptedException {
        generateNextTile();
        activeTile = board.addTileToField(nextTile, Rotation.DEGREE_90, new Position(5, 11), nextColor);

        generateNextTile();
        renderer.render(board);

        while (true) {

            Thread.sleep(1000);


            if (board.canMove(activeTile, Direction.DOWN)) {
                activeTile = board.moveTile(activeTile, Direction.DOWN);
            } else {
                System.out.println("End of board reached");
                board.removeFullRows();

                activeTile = board.addTileToField(nextTile, Rotation.DEGREE_0, new Position(6, 12 - nextTile.getHeight()), nextColor);

                generateNextTile();

            }
            renderer.render(board);
        }
    }

    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        ArcadeGameMain game = new ArcadeGameMain();
        GlobalScreen.addNativeKeyListener(game);
        game.run();

    }
}


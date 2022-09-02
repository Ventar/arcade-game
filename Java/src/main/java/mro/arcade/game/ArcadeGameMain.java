package mro.arcade.game;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.model.*;
import mro.arcade.game.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.SwingRenderer;

import java.util.Random;

public class ArcadeGameMain implements NativeKeyListener {

    private static final Logger LOG = LoggerFactory.getLogger(ArcadeGameMain.class);
    //private BoardRenderer renderer = new ArduinoHTTPRenderer("192.168.2.207");
    //private BoardRenderer renderer = new ASCIIRenderer();
    private BoardRenderer renderer = new SwingRenderer(new Size(12, 12));

    private Gameboard board = new Gameboard(new Size(12, 12));

    private Tile activeTile;

    private Tile nextTile;


    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        // To see key codes use: System.out.println(nativeEvent.getKeyCode());

        if (nativeEvent.getKeyCode() == 1) {
            renderer.clear();
            System.exit(0);
        }
        if (activeTile != null) {
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
        }


        renderer.render(board);

    }

    public void generateNextTile() {
        Random random = new Random();

        // Take a random tile from the TileLibary
        nextTile = TileLibary.TILE_TEMPLATES[random.nextInt(TileLibary.TILE_TEMPLATES.length)];

        // Create a copy of the random tile and assign a random color. This is necessary because TileTemplates
        // do not have a color.
        nextTile = new Tile(nextTile.getName(), nextTile.getPositions(), Color.COLORS[random.nextInt(Color.COLORS.length)]);

        // Perform a random rotation.
        nextTile.rotate(Rotation.ROTATIONS[random.nextInt(Rotation.ROTATIONS.length)]);
    }


    public void run() throws InterruptedException {
        generateNextTile();


        activeTile = board.addTileToField(nextTile, new Position(5, 12 - nextTile.getHeight()));

        generateNextTile();
        renderer.render(board);

        while (true) {

            Thread.sleep(1000);

            if (board.canMove(activeTile, Direction.DOWN)) {
                activeTile = board.moveTile(activeTile, Direction.DOWN);
            } else {
                LOG.trace("End of board reached");
                board.removeFullRows();


                activeTile = board.addTileToField(nextTile, new Position(6, 12 - nextTile.getHeight()));
                if (activeTile == null) {
                    LOG.trace("GAME OVER");
                    System.exit(0);
                }
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


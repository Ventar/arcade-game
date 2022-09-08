package mro.arcade.game;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.model.*;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.view.RenderDataContainer;
import mro.arcade.game.view.renderer.ArduinoUDPRenderer;
import mro.arcade.game.view.renderer.SwingRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ArcadeGameMain implements NativeKeyListener {

    private static final Logger LOG = LoggerFactory.getLogger(ArcadeGameMain.class);
    private static final Size SIZE = new Size(24, 24);

    private BoardRenderer renderer = new ArduinoUDPRenderer(SIZE, "192.168.51.51");
    //private BoardRenderer renderer = new ASCIIRenderer(SIZE);
    //private BoardRenderer renderer = new SwingRenderer(SIZE);

    private Gameboard board = new Gameboard(new Size(12, 12), new Position(7, 1));

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


        render();

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

        renderer.clear();

        generateNextTile();

        activeTile = board.addTileToField(nextTile);

        generateNextTile();
        render();

        while (true) {

            Thread.sleep(1000);

            if (board.canMove(activeTile, Direction.DOWN)) {
                activeTile = board.moveTile(activeTile, Direction.DOWN);
            } else {
                LOG.trace("End of board reached");
                board.removeFullRows();


                activeTile = board.addTileToField(nextTile);
                if (activeTile == null) {
                    LOG.trace("GAME OVER");
                    System.exit(0);
                }
                generateNextTile();

            }
            render();
        }
    }


    public void render() {
        RenderDataContainer container = new RenderDataContainer();
        container.addRenderData(board);
        container.addRenderData(new RenderData() {
            @Override
            public Color getFieldColor(Position position) {
                if (position.equals(new Position(23, 23))) {
                    return Color.COLOR_RED;
                } else {
                    return Color.COLOR_BLACK;
                }

            }

        });
        renderer.render(container);
    }

    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        ArcadeGameMain game = new ArcadeGameMain();
        GlobalScreen.addNativeKeyListener(game);
        game.run();

        // int row = 5;
        // int column = 13;
        // int boardsIndRow = 2;
        //
        // int pixelRow = (row / 12) * 144 * boardsIndRow; // the number of pixel we need to add for the rows of the boards below
        // int pixelColumn = (column / 12) * 144; // the number of pixel we need to add for the columns of the boards to the left
        //
        // int pixel = (row % 12) * 12 + (column % 12) + pixelColumn + pixelRow;
        //
        // System.out.println(pixel);


    }
}


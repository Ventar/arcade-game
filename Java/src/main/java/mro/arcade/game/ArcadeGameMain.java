package mro.arcade.game;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.model.*;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.view.RenderDataContainer;
//import mro.arcade.game.view.renderer.ArduinoUDPRenderer;
import mro.arcade.game.view.renderer.SwingRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ArcadeGameMain implements NativeKeyListener {

    private static final Logger LOG = LoggerFactory.getLogger(ArcadeGameMain.class);
    private static final Size SIZE = new Size(24, 24);


    //    private BoardRenderer renderer = new ArduinoUDPRenderer(SIZE, "192.168.51.52");
    //private BoardRenderer renderer = new ArduinoUDPRenderer(new Size(24, 24), "172.17.196.70");
    //private BoardRenderer renderer = new ArduinoHTTPRenderer("192.168.2.207");
    //private BoardRenderer renderer = new ASCIIRenderer();
    private BoardRenderer renderer = new SwingRenderer(SIZE);

    private Gameboard board = new Gameboard(new Size(12, 12), new Position(1, 1));

    private boolean showWholeCounter = false;

    private Counter counter = new Counter(new Size(5, 16), new Position(9, 19), showWholeCounter);

    private Tile activeTile;

    //private Tile nextTile;

    private Basics nextTileField = new Basics(new Size(8, 8), new Position(16, 6));

    private GameboardFrame gameboardFrame = new GameboardFrame(new Size(13,14), new Position(0, 0), new Color(255, 255, 255));

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

    public Tile generateNextTile() {
        Random random = new Random();

        // Take a random tile from the TileLibary
        Tile nextTile = TileLibary.TILE_TEMPLATES[random.nextInt(TileLibary.TILE_TEMPLATES.length)];

        // Create a copy of the random tile and assign a random color. This is necessary because TileTemplates
        // do not have a color.
        nextTile = new Tile(nextTile.getName(), nextTile.getPositions(), Color.COLORS[random.nextInt(Color.COLORS.length)]);

        // Perform a random rotation.
        nextTile.rotate(Rotation.ROTATIONS[random.nextInt(Rotation.ROTATIONS.length)]);

        return nextTile;
    }


    public void run() throws InterruptedException {

        renderer.clear();

        counter.add(0);

        Tile nextTile = generateNextTile();
        activeTile = board.addTileToField(nextTile);

        nextTile = generateNextTile();
        Tile t = nextTileField.addTileToField(nextTile, new Position(1, 1));

        render();


        while (true) {
            //nextTileField.removeTile(t);
            Thread.sleep(1000);

            if (board.canMove(activeTile, Direction.DOWN)) {
                activeTile = board.moveTile(activeTile, Direction.DOWN);
            } else {
                LOG.trace("End of board reached");
                counter.add(board.removeFullRows() * 50);

                activeTile = board.addTileToField(nextTile);

                counter.add(5);

                if (activeTile == null) {
                    LOG.trace("GAME OVER");
                    System.exit(0);
                }

                nextTileField.removeTile(t);
                nextTile=  generateNextTile();
                t = nextTileField.addTileToField(nextTile, new Position(1, 1));
            }
            render();
        }
    }


    public void render() {
        RenderDataContainer container = new RenderDataContainer();
        container.addRenderData(board);
        container.addRenderData(counter);
        container.addRenderData(nextTileField);
        container.addRenderData(gameboardFrame);
//        container.addRenderData(new RenderData() {
//            @Override
//            public Color getFieldColor(Position position) {
//                if (position.equals(new Position(23, 23))){
//                    return Color.COLOR_RED;
//                }else {
//                    return Color.COLOR_BLACK;
//                }
//
//            }
//
//        });
        renderer.render(container);
    }

    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        ArcadeGameMain game = new ArcadeGameMain();
        GlobalScreen.addNativeKeyListener(game);
        game.run();


    }
}


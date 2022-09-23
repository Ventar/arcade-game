package mro.arcade.game.tetris;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.common.*;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderDataContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class TetrisGame implements NativeKeyListener {

    private static final Logger LOG = LoggerFactory.getLogger(TetrisGame.class);
    private Gameboard board = new Gameboard(new Size(12, 12), new Position(1, 1));

    private boolean showSingleCounter = false;
    private boolean showWholeCounter = true;

    private Counter counter = new Counter(new Size(5, 16), new Position(9, 19), showSingleCounter);

    private Counter endCounter = new Counter(new Size(5,16), new Position(4, 2), showWholeCounter);

    private Tile activeTile;

    private BoardRenderer renderer;

    TileContainer gameOverDisplay = new TileContainer(new Size(24, 24), new Position(0, 0));

    private TileContainer nextTileField = new TileContainer(new Size(8, 8), new Position(16, 6));

    private GameboardFrame gameboardFrame = new GameboardFrame(new Size(13, 14), new Position(0, 0), new Color(255, 255, 255));

    public TetrisGame(BoardRenderer boardRenderer) {
        this.renderer = boardRenderer;
    }

    /**
     *
     * Checks which key is pressed on the keyboard and then responds with the appropriate command.
     *
     * @param nativeEvent
     */
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

    /**
     *
     * Generate the next tile for the Tetris game.
     *
     * @return Tile
     */
    public Tile generateNextTile() {
        Random random = new Random();

        // Take a random tile from the TileLibary
        Tile nextTile = TileLibary.TILE_TEMPLATES[random.nextInt(TileLibary.TILE_TEMPLATES.length)];

        // Create a copy of the random tile and assign a random color. This is necessary because TileTemplates
        // do not have a color.
        nextTile = new Tile(nextTile.getName(), nextTile.getPositions(), Color.COLORS[random.nextInt(Color.COLORS.length)]);

        // Perform a random rotation.
        nextTile.rotate(Rotation.ROTATIONS[random.nextInt(Rotation.ROTATIONS.length)]);

        if (nextTile.equals(TileLibary.O_TEMPLATE)){


        }



        return nextTile;
    }


    public void run() throws InterruptedException {
        boolean loop = true;

        renderer.clear();
        counter.add(0);

        Tile nextTile = generateNextTile();
        activeTile = board.addTileToField(nextTile);

        nextTile = generateNextTile();
        Tile t = nextTileField.addTileToField(nextTile, new Position(1, 1));

        render();

        while (loop) {
            //nextTileField.removeTile(t);
            Thread.sleep(1000);

            if (board.canMove(activeTile, Direction.DOWN)) {
                activeTile = board.moveTile(activeTile, Direction.DOWN);
            } else {
                LOG.trace("End of board reached");
                int score = board.removeFullRows() * 50;
                counter.add(score);
                endCounter.add(score);

                activeTile = board.addTileToField(nextTile);

                counter.add(1);
                endCounter.add(1);

                if (activeTile == null) {
                    LOG.trace("GAME OVER");
                    renderer.clear();
                    loop = false;
                }

                nextTileField.removeTile(t);
                nextTile = generateNextTile();
                t = nextTileField.addTileToField(nextTile, new Position(1, 1));
            }
            render();
        }
        renderEndScreen();
    }


    public void render() {
        RenderDataContainer container = new RenderDataContainer();
        container.addRenderData(board);
        container.addRenderData(counter);
        container.addRenderData(nextTileField);
        container.addRenderData(gameboardFrame);

        renderer.render(container);
    }

    public void renderEndScreen(){
        RenderDataContainer container = new RenderDataContainer();
        generateGameOverScreen();
        container.addRenderData(gameOverDisplay);
        container.addRenderData(endCounter);
        renderer.render(container);
    }

    /**
     * Generates the screen when the game is over.
     *
     */
    private void generateGameOverScreen() {
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_G, new Position(3, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_A, new Position(8, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_M, new Position(12, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_E, new Position(18, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_O, new Position(4, 9));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_V, new Position(8, 9));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_E, new Position(12, 9));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_R, new Position(16, 9));
    }

}

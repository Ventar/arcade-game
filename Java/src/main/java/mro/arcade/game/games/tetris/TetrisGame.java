package mro.arcade.game.games.tetris;

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
    private TetrisBoard board = new TetrisBoard(new Size(17, 12), new Position(1, 1));

    private boolean showSingleCounter = false;
    private boolean showWholeCounter = true;

    private Counter counter = new Counter(new Size(5, 16), new Position(9, 19), showSingleCounter);

    private Counter endCounter = new Counter(new Size(5, 16), new Position(4, 2), showWholeCounter);

    private Tile activeTile;

    private BoardRenderer renderer;

    TileContainer gameOverDisplay = new TileContainer(new Size(24, 24), new Position(0, 0));

    private TileContainer nextTileField = new TileContainer(new Size(8, 8), new Position(16, 6));

    private TetrisBoardFrame gameboardFrame = new TetrisBoardFrame(new Size(17, 14), new Position(0, 0), new Color(255, 255, 255));
    boolean pause = false;

    public TetrisGame(BoardRenderer boardRenderer) {
        this.renderer = boardRenderer;
    }

    /**
     * Checks which key is pressed on the keyboard and then responds with the appropriate command.
     *
     * @param nativeEvent
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {

        if (nativeEvent.getKeyCode() == 1) {
            renderer.clear();
            System.exit(0);
        }
        if (activeTile != null) {

            switch (nativeEvent.getKeyCode()) {
                case 25:
                    pause = !pause;
                    break;
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
                    if (TetrisConfig.TILE_DROP_INSTANT == true) {
                        while (board.canMove(activeTile, Direction.DOWN)) {
                            activeTile = board.moveTile(activeTile, Direction.DOWN);
                        }
                        break;
                    } else {
                        activeTile = board.moveTile(activeTile, Direction.DOWN);
                    }

                    break;

                //                case 57416:
                //                    activeTile = board.moveTile(activeTile, Direction.UP);
                //                    break;
                default:
                    break;  // nothing to do
            }
        }


        render();

    }

    /**
     * Generate the next tile for the Tetris game.
     *
     * @return Tile
     */
    public Tile generateNextTile() {
        Random random = new Random();
        int colorValue = random.nextInt(Color.COLORS.length);
        // Take a random tile from the TileLibary
        Tile nextTile = TileLibary.TILE_TEMPLATES[random.nextInt(TileLibary.TILE_TEMPLATES.length)];

        // Create a copy of the random tile and assign a random color. This is necessary because TileTemplates
        // do not have a color.
        nextTile = new Tile(nextTile.getName(), nextTile.getPositions(), Color.COLORS[colorValue]);

        // Perform a random rotation.
        //nextTile.rotate(Rotation.ROTATIONS[random.nextInt(Rotation.ROTATIONS.length)]);

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
        int speed = TetrisConfig.START_SPEED_MS;
        int speedcounter = 0;

        while (loop) {

            if (speedcounter > TetrisConfig.SPEED_COUNTER_LIMIT) {
                speedcounter = 0;
                if (speed > TetrisConfig.MINIMUM_SPEED) {
                    speed -= TetrisConfig.SPEED_REDUCTION;
                }
            }

            Thread.sleep(speed);
            if (!pause) {


                if (board.canMove(activeTile, Direction.DOWN)) {
                    activeTile = board.moveTile(activeTile, Direction.DOWN);
                    render();
                } else {
                    LOG.trace("End of board reached");
                    int score = board.removeFullRows() * TetrisConfig.POINTS_PER_ROW;
                    speedcounter += score;
                    counter.add(score);
                    endCounter.add(score);

                    activeTile = board.addTileToField(nextTile);

                    speedcounter += TetrisConfig.POINTS_PER_TILE;
                    counter.add(TetrisConfig.POINTS_PER_TILE);
                    endCounter.add(TetrisConfig.POINTS_PER_TILE);

                    if (activeTile == null) {
                        LOG.trace("GAME OVER");
                        renderer.clear();
                        loop = false;
                    } else {
                        nextTileField.removeTile(t);
                        nextTile = generateNextTile();
                        t = nextTileField.addTileToField(nextTile, new Position(1, 1));
                        render();
                    }
                }

            }
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

    public void renderEndScreen() {
        RenderDataContainer container = new RenderDataContainer();
        generateGameOverScreen();
        container.addRenderData(gameOverDisplay);
        container.addRenderData(endCounter);
        renderer.render(container);
    }

    /**
     * Generates the screen when the game is over.
     */
    private void generateGameOverScreen() {
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_G.setColor(Color.COLOR_RED), new Position(3, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_A.setColor(Color.COLOR_RED), new Position(8, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_M.setColor(Color.COLOR_RED), new Position(12, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_E.setColor(Color.COLOR_RED), new Position(18, 17));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_O.setColor(Color.COLOR_RED), new Position(4, 9));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_V.setColor(Color.COLOR_RED), new Position(8, 9));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_E.setColor(Color.COLOR_RED), new Position(12, 9));
        gameOverDisplay.addTileToField(TileLibary.LETTER_TEMPLATE_R.setColor(Color.COLOR_RED), new Position(16, 9));
    }

}

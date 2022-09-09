package mro.arcade.game.model;

import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.renderer.SwingRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counter extends Basics {
    public static final Logger LOG = LoggerFactory.getLogger(Counter.class);
    private static final BoardRenderer renderer = new SwingRenderer(new Size(14, 18));
    private static int MAX_VALUE = 9999;
    private int value = 0;

    Tile[] digitTiles = new Tile[4];
    int[] digits = new int[4];

    public Counter(Size size, Position position) {
        super(size, position);
    }


    public void add(int integer) {


        if (value + integer < MAX_VALUE) {
            value = value + integer;
        } else {
            value = MAX_VALUE;
        }
        calculateDigits();
        addDigitTiletoField();
    }

    public void sub(int integer) {


        if (value - integer > 0) {
            value = value - integer;
        } else {
            value = 0;
        }
        calculateDigits();
        addDigitTiletoField();
    }


    private void calculateDigits() {

        int rest = value;
        digits[0] = rest / 1000;
        rest = rest - 1000 * digits[0];
        digits[1] = rest / 100;
        rest = rest - 100 * digits[1];
        digits[2] = rest / 10;
        digits[3] = rest - 10 * digits[2];

    }


    private void addDigitTiletoField() {

        for (int i = 0; i < digits.length; i++) {

            if (digitTiles[i] != null) {
                removeTile(digitTiles[i]);
                LOG.debug("Removed Tile:{} ", digitTiles[i]);
            }
            digitTiles[i] = addTileToField(digitToTile(digits[i]), new Position(i * 4, 0));
        }

    }

    private Tile digitToTile(int integer) {

        return switch (integer) {

            case 0 -> TileLibary.NUMBER_TEMPLATE_ZERO;
            case 1 -> TileLibary.NUMBER_TEMPLATE_ONE;
            case 2 -> TileLibary.NUMBER_TEMPLATE_TWO;
            case 3 -> TileLibary.NUMBER_TEMPLATE_THREE;
            case 4 -> TileLibary.NUMBER_TEMPLATE_FOUR;
            case 5 -> TileLibary.NUMBER_TEMPLATE_FIVE;
            case 6 -> TileLibary.NUMBER_TEMPLATE_SIX;
            case 7 -> TileLibary.NUMBER_TEMPLATE_SEVEN;
            case 8 -> TileLibary.NUMBER_TEMPLATE_EIGHT;
            case 9 -> TileLibary.NUMBER_TEMPLATE_NINE;
            default -> null;

        };

    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(new Size(5, 18), new Position(0, 0));
        counter.add(5679);
        counter.add(100);
        renderer.render(counter);
        for (int i = 0; i < 100; i++) {
            counter.add(50);
            renderer.render(counter);
            Thread.sleep(500);
        }
    }
}

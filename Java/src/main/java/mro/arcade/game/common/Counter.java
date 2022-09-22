package mro.arcade.game.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counter extends TileContainer {
    public static final Logger LOG = LoggerFactory.getLogger(Counter.class);
    private static int MAX_VALUE = 9999;
    private int value = 0;

    private boolean showWholeCounter;

    Tile[] digitTiles = new Tile[4];
    int[] digits = new int[4];

    public Counter(Size size, Position position, boolean showWholeCounter) {
        super(size, position);
        this.showWholeCounter = showWholeCounter;
    }

    /**
     *
     * Adds the corresponding number to the counter
     * @param integer
     */
    public void add(int integer) {


        if (value + integer < MAX_VALUE) {
            value = value + integer;
        } else {
            value = MAX_VALUE;
        }
        calculateDigits();
        if (showWholeCounter == true) {
            addDigitTileToField();
        } else {
            addSingleDigitTileToField();
        }
    }

    public void sub(int integer) {


        if (value - integer > 0) {
            value = value - integer;
        } else {
            value = 0;
        }
        calculateDigits();
        addDigitTileToField();
    }

    /**
     * calculate all digits that we have.
     *
     */
    private void calculateDigits() {

        int rest = value;
        digits[0] = rest / 1000;
        rest = rest - 1000 * digits[0];
        digits[1] = rest / 100;
        rest = rest - 100 * digits[1];
        digits[2] = rest / 10;
        digits[3] = rest - 10 * digits[2];

    }


    private void addDigitTileToField() {

        for (int i = 0; i < digits.length; i++) {

            if (digitTiles[i] != null) {
                removeTile(digitTiles[i]);
                LOG.debug("Removed Tile:{} ", digitTiles[i]);
            }
            digitTiles[i] = addTileToField(digitToTile(digits[i]), new Position(i * 4, 0));
        }

    }

    private void addSingleDigitTileToField() {
        int checkValue = 1;
        // for loop from 3 down to 0
        // checkValue = 1
        // if the value in this class is larger than check value minus 1, render it
        // otherwise do not render it
        // in each iteration check value * 10

        if (digitTiles[3] != null) {
                removeTile(digitTiles[3]);
                LOG.debug("Removed Tile:{} ", digitTiles[3]);
            }
            digitTiles[3] = addTileToField(digitToTile(digits[3]), new Position(3 * 4, 0));

        for (int i = 3; i > 0; i--){
            if (value > checkValue - 1){
                if (digitTiles[i] != null) {
                removeTile(digitTiles[i]);
                LOG.debug("Removed Tile:{} ", digitTiles[i]);
            }
                digitTiles[i] = addTileToField(digitToTile(digits[i]), new Position(i * 4, 0));
                checkValue *= 10;
            }
        }

//        if(value > 999){
//            if (digitTiles[0] != null) {
//                removeTile(digitTiles[0]);
//                LOG.debug("Removed Tile:{} ", digitTiles[0]);
//            }
//            digitTiles[0] = addTileToField(digitToTile(digits[0]), new Position(0 * 4, 0));
//        }
//        if(value > 99){
//            if (digitTiles[1] != null) {
//                removeTile(digitTiles[1]);
//                LOG.debug("Removed Tile:{} ", digitTiles[1]);
//            }
//            digitTiles[1] = addTileToField(digitToTile(digits[1]), new Position(1 * 4, 0));
//        }
//        if(value > 9){
//            if (digitTiles[2] != null) {
//                removeTile(digitTiles[2]);
//                LOG.debug("Removed Tile:{} ", digitTiles[2]);
//            }
//            digitTiles[2] = addTileToField(digitToTile(digits[2]), new Position(2 * 4, 0));
//        }
//        if (digitTiles[3] != null) {
//            removeTile(digitTiles[3]);
//            LOG.debug("Removed Tile:{} ", digitTiles[3]);
//        }
//        digitTiles[3] = addTileToField(digitToTile(digits[3]), new Position(3 * 4, 0));
    }

    /**
     *
     * turn the digits into tiles.
     * @param integer
     * @return
     */
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
}

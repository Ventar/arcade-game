package mro.arcade.game.match4;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.common.Tile;
import mro.arcade.game.common.TileContainer;
import mro.arcade.game.tetris.TetrisBoard;
import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.SimpleTimeZone;

public class Match4Board extends TileContainer implements RenderData{

    public static final Logger LOG = LoggerFactory.getLogger(Match4Board.class);
    public Match4Board(Size size, Position offsetPoint) {
        super(size, offsetPoint);
    }

    public Tile insertTileToField(Tile tile, int column) {
        Position position;
        int i = 0;

        while (i < size.getHeight() - 1){
            position = new Position(column, i);

            LOG.trace("Try to add tile ::= [{}] to position ::= [{}]", tile, position);

            tile = tile.translate(position);

            for (Position pos : tile.getPositions()) {
                if (detectCollision(pos) || !isPositionOnBoard(pos)) {
                    System.out.println("Column is full");
                    break;
                }else {
                    i = 100;
                }
            }
            i++;
        }
        tiles.add(tile);
        LOG.debug("Added tile ::= [{}] to board", tile);
        return tile;
    }

    public boolean canInsertTile(int columToInsertCoin, Tile tile) {
        for (int i = 0; i <= size.getHeight(); i++){
            if (detectCollision(new Position(columToInsertCoin, i)) || isTileOnBoard(tile)){
                return false;
            }
        }
        return true;
    }

    public void changeTileOnField(Position position){

        for (Tile position1 : tiles){
            for (Position position2 : position1.getPositions()){
                if (position2.equals(position)){
                    Tile newTile = new Tile("New Tile", position1.getPositions(), Color.COLOR_YELLOW);
                    tiles.remove(position2);
                    tiles.add(newTile);
                }
            }
        }
    }

    public void markWinPattern(Position position, WinPatternToCheck winPatternToCheck) {

        switch (winPatternToCheck) {
            case VERTICAL:
                changeTileOnField(new Position(position.getColumn(), position.getRow()));
                changeTileOnField(new Position(position.getColumn(),position.getRow() - 1));
                changeTileOnField(new Position(position.getColumn(),position.getRow() - 2));
                changeTileOnField(new Position(position.getColumn(),position.getRow() - 3));
                break;
            case HORIZONTAL:
                changeTileOnField(new Position(position.getColumn(),position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 1,position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 2,position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 3,position.getRow()));
                break;
            case DIAGONAL_RIGHT:
                changeTileOnField(new Position(position.getColumn(),position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 1,position.getRow() - 1));
                changeTileOnField(new Position(position.getColumn() + 2,position.getRow() - 2));
                changeTileOnField(new Position(position.getColumn() + 3,position.getRow() - 3));
                break;
            case DIAGONAL_LEFT:
                changeTileOnField(new Position(position.getColumn(),position.getRow()));
                changeTileOnField(new Position(position.getColumn() - 1,position.getRow() - 1));
                changeTileOnField(new Position(position.getColumn() - 2,position.getRow() - 2));
                changeTileOnField(new Position(position.getColumn() - 3,position.getRow() - 3));
                break;
        }
    }


    public boolean verticalWinPattern(int column) {
        int counterPlayer = 0;
        int counterOpponent = 0;
        for (int j = size.getWidth() - 1; j >= 0; j--) {
            if (j - 3 >= 0) {
                if (getFieldColor(new Position(column, j)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(column, j - 1)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(column, j - 2)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(column, j - 3)).equals(Color.COLOR_BLUE)) {
                    counterPlayer = 4;
                    counterOpponent = 0;
                    if (counterPlayer == 4) {
                        markWinPattern(new Position(column,j), WinPatternToCheck.VERTICAL);
                        break;
                    }
                }
                if (getFieldColor(new Position(column, j)).equals(Color.COLOR_RED) && getFieldColor(new Position(column, j - 1)).equals(Color.COLOR_RED) && getFieldColor(new Position(column, j - 2)).equals(Color.COLOR_RED) && getFieldColor(new Position(column, j - 3)).equals(Color.COLOR_RED)) {
                    counterPlayer = 4;
                    counterOpponent = 0;
                    if (counterPlayer == 4) {
                        markWinPattern(new Position(column,j), WinPatternToCheck.VERTICAL);
                        break;
                    }
                }
                if (getFieldColor(new Position(column, j)).equals(Color.COLOR_BLACK)) {
                    counterPlayer = 0;
                    counterOpponent = 0;
                }
            }
        }
        if (counterPlayer == 4 || counterOpponent == 4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean horizontallyWinPattern() {
        int counterPlayer = 0;
        int counterOpponent = 0;

        OUTER:
        for (int row = size.getHeight() - 1; row >= 0; row--) {
            for (int column = 0; column <= size.getWidth() - 1; column++) {
                if (column + 3 <= size.getWidth() - 1) {
                    if (getFieldColor(new Position(column, row)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(column + 1, row)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(column + 2, row)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(column + 3, row)).equals(Color.COLOR_BLUE)) {
                        counterPlayer = 4;
                        counterOpponent = 0;
                        if (counterPlayer == 4) {
                            markWinPattern(new Position(column, row), WinPatternToCheck.HORIZONTAL);
                            break OUTER;
                        }
                    }
                    if (getFieldColor(new Position(column, row)).equals(Color.COLOR_RED) && getFieldColor(new Position(column + 1, row)).equals(Color.COLOR_RED) && getFieldColor(new Position(column + 2, row)).equals(Color.COLOR_RED) && getFieldColor(new Position(column + 3, row)).equals(Color.COLOR_RED)) {
                        counterPlayer = 4;
                        counterOpponent = 0;
                        if (counterPlayer == 4) {
                            markWinPattern(new Position(column, row), WinPatternToCheck.HORIZONTAL);
                            break OUTER;
                        }
                    }
                    if (getFieldColor(new Position(column, row)).equals(Color.COLOR_BLACK)) {
                        counterPlayer = 0;
                        counterOpponent = 0;
                    }
                }
            }
        }

        if (counterPlayer == 4 || counterOpponent == 4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean diagonallyWinPattern() {
        int counterPlayer = 0;
        int counterOpponent = 0;

        Outer:
        for (int i = 0; i <= size.getWidth() - 1; i++) {
            for (int j = size.getHeight() - 1; j >= 0; j--) {
                if (j - 3 >= 0) {
                    if (i + 3 <= size.getWidth() - 1) {
                        if (getFieldColor(new Position(i,j)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(i + 1,j - 1)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(i + 2,j - 2)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(i + 3,j - 3)).equals(Color.COLOR_BLUE)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                        } else if (getFieldColor(new Position(i,j)).equals(Color.COLOR_RED) && getFieldColor(new Position(i + 1,j - 1)).equals(Color.COLOR_RED) && getFieldColor(new Position(i + 2,j - 2)).equals(Color.COLOR_RED) && getFieldColor(new Position(i + 3,j - 3)).equals(Color.COLOR_RED)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                            if (getFieldColor(new Position(i,j)) == Color.COLOR_BLACK) {
                                counterPlayer = 0;
                                counterOpponent = 0;
                            }
                        }
                    }
                    if (i - 3 >= 0) {
                        if (getFieldColor(new Position(i,j)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(i - 1,j - 1)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(i - 2,j - 2)).equals(Color.COLOR_BLUE) && getFieldColor(new Position(i - 3,j - 3)).equals(Color.COLOR_BLUE)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                        } else if (getFieldColor(new Position(i,j)).equals(Color.COLOR_RED) && getFieldColor(new Position(i - 1,j - 1)).equals(Color.COLOR_RED) && getFieldColor(new Position(i - 2,j - 2)).equals(Color.COLOR_RED) && getFieldColor(new Position(i - 3,j - 3)).equals(Color.COLOR_RED)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                            if (getFieldColor(new Position(i,j)) == Color.COLOR_BLACK) {
                                counterPlayer = 0;
                                counterOpponent = 0;
                            }
                        }
                    }
                }
            }
        }
        if (counterPlayer == 4 || counterOpponent == 4) {
            return true;
        } else {
            return false;
        }
    }

}

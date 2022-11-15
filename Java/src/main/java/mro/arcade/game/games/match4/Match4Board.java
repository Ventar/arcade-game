package mro.arcade.game.games.match4;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.common.Tile;
import mro.arcade.game.common.TileContainer;
import mro.arcade.game.view.RenderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Match4Board extends TileContainer implements RenderData {

    public static final Logger LOG = LoggerFactory.getLogger(Match4Board.class);

    public Match4Board(Size size, Position offsetPoint) {
        super(size, offsetPoint);
    }

    public Tile insertTileToField(Tile originalTile, int column, String player) {
        Position position;
        Tile tmpTile = null;


        for (int i = 0; i <= size.getHeight() - 1; i++) {
            position = new Position(column, i);

            LOG.trace("Try to add tile ::= [{}] to position ::= [{}]", originalTile, position);

            tmpTile = originalTile.translate(position);
            if (player.equals("Player")) {
                tmpTile.setColor(Color.COLOR_BLUE);
            } else if (player.equals("Opponent")) {
                tmpTile.setColor(Color.COLOR_RED);
            }

            LOG.trace("Translated tile ::= [{}]", tmpTile);
            for (Position pos : tmpTile.getPositions()) {
                if (detectCollision(pos)) {
                    System.out.println("Collision detected, checking next row");
                    break;
                } else if (!isPositionOnBoard(pos)) {
                    System.out.println("Column is full");
                    break;
                } else {
                    i = 100;
                }
            }
        }

        tiles.add(tmpTile);
        LOG.debug("Added tile ::= [{}] to board", tmpTile);
        return tmpTile;
    }

    private void changeTileOnField(Position position) {
        for (Tile tile : tiles) {
            for (Position tilePosition : tile.getPositions()) {
                if (tilePosition.equals(position)) {
                    tile.setColor(Color.COLOR_YELLOW);
                }
            }
        }
    }

    public void markWinPattern(Position position, WinPatternToCheck winPatternToCheck) {

        switch (winPatternToCheck) {
            case VERTICAL:
                changeTileOnField(new Position(position.getColumn(), position.getRow()));
                changeTileOnField(new Position(position.getColumn(), position.getRow() - 1));
                changeTileOnField(new Position(position.getColumn(), position.getRow() - 2));
                changeTileOnField(new Position(position.getColumn(), position.getRow() - 3));
                break;
            case HORIZONTAL:
                changeTileOnField(new Position(position.getColumn(), position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 1, position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 2, position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 3, position.getRow()));

                System.out.println("\n\n CHANGED for position ####> " + position);
                System.out.println(tiles);
                System.out.println("\n\n");

                break;
            case DIAGONAL_RIGHT:
                changeTileOnField(new Position(position.getColumn(), position.getRow()));
                changeTileOnField(new Position(position.getColumn() + 1, position.getRow() - 1));
                changeTileOnField(new Position(position.getColumn() + 2, position.getRow() - 2));
                changeTileOnField(new Position(position.getColumn() + 3, position.getRow() - 3));
                break;
            case DIAGONAL_LEFT:
                changeTileOnField(new Position(position.getColumn(), position.getRow()));
                changeTileOnField(new Position(position.getColumn() - 1, position.getRow() - 1));
                changeTileOnField(new Position(position.getColumn() - 2, position.getRow() - 2));
                changeTileOnField(new Position(position.getColumn() - 3, position.getRow() - 3));
                break;
        }
    }


    public boolean verticalWinPattern(int column) {

        LOG.trace("Try to detect vertical WIN pattern for column ::= [{}]", column);

        for (int row = size.getHeight() - 1; row >= 0; row--) {
            if (row - 3 >= 0) {
                if (getFieldColorWithoutOffset(new Position(column, row)).equals(Color.COLOR_BLUE) &&
                        getFieldColorWithoutOffset(new Position(column, row - 1)).equals(Color.COLOR_BLUE) &&
                        getFieldColorWithoutOffset(new Position(column, row - 2)).equals(Color.COLOR_BLUE) &&
                        getFieldColorWithoutOffset(new Position(column, row - 3)).equals(Color.COLOR_BLUE)) {
                    LOG.trace("WIN Pattern detected for BLUE with row ::= [{}]", row);

                    markWinPattern(new Position(column, row), WinPatternToCheck.VERTICAL);
                    return true;
                }

                if (getFieldColorWithoutOffset(new Position(column, row)).equals(Color.COLOR_RED) &&
                        getFieldColorWithoutOffset(new Position(column, row - 1)).equals(Color.COLOR_RED) &&
                        getFieldColorWithoutOffset(new Position(column, row - 2)).equals(Color.COLOR_RED) &&
                        getFieldColorWithoutOffset(new Position(column, row - 3)).equals(Color.COLOR_RED)) {
                    LOG.trace("WIN Pattern detected for RED with row ::= [{}]", row);
                    markWinPattern(new Position(column, row), WinPatternToCheck.VERTICAL);
                    return true;
                }

            }
        }

        return false;

    }

    public boolean horizontallyWinPattern() {
        int counterPlayer = 0;
        int counterOpponent = 0;

        System.out.println("\n\n ####>");
        System.out.println(tiles);
        System.out.println("\n\n");

        OUTER:
        for (int row = size.getHeight() - 1; row >= 0; row--) {
            for (int column = 0; column <= size.getWidth() - 1; column++) {
                if (column + 3 <= size.getWidth() - 1) { // check if the three fields to the right left the game board
                    if (getFieldColorWithoutOffset(new Position(column, row)).equals(Color.COLOR_BLUE) &&
                            getFieldColorWithoutOffset(new Position(column + 1, row)).equals(Color.COLOR_BLUE) &&
                            getFieldColorWithoutOffset(new Position(column + 2, row)).equals(Color.COLOR_BLUE) &&
                            getFieldColorWithoutOffset(new Position(column + 3, row)).equals(Color.COLOR_BLUE)) {
                        counterPlayer = 4;
                        counterOpponent = 0;
                        if (counterPlayer == 4) {
                            markWinPattern(new Position(column, row), WinPatternToCheck.HORIZONTAL);
                            break OUTER;
                        }
                    }
                    if (getFieldColorWithoutOffset(new Position(column, row)).equals(Color.COLOR_RED) &&
                            getFieldColorWithoutOffset(new Position(column + 1, row)).equals(Color.COLOR_RED) &&
                            getFieldColorWithoutOffset(new Position(column + 2, row)).equals(Color.COLOR_RED) &&
                            getFieldColorWithoutOffset(new Position(column + 3, row)).equals(Color.COLOR_RED)) {
                        counterPlayer = 0;
                        counterOpponent = 4;
                        if (counterOpponent == 4) {
                            markWinPattern(new Position(column, row), WinPatternToCheck.HORIZONTAL);
                            break OUTER;
                        }
                    }
                    if (getFieldColorWithoutOffset(new Position(column, row)).equals(Color.COLOR_BLACK)) {
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
                        if (getFieldColorWithoutOffset(new Position(i, j)).equals(Color.COLOR_BLUE) &&
                                getFieldColorWithoutOffset(new Position(i + 1, j - 1)).equals(Color.COLOR_BLUE) &&
                                getFieldColorWithoutOffset(new Position(i + 2, j - 2)).equals(Color.COLOR_BLUE) &&
                                getFieldColorWithoutOffset(new Position(i + 3, j - 3)).equals(Color.COLOR_BLUE)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                        } else if (getFieldColorWithoutOffset(new Position(i, j)).equals(Color.COLOR_RED) &&
                                getFieldColorWithoutOffset(new Position(i + 1, j - 1)).equals(Color.COLOR_RED) &&
                                getFieldColorWithoutOffset(new Position(i + 2, j - 2)).equals(Color.COLOR_RED) &&
                                getFieldColorWithoutOffset(new Position(i + 3, j - 3)).equals(Color.COLOR_RED)) {
                            counterPlayer = 0;
                            counterOpponent = 4;
                            if (counterOpponent == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                            if (getFieldColorWithoutOffset(new Position(i, j)) == Color.COLOR_BLACK) {
                                counterPlayer = 0;
                                counterOpponent = 0;
                            }
                        }
                    }
                    if (i - 3 >= 0) {
                        if (getFieldColorWithoutOffset(new Position(i, j)).equals(Color.COLOR_BLUE) &&
                                getFieldColorWithoutOffset(new Position(i - 1, j - 1)).equals(Color.COLOR_BLUE) &&
                                getFieldColorWithoutOffset(new Position(i - 2, j - 2)).equals(Color.COLOR_BLUE) &&
                                getFieldColorWithoutOffset(new Position(i - 3, j - 3)).equals(Color.COLOR_BLUE)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_LEFT);
                                break Outer;
                            }
                        } else if (getFieldColorWithoutOffset(new Position(i, j)).equals(Color.COLOR_RED) &&
                                getFieldColorWithoutOffset(new Position(i - 1, j - 1)).equals(Color.COLOR_RED) &&
                                getFieldColorWithoutOffset(new Position(i - 2, j - 2)).equals(Color.COLOR_RED) &&
                                getFieldColorWithoutOffset(new Position(i - 3, j - 3)).equals(Color.COLOR_RED)) {
                            counterPlayer = 0;
                            counterOpponent = 4;
                            if (counterOpponent == 4) {
                                markWinPattern(new Position(i, j), WinPatternToCheck.DIAGONAL_LEFT);
                                break Outer;
                            }
                            if (getFieldColorWithoutOffset(new Position(i, j)) == Color.COLOR_BLACK) {
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

    public void setFieldColor(Position position) {
    }
}

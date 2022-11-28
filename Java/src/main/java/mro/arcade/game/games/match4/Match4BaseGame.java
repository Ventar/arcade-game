package mro.arcade.game.games.match4;

import mro.arcade.game.common.Color;

public abstract class Match4BaseGame {

    private enum WinPatternToCheck {
        VERTICAL,
        HORIZONTAL,
        DIAGONAL_RIGHT,
        DIAGONAL_LEFT;
    }

    protected Match4Model model = new Match4Model(7, 6);

    public abstract void play(Match4ModelListener modelListener);

    /**
     * The Input of the Player decides in which column the coin is inserted to. (Checks from the bottom of the field whether a coin is already set and stack the following coins on
     * top)
     *
     * @param gameBoardModel the board to choose the column.
     * @param column         to insert the coin.
     * @param color          the color to set the Coins.
     */
    public void insertCoin(Match4Model gameBoardModel, int column, Color color) {
        Color[][] field = gameBoardModel.getFields();

        for (int row = gameBoardModel.getRows() - 1; row >= 0; row--) {
            if (field[column][row] == Color.COLOR_BLACK) {
                gameBoardModel.setColor(color, column, row);
                break;
            }
        }
    }

    /**
     * Checks whether the Player can put this coin into the column or not.
     *
     * @param gameBoardModel to check the column from the gameboardmodel.
     * @param column         to check if there space or not.
     *
     * @return if the column is full return false else return true.
     */
    public boolean canInsertCoin(Match4Model gameBoardModel, int column) {
        Color[][] field = gameBoardModel.getFields();
        if (field[column][0] != Color.COLOR_BLACK) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if one of ther Winning Pattern is true and a Player has won.
     *
     * @param gameBoardModel the winning pattern were checked on this gameboard.
     *
     * @return true if one of the pattern is also true.
     */
    public boolean hasWon(Match4Model gameBoardModel, int column) {
        return verticalWinPattern(gameBoardModel, column) || horizontallyWinPattern(gameBoardModel) || diagonallyWinPattern(gameBoardModel);
    }

    /**
     * Changes the color of the coins based on the given winning pattern to check
     *
     * @param gameBoardModel
     * @param column
     * @param row
     * @param winPatternToCheck
     */
    public void markWinPattern(Match4Model gameBoardModel, int column, int row, WinPatternToCheck winPatternToCheck) {

        switch (winPatternToCheck) {
            case VERTICAL:
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row - 1);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row - 2);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row - 3);
                break;
            case HORIZONTAL:
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column + 1, row);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column + 2, row);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column + 3, row);
                break;
            case DIAGONAL_RIGHT:
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column + 1, row - 1);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column + 2, row - 2);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column + 3, row - 3);
                break;
            case DIAGONAL_LEFT:
                gameBoardModel.setColor(Color.COLOR_YELLOW, column, row);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column - 1, row - 1);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column - 2, row - 2);
                gameBoardModel.setColor(Color.COLOR_YELLOW, column - 3, row - 3);
                break;
        }
    }

    /**
     * Checks if every position of the Gameboard and counts the value of the coins in the same color in a vertical row.The Player were the counter reaches four first, wins.
     *
     * @param gameBoardModel the vertical pattern is checked on this gameboard.
     *
     * @return true.
     */
    public boolean verticalWinPattern(Match4Model gameBoardModel, int column) {
        int counterPlayer = 0;
        int counterOpponent = 0;
        Color[][] field = gameBoardModel.getFields();
        for (int j = field[0].length - 1; j >= 0; j--) {
            if (j - 3 >= 0) {
                if (field[column][j].equals(Color.COLOR_BLUE) && field[column][j - 1].equals(Color.COLOR_BLUE) && field[column][j - 2].equals(Color.COLOR_BLUE) && field[column][j - 3].equals(Color.COLOR_BLUE)) {
                    counterPlayer = 4;
                    counterOpponent = 0;
                    if (counterPlayer == 4) {
                        markWinPattern(gameBoardModel, column, j, WinPatternToCheck.VERTICAL);
                        break;
                    }
                }
                if (field[column][j].equals(Color.COLOR_RED) && field[column][j - 1].equals(Color.COLOR_RED) && field[column][j - 2].equals(Color.COLOR_RED) && field[column][j - 3].equals(Color.COLOR_RED)) {
                    counterOpponent = 4;
                    counterPlayer = 0;
                    if (counterOpponent == 4) {
                        markWinPattern(gameBoardModel, column, j, WinPatternToCheck.VERTICAL);
                        break;
                    }
                }
                if (field[column][j] == Color.COLOR_BLACK) {
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

    /**
     * Checks if every position of the Gameboard and counts the value of the coins in the same color in a horizontal row.The Player were the counter reaches four first, wins.
     *
     * @param gameBoardModel the horizontal pattern is checked on this gameboard.
     *
     * @return true if player counter hits four else false.
     */
    public boolean horizontallyWinPattern(Match4Model gameBoardModel) {
        int counterPlayer = 0;
        int counterOpponent = 0;
        Color[][] field = gameBoardModel.getFields();

        OUTER:
        for (int row = field[0].length - 1; row >= 0; row--) {
            for (int column = 0; column <= field.length - 1; column++) {
                if (column + 3 <= gameBoardModel.getColumns() - 1) {
                    if (field[column][row].equals(Color.COLOR_BLUE) && field[column + 1][row].equals(Color.COLOR_BLUE) && field[column + 2][row].equals(Color.COLOR_BLUE) && field[column + 3][row].equals(Color.COLOR_BLUE)) {
                        counterPlayer = 4;
                        counterOpponent = 0;
                        if (counterPlayer == 4) {
                            markWinPattern(gameBoardModel, column, row, WinPatternToCheck.HORIZONTAL);
                            break OUTER;
                        }
                    }
                    if (field[column][row].equals(Color.COLOR_RED) && field[column + 1][row].equals(Color.COLOR_RED) && field[column + 2][row].equals(Color.COLOR_RED) && field[column + 3][row].equals(Color.COLOR_RED)) {
                        counterOpponent = 4;
                        counterPlayer = 0;
                        if (counterOpponent == 4) {
                            markWinPattern(gameBoardModel, column, row, WinPatternToCheck.HORIZONTAL);
                            break OUTER;
                        }
                    }
                    if (field[column][row] == Color.COLOR_BLACK) {
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

    /**
     * Checks if every position of the Gameboard and counts the value of the coins in the same color in a diagonal row.The Player were the counter reaches four first, wins.
     *
     * @param gameBoardModel the diagonal pattern is checked on this gameboard.
     *
     * @return true if player counter hits four else false.
     */
    public boolean diagonallyWinPattern(Match4Model gameBoardModel) {
        int counterPlayer = 0;
        int counterOpponent = 0;
        Color[][] field = gameBoardModel.getFields();

        Outer:
        for (int i = 0; i <= field.length - 1; i++) {
            for (int j = field[0].length - 1; j >= 0; j--) {
                if (j - 3 >= 0) {
                    if (i + 3 <= field.length - 1) {
                        if (field[i][j].equals(Color.COLOR_BLUE) && field[i + 1][j - 1].equals(Color.COLOR_BLUE) && field[i + 2][j - 2].equals(Color.COLOR_BLUE) && field[i + 3][j - 3].equals(Color.COLOR_BLUE)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(gameBoardModel, i, j, WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                        } else if (field[i][j].equals(Color.COLOR_RED) && field[i + 1][j - 1].equals(Color.COLOR_RED) && field[i + 2][j - 2].equals(Color.COLOR_RED) && field[i + 3][j - 3].equals(Color.COLOR_RED)) {
                            counterOpponent = 4;
                            counterPlayer = 0;
                            if (counterOpponent == 4) {
                                markWinPattern(gameBoardModel, i, j, WinPatternToCheck.DIAGONAL_RIGHT);
                                break Outer;
                            }
                            if (field[i][j] == Color.COLOR_BLACK) {
                                counterPlayer = 0;
                                counterOpponent = 0;
                            }
                        }
                    }
                    if (i - 3 >= 0) {
                        if (field[i][j].equals(Color.COLOR_BLUE) && field[i - 1][j - 1].equals(Color.COLOR_BLUE) && field[i - 2][j - 2].equals(Color.COLOR_BLUE) && field[i - 3][j - 3].equals(Color.COLOR_BLUE)) {
                            counterPlayer = 4;
                            counterOpponent = 0;
                            if (counterPlayer == 4) {
                                markWinPattern(gameBoardModel, i, j, WinPatternToCheck.DIAGONAL_LEFT);
                                break Outer;
                            }
                        } else if (field[i][j].equals(Color.COLOR_RED) && field[i - 1][j - 1].equals(Color.COLOR_RED) && field[i - 2][j - 2].equals(Color.COLOR_RED) && field[i - 3][j - 3].equals(Color.COLOR_RED)) {
                            counterOpponent = 4;
                            counterPlayer = 0;
                            if (counterOpponent == 4) {
                                markWinPattern(gameBoardModel, i, j, WinPatternToCheck.DIAGONAL_LEFT);
                                break Outer;
                            }
                            if (field[i][j] == Color.COLOR_BLACK) {
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

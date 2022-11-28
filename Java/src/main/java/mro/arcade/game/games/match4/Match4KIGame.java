package mro.arcade.game.games.match4;

import mro.arcade.game.common.Color;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Match4KIGame extends Match4BaseGame {

    Scanner scanner = new Scanner(System.in);
    int columnInsertCoinTo;
    boolean win = false;
    //Match4GameBoardFrame frame = new Match4GameBoardFrame(model);


    @Override
    public void play(Match4ModelListener modelListener) {
        model.clear();
        modelListener.modelUpdated(model);
        System.out.println("Match 4 started...");

        win = false;
        while (!win) {

            System.out.println("In which column do you want to insert the Coin?");
            String input = scanner.nextLine();

            try {
                columnInsertCoinTo = Integer.parseInt(input) - 1;

                if (canInsertCoin(model, columnInsertCoinTo)) {
                    insertCoin(model, columnInsertCoinTo, mro.arcade.game.common.Color.COLOR_BLUE);
                    modelListener.modelUpdated(model);
                    if (hasWon(model, columnInsertCoinTo)) {
                        win = true;
                        System.out.println("Player has Won");
                        continue;
                    }
                } else {
                    System.out.println("Column is already full, chose an another column");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Please choose only  numbers with the length of the gameboard");
                continue;
            }

            if (kiPlayerTurn(modelListener, model)) {
                System.out.println("KI won");
                win = true;
                continue;
            }
        }
    }

    private boolean kiPlayerTurn(Match4ModelListener modelListener, Match4Model gameBoardModel) {
        Match4Model original = gameBoardModel;
        Match4Model copy;
        Random randomNumber = new Random();
        int positionToCheck = randomNumber.nextInt(original.getColumns());
        ArrayList columnNumber = new ArrayList();
        for (int column = 0; column < original.getColumns(); column++) {
            columnNumber.add(column);
        }

        for (int column = 0; column < original.getColumns(); column++) {
            copy = new Match4Model(gameBoardModel);
            if (canInsertCoin(copy, column)) {
                insertCoin(copy, column, mro.arcade.game.common.Color.COLOR_RED);

                if (hasWon(copy, column)) {
                    insertCoin(original, column, mro.arcade.game.common.Color.COLOR_RED);
                    hasWon(original, column);
                    modelListener.modelUpdated(model);
                    System.out.println("Ki has Won");
                    return true;
                }
            }
        }

        for (int column = 0; column < original.getColumns(); column++) {
            copy = new Match4Model(original);
            if (canInsertCoin(copy, column)) {
                insertCoin(copy, column, mro.arcade.game.common.Color.COLOR_BLUE);

                if (hasWon(copy, column)) {
                    insertCoin(original, column, mro.arcade.game.common.Color.COLOR_RED);
                    modelListener.modelUpdated(model);
                    return false;
                }
            }
        }

        for (int column = 0; column < original.getColumns(); column++) {
            copy = new Match4Model(original);
            positionToCheck = randomNumber.nextInt(columnNumber.size());
            if (canInsertCoin(copy, positionToCheck)) {
                insertCoin(copy, positionToCheck, mro.arcade.game.common.Color.COLOR_RED);
                if (canInsertCoin(copy, positionToCheck)) {
                    insertCoin(copy, positionToCheck, Color.COLOR_BLUE);
                    if (hasWon(copy, positionToCheck)) {
                        columnNumber.remove(positionToCheck);
                        continue;
                    } else if (columnNumber.isEmpty()) {
                        if (canInsertCoin(original, positionToCheck)) {
                            insertCoin(original, positionToCheck, mro.arcade.game.common.Color.COLOR_RED);
                            modelListener.modelUpdated(model);
                        }
                        return false;
                    } else {
                        insertCoin(original, positionToCheck, mro.arcade.game.common.Color.COLOR_RED);
                        modelListener.modelUpdated(model);
                        return false;
                    }
                } else {
                    columnNumber.remove(positionToCheck);
                }
            } else {
                columnNumber.remove(positionToCheck);
            }

        }
        insertCoin(original, randomNumber.nextInt(original.getColumns()), mro.arcade.game.common.Color.COLOR_RED);
        return false;
    }
}

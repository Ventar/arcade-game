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

        System.out.println("Choose a color: \n" +
                "Red\n" +
                "Blue\n" +
                "Green\n" +
                "Purple\n" +
                "Brown\"" +
                "Orange\n" +
                "Rose\n" +
                "Babyblue");

        String colorInput = scanner.nextLine();

        switch (colorInput) {
            case "Red":
                playerColor = Color.COLOR_RED;
                break;
            case "Blue":
                playerColor = Color.COLOR_BLUE;
                break;
            case "Green":
                playerColor = Color.COLOR_GREEN;
                break;
            case "Purple":
                playerColor = Color.COLOR_PURPLE;
                break;
            case "Brown":
                playerColor = Color.COLOR_BROWN;
                break;
            case "Orange":
                playerColor = Color.COLOR_ORANGE;
                break;
            case "Rose":
                playerColor = Color.COLOR_ROSE;
                break;
            case "Babyblue":
                playerColor = Color.COLOR_BABYBLUE;
                break;
        }

        win = false;
        while (!win) {

            System.out.println("In which column do you want to insert the Coin?");
            String input = scanner.nextLine();

            try {
                columnInsertCoinTo = Integer.parseInt(input) - 1;

                if (canInsertCoin(model, columnInsertCoinTo)) {
                    insertCoin(model, columnInsertCoinTo, playerColor);
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
        opponentColor = Color.COLOR_RED;
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
                insertCoin(copy, column, opponentColor);

                if (hasWon(copy, column)) {
                    insertCoin(original, column, opponentColor);
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
                insertCoin(copy, column, playerColor);

                if (hasWon(copy, column)) {
                    insertCoin(original, column, opponentColor);
                    modelListener.modelUpdated(model);
                    return false;
                }
            }
        }

        for (int column = 0; column < original.getColumns(); column++) {
            copy = new Match4Model(original);
            positionToCheck = randomNumber.nextInt(columnNumber.size());
            if (canInsertCoin(copy, positionToCheck)) {
                insertCoin(copy, positionToCheck, opponentColor);
                if (canInsertCoin(copy, positionToCheck)) {
                    insertCoin(copy, positionToCheck, playerColor);
                    if (hasWon(copy, positionToCheck)) {
                        columnNumber.remove(positionToCheck);
                        continue;
                    } else if (columnNumber.isEmpty()) {
                        if (canInsertCoin(original, positionToCheck)) {
                            insertCoin(original, positionToCheck, opponentColor);
                            modelListener.modelUpdated(model);
                        }
                        return false;
                    } else {
                        insertCoin(original, positionToCheck, opponentColor);
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
        insertCoin(original, randomNumber.nextInt(original.getColumns()), opponentColor);
        return false;
    }
}

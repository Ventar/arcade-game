package mro.arcade.game.games.match4;



import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class KIPlayerService {
//    GameService gameService = new GameService();
//
//    public boolean kiPlayerTurn(GameBoardFrame gameBoardFrame, GameBoardModel gameBoardModel) {
//        GameBoardModel original = gameBoardModel;
//        GameBoardModel copy;
//        Random randomNumber = new Random();
//        int positionToCheck = randomNumber.nextInt(original.getColumns());
//        ArrayList columnNumber = new ArrayList();
//        for (int column = 0; column < original.getColumns(); column++) {
//            columnNumber.add(column);
//        }
//
//        for (int column = 0; column < original.getColumns(); column++) {
//            copy = new GameBoardModel(gameBoardModel);
//            if (gameService.canInsertCoin(copy, column)) {
//                gameService.insertCoin(copy, column, Color.red);
//
//                if (gameService.hasWon(copy, column)) {
//                    gameService.insertCoin(original, column, Color.red);
//                    gameService.hasWon(original, column);
//                    gameBoardFrame.setModel(original);
//                    System.out.println("Ki has Won");
//                    return true;
//                }
//
//            }
//        }
//
//        for (int column = 0; column < original.getColumns(); column++) {
//            copy = new GameBoardModel(original);
//            if (gameService.canInsertCoin(copy, column)) {
//                gameService.insertCoin(copy, column, Color.blue);
//
//                if (gameService.hasWon(copy, column)) {
//                    gameService.insertCoin(original, column, Color.red);
//                    gameBoardFrame.setModel(original);
//                    return false;
//                }
//            }
//        }
//
//        for (int column = 0; column < original.getColumns(); column++) {
//            copy = new GameBoardModel(original);
//            positionToCheck = randomNumber.nextInt(columnNumber.size());
//            if (gameService.canInsertCoin(copy, positionToCheck)) {
//                gameService.insertCoin(copy, positionToCheck, Color.red);
//                if (gameService.canInsertCoin(copy, positionToCheck)) {
//                    gameService.insertCoin(copy, positionToCheck, Color.blue);
//                    if (gameService.hasWon(copy, positionToCheck)) {
//                        columnNumber.remove(positionToCheck);
//                        continue;
//                    } else if (columnNumber.isEmpty()) {
//                        if (gameService.canInsertCoin(original, positionToCheck)) {
//                            gameService.insertCoin(original, positionToCheck, Color.red);
//                            gameBoardFrame.setModel(original);
//                        }
//                        return false;
//                    } else {
//                        gameService.insertCoin(original, positionToCheck, Color.red);
//                        gameBoardFrame.setModel(original);
//                        return false;
//                    }
//                }
//                else {
//                    columnNumber.remove(positionToCheck);
//                }
//            }
//            else {
//                columnNumber.remove(positionToCheck);
//            }
//
//        }
//        gameService.insertCoin(original, randomNumber.nextInt(original.getColumns()), Color.red);
//        return false;
//    }
}

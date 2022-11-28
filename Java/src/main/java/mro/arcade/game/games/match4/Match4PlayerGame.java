package mro.arcade.game.games.match4;

import java.util.Scanner;

public class Match4PlayerGame extends Match4BaseGame {

    Scanner scanner = new Scanner(System.in);
    int columnInsertCoinTo;
    boolean win = false;
    //    Match4GameBoardFrame frame = new Match4GameBoardFrame(model);

    @Override
    public void play(Match4ModelListener modelListener) {
        model.clear();
        modelListener.modelUpdated(model);
        System.out.println("Match 4 started...");

        win = false;
        OUTER:
        while (!win) {
            System.out.println("In which column do you want to insert the Coin?");
            String input = scanner.nextLine();

            try {
                columnInsertCoinTo = Integer.parseInt(input) - 1;
                if (canInsertCoin(model, columnInsertCoinTo)) {
                    insertCoin(model, columnInsertCoinTo, mro.arcade.game.common.Color.COLOR_BLUE);
                    modelListener.modelUpdated(model);
                    if (hasWon(model, columnInsertCoinTo)) {
                        modelListener.modelUpdated(model);
                        System.out.println("Player has Won");
                        win = true;
                        continue;
                    }

                } else {
                    System.out.println("Column is already full, chose an another column");
                    continue;
                }

                boolean loop = true;
                while (loop) {
                    System.out.println("In which column do the opponent want to insert the Coin?");
                    input = scanner.nextLine();
                    try {
                        columnInsertCoinTo = Integer.parseInt(input) - 1;
                        if (canInsertCoin(model, columnInsertCoinTo)) {
                            insertCoin(model, columnInsertCoinTo, mro.arcade.game.common.Color.COLOR_RED);
                            modelListener.modelUpdated(model);
                            if (hasWon(model, columnInsertCoinTo)) {
                                win = true;
                                System.out.println("Opponent has Won");
                                continue OUTER;
                            }
                        } else {
                            System.out.println("Column is already full, chose an another column");
                            continue;
                        }
                    } catch (Exception exception) {
                        System.out.println("Please only choose numbers with the length of the gameboard");
                        continue;
                    }
                    loop = false;
                }
            } catch (Exception e) {
                System.out.println("Please only choose numbers with the length of the gameboard");
            }
        }
    }
}

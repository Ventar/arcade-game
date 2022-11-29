package mro.arcade.game.games.match4;

import mro.arcade.game.common.Color;

import java.util.Scanner;

public class Match4PlayerGame extends Match4BaseGame {

    Scanner scanner = new Scanner(System.in);
    int columnInsertCoinTo;
    boolean win = false;


    //    Match4GameBoardFrame frame = new Match4GameBoardFrame(model);

    @Override
    public void play(Match4ModelListener modelListener) {
        boolean loop = false;
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


        System.out.println("Choose opponents color: ");

        while (!loop) {

            System.out.println("Red\n" +
                "Blue\n" +
                "Green\n" +
                "Purple\n" +
                "Brown\"" +
                "Orange\n" +
                "Rose\n" +
                "Babyblue");
        colorInput = scanner.nextLine();

            switch (colorInput) {
                case "Red":
                    opponentColor = Color.COLOR_RED;
                    break;
                case "Blue":
                    opponentColor = Color.COLOR_BLUE;
                    break;
                case "Green":
                    opponentColor = Color.COLOR_GREEN;
                    break;
                case "Purple":
                    opponentColor = Color.COLOR_PURPLE;
                    break;
                case "Brown":
                    opponentColor = Color.COLOR_BROWN;
                    break;
                case "Orange":
                    opponentColor = Color.COLOR_ORANGE;
                    break;
                case "Rose":
                    opponentColor = Color.COLOR_ROSE;
                    break;
                case "Babyblue":
                    opponentColor = Color.COLOR_BABYBLUE;
                    break;
            }
            if (opponentColor.equals(playerColor)) {
                System.out.println("choose another color");
            } else {
                break;
            }
        }

        win = false;
        OUTER:
        while (!win) {
            System.out.println("In which column do you want to insert the Coin?");
            String input = scanner.nextLine();

            try {
                columnInsertCoinTo = Integer.parseInt(input) - 1;
                if (canInsertCoin(model, columnInsertCoinTo)) {
                    insertCoin(model, columnInsertCoinTo, playerColor);
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

                loop = true;

                while (loop) {
                    System.out.println("In which column do the opponent want to insert the Coin?");
                    input = scanner.nextLine();
                    try {
                        columnInsertCoinTo = Integer.parseInt(input) - 1;
                        if (canInsertCoin(model, columnInsertCoinTo)) {
                            insertCoin(model, columnInsertCoinTo, opponentColor);
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

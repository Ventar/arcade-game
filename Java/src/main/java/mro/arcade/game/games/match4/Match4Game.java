package mro.arcade.game.games.match4;


import mro.arcade.game.common.*;
import mro.arcade.game.common.Color;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderDataContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Match4Game {

    public static final Logger LOG = LoggerFactory.getLogger(Match4Game.class);
    Match4Board match4Board = new Match4Board(new Size(11, 10), new Position(4, 1));

    Match4BoardFrame match4BoardFrame = new Match4BoardFrame(new Size(12, 12), new Position(3, 0), new Color(150, 150, 150));

    private BoardRenderer renderer;

    public Match4Game(BoardRenderer renderer) {
        this.renderer = renderer;
    }


    /**
     * Checks if one of ther Winning Pattern is true and a Player has won.
     *
     * @return true if one of the pattern is also true.
     */
    public boolean hasWon(int column) {
        return match4Board.verticalWinPattern(column) || match4Board.horizontallyWinPattern() || match4Board.diagonallyWinPattern();
    }

    public void run() throws InterruptedException {
        boolean loop = true;
        boolean win = false;
        Scanner scanner = new Scanner(System.in);
        String player = "Player";
        String player2 = "Opponent";

        renderer.clear();
        render();

        while (!win) {
            System.out.println("In which column do you want to insert the Coin?");
            String input = scanner.nextLine();
            int columnInsertCoinTo = Integer.parseInt(input) - 1;
            match4Board.insertTileToField(TileLibary.DOT_TEMPLATE, columnInsertCoinTo, player);
            render();
            if (hasWon(columnInsertCoinTo)) {
                render();
                System.out.println("Player has Won");
                System.out.println("1. Continue Playing\n" +
                        "2. Return to Menu\n" +
                        "3. Exit Game");
                int choiceInt = Integer.parseInt(scanner.nextLine());
                if (choiceInt == 1) {
                    match4Board.clearField();
                    render();
                    continue;
                } else if (choiceInt == 2) {
                    break;
                } else if (choiceInt == 3) {
                    System.exit(0);
                }
            }
            System.out.println("Opponents turn");
            System.out.println("In which column do you want to insert the Coin?");
            String input2 = scanner.nextLine();
            int columnInsertCoinTo2 = Integer.parseInt(input2) - 1;
            match4Board.insertTileToField(TileLibary.DOT_TEMPLATE, columnInsertCoinTo2, player2);
            render();
            if (hasWon(columnInsertCoinTo2)) {
                render();
                System.out.println("Opponent has Won");
                System.out.println("1. Continue Playing\n" +
                        "2. Return to Menu\n" +
                        "3. Exit Game");
                int choiceInt = Integer.parseInt(scanner.nextLine());
                if (choiceInt == 1) {
                    renderer.clear();
                    render();
                    continue;
                } else if (choiceInt == 2) {
                    break;
                } else if (choiceInt == 3) {
                    System.exit(0);
                }
            }
        }
    }

    public void render() {
        RenderDataContainer container = new RenderDataContainer();
        container.addRenderData(match4Board);
        container.addRenderData(match4BoardFrame);

        renderer.render(container);
    }

}

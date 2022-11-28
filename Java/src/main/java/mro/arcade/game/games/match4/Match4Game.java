package mro.arcade.game.games.match4;


import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.common.Size;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderDataContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Match4Game {

    public static final Logger LOG = LoggerFactory.getLogger(Match4Game.class);

    Match4BoardFrame match4BoardFrame = new Match4BoardFrame(new Size(12, 12), new Position(3, 0), new Color(150, 150, 150));

    private BoardRenderer renderer;

    public Match4Game(BoardRenderer renderer) {
        this.renderer = renderer;
    }

    public void run() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which Gamemode do you want to play?\n" +
                                   "1. Player vs Player\n" +
                                   "2. Player vs KI\n" +
                                   "3. Exit");

        int gamemodeToSelect = Integer.parseInt(scanner.nextLine());

        if (gamemodeToSelect == 1) {
            Match4BaseGame game = new Match4PlayerGame();
            game.play(model -> render(model));
            System.out.println("End game");
        } else if (gamemodeToSelect == 2) {
            Match4BaseGame game = new Match4KIGame();
            game.play(model -> render(model));
            System.out.println("End game");
        } else if (gamemodeToSelect == 3) {
            System.out.println("Exit game");
            System.exit(0);
        }
    }


    public void render(Match4Model model) {
        RenderDataContainer container = new RenderDataContainer();

        container.addRenderData(position -> {

            //LOG.debug("Try to render model ::= [] at position ::= [{}]",  position);
            if (model == null) {
                return Color.COLOR_BLACK;
            } else {
                Color c =  model.getColor(position.getColumn() - 4, (6 -  position.getRow() - 1) + 1);

                if (c.equals(Color.COLOR_RED) || c.equals(Color.COLOR_BLUE)) {
                    LOG.debug("Return player color for position ::= {}", position);
                }
                return c;
            }
        });

        container.addRenderData(match4BoardFrame);
        renderer.render(container);
    }


}

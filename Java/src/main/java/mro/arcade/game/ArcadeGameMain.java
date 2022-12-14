package mro.arcade.game;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import mro.arcade.game.common.*;
import mro.arcade.game.games.match4.Match4Game;
import mro.arcade.game.games.tetris.TetrisGame;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.renderer.ArduinoUDPRenderer;
import mro.arcade.game.view.renderer.SwingRenderer;


import java.util.Scanner;

public class ArcadeGameMain implements NativeKeyListener {
    private static final Size SIZE = new Size(24, 24);

    private static BoardRenderer renderer = new ArduinoUDPRenderer(SIZE, "192.168.51.50");
    //private BoardRenderer renderer = new ASCIIRenderer();
    //private static BoardRenderer renderer = new SwingRenderer(SIZE);


    public static void main(String[] args) throws Exception {

        GlobalScreen.registerNativeHook();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        renderer.showIntro();

        while (loop) {
            System.out.println("Which Game do you want to Play?");
            System.out.println("1: Tetris");
            System.out.println("2: Match 4");
            System.out.println("3: Exit");

            int input = Integer.parseInt(scanner.nextLine());

            if (input == 1) {
                renderer.clear();
                TetrisGame tetrisGame = new TetrisGame(renderer);
                GlobalScreen.addNativeKeyListener(tetrisGame);
                tetrisGame.run();
                GlobalScreen.removeNativeKeyListener(tetrisGame);
            } else if (input == 2) {
                renderer.clear();
                Match4Game match4Game = new Match4Game(renderer);
                match4Game.run();
            } else if (input == 3) {
                renderer.showIntro();
                System.exit(0);
                break;
            }
        }
    }
}


package mro.arcade.game;

import mro.arcade.game.model.Gameboard;
import mro.arcade.game.model.Position;
import mro.arcade.game.model.Size;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.renderer.SwingRenderer;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gameboard gameboard = new Gameboard(new Size(24, 24));
        BoardRenderer renderer = new SwingRenderer(new Size(24, 24));
        Position position;
        int column = 15;
        int row = 8;
        int boardPerRow = 2;
        int boardPerColumn = 2;
        int ledPerBoard = 144;
        int ledOfToken;

        renderer.render(gameboard);

        //System.out.println("In which columm do you want to set the Token?");

        int columnOnSingleBoard = gameboard.getSize().getWidth() % column;
        int rowOnSingleBoard = gameboard.getSize().getHeight() & row;
        ledOfToken = ledPerBoard * boardPerColumn + ledPerBoard * boardPerRow / 2 + gameboard.getSize().getWidth() * rowOnSingleBoard + columnOnSingleBoard;
    }
}

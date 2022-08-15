package mro.arcade.game.view;

import mro.arcade.game.model.Color;
import mro.arcade.game.model.Position;

public class ASCIIRenderer implements BoardRenderer {

    /**
     * Number of - columns.
     */
    private static final int COLUMN_WIDTH = 8;

    /**
     * Number of | rows.
     */
    private static final int ROW_HEIGHT = 3;


    @Override
    public void render(RenderData data) {
        int columnCount = 8;
        int rowCount = 8;

        String result = "";


        result += tetrisFrame(columnCount, "┌", "─", "┬", "┐");
        result += fillerSign(data, columnCount, 0);

        for (int row = 1; row < rowCount; row++) {
            result += tetrisFrame(columnCount, "├", "─", "┼", "┤");
            result += fillerSign(data, columnCount, row);
        }

        result += tetrisFrame(rowCount, "└", "─", "┴", "┘");

        System.out.println(result);

    }


    public String fillerSign(RenderData data, int columnCount, int rowNumber) {

        String result = "";
        for (int rHeight = 0; rHeight < ROW_HEIGHT; rHeight++) {
            result += "│";

            for (int column = 0; column < columnCount; column++) {
                Color color = data.getFieldColor(new Position(column, rowNumber));

                if (rHeight == 0) {
                    result += " (" + column + "|" + rowNumber + ")" + " ".repeat(COLUMN_WIDTH - 6);
                } else if (rHeight == ROW_HEIGHT - 1 && !color.equals(Color.COLOR_BLACK)) {
                    result += " ";
                    result += color.getHexString();
                    result += " ".repeat(COLUMN_WIDTH - 7);
                } else {
                    result += " ".repeat(COLUMN_WIDTH);

                }
                result += "│";
            }

            result += "  " + rHeight + "\n";

        }

        return result;
    }


    private String tetrisFrame(int columnCount, String start, String filler, String middle, String end) {

        String result = "";

        result += start;

        for (int a = 1; a < columnCount + 1; a++) {

            result += filler.repeat(COLUMN_WIDTH);

            if (a == columnCount) {
                result += end;
            } else {
                result += middle;

            }

        }
        result += "\n";
        return result;
    }


}



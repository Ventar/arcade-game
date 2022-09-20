package mro.arcade.game.view.renderer;

import mro.arcade.game.common.Color;
import mro.arcade.game.common.Position;
import mro.arcade.game.view.BoardRenderer;
import mro.arcade.game.view.RenderData;
import mro.arcade.game.common.Size;

@SuppressWarnings("java:S106")
public class ASCIIRenderer implements BoardRenderer {

    /**
     * Number of - columns.
     */
    private static final int COLUMN_WIDTH = 8;

    /**
     * Number of | rows.
     */
    private static final int ROW_HEIGHT = 3;

    private Size size;


    public ASCIIRenderer(Size size) {
        this.size = size;
    }

    @Override
    public void clear() {
        // nothing to do here
    }


    public void render(RenderData data) {

        int columnCount = size.getWidth();
        int rowCount = size.getHeight();

        StringBuilder result = new StringBuilder();


        for (int row = 0; row < rowCount; row++) {
            if (row == 0) {
                result.append(tetrisFrame(columnCount, "┌", "─", "┬", "┐"));
                result.append(fillerSign(data, columnCount, rowCount - 1 - row));
            } else {
                result.append(tetrisFrame(columnCount, "├", "─", "┼", "┤"));
                result.append(fillerSign(data, columnCount, rowCount - 1 - row));
            }
        }
        result.append(tetrisFrame(columnCount, "└", "─", "┴", "┘"));

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



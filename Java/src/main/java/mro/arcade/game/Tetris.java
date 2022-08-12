package mro.arcade.game;

public class Tetris {

    private static final String[][] data = {
            {"h", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"c", "", "", "", "", "", "", ""},
            {"", "", "", "#", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "o"},
    };

    private static String renderScreenRow(String start, String middle, String end, String fill, int columnCount, int columnWidth) {

        String result = "";

        result += start;

        for (int a = 0; a < columnCount; a++) {

            result += fill.repeat(columnWidth);

            if (a == columnCount - 1) {
                result += end;
            } else {
                result += middle;
            }

        }

        result += "\n";
        return result;
    }


    private static String renderCompleteRow(String start, String middle, String end, String fill, int columnCount, int columnWidth, int rowHeight) {

        String result = "";

        result += renderScreenRow(start, middle, end, fill, columnCount, columnWidth);

        for (int rHeight = 0; rHeight < rowHeight; rHeight++) {
            result += renderScreenRow("│", "│", "│", " ", columnCount, columnWidth);
        }
        return result;
    }

    private static void tetrisBoard(int columnWidth, int rowHeight, int columnCount, int rowCount) {

        String result = "";

        result += renderCompleteRow("┌", "┬", "┐", "─", columnCount, columnWidth, rowHeight);

        for (int s = 0; s < rowCount - 1; s++) {
            result += renderCompleteRow("├", "┼", "┤", "─", columnCount, columnWidth, rowHeight);
        }

        result += renderScreenRow("└", "┴", "┘", "─", columnCount, columnWidth);


        System.out.println(result);


    }


    public static void fillerZeichen(int ROW_HEIGHT, int COLUMN_COUNT, int COLUMN_WIDTH) {

        String result = "";


        for (int rHeight = 0; rHeight < ROW_HEIGHT; rHeight++) {
            result += "│";

            for (int column = 0; column < COLUMN_COUNT; column++) {
                if (rHeight == 0) {
                    result += " (" + column + "|" + 0 + ")" + " ".repeat(COLUMN_WIDTH - 6);
                } else if (rHeight == ROW_HEIGHT - 1 && !data[0][column].isEmpty()) {
                    result += " ";
                    result += data[0][column];
                    result += " ".repeat(COLUMN_WIDTH - 2);
                } else {
                    result += " ".repeat(COLUMN_WIDTH);

                }
                result += "│";
            }

            result += "  " + rHeight + "\n";

        }


    }

    public static void main(String[] args) {

        fillerZeichen(4, 4, 4);
        tetrisBoard(8, 3, data[0].length, data.length);

    }

}

//}

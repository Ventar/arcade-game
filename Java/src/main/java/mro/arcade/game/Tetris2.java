package mro.arcade.game;

public class Tetris2 {

    private static final String[][] data = {
            {"", "u", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"c", "", "", "", "", "", "", ""},
            {"", "", "", "#", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "o"},
    };

    public static void main(String[] args) {

        int COLUMN_WIDTH = 12;
        int ROW_HEIGHT = 4;
        int COLUMN_COUNT = 4;
        int ROW_COUNT = 4;

        String result = "";

        // ---------------------------------------------------------------------
        // HEADER_TOP
        // ---------------------------------------------------------------------

        result += "┌"; // result = result + "┌";

        for (int a = 1; a < COLUMN_COUNT + 1; a++) {

            result += "─".repeat(COLUMN_WIDTH);

            if (a == COLUMN_COUNT) {
                result += "┐"; // neue Zeile "\n"
            } else {
                result += "┬";  // neue Zeile "\n"
            }

        }

        result += "\n";

        // ---------------------------------------------------------------------
        // EMPTY ROWS |   |   |   |
        // ---------------------------------------------------------------------


        for (int rHeight = 0; rHeight < ROW_HEIGHT; rHeight++) {
            result += "│";
            for (int col = 0; col < COLUMN_COUNT; col++) {
                result += " (" + col + "|" + 0 + ")" + " ".repeat(COLUMN_WIDTH - 6);
                result += "│";
            }
            result += "\n";
        }

        // ---------------------------------------------------------------------
        // HEADER MIDDLE
        // ---------------------------------------------------------------------
        for (int row = 1; row < ROW_COUNT; row++) {
            result += "├";

            for (int a = 1; a < COLUMN_COUNT + 1; a++) {

                result += "─".repeat(COLUMN_WIDTH);

                if (a == COLUMN_COUNT) {
                    result += "┤";
                } else {
                    result += "┼";
                }
            }

            result += "\n";

            // ---------------------- -----------------------------------------------
            // EMPTY ROWS |   |   |   |
            // ---------------------------------------------------------------------


            for (int rHeight = 0; rHeight < ROW_HEIGHT; rHeight++) {
                result += "│";

                for (int column = 0; column < COLUMN_COUNT; column++) {
                    if (rHeight == 0) {
                        result += " (" + column + "|" + row + ")" + " ".repeat(COLUMN_WIDTH - 6);
                    } else if (rHeight == ROW_HEIGHT - 1 && !data[row][column].isEmpty()) {
                        result += " ";
                        result += data[row][column];
                        result += " ".repeat(COLUMN_WIDTH - 2);
                    } else {
                        result += " ".repeat(COLUMN_WIDTH);

                    }
                    result += "│";
                }

                result += "  " + rHeight + "\n";

            }

        }
        // ---------------------------------------------------------------------
        // FOOTER
        // ---------------------------------------------------------------------

        result += "└";

        for (int a = 1; a < COLUMN_COUNT + 1; a++) {

            result += "─".repeat(COLUMN_WIDTH);

            if (a == COLUMN_COUNT) {
                result += "┘";
            } else {
                result += "┴";

            }

        }


        System.out.println(result);

    }


    private static void tetrisBoard() {


    }

}
//}
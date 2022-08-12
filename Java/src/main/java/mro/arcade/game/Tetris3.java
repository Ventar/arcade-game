package mro.arcade.game;

public class Tetris3 {

    private static final String[][] data = {
            {"", "", "b", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "#", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
    };

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private static void move(int column, int row, Direction direction) {
        String game = data[column][row];
        if (direction.equals(Direction.UP)) {


            add(game, column + 1, row);
            delete(column, row);

        } else if (direction.equals(Direction.DOWN)) {
            add(game, column - 1, row);
            delete(column, row);

        } else if (direction.equals(Direction.LEFT)) {
            add(game, column, row - 1);
            delete(column, row);
        } else {
            add(game, column, row + 1);
            delete(column, row);
        }
    }

    private static void delete(int column, int row) {

        if (!data[column][row].equals("")) {

            data[column][row] = "";

        }


    }


    private static void add(String adder, int column, int row) {

        data[column][row] = adder;

    }

    public static void main(String[] args) {
        Direction dir = Direction.DOWN;

        if (dir == Direction.UP) {
            System.out.println("Direction is UP");
        } else if (dir == Direction.DOWN) {
            System.out.println("Direction is DOWN");
        } else if (dir == Direction.LEFT) {
            System.out.println("Direction is LEFT");
        } else if (dir == Direction.RIGHT) {
            System.out.println("Direction is RIGHT");
        }

        renderField();
        move(3, 3, Direction.UP);
        //renderField();
        // move(3, 4, LEFT);
        //renderField();


    }

    //    private static String headTop(int COLUMN_COUNT, int COLUMN_WIDTH) {
    //
    //
    //        String result = "";
    //
    //        result += "┌"; // result = result + "┌";
    //
    //        for (int a = 1; a < COLUMN_COUNT + 1; a++) {
    //
    //            result += "─".repeat(COLUMN_WIDTH);
    //
    //            if (a == COLUMN_COUNT) {
    //                result += "┐"; // neue Zeile "\n"
    //            } else {
    //                result += "┬";  // neue Zeile "\n"
    //            }
    //
    //        }
    //
    //
    //        result += "\n";
    //        return result;
    //    }

    public static String fillerSign(int ROW_HEIGHT, int COLUMN_COUNT, int COLUMN_WIDTH, int rowNumber) {

        String result = "";
        for (int rHeight = 0; rHeight < ROW_HEIGHT; rHeight++) {
            result += "│";

            for (int column = 0; column < COLUMN_COUNT; column++) {
                if (rHeight == 0) {
                    result += " (" + column + "|" + rowNumber + ")" + " ".repeat(COLUMN_WIDTH - 6);
                } else if (rHeight == ROW_HEIGHT - 1 && !data[rowNumber][column].isEmpty()) {
                    result += " ";
                    result += data[rowNumber][column];
                    result += " ".repeat(COLUMN_WIDTH - 2);
                } else {
                    result += " ".repeat(COLUMN_WIDTH);

                }
                result += "│";
            }

            result += "  " + rHeight + "\n";

        }

        return result;
    }


    //    private static String headMid(int COLUMN_COUNT, int COLUMN_WIDTH) {
    //
    //        String result = "";
    //        result += "├";
    //
    //        for (int a = 1; a < COLUMN_COUNT + 1; a++) {
    //
    //            result += "─".repeat(COLUMN_WIDTH);
    //
    //            if (a == COLUMN_COUNT) {
    //                result += "┤";
    //            } else {
    //                result += "┼";
    //            }
    //        }
    //
    //        result += "\n";
    //
    //        return result;
    //    }

    //    private static String foot(int COLUMN_COUNT, int COLUMN_WIDTH) {
    //        String result = "";
    //
    //        result += "└";
    //
    //        for (int a = 1; a < COLUMN_COUNT + 1; a++) {
    //
    //            result += "─".repeat(COLUMN_WIDTH);
    //
    //            if (a == COLUMN_COUNT) {
    //                result += "┘";
    //            } else {
    //                result += "┴";
    //
    //            }
    //
    //        }
    //        return result;
    //    }


    private static String tetrisFrame(int COLUMN_COUNT, int COLUMN_WIDTH, String start, String filler, String middle, String end) {

        String result = "";

        result += start;

        for (int a = 1; a < COLUMN_COUNT + 1; a++) {

            result += filler.repeat(COLUMN_WIDTH);

            if (a == COLUMN_COUNT) {
                result += end;
            } else {
                result += middle;

            }

        }
        result += "\n";
        return result;
    }


    private static String renderField() {
        int COLUMN_WIDTH = 8;
        int ROW_HEIGHT = 3;
        int COLUMN_COUNT = 8;
        int ROW_COUNT = 6;

        String result = "";

        // ---------------------------------------------------------------------
        // HEADER_TOP
        // ---------------------------------------------------------------------
        result += tetrisFrame(COLUMN_COUNT, COLUMN_WIDTH, "┌", "─", "┬", "┐");


        // ---------------------------------------------------------------------
        // EMPTY ROWS |   |   |   |
        // ---------------------------------------------------------------------

        result += fillerSign(ROW_HEIGHT, COLUMN_COUNT, COLUMN_WIDTH, 0);


        // ---------------------------------------------------------------------
        // HEADER MIDDLE
        // ---------------------------------------------------------------------
        for (int row = 1; row < ROW_COUNT; row++) {
            result += tetrisFrame(COLUMN_COUNT, COLUMN_WIDTH, "├", "─", "┼", "┤");

            // ---------------------- -----------------------------------------------
            // EMPTY ROWS |   |   |   |
            // ---------------------------------------------------------------------


            result += fillerSign(ROW_HEIGHT, COLUMN_COUNT, COLUMN_WIDTH, row);

        }
        // ---------------------------------------------------------------------
        // FOOTER
        // ---------------------------------------------------------------------

        result += tetrisFrame(COLUMN_COUNT, COLUMN_WIDTH, "└", "─", "┴", "┘");


        System.out.println(result);
        return result;
    }


}



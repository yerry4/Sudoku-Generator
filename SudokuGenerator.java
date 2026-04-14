import java.util.ArrayList;

public class SudokuGenerator {

    public static int[][] board = new int[9][9];

    public static void main(String[] args) {
        fillBoard();
        mixRows();
        mixCols();
        mixNums();
        printBoard();

    }

    public static void shuffle(ArrayList<Integer> list) {

        int i = list.size() - 1;

        while (i > 0) {
            int j = (int)(Math.random() * (i + 1));
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
            i--;
        }

    }

    public static void fillBoard() {

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < 9; i++) {
            int shift = (i * 3 + i / 3) % 9;
            for (int j = 0; j < 9; j++) {
                board[i][j] = nums[(j + shift) % 9];
            }
        }

    }

    public static void mixRows() {

        for (int g = 0; g < 3; g++) {

            ArrayList<Integer> rows = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                rows.add(g * 3 + i);
            }

            shuffle(rows);

            int[][] temp = new int[3][9];

            for (int i = 0; i < 3; i++) {
                temp[i] = board[rows.get(i)];
            }

            for (int i = 0; i < 3; i++) {
                board[g * 3 + i] = temp[i];
            }

        }

    }

    public static void mixCols() {

        for (int g = 0; g < 3; g++) {

            ArrayList<Integer> cols = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                cols.add(g * 3 + i);
            }

            shuffle(cols);

            for (int r = 0; r < 9; r++) {

                int[] temp = new int[3];

                for (int i = 0; i < 3; i++) {
                    temp[i] = board[r][cols.get(i)];
                }

                for (int i = 0; i < 3; i++) {
                    board[r][g * 3 + i] = temp[i];
                }

            }

        }

    }

    public static void mixNums() {

        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            nums.add(i);
        }

        shuffle(nums);

        int[] loc = new int[10];

        for (int i = 1; i <= 9; i++) {
            loc[i] = nums.get(i - 1);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = loc[board[i][j]];
            }
        }

    }

    public static void printBoard() {

        System.out.println("+-------+-------+-------+");

        for (int i = 0; i < 9; i++) {
            System.out.print("| ");
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
        }

    }

}

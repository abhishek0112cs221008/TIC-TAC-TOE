import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final char[][] board = new char[3][3];
    private static char turn = 'X';

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    private static void initilizeBoard() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean gameOver = false;

        // initialized Board
        initilizeBoard();

        System.out.println("TIC-TAE-TOE");
        printBoard();

        while(!gameOver) {
            System.out.println(turn+"'s Enter row, column (0-2)");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (isValidMove(row, col)) {
                placeMark(row, col);
                printBoard();

                if (checkWin()) {
                    System.out.println("Player " + turn + " wins!");
                    gameOver = true;
                } else if (checkDraw()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            }
            else {
                System.out.println("Invalid move. Try again.");
            }
        }
        sc.close();
    }
    private static void switchPlayer() {
        turn = (turn == 'X') ? 'O' : 'X';
    }

    private static void placeMark(int row, int col) {
        board[row][col] = turn;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == turn && board[i][1] == turn && board[i][2] == turn) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == turn && board[1][i] == turn && board[2][i] == turn) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) {
            return true;
        }
        if (board[0][2] == turn && board[1][1] == turn && board[2][0] == turn) {
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // If any empty cell is found, the game is not a draw
                }
            }
        }
        return true; // All cells are filled, and no winner, so it's a draw
    }
}
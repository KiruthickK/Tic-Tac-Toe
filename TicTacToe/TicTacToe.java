package TicTacToe;

import java.util.*;

public class TicTacToe {
    private static final int fixed_size = 3;
    private char player, computer;
    private int steps;
    char board[][];

    public TicTacToe() {
        board = new char[fixed_size][fixed_size];
        steps = 0;
        player = 'x';
        computer = 'o';
        initiateBoard();
    }

    public TicTacToe(char option) {
        board = new char[fixed_size][fixed_size];
        steps = 0;
        if (option == 'x') {
            player = 'x';
            computer = 'o';
        } else {
            computer = 'x';
            player = 'o';
        }
        initiateBoard();
    }

    public void initiateBoard() {
        for (int i = 0; i < fixed_size; i++) {
            for (int j = 0; j < fixed_size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private boolean isIndexValid(int i, int j) {
        return (i < fixed_size && j < fixed_size && i >= 0 && j >= 0);
    }

    private boolean isIndexFree(int i, int j) {
        return board[i][j] == ' ';
    }

    private boolean insertBoard(int i, int j) {
        if (isIndexValid(i, j) && isIndexFree(i, j)) {
            board[i][j] = player;
            return true;
        }
        System.out.println("The position has filled already or invalid index, try another position!");
        return false;
    }

    private void printBoard() {
        System.out.println("Tic Tac Toe now:");
        for (int i = 0; i < fixed_size; i++) {
            for (int j = 0; j < fixed_size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Main function to play the game
     * this function will call the helper functions if needed
     */
    public void StartGame(Scanner sc) {
        boolean GameOver = false;
        while (!GameOver) {
            if (steps == 9) {
                GameOver = true;
                return;
            }
            System.out.print("Enter position to insert 'x'(x , y seperated by spaces):");
            int i = sc.nextInt(), j = sc.nextInt();
            if (!insertBoard(i, j)) {
                continue;
            }
            printBoard();
        }
        // sc.close();
    }
}

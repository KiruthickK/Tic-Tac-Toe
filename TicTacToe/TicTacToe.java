package TicTacToe;

import java.util.*;

public class TicTacToe {
    private static final int fixed_size = 3;
    private char player, computer;
    private boolean GameOver;
    private int steps;
    char board[][];

    public TicTacToe() {
        board = new char[fixed_size][fixed_size];
        steps = 0;
        player = 'x';
        computer = 'o';
        GameOver = false;
        initiateBoard();
    }

    public TicTacToe(char option) {
        board = new char[fixed_size][fixed_size];
        steps = 0;
        GameOver = false;
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
            steps++;
            return true;
        }
        System.out.println("The position has filled already or invalid index, try another position!");
        return false;
    }

    private void printBoard() {
        System.out.println("Tic Tac Toe's current state is:");
        System.out.print("*======*\n");
        for (int i = 0; i < fixed_size; i++) {
            System.out.print("|");
            for (int j = 0; j < fixed_size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.print("*======*\n");
    }

    private void computerMove() {
        int min = 0;
        int max = 2;
        if (steps == 9) {
            return;
        }
        int i = (int) (Math.random() * (max - min + 1) + min), j = (int) (Math.random() * (max - min + 1) + min);
        while (!isIndexFree(i, j)) {
            i = (int) (Math.random() * (max - min + 1) + min);
            j = (int) (Math.random() * (max - min + 1) + min);
        }
        board[i][j] = computer;
        steps++;
    }

    private boolean isGameOver(char lastplay) {
        boolean win = true;
        int i = 0, j = 0;
        // 3 cases to win for a player
        // case 1 : column wise check
        while (j < fixed_size) {
            while (i < fixed_size) {
                if (board[i][j] != lastplay) {
                    win = false;
                    break;
                }
                i++;
            }
            if (win) {
                winningMessage(lastplay);
                return true;
            }
            j++;
        }
        // Case 2: row wise check
        win = true;
        i = j = 0;
        while (i < fixed_size) {
            while (j < fixed_size) {
                if (board[i][j] != lastplay) {
                    win = false;
                    break;
                }
                j++;
            }
            if (win) {
                winningMessage(lastplay);
                return true;
            }
            i++;
        }
        // case 3: diagonal check
        win = true;
        i = j = 0;
        while (i < fixed_size) {
            if (board[i][j] != lastplay) {
                win = false;
                break;
            }
            // since we know board will be n x n matrix we are doing this
            i++;
            j++;
        }
        if (win) {
            winningMessage(lastplay);
            return true;
        }
        return false;

    }

    private void winningMessage(char lastplay) {
        GameOver = true;
        String msg = "";
        if (lastplay == computer) {
            msg = "ah oh, you lose, luck wont help me all the time, you may try again";
        } else {
            msg = "Yay congrats!, you won!! but may be next time i will try my best";
        }
        System.out.println(msg);
    }

    /**
     * Main function to play the game
     * this function will call the helper functions if needed
     */
    public void StartGame(Scanner sc) {
        while (!GameOver) {
            if (steps == 9) {
                System.out.println("\nMaximum moves have been reached!, Game Draw!");
                GameOver = true;
                return;
            }
            System.out.print("Enter position to insert 'x'(x , y seperated by spaces):");
            int i = sc.nextInt(), j = sc.nextInt();
            if (!insertBoard(i, j)) {
                continue;
            }
            printBoard();
            if(isGameOver(player)){
                return;
            }
            computerMove();
            System.out.print("After my move, ");
            printBoard();
            if(isGameOver(computer)){
                return;
            }
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        // game.StartGame(new Scanner(System.in));
        game.computerMove();
    }
}

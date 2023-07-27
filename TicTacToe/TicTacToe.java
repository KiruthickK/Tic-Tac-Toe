package TicTacToe;

import java.util.*;

public class TicTacToe {
    // by default we are playing with 3 x 3, if we want we can change from here
    private static final int fixed_size = 3;
    // computer and player character, can be decided by user
    private char player, computer;
    // flag to check whether the game is over or not
    private boolean GameOver;
    // to acknowledge whether all entries of the board have filled or not
    private int steps;
    char board[][];

    // constructors for the game class, and initiating everything
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

    /**
     * function to initiate the board with empty spaces
     */
    public void initiateBoard() {
        for (int i = 0; i < fixed_size; i++) {
            for (int j = 0; j < fixed_size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * function to check whether the user given index or computer's chosen index by
     * random number is valid or not
     * 
     * @param i
     * @param j
     * @return
     */
    private boolean isIndexValid(int i, int j) {
        return (i < fixed_size && j < fixed_size && i >= 0 && j >= 0);
    }

    /**
     * function to check whether the index of the board the player is going to entry
     * is free or not
     * 
     * @param i
     * @param j
     * @return
     */
    private boolean isIndexFree(int i, int j) {
        return board[i][j] == ' ';
    }

    /**
     * function to insert players character at specified index
     * 
     * @param i
     * @param j
     * @return
     */
    private boolean insertBoard(int i, int j) {
        if (isIndexValid(i, j) && isIndexFree(i, j)) {
            board[i][j] = player;
            steps++;
            return true;
        }
        System.out.println("The position has filled already or invalid index, try another position!");
        return false;
    }

    /**
     * Function to print the status of the board at each move by both player and
     * computer
     */
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

    /**
     * Function to make a move for computer
     */
    private void computerMove() {
        int min = 0;
        int max = 2;
        if (steps == 9) {
            return;
        }
        // we are creating 2 random indeces between 0 - 2
        int i = (int) (Math.random() * (max - min + 1) + min), j = (int) (Math.random() * (max - min + 1) + min);
        // if the index is not valid , we are finding a valid index(free index)
        while (!isIndexFree(i, j)) {
            i = (int) (Math.random() * (max - min + 1) + min);
            j = (int) (Math.random() * (max - min + 1) + min);
        }
        board[i][j] = computer;
        steps++;
    }

    /**
     * Function to check whether the player's latest move stepped for a win or not
     * 
     * @param lastplay
     * @return
     */
    private boolean isGameOver(char lastplay) {
        boolean win;
        int i = 0, j = 0;
        // 4 cases to win for a player -row wise fills, col wise, diagonal x 2
        // case 1 : column wise check
        while (j < fixed_size) {
            i = 0;
            win = true;
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
        i = j = 0;
        while (i < fixed_size) {
            j = 0;
            win = true;
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
        // case 4: cross diagonal check
        win = true;
        i = fixed_size -1;
        j = 0;
        while (i >= 0) {
            if (board[i][j] != lastplay) {
                win = false;
                break;
            }
            // since we know board will be n x n matrix we are doing this
            i--;
            j++;
        }
        if (win) {
            winningMessage(lastplay);
            return true;
        }
        return false;

    }

    /**
     * function to display the winning message
     * 
     * @param lastplay
     */
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
            // if 9 steps done it means the board is full wihtout no one win
            if (steps == 9) {
                System.out.println("\nMaximum moves have been reached!, Game Draw!");
                GameOver = true;
                return;
            }
            System.out.print("Enter position to insert 'x'(x , y seperated by spaces):");
            int i = sc.nextInt(), j = sc.nextInt();
            // inserting user's charcter
            if (!insertBoard(i, j)) {
                continue;
            }
            printBoard();
            // if user's move resulted in winning , it will be acknowledged and game will be
            // over
            if (isGameOver(player)) {
                return;
            }
            // time for computer's turn
            computerMove();
            System.out.print("After my move, ");
            printBoard();
            if (isGameOver(computer)) {
                return;
            }
        }
    }
}

package TicTacToe;

public class TicTacToe {
    private static final int fixed_size = 3;
    char board[][];

    public TicTacToe() {
        board = new char[fixed_size][fixed_size];
        initiateBoard();
    }

    private void initiateBoard() {
        for (int i = 0; i < fixed_size; i++) {
            for (int j = 0; j < fixed_size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
        System.out.println("Tic Tac Toe now:");
        for (int i = 0; i < fixed_size; i++) {
            for (int j = 0; j < fixed_size; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void StartGame() {
        boolean GameOver = false;
        while(GameOver){
            printBoard();
            
        }
    }
}

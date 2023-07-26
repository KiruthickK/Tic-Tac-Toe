import java.util.*;
import TicTacToe.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Tic Tac Toe");
            game.StartGame();
            System.out.print("Do You Want to play again?\n1 for yes, any other number to exit\nYour Opinion:");
            if (sc.nextInt() != 1) {
                break;
            }
        }
        sc.close();
    }
}

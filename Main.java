import java.util.*;
import TicTacToe.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "Welcome to Tic Tac Toe\nWhat you are choosing 'x' or 'o'?(Type x or o any other input will consider to be default settings):");
            char option = sc.next().charAt(0);
            TicTacToe game = new TicTacToe(option);
            game.StartGame(sc);
            System.out.print("Do You Want to play again?\n1 for yes, any other number to exit\nYour Opinion:");
            if (sc.nextInt() != 1) {
                break;
            }
            game.initiateBoard();
        }
        sc.close();
    }
}

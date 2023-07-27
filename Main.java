import java.util.*;
import TicTacToe.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // until user wants to stop, we are running the game again and again
        while (true) {
            System.out.print(
                    "Welcome to Tic Tac Toe\nWhat you are choosing 'x' or 'o'?(Type x or o any other input will consider to be default settings):");
            char option = sc.next().charAt(0);
            TicTacToe game;
            if(option == 'x' || option == 'o'){
                game = new TicTacToe(option);
            } else{
                game = new TicTacToe();
            }
            // one complete game will run after the following function call
            game.StartGame(sc);
            System.out.print("Do You Want to play again?\n1 for yes, any other number to exit\nYour Opinion:");
            // breaking point of the loop
            if (sc.nextInt() != 1) {
                break;
            }
        }
        sc.close();
    }
}

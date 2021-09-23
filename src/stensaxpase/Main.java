package stensaxpase;

import java.util.Scanner;

/**
 * Main class responsible for: creating the Scanner object used for user input in the game, creating the Game object
 * and passing it the scanner and calling the method to display the menu.
 *
 * @author Jan Beszterda
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game newGame = new Game(scanner);
        newGame.showMenu();
    }
}

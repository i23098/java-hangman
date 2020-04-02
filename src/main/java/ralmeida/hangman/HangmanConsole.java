package ralmeida.hangman;

import java.util.Scanner;

/**
 * Allows Hangman to be played in the Console.
 * 
 * @author Ricardo Almeida
 */
public class HangmanConsole {
    private final HangmanGame game;
    private final Scanner consoleScanner;
    
    /**
     * Instantiates a new HangmanConsoleUI instance.
     */
    public HangmanConsole(String word) {
        this.game = new HangmanGame(word);
        this.consoleScanner = new Scanner(System.in);
    }
    
    /**
     * Plays the game, interacting with the user until the game is over.
     */
    public void play() {
        System.out.println("Welcome to Hangman!");
        
        System.out.printf("Errors: %d\nCurrent guess: %s\n", game.getNumErrors(), game.getCurrentGuess());
        while (!game.isGameOver()) {
            char letter = readLetter();
            
            game.request(letter);
            
            System.out.printf("Errors: %d\nCurrent guess: %s\n", game.getNumErrors(), game.getCurrentGuess());
        }
        
        System.out.println("Game Over!");
        if (game.isWin()) {
            System.out.println("Congratulations! You Won!!!");
        } else {
            System.out.println("You Loose!!!");
        }
    }
    
    /**
     * Reads a letter from the user that can be played (i.e. A letter from A-Z that has not been requested yet).
     * 
     * @return the letter to be played
     * 
     * @see HangmanGame#isRequested(char)
     */
    char readLetter() {
        while (true) {
            System.out.print("\nInsert letter to play: ");
            String line = consoleScanner.nextLine();
            if (line.length() != 1) {
                System.err.println("Please just insert the letter to play");
                continue;
            }
            
            char letter = line.charAt(0);
            letter = Character.toUpperCase(letter);
            
            if (letter < 'A' || letter > 'Z') {
                System.err.println("Please insert a valid letter to play");
                continue;
            }
            
            if (game.isRequested(letter)) {
                System.err.printf("Letter [%c] as already been requested!\n", letter);
                continue;
            }
            
            return letter;
        }
    }
    
    public static void main(String[] args) {
        HangmanConsole hangmanConsole = new HangmanConsole("hangman");
        hangmanConsole.play();
    }
}

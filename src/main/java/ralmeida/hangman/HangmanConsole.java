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
        drawWelcome();
        drawCurrentStatus();
        
        while (!game.isGameOver()) {
            char letter = readLetter();
            
            game.request(letter);
            
            drawCurrentStatus();
        }
        
        drawGameOver();
    }
    
    /**
     * Draw "Welcome to hangman" message.
     */
    void drawWelcome() {
        System.out.println("              _                             _\n" +
                "             | |                           | |\n" +
                "__      _____| | ___ ___  _ __ ___   ___   | |_ ___\n" +
                "\\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\  | __/ _ \\\n" +
                " \\ V  V /  __/ | (_| (_) | | | | | |  __/  | || (_) |\n" +
                "  \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|   \\__\\___/\n" +
                "   _                                             \n" +
                "  | |                                            \n" +
                "  | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" +
                "  | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" +
                "  | | | | (_| | | | | (_| | | | | | | (_| | | | |\n" +
                "  |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" +
                "                      __/ |                      \n" +
                "                     |___/ \n");
    }

    /**
     * Draw current hangman status.
     */
    void drawCurrentStatus() {
        int nErrors = game.getNumErrors();
        String currentGuess = StringUtil.spaceChars(game.getCurrentGuess());
        
        // Taken from http://ascii.co.uk/art/hangman
        System.out.println("   _________");
        System.out.println("   |/      |");
        System.out.printf("   |%s\n", (nErrors == 0 ? "" : "      (_)"));
        System.out.print("   |");
        if (nErrors > 1) {
            System.out.printf("      %c|%s", (nErrors < 5 ? ' ' : '\\'), (nErrors == 6 ? "/" : ""));
        }
        System.out.println();
        System.out.printf("   |       %c          %s\n", (nErrors > 1 ? '|' : ' '), currentGuess);
        System.out.printf("   |%s%s\n", (nErrors > 2 ? "      /" : ""), (nErrors > 3 ? " \\" : ""));
        System.out.println("   |");
        System.out.println("___|___");
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
    
    /**
     * Draws final message.
     */
    void drawGameOver() {
        System.out.println("\n" +
                "  __ _  __ _ _ __ ___   ___    _____   _____ _ __\n" +
                " / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|\n" +
                "| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |\n" +
                " \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|\n" +
                "  __/ |\n" +
                " |___/\n");
        
        if (game.isWin()) {
            System.out.println("\nCongratulations! You Won!!!");
        } else {
            System.out.println("\nYou Loose!!!");
        }
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Must specify the word as argument!");
            return;
        }
        
        HangmanConsole hangmanConsole = new HangmanConsole(args[0]);
        hangmanConsole.play();
    }
}

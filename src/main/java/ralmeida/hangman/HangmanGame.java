package ralmeida.hangman;

import java.util.Arrays;

/**
 * This class represents one Hangman game.
 * 
 * @author Ricardo Almeida
 */
public class HangmanGame {
    /** Number of errors that the game is over */
    public static final int NUM_ERRORS_DEAD = 6;
    /** Char representing a letter that needs to be guessed */
    public static final char NOT_GUESSED_CHAR = '_';

    /** The word that the player has to guess */
    private final char[] word;
    /** The current guessed */
    private char[] currentGuess;
    /** The letters */
    private boolean[] requestedLetters;
    /** number of errors */
    private int numErrors;

    /**
     * Instantiates a new Hangman Game.
     * 
     * @param word the word to be guessed.
     */
    public HangmanGame(String word) {
        this.word = word.toUpperCase().toCharArray();
        this.currentGuess = new char[word.length()];
        Arrays.fill(currentGuess, NOT_GUESSED_CHAR);
        this.requestedLetters = new boolean[26];
        this.numErrors = 0;
    }

    /**
     * Checks if a given letter was already requested.
     * 
     * @param letter the letter to check.
     * 
     * @return true if the letter was already requested, false otherwise.
     * 
     * @throws IllegalArgumentException if not alphabetic char.
     */
    public boolean isRequested(char letter) {
        char c = Character.toUpperCase(letter);
        if (c < 'A' || c > 'Z') {
            throw new IllegalArgumentException("Invalid letter [" + letter + "]");
        }

        return requestedLetters[c - 'A'];
    }

    /**
     * Checks if the game is over (either won or lost).
     * 
     * @return true if game is over, false otherwise.
     */
    public boolean isGameOver() {
        return (numErrors == NUM_ERRORS_DEAD) || isWin();
    }

    /**
     * Checks if the game finished with a win.
     * 
     * @return true if game is finished and won, false otherwise.
     */
    public boolean isWin() {
        for (char c : currentGuess) {
            if (c == NOT_GUESSED_CHAR) {
                return false;
            }
        }

        return true;
    }

    /**
     * Request a letter. If letter exists, guess word is updated. Otherwise, errors are increased.
     * 
     * @param letter the letter to be requested.
     * 
     * @return false if request was not accepted (i.e. letter previously requested), true otherwise.
     * 
     * @throws IllegalArgumentException if not alphabetic char.
     */
    public boolean request(char letter) {
        char c = Character.toUpperCase(letter);
        if (c < 'A' || c > 'Z') {
            throw new IllegalArgumentException("Invalid letter [" + letter + "]");
        }

        if (requestedLetters[c - 'A']) {
            return false; // already requested...
        }

        // Update if guessed
        boolean exists = false;
        for (int i = 0; i < word.length; i++) {
            if (word[i] == c) {
                currentGuess[i] = c;
                exists = true;
            }
        }

        if (!exists) {
            numErrors++;
        }
        requestedLetters[c - 'A'] = true;
        
        return true;
    }
    
    /**
     * Return the number of wrong guesses.
     * 
     * @return number of wrong guesses.
     */
    public int getNumErrors() {
        return numErrors;
    }
    
    /**
     * Returns current guess. Letters not yet guessed are represented using {@link #NOT_GUESSED_CHAR}.
     * 
     * @return String representing the current guessed word status.
     */
    public String getCurrentGuess() {
        return new String(currentGuess);
    }
}

package ralmeida.hangman;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is Immutable and represents a Hangman game.
 * 
 * @author Ricardo Almeida
 */
public class HangmanGame {
    /** Number of errors that the game is over */
    public static final int NUM_ERRORS_DEAD = 6;
    /** Char representing a letter that needs to be guessed */
    public static final char NOT_GUESSED_CHAR = '_';

    /** The word that the player has to guess (non-normalized, upper case) */
    private final String originalWord;
    /** The word that the player has to guess (normalized) */
    private final transient String word;
    /** The current guessed */
    private final transient String currentGuess;
    /** The letters */
    private final Set<Character> requestedLetters;
    /** number of errors */
    private final transient int numErrors;
    /** true if won, false otherwise */
    private final transient boolean win;

    /**
     * Instantiates a newly started Hangman Game.
     * 
     * @param word the word to be guessed.
     * 
     * @throws IllegalArgumentException if can't represent word with A to Z letters only.
     */
    public HangmanGame(String word) {
        this(word, Collections.emptySet());
    }
    
    /**
     * Instantiates a Hangman Game.
     * 
     * @param word the word to be guessed.
     * @param requestedLetters requested letters (Letters MUST be uppercase)
     * 
     * @throws IllegalArgumentException if can't represent word with A to Z letters only or if requested letters aren't A to Z letters only
     */
    private HangmanGame(String word, Set<Character> requestedLetters) {
        String originalWord = word;
        word = StringUtil.normalize(word);
        if (word.length() != originalWord.length()) {
            throw new IllegalArgumentException("Unable to normalize [" + originalWord + "]");
        }
        if (word.contains("-") || word.contains(" ")) {
            throw new IllegalArgumentException("Only simple word supported!");
        }
        
        for (char c : requestedLetters) {
            if (c < 'A' || c > 'Z') {
                throw new IllegalArgumentException("Invalid letter [" + c + "]");
            }
        }
        
        this.originalWord = originalWord.toUpperCase();
        this.word = word.toUpperCase();
        this.requestedLetters = Collections.unmodifiableSet(requestedLetters);
        
        StringBuilder currentGuess = new StringBuilder(word.length());
        Set<Character> guessedLetters = new HashSet<>();
        boolean notGuessed = false;
        for (int i = 0; i < this.word.length(); i++) {
            char c = this.word.charAt(i);
            
            if (requestedLetters.contains(c)) {
                guessedLetters.add(c);
                currentGuess.append(c);
            } else {
                currentGuess.append(NOT_GUESSED_CHAR);
                notGuessed = true;
            }
        }
        
        this.currentGuess = currentGuess.toString();
        this.numErrors = requestedLetters.size() - guessedLetters.size();
        this.win = !notGuessed;
        
        if (numErrors > NUM_ERRORS_DEAD) {
            throw new IllegalArgumentException("Too many requested wrong letters!");
        }
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

        return requestedLetters.contains(c);
    }

    /**
     * Checks if the game is over (either won or lost).
     * 
     * @return true if game is over, false otherwise.
     */
    public boolean isGameOver() {
        return (numErrors == NUM_ERRORS_DEAD) || win;
    }

    /**
     * Checks if the game finished with a win.
     * 
     * @return true if game is finished and won, false otherwise.
     */
    public boolean isWin() {
        return win;
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
    public HangmanGame withRequested(char letter) {
        char c = Character.toUpperCase(letter);
        if (c < 'A' || c > 'Z') {
            throw new IllegalArgumentException("Invalid letter [" + letter + "]");
        }
        
        if (requestedLetters.contains(c)) {
            return this;
        }
        
        Set<Character> requestedLetters = new HashSet<>(this.requestedLetters);
        requestedLetters.add(c);
        
        return new HangmanGame(originalWord, requestedLetters);
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

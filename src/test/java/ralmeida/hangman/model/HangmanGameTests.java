package ralmeida.hangman.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HangmanGameTests {
    @Test
    void test1() {
        HangmanGame game = new HangmanGame("hello");
        assertEquals(0, game.getNumErrors());
        assertEquals("_____", game.getCurrentGuess());
        assertFalse(game.isGameOver());
        
        game = game.withRequested('a');
        assertEquals(1, game.getNumErrors());
        assertEquals("_____", game.getCurrentGuess());
        assertFalse(game.isGameOver());
        
        game = game.withRequested('l');
        assertEquals(1, game.getNumErrors());
        assertEquals("__LL_", game.getCurrentGuess());
        assertFalse(game.isGameOver());
        
        game = game.withRequested('a');
        assertEquals(1, game.getNumErrors());
        assertEquals("__LL_", game.getCurrentGuess());
        assertFalse(game.isGameOver());
        
        game = game.withRequested('o');
        assertEquals(1, game.getNumErrors());
        assertEquals("__LLO", game.getCurrentGuess());
        assertFalse(game.isGameOver());
        
        game = game.withRequested('z');
        assertEquals(2, game.getNumErrors());
        assertEquals("__LLO", game.getCurrentGuess());
        assertFalse(game.isGameOver());
        
        game = game.withRequested('h');
        game = game.withRequested('e');
        assertEquals(2, game.getNumErrors());
        assertEquals("HELLO", game.getCurrentGuess());
        assertTrue(game.isGameOver());
        assertTrue(game.isWin());
    }
}

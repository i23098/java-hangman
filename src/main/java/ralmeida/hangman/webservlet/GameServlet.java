package ralmeida.hangman.webservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ralmeida.hangman.dao.WordDAO;
import ralmeida.hangman.model.HangmanGame;

/**
 * This servlet handles hangman game requests.
 * 
 * @author ralmeida
 */
@WebServlet("/game")
public class GameServlet extends HangmanServlet {
    private static final String LETTER_PARAM = "letter";
    
    private static final String GAME_ATTR = "hangmanGame";
    
    private static final String GAME_JSP = "/WEB-INF/jsp/game.jsp";
    
    /**
     * Handles new game or letter request, based on the parametar passed.
     * If "word" is passed, starts a new game with that word.
     * If "letter" is passed, requests letter in existing game in session.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String letter = request.getParameter(LETTER_PARAM);
        if (letter == null) {
            startNewGame(request, response);
            return;
        }
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(GAME_ATTR) == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        if (letter.length() == 1) {
            play(letter.charAt(0), request, response);
            return;
        }
        
        response.sendRedirect("index.jsp");
    }
    
    /**
     * Starts a new hangman game. Game is stored in session.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * 
     * @throws ServletException
     * @throws IOException
     */
    private void startNewGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String word;
        try (Connection con = getConnection()) {
            WordDAO dao = new WordDAO(con);
            
            word = dao.getRandomWord();
        } catch (SQLException e) {
            throw new ServletException("Error loading word from DB", e);
        }
        
        HangmanGame hangmanGame = new HangmanGame(word);
        
        request.getSession().setAttribute(GAME_ATTR, hangmanGame);
        
        request.getRequestDispatcher(GAME_JSP).forward(request, response);
    }
    
    /**
     * Requests a letter in an existing (in session) hangman game.
     * 
     * @param letter letter to request
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * 
     * @throws ServletException
     * @throws IOException
     */
    private void play(char letter, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HangmanGame hangmanGame = (HangmanGame) session.getAttribute(GAME_ATTR);
        
        hangmanGame = hangmanGame.withRequested(letter);
        
        session.setAttribute(GAME_ATTR, hangmanGame);
        
        request.getRequestDispatcher(GAME_JSP).forward(request, response);
    }
}

package ralmeida.hangman.webservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ralmeida.hangman.dao.WordDAO;

@WebServlet("/admin")
public class AdminWordServlet extends HangmanServlet {
    private static final String ADMIN_JSP = "/WEB-INF/jsp/admin.jsp";    
    private static final String WORDS_ATTR = "words";
    private static final String WORD_PARAM = "word";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> words;
        try (Connection con = getConnection()) {
            WordDAO dao = new WordDAO(con);
            
            words = dao.getAllWords();
        } catch (SQLException e) {
            throw new ServletException("Error loading words from DB", e);
        }
        
        request.setAttribute(WORDS_ATTR, words);
        
        request.getRequestDispatcher(ADMIN_JSP).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String word = request.getParameter(WORD_PARAM);
        
        List<String> words;
        try (Connection con = getConnection()) {
            WordDAO dao = new WordDAO(con);
            
            dao.addWord(word);
            words = dao.getAllWords();
        } catch (SQLException e) {
            throw new ServletException("Error accessing DB", e);
        }
        
        request.setAttribute(WORDS_ATTR, words);
        
        request.getRequestDispatcher(ADMIN_JSP).forward(request, response);
    }
}

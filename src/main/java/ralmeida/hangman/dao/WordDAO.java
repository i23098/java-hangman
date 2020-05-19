package ralmeida.hangman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data Access Object to access words in DB
 * 
 * @author ralmeida
 */
public class WordDAO {
    private static final String GET_NUM_WORDS_SQL =
            "SELECT COUNT(*)"
           + " FROM word";
    private static final String GET_WORD_ROW_N_SQL =
            "SELECT word"
           + " FROM word"
          + " LIMIT 1"
         + " OFFSET ?";
    
    private final Connection con;
    
    /**
     * Instantiates a new WordDAO
     * 
     * @param con DB connection
     */
    public WordDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Gets a random word from the DB
     * 
     * @return word
     * 
     * @throws SQLException If an error occurs accessing the DB
     */
    public String getRandomWord() throws SQLException {
        int nWords;
        try (
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(GET_NUM_WORDS_SQL);
        ) {
            rs.next();
            nWords = rs.getInt(1);
        }
        
        if (nWords == 0) {
            throw new SQLException("Table WORD is empty!");
        }
            
        String word;
        try (PreparedStatement ps = con.prepareStatement(GET_WORD_ROW_N_SQL)) {
            ps.setInt(1, (int) Math.random() * nWords);
            
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                word = rs.getString(1);
            }
        }
        
        return word;
    }
}

package ralmeida.hangman.webservlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public abstract class HangmanServlet extends HttpServlet {
    private static final String DS_JNDI_NAME = "java:comp/env/jdbc/hangmanDS";
    private static final int TRANSACTION_ISOLATION_LEVEL = Connection.TRANSACTION_READ_COMMITTED;
    
    protected Connection getConnection() throws ServletException, SQLException {
        DataSource ds;
        try {
            ds = (DataSource) new InitialContext().lookup(DS_JNDI_NAME);
        } catch (NamingException e) {
            throw new ServletException("Error doing JNDI lookup", e);
        }
        
        Connection con = ds.getConnection();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
        } catch (SQLException e) {
            try {
                con.close();
            } catch (Exception ex) {
                e.addSuppressed(ex);
            }
            
            throw e;
        }
        
        return con;
    }
}

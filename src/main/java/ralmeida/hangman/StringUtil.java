package ralmeida.hangman;

/**
 * Utility class for String operations
 * 
 * @author Ricardo Almeida
 */
public class StringUtil {
    /* private constructor so it can't be instantiated */
    private StringUtil() {
    }
    
    /**
     * Inserts a space between each character of a String. For example, "abc" becomes "a b c".
     * 
     * @param str
     * 
     * @return String with space inserted between each character
     */
    public static String spaceChars(String str) {
        StringBuilder sb = new StringBuilder(str.length()*2 - 1);
        for (int i=0; i<str.length(); i++) {
            sb.append(str.charAt(i));
            if (i < str.length() - 1) {
                sb.append(' ');
            }
        }
        
        return sb.toString();
    }
}

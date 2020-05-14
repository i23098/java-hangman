package ralmeida.hangman.util;

import java.text.Normalizer;

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
     * 
     * @throws NullPointerException if str is null
     */
    public static String spaceChars(String str) {
        if (str.equals("")) { // throws NullPointerException if str == null
            return "";
        }
        
        StringBuilder sb = new StringBuilder(str.length()*2 - 1);
        for (int i=0; i<str.length(); i++) {
            sb.append(str.charAt(i));
            if (i < str.length() - 1) {
                sb.append(' ');
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Normalize a string by removing diacritical marks and non-A to Z letter and converts it to uppercase.
     * 
     * @param str the string to normalize
     * 
     * @return Normalized string
     */
    public static String normalize(String str) {
        // Remove diacritical marks
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        // Converts to uppercase
        normalized = normalized.toUpperCase();
        // Removes non-letters chars
        normalized = normalized.replaceAll("[^A-Z- ]", "");
        
        return normalized;
    }
}

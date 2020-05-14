package ralmeida.hangman.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilTests {
    @Test
    void testSpaceChars() {
        assertThrows(NullPointerException.class, () -> StringUtil.spaceChars(null));
        
        assertEquals("", StringUtil.spaceChars(""));
        assertEquals("a", StringUtil.spaceChars("a"));
        assertEquals("a b c", StringUtil.spaceChars("abc"));
    }
    
    @Test
    void testNormalize() {
        assertEquals("A", StringUtil.normalize("á"));
        assertEquals("BB", StringUtil.normalize("b́b̧"));
        assertEquals("CCCCCCCCC", StringUtil.normalize("c̃c̈c̊Çççćĉč"));
        assertEquals("DDDDDD", StringUtil.normalize("d̂d̃d̈ďdḑ"));
        assertEquals("E", StringUtil.normalize("é"));
        assertEquals("FF", StringUtil.normalize("f̈f"));
        assertEquals("GGGGGGGG", StringUtil.normalize("g̀ǵg̃g̈gģĝǧ"));
        assertEquals("HH", StringUtil.normalize("ḧĥ"));
        assertEquals("I", StringUtil.normalize("í"));
        assertEquals("JJ", StringUtil.normalize("j̈j"));
        assertEquals("KKKKK", StringUtil.normalize("ḱk̂k̈kǩ"));
        assertEquals("LLL", StringUtil.normalize("l̂l̃l̈"));
        assertEquals("MMMMMM", StringUtil.normalize("m̀m̂m̃m̈m̊m̌"));
        assertEquals("NNNNNNNNN", StringUtil.normalize("ǹn̂n̈n̊nńņňñ"));
        assertEquals("O", StringUtil.normalize("ó"));
        assertEquals("PP", StringUtil.normalize("p̂p̈"));
        assertEquals("RRRRRRR", StringUtil.normalize("r̀r̂r̃r̈rŕř"));
        assertEquals("SSSSSSSSSSS", StringUtil.normalize("s̀s̃s̈s̊ssśŝŞşš"));
        assertEquals("TTTT", StringUtil.normalize("t̀t̂ẗţ"));
        assertEquals("U", StringUtil.normalize("ú"));
        assertEquals("VVVV", StringUtil.normalize("v̂v̈vv"));
        assertEquals("W", StringUtil.normalize("ẅ"));
        assertEquals("X", StringUtil.normalize("ẍ"));
        assertEquals("YYYYYYYY", StringUtil.normalize("ỳỹẙyy̎ýÿŷ"));
    }
}
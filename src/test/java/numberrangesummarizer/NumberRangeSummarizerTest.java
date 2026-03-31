package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lerato Kgomoeswana
 * */
public class NumberRangeSummarizerTest {

    private final NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

    @Test
    void testGivenInput() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String expectedResult = "1, 3, 6-8, 12-15, 21-24, 31";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void testEmptyInput() {
        String input = "";
        String expectedResult = "";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

     @Test
    void testNullInput() {
        String input = null;
        String expectedResult = "";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSingleNumberInput() {
        String input = "8";
        String expectedResult = "8";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testNonSequentialInput() {
        String input = "2,4,6,8";
        String expectedResult = "2, 4, 6, 8";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSequentialInput() {
        String input = "56,57,58,59,60,61";
        String expectedResult = "56-61";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testNegativeNumbersInput() {
        String input = "-7,-3,-2,-1,16,17,18,66";
        String expectedResult = "-7, -3--1, 16-18, 66";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

     @Test
    void testWhitespacesInput() {
        String input = "4 ,5,   6 ,9 , 20,21 ";
        String expectedResult = "4-6, 9, 20-21";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testUnsortedInput() {
        String input = "17,15,21,16,20,19";
        String expectedResult = "15-17, 19-21";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testDuplicates() {
        String input = "36,37,37,40,40,41,36";
        String expectedResult = "36-37, 40-41";
        String actualResult = summarizer.summarizeCollection(summarizer.collect(input));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testNotCommaSeparated() {
        String input = "56;57;58;59;60;61";

        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect(input);
        });
    }

    @Test
    void testAlphabetInput() {
        String input = "l,e,r,a,t,o";

        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect(input);
        });
    }

    @Test
    void testAlphaNumericInput() {
        String input = "l,6,r,2,t,8";

        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect(input);
        });
    }

    @Test
    void testDecimalInput() {
        String input = "6,8.5,10,11.1,17";

        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect(input);
        });
    }

    @Test
    public void testSpecialCharacterInput() {
        String input = "?,3,@,66,#,!";

        assertThrows(IllegalArgumentException.class, () -> {
            summarizer.collect(input);
        });
    }

}

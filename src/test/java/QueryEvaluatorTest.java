import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class QueryEvaluatorTest {

    @Test
    void filterQueries(){
        List<List<String>> testing = asList(
                asList("Double", "Star", "Robert", "Heintein"),
                asList("451-P3669-060"),
                asList("ROGER", "ZELAZNY"),
                asList("U6124", "75¢"),
                asList("ANALOG", "2", "John", "Campbell"),
                asList("THE", "THREE", "MUSKETEERS"),
                asList("PYRAMID", "BOOKS")
        );

        List<List<String>> expected = asList(
                asList("Double", "Star", "Robert", "Heintein"),
                asList("ROGER", "ZELAZNY"),
                asList("ANALOG", "John", "Campbell"),
                asList("THE", "THREE", "MUSKETEERS"),
                asList("PYRAMID")
        );
        assertEquals(QueryEvaluator.queryEvaluator(testing), expected);
    }

    @Test
    void excludeWordsAssertion(){
        List<String> example = asList("ANALOG", "2", "Edited", "John", "Campbell", "1977");
        List<String> expected = asList("ANALOG", "John", "Campbell");
        assertEquals(QueryEvaluator.removeOffendingString(example), expected);
    }

    @Test
    void containsNumbersPositive() {
        assertAll(
                () -> assertTrue(QueryEvaluator.containsNumbers("1234")),
                () -> assertTrue(QueryEvaluator.containsNumbers("hello1test"))
        );
    }

    @Test
    void containsNumbersNegative() {
        assertAll(
                () -> assertFalse(QueryEvaluator.containsNumbers("hello")),
                () -> assertFalse(QueryEvaluator.containsNumbers("test"))
        );
    }

    @Test
    void isBannedWordPositive(){
        assertAll(
                () -> assertTrue(QueryEvaluator.isBannedWord("bestselling")),
                () -> assertTrue(QueryEvaluator.isBannedWord("author")),
                () -> assertTrue(QueryEvaluator.isBannedWord("books"))
        );
    }

    @Test
    void isBannedWordNegative(){
        assertAll(
                () -> assertFalse(QueryEvaluator.isBannedWord("tomato")),
                () -> assertFalse(QueryEvaluator.isBannedWord("dance")),
                () -> assertFalse(QueryEvaluator.isBannedWord("pencil"))
        );
    }
}
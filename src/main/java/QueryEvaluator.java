import java.util.*;
import java.util.stream.Collectors;

public class QueryEvaluator {

    /* Currently: Throwing out all words with numbers or with specific words
    *
    *  This will throw out some titles/parts of titles
    *
    *  words ("bestselling", "author", "fiction", "fantasy", "edited", "book", "books", "u.s.", "canada", "library")
    *  publishers ("bantam", "harper", "tor")
    *
    *  Needs refining
    * */

    public static List<List<String>> queryEvaluator(List<List<String>> queries){
        // Commented out code throws out query if it contains any offending string
        //return queries.stream().filter(QueryEvaluator::include).collect(Collectors.toList());
        return queries.stream().map(query -> removeOffendingString(query)).filter(query -> query.size() > 0).collect(Collectors.toList());
    }

    public static boolean include(List<String> query){
        return !query.stream().anyMatch(str -> containsNumbers(str) || isBannedWord(str) || isPublisher(str));
    }

    public static List<String> removeOffendingString(List<String> query){
        return query.stream()
                .filter(str -> !containsNumbers(str) && !isBannedWord(str) && !isPublisher(str))
                .collect(Collectors.toList());
    }

    public static boolean containsNumbers(String word){
        return !word.equals(word.replaceAll("[*0-9]", ""));
    }

    public static boolean isBannedWord(String word){
        Set<String> bannedWords = new HashSet<String>(Arrays.asList("bestselling", "author", "fiction", "fantasy", "edited", "book", "books", "u.s.", "canada", "library"));
        return bannedWords.contains(word.toLowerCase());
    }

    public static boolean isPublisher(String word){
        Set<String> publishers = new HashSet<String>(Arrays.asList("bantam", "harper", "tor"));
        return publishers.contains(word.toLowerCase());
    }
}

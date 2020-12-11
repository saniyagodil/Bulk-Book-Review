import java.util.*;

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
        List<List<String>> filteredQueries = new ArrayList<List<String>>();

        for(List<String> query : queries){
            if(include(query)){
                filteredQueries.add(query);
            }
        }
        return filteredQueries;
    }

    public static boolean include(List<String> query){
        for(String str : query){
            if(containsNumbers(str) || isBannedWord(str) || isPublisher(str)){
                return false;
            }
        }
        return true;
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

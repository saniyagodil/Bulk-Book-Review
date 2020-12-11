import java.util.*;

public class QueryEvaluator {

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
        if(isNewYorkTimes(query)){
            return false;
        }
        for(String str : query){
            if(containsNumbers(str) || containsBannedWord(str)){
                return false;
            }
        }
        return true;
    }

    public static boolean containsNumbers(String word){
        return !word.equals(word.replaceAll("[*0-9]", ""));
    }

    public static boolean containsBannedWord(String word){
        Set<String> bannedWords = new HashSet<String>(Arrays.asList("bestselling", "author", "fiction", "fantasy", "by", "edited", "book", "books", "bantam", "harper", "u.s.", "canada"));
        return bannedWords.contains(word.toLowerCase());
    }

    public static boolean isNewYorkTimes(List<String> query){
        return query.equals(Arrays.asList("NEW", "YORK", "TIMES", "BESTSELLING", "AUTHOR"));
    }


}

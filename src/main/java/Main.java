import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String get_api_key() throws FileNotFoundException {
        // Create API_KEY.txt file in parent dir, with Google API Key
        Scanner scanner = new Scanner(new File("..\\API_KEY.txt"));
        return scanner.nextLine();
    }

    public static void main(String[] args) throws IOException {
        BookService query_tool = new BookService(get_api_key());
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("Far", "Call", "Gordon", "Dickson"),
                Arrays.asList("Moon", "Mutiny", "Lester")
        );
        List<List<Book>> results = query_tool.processQueries(queries);
        System.out.print(results);
    }

}
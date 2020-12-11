import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String get_api_key() throws FileNotFoundException {
        // Create API_KEY.txt file in parent dir, with Google API Key
        Scanner scanner = new Scanner(new File("..\\API_KEY.txt"));
        return scanner.nextLine();
    }

    public static void main(String[] args) throws IOException {
        List<List<String>> queries2 = DetectText.detectText();

        BookService query_tool = new BookService(get_api_key());
//        List<List<String>> queries = Arrays.asList(
//                Arrays.asList("Wings", "Of", "Omen"),
//                Arrays.asList("The", "Way", "Of", "Kings")
//        );
//        List<List<Book>> results = query_tool.processQueries(queries);

        List<List<Book>> results = query_tool.processQueries(queries2);
        System.out.print(results);
    }

}
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
        // Add image project directory and update relative path
        String filePath = "image.jpg";

        List<List<String>> queries = DetectText.detectText(filePath);
        System.out.println(queries);
        List<List<String>> filteredQueries = QueryEvaluator.queryEvaluator(queries);
        System.out.println(filteredQueries);
        BookService query_tool = new BookService(get_api_key());
        List<List<Book>> results = query_tool.processQueries(filteredQueries);
        System.out.print(results);
    }

}
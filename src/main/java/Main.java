import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> terms = Arrays.asList(new String[]{"Brandon", "Sanderson"});
        List<Book> books = BookService.getBooks(terms);
        System.out.print(books);
    }


}
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

public class BookService {
    static String SCHEME = "https";
    static String HOST = "www.googleapis.com";
    static String API_NAME = "books";
    static String API_VERSION = "v1";
    static String RESOURCE_PATH = "volumes";

    private String NUM_RESULTS;
    private String API_KEY;

    public BookService(String API_KEY) {
        this.API_KEY = API_KEY;
        this.NUM_RESULTS = "1";
    }

    public BookService(String API_KEY, Integer num_results) {
        this.API_KEY = API_KEY;
        this.NUM_RESULTS = String.valueOf(num_results);
    }

    public List<List<Book>> processQueries(List<List<String>> queries) throws IOException{
        List<List<Book>> results = new ArrayList<List<Book>>();
        for(List<String> query : queries){
            results.add(getBooks(query));
        }
        return results;
    }

    public List<Book> getBooks(List<String> queryTerms) throws IOException {
        String query = String.join(" ", queryTerms);
        HttpUrl URL = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment(API_NAME)
                .addPathSegment(API_VERSION)
                .addPathSegment(RESOURCE_PATH)
                .addQueryParameter("maxResults", NUM_RESULTS)
                .addQueryParameter("q", query)
                .addQueryParameter("key", API_KEY)
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .build(); // defaults to GET

        Response response = client.newCall(request).execute();
        ResponseBody rbody = response.body();
        String str = rbody.string();

        Gson g = new Gson();
        JsonObject jo = g.fromJson(str, JsonObject.class);
        Integer resultsCount = jo.get("totalItems").getAsInt();
        JsonArray jarray = jo.getAsJsonArray("items");
        List<Book> books = new ArrayList<Book>();

        for(JsonElement ele : jarray){
            books.add(deserializeBookData(ele));
        }
        return books;
    }

    public static Book deserializeBookData(JsonElement je){
        Book book = new Book();
        JsonObject thingObj = je.getAsJsonObject();
        JsonObject volumeInfo = thingObj.get("volumeInfo").getAsJsonObject();
        JsonElement avgRatings = tryToGet(volumeInfo,"averageRating");
        JsonArray authors = volumeInfo.getAsJsonArray("authors");
        JsonElement ratingsCount = tryToGet(volumeInfo,"ratingsCount");
        JsonElement imageLinks = tryToGet(volumeInfo, "imageLinks");
        JsonElement description = tryToGet(volumeInfo, "description");
        JsonElement title = tryToGet(volumeInfo, "title");
        JsonElement id = tryToGet(thingObj, "id");
        JsonElement publisher = tryToGet(volumeInfo, "publisher");
        JsonElement publishedDate = tryToGet(volumeInfo, "publishedDate");
        List<String> authorStrings = new ArrayList<String>();
        if(authors != null){
            for(JsonElement ele : authors){
                if(ele != null){
                    authorStrings.add(ele.toString());
                }
            }
            book.setAuthors(authorStrings);
        }

        if(imageLinks != null){
            JsonElement thumbnail = tryToGet(imageLinks.getAsJsonObject(), "thumbnail");
            if(thumbnail != null){
                book.setImageLinks(thumbnail.getAsString());
            }
        }
        if(description != null){
            book.setDescription(description.getAsString());
        }
        if(avgRatings != null){
            book.setAverageRatings(avgRatings.getAsLong());
        }
        if(ratingsCount != null){
            book.setRatingsCount(ratingsCount.getAsInt());
        }
        if(title != null){
            book.setTitle(title.getAsString());
        }
        if(id != null){
            book.setId(id.getAsString());
        }
        if(publisher != null){
            book.setPublisher(publisher.getAsString());
        }
        if(publishedDate != null){
            book.setPublishedDate(publishedDate.getAsString());
        }
        return book;
    }

    public static JsonElement tryToGet(JsonObject jo, String key) {
        if(jo.has(key))
            return jo.get(key);
        return null;
    }

}

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    public static List<Book> getBooks(List<String> queryTerms) throws IOException {
        String SCHEME = "https";
        String HOST = "www.googleapis.com";
        String PATH_SEGMENT_1 = "books";
        String PATH_SEGMENT_2 = "v1";
        String PATH_SEGMENT_3 = "volumes";
        String API_KEY = "Your API Key Here";
        String query = "Brandon Sanderson";
        
//        String query1 = String.join(" ", queryTerms);
        HttpUrl URL = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment(PATH_SEGMENT_1)
                .addPathSegment(PATH_SEGMENT_2)
                .addPathSegment(PATH_SEGMENT_3)
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
        JsonObject job = g.fromJson(str, JsonObject.class);
        Integer resultsCount = job.get("totalItems").getAsInt();
        JsonArray jarray = job.getAsJsonArray("items");
        List<Book> books = new ArrayList<Book>();

        for(JsonElement thing : jarray){
            Book book = new Book();
            JsonObject thingObj = thing.getAsJsonObject();
            JsonObject volumeInfo = thingObj.get("volumeInfo").getAsJsonObject();
            JsonElement avgRatings = tryToGet(volumeInfo,"averageRating");
            JsonArray authors = volumeInfo.getAsJsonArray("authors");
            JsonElement ratingsCount = tryToGet(volumeInfo,"ratingsCount");
            JsonElement imageLinks = tryToGet(volumeInfo, "imageLinks");
            JsonElement description = tryToGet(volumeInfo, "description");
            JsonElement title = tryToGet(volumeInfo, "title");
            JsonElement id = tryToGet(thingObj, "id");
            List<String> authorStrings = new ArrayList<String>();
            for(JsonElement ele : authors){
                if(ele != null){
                    authorStrings.add(ele.toString());
                }
            }
            book.setAuthors(authorStrings);
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
            books.add(book);
        }
        return books;
    }
    public static JsonElement tryToGet(JsonObject jo, String key) {
        if(jo.has(key))
            return jo.get(key);
        return null;
    }

//    public static Object formatElement(JsonElement je, String s){
//        if(je == null){
//            return null;
//        } else if(s.equals("int")){
//            return je.getAsInt();
//        } else if(s.equals("long")){
//            return je.getAsLong();
//        } else if(s.equals("string")){
//            return je.getAsString();
//        }
//        return "Error";
//    }

//    public <T> formatElement3(JsonElement je, T type) {
//
//    }
}

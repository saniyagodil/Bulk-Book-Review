import java.util.List;

public class Book {

    private String id;

    private String title;

    private String description;

    private List<String> authors;

    private long averageRatings;

    private Integer ratingsCount;

    private String imageLinks;

    private String publisher;

    private String publishedDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public long getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(long averageRatings) {
        this.averageRatings = averageRatings;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Book id= " + id + '\n' +
                "title=" + title + '\n' +
                "authors=" + authors + '\n' +
                "description=" + description + '\n' +
                "averageRatings=" + averageRatings + '\n' +
                "ratingsCount=" + ratingsCount + '\n' +
                "publisher=" + publisher + '\n' +
                "publishedDate=" + publishedDate + '\n' +
                "imageLinks=" + imageLinks + '\n' + '\n';
    }

}

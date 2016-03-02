package mx.edu.utng.fragments;

/**
 * Created by qas on 1/03/16.
 */
public class BookInfo {
    private String name;
    private String author;
    private int resourceImage;
    private String url;

    public BookInfo(String name, String author, int resourceImage, String url) {
        this.name = name;
        this.author = author;
        this.resourceImage = resourceImage;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", resourceImage=" + resourceImage +
                ", url='" + url + '\'' +
                '}';
    }
}

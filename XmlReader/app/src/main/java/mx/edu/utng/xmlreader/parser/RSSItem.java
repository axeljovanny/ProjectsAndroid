package mx.edu.utng.xmlreader.parser;

import java.io.Serializable;

/**
 * Created by qas on 15/03/16.
 */
public class RSSItem implements Serializable {

    private String title = null;
    private String description = null;
    private String date = null;
    private String image = null;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

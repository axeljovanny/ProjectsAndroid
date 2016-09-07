package mx.edu.uttt.feeds;

import java.io.Serializable;

/**
 * Created by qas on 25/08/16.
 */
public class RSSItem implements Serializable{

    private String _title = null;
    private String _description = null;
    private String _date = null;
    private String _image = null;
    private String _content = null;

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

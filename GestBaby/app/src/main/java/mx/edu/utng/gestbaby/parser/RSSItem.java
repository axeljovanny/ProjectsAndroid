package mx.edu.utng.gestbaby.parser;

import java.io.Serializable;

public class RSSItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _title = null;
	private String _description = null;
	private String _date = null;
	private String _image = null;
	private String _content = null;

	void setTitle(String title) {
		_title = title;
	}

	void setDescription(String description) {
		_description = description;
	}

	void setDate(String pubdate) {
		_date = pubdate;
	}

	void setImage(String image) {
		_image = image;
	}

	void setContent(String _content) {
		this._content = _content;
	}

	public String getTitle() {
		return _title;
	}

	public String getDescription() {
		return _description;
	}

	public String getDate() {
		return _date;
	}

	public String getImage() {
		return _image;
	}

	public String getContent() {
		return _content;
	}
}

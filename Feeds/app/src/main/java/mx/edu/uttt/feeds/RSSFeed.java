package mx.edu.uttt.feeds;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by qas on 25/08/16.
 */
public class RSSFeed implements Serializable{

    private int _item_count = 0;
    private List<RSSItem> _item_list;

    public RSSFeed() {
        _item_list = new Vector<RSSItem>(0);

    }

    public void add_item(RSSItem item){
        _item_list.add(item);
        _item_count++;
    }

    public RSSItem get_item(int location){
        return _item_list.get(location);
    }

    public int get_item_count() {
        return _item_count;
    }
}

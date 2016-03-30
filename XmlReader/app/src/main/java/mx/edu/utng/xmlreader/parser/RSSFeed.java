package mx.edu.utng.xmlreader.parser;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by qas on 15/03/16.
 */
public class RSSFeed  implements Serializable{

    private int itemCount = 0;
    private List<RSSItem> rssItems;

    public RSSFeed(){
        rssItems = new Vector<RSSItem>(0);
    }

    public void addItem(RSSItem item){
        rssItems.add(item);
        itemCount++;
    }

    public RSSItem getItem(int location){
        return rssItems.get(location);
    }

    public int getItemCount() {
        return itemCount;
    }
}

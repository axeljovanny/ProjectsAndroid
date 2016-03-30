package mx.edu.utng.xmlreader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import mx.edu.utng.xmlreader.image.ImageLoader;
import mx.edu.utng.xmlreader.parser.RSSFeed;

/**
 * Created by qas on 15/03/16.
 */
public class ListActivity extends Activity {
    Application application;
    RSSFeed feed;
    ListView lsvNoticias;
    CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        application = getApplication();

        feed = (RSSFeed) getIntent().getExtras().get("feed");

        lsvNoticias = (ListView) findViewById(R.id.lsv_noticias);
        lsvNoticias.setVerticalFadingEdgeEnabled(true);

        adapter = new CustomListAdapter(this);

        lsvNoticias.setAdapter(adapter);

    }


    class CustomListAdapter extends BaseAdapter{
        private LayoutInflater layoutInflater;
        private ImageLoader imageLoader;

        public CustomListAdapter(ListActivity activity){
            layoutInflater = (LayoutInflater)
                    activity.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            imageLoader = new ImageLoader(
                    activity.getApplicationContext());


        }

        @Override
        public int getCount() {
            return feed.getItemCount();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItem = convertView;
            if(listItem==null) {
                listItem = layoutInflater.inflate(
                        R.layout.item_layout, null);
            }
                ImageView imvThump =
                        (ImageView) listItem.findViewById(R.id.imv_thumb);
                TextView txvTitle =
                        (TextView) listItem.findViewById(R.id.txv_title);
                TextView txvDate =
                        (TextView) listItem.findViewById(R.id.txv_date);

                imageLoader.displayImage(
                        feed.getItem(position).getImage(), imvThump);
                txvTitle.setText(feed.getItem(position).getTitle());
            Log.w("Noticia "+position, feed.getItem(position).getTitle());
                txvDate.setText(feed.getItem(position).getDate());
           return listItem;
        }
    }
}

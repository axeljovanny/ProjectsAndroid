package mx.edu.uttt.feeds;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import mx.edu.uttt.feeds.image.ImageLoader;

public class MainActivity extends Activity {
    private String RSSFEEDURL = "http://www.vidaysalud.com/tema/diario/bebes/feed/";

    Application myApp;
    RSSFeed feed;
    ListView lv;
    CustomListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() == null
                && !conMgr.getActiveNetworkInfo().isConnected()
                && !conMgr.getActiveNetworkInfo().isAvailable()) {
            // No connectivity - Show alert
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    "Unable to reach server, \nPlease check your connectivity.")
                    .setTitle("TD RSS Reader")
                    .setCancelable(false)
                    .setPositiveButton("Exit",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                   // this.finish();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();

        } else {
            // Connected - Start parsing
            new AsyncLoadXMLFeed().execute();

        }
        setContentView(R.layout.activity_main);

        myApp = getApplication();

        // Get feed form the file
        feed = (RSSFeed) getIntent().getExtras().get("feed");

        // Initialize the variables:
        lv = (ListView) findViewById(R.id.lsv_data);
        lv.setVerticalFadingEdgeEnabled(true);

        // Set an Adapter to the ListView
        adapter = new CustomListAdapter(this);
        lv.setAdapter(adapter);

        // Set on item click listener to the ListView
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // actions to be performed when a list item clicked
                int pos = arg2;

                Bundle bundle = new Bundle();
                bundle.putSerializable("feed", feed);
                Intent intent = new Intent(MainActivity.this,
                        DetailActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("pos", pos);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.imageLoader.clearCache();
        adapter.notifyDataSetChanged();
    }

    class CustomListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        public ImageLoader imageLoader;

        public CustomListAdapter(MainActivity activity) {

            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }

        @Override
        public int getCount() {

            // Set the total list item count
            return feed.get_item_count();
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

            // Inflate the item layout and set the views
            View listItem = convertView;
            int pos = position;
            if (listItem == null) {
                listItem = layoutInflater.inflate(R.layout.list_item, null);
            }

            // Initialize the views in the layout
            ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
            TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
            TextView tvDate = (TextView) listItem.findViewById(R.id.date);

            // Set the views in the layout
            imageLoader.DisplayImage(feed.get_item(pos).get_image(), iv);
            tvTitle.setText(feed.get_item(pos).get_title());
            tvDate.setText(feed.get_item(pos).get_date());

            return listItem;
        }

    }

    private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            // Obtain feed
            DOMParser myParser = new DOMParser();
            feed = myParser.parseXML(RSSFEEDURL);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Bundle bundle = new Bundle();
            bundle.putSerializable("feed", feed);
            onCreate(bundle);
            // launch List activity

            // kill this activity
            //getActivity().finish();
        }

    }

}
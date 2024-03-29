package mx.edu.utng.gestbaby;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;
import mx.edu.utng.gestbaby.parser.RSSFeed;

public class DetailActivity extends Activity {

	RSSFeed feed;
	TextView title;
	WebView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		// Enable the vertical fading edge (by default it is disabled)
		ScrollView sv = (ScrollView) findViewById(R.id.sv);
		sv.setVerticalFadingEdgeEnabled(true);

		// Get the feed object and the position from the Intent
		feed = (RSSFeed) getIntent().getExtras().get("feed");
		int pos = getIntent().getExtras().getInt("pos");

		// Initialize the views
		title = (TextView) findViewById(R.id.title);
		content = (WebView) findViewById(R.id.desc);

		// set webview properties
		WebSettings ws = content.getSettings();
		ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		ws.getPluginState();
		ws.setPluginState(PluginState.ON);
		ws.setJavaScriptEnabled(true);
		ws.setBuiltInZoomControls(true);

		// Set the views
		title.setText(feed.getItem(pos).getTitle());
		/*content.loadDataWithBaseURL("http://www.vidaysalud.com/tema/diario/bebes/feed/", feed
				.getItem(pos).getDescription(), "text/html", "UTF-8", null);*/
		content.loadDataWithBaseURL("http://www.vidaysalud.com/tema/diario/bebes/feed/", feed
				.getItem(pos).getContent(), "text/html", "UTF-8", null);
	}

}

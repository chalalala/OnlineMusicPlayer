package vn.edu.usth.onlinemusicplayer.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.fragment.SearchResultsFragment;
import vn.edu.usth.onlinemusicplayer.fragment.SearchSongFragment;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Search title
        TextView header_title = findViewById(R.id.header_title);
        header_title.setText("Search results");

        // Get query
        Bundle b = getIntent().getExtras();
        String query = b.getString("query");

        // Pass query to Result Fragment
        Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG);
        Bundle bundle = new Bundle();
        bundle.putString("query", query);

        SearchResultsFragment search_fragment = new SearchResultsFragment();
        search_fragment.setArguments(bundle);
    }
}
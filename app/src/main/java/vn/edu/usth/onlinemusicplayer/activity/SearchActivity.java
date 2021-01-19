package vn.edu.usth.onlinemusicplayer.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.onlinemusicplayer.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Search title
        TextView header_title = findViewById(R.id.header_title);
        header_title.setText("Search results");
    }
}
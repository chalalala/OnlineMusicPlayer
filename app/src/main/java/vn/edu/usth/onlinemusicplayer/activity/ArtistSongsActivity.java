package vn.edu.usth.onlinemusicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.menu.CustomActionBarFragment;

public class ArtistSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_songs);
        CustomActionBarFragment.header_title.setText("Songs of Artists");
    }
}
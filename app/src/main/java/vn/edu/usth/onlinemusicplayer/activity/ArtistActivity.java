package vn.edu.usth.onlinemusicplayer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.menu.CustomActionBarFragment;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        CustomActionBarFragment.header_title.setText("Artist");
    }
}
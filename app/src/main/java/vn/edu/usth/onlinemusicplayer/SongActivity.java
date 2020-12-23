package vn.edu.usth.onlinemusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        CustomActionBarFragment.header_title.setText("Songs");
    }
}
package vn.edu.usth.onlinemusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import org.w3c.dom.Text;

import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity {
    public static MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String file_name = b.getString("file_name");
        setContentView(R.layout.activity_music_player);

        String[] title = file_name.replace(".mp3", "").split(" - ");
        String artist = title[0];
        String song = title[1];

        // Set song name
        TextView song_name = findViewById(R.id.song_name);
        song_name.setText(song);

        // Set artist name
        TextView artist_name = findViewById(R.id.artist);
        artist_name.setText(artist);

        player = new MediaPlayer();
        ImageView play = findViewById(R.id.play);
        Drawable play_button = MaterialDrawableBuilder.with(this) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.PLAY_CIRCLE) // provide an icon
                .setColor(getResources().getColor(R.color.purple_500)) // set the icon color
                .setSizeDp(80)
                .build();
        Drawable pause_button = MaterialDrawableBuilder.with(this) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.PAUSE_CIRCLE) // provide an icon
                .setColor(getResources().getColor(R.color.purple_500)) // set the icon color
                .setSizeDp(80)
                .build();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    player.pause();
                    play.setImageDrawable(play_button);
                }
                else {
                    AssetFileDescriptor afd;
                    try {
                        afd = getApplicationContext().getAssets().openFd("musics/" + file_name);
                        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        player.prepare();
                        player.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    play.setImageDrawable(pause_button);
                }
            }
        });
    }
}
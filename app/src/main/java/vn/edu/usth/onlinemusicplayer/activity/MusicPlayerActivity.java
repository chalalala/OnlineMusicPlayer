package vn.edu.usth.onlinemusicplayer.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistGridViewAdapter;
import vn.edu.usth.onlinemusicplayer.menu.CustomActionBarFragment;

public class MusicPlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Bundle b = getIntent().getExtras();

        String song_id;
        song_id = b.getString("song_id");

        String name = b.getString("song_name");
        TextView song_name = findViewById(R.id.song_name);
        song_name.setText(name);

        TextView artist_name = findViewById(R.id.artist);
        artist_name.setText(b.getString("song_artist"));

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ac.mp3.zing.vn/complete?type=song&num=1&query=" + name;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray raw_data = obj.getJSONArray("data");
                            JSONObject data = raw_data.getJSONObject(0);
                            JSONArray song_list = data.getJSONArray("song");
                            JSONObject song = song_list.getJSONObject(0);

                            String img_url = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/" + song.getString("thumb");
                            getImage(img_url, queue);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        TextView title = findViewById(R.id.header_title);
        title.setTextColor(getColor(R.color.white));

        ImageView back_button = findViewById(R.id.back_button);
        back_button.setColorFilter(getColor(R.color.white));

        ImageButton play_btn = findViewById(R.id.play);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play_btn.setImageResource(R.drawable.ic_play);
                } else {
                    playAudioUrl(song_id);
                    play_btn.setImageResource(R.drawable.ic_pause);
                }
            }
        });

        seekbar = findViewById(R.id.seekBar);
        playAudioUrl(song_id);
        updateSeekbar();

    }

    private void updateSeekbar() {
        int duration = mediaPlayer.getDuration()/1000;
        seekbar.setMax(duration);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer.isPlaying()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int current_position = mediaPlayer.getCurrentPosition()/1000;
                    Log.i("current", "current position: " + current_position);
                    Log.i("current", "duration: " + duration);
                    seekbar.setProgress(current_position);
                }
            }
        });

        t.start();
    }

    private void getImage(String img_url, RequestQueue queue) {
        Response.Listener<Bitmap> listener =
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        try {
                            ImageView thumbnail = findViewById(R.id.image_album);
                            Drawable img = new BitmapDrawable(getResources(), response);
                            thumbnail.setImageDrawable(img);
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                };
        ImageRequest imageRequest = new ImageRequest(
                img_url,
                listener, 250, 250, ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888, null);
        queue.add(imageRequest);
    }

    private void playAudioUrl(String songId) {
        String audioUrl = "http://api.mp3.zing.vn/api/streaming/audio/" + songId + "/128";

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekbar.setProgress(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
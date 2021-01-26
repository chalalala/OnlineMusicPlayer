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
    private int duration;

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

                            duration = song.getInt("duration");

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

        playAudioUrl(song_id);

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
        seekbar.setMax(duration);
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

        Thread t = new Thread(new Runnable() {
            int current_position;

            @Override
            public void run() {
                if (mediaPlayer != null) {
                    current_position = mediaPlayer.getCurrentPosition();
                }

                Bundle bundle = new Bundle();
                bundle.putInt("current", current_position);

                // notify main thread
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        t.start();
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            int current_position = msg.getData().getInt("current");
            seekbar.setProgress(current_position / duration);
            if (mediaPlayer != null) {
                seekbar.setProgress(current_position/duration);
            }
        }
    };

    private void playAudioUrl(String songId) {
        String audioUrl = "http://api.mp3.zing.vn/api/streaming/audio/" + songId + "/128";

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
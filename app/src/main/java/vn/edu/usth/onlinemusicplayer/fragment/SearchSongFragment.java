package vn.edu.usth.onlinemusicplayer.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.activity.MusicPlayerActivity;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistSongsAdapter;

public class SearchSongFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String query;
    MediaPlayer mediaPlayer;
    ArtistSongsAdapter songAdapter;
    Button playBtn, pauseBtn;

    public SearchSongFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchSongFragment newInstance(String param1) {
        SearchSongFragment fragment = new SearchSongFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_song, container, false);

//        Toast.makeText(getContext(), query, Toast.LENGTH_LONG).show();

        // Spinner that appears while waiting for the data
        ProgressBar spinner = view.findViewById(R.id.spinner);

        // Initialize list containing track detail
        ArrayList<String> song_names = new ArrayList<String>();
        ArrayList<String> artist_names = new ArrayList<String>();
        ArrayList<String> song_ids = new ArrayList<String>();
        ArrayList<Bitmap> thumbnails = new ArrayList<Bitmap>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "http://ac.mp3.zing.vn/complete?type=artist,song,key,code&num=10&query=" + query;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray raw_data = obj.getJSONArray("data");
                            JSONObject data = raw_data.getJSONObject(0);
                            JSONArray list_songs = data.getJSONArray("song");
                            for (int i = 0; i < list_songs.length(); i++) {
                                JSONObject item = list_songs.getJSONObject(i);
                                song_names.add(item.getString("name"));
                                artist_names.add(item.getString("artist"));
                                song_ids.add(item.getString("id"));
                            }
                            try {
                                ListView list = view.findViewById(R.id.search_song);
                                songAdapter = new ArtistSongsAdapter(getContext(), song_names, artist_names, song_ids);
                                Log.d("SearchSongFragment song_names", String.valueOf(song_names));
                                //click on song item title and artist name listener
                                songAdapter.setCustomButtonListner(new ArtistSongsAdapter.customButtonListener() {
                                    @Override
                                    public void onButtonClickListner(int position, String value) {
                                        Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                                        intent.putExtra("song_id", value);
                                        intent.putExtra("song_name", song_names.get(position));
                                        intent.putExtra("song_artist", artist_names.get(position));
                                        startActivity(intent);
                                    }
                                });
                                list.setAdapter(songAdapter);
                                spinner.setVisibility(View.GONE);
                            } catch (Exception e) {
                                Log.e("error", e.getMessage());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return view;
    }

    private void playAudioUrl(String songId) {
        String audioUrl = "http://api.mp3.zing.vn/api/streaming/audio/"+ songId + "/128";

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

//    SimpleExoPlayer exoPlayer;
//    SimpleExoPlayerView exoPlayerView;
//
//    private void playAudioUrl(String songId) {
//        String audioUrl = "http://api.mp3.zing.vn/api/streaming/audio/"+ songId + "/128";
//
//            try {
//                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
//                exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//                Uri audioUrl = Uri.parse(audioUrl);
//                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_music");
//                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//                MediaSource mediaSource = new ExtractorMediaSource(audioUrl, dataSourceFactory, extractorsFactory, null, null);
//                exoPlayerView.setPlayer(exoPlayer);
//                exoPlayer.prepare(mediaSource);
//                exoPlayer.setPlayWhenReady(true);
//            } catch (Exception e) {
//                Log.e("TAG", "Error : " + e.toString());
//            }
//    }

}
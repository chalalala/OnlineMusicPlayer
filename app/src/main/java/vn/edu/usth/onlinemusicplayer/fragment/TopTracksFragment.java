
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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
import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.activity.MusicPlayerActivity;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistSongsAdapter;
import vn.edu.usth.onlinemusicplayer.adapter.TopTrackAdapter;

public class TopTracksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopTracksFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TopTracksFragment newInstance() {
        TopTracksFragment fragment = new TopTracksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_tracks, container, false);

        // Spinner that appears while waiting for the data
        ProgressBar spinner = view.findViewById(R.id.spinner);

        // Initialize list containing track detail
        ArrayList<String> song_names = new ArrayList<String>();
        ArrayList<String> artist_names = new ArrayList<String>();
        ArrayList<String> song_ids = new ArrayList<String>();
        ArrayList<String> img_urls = new ArrayList<String>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "https://mp3.zing.vn/xhr/chart-realtime?songId=0&videoId=0&albumId=0&chart=song&time=-1";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONObject data = obj.getJSONObject("data");
                            JSONArray list_songs = data.getJSONArray("song");

                            for (int i = 0; i < list_songs.length(); i++) {
                                JSONObject item = list_songs.getJSONObject(i);
                                song_names.add(item.getString("name"));
                                artist_names.add(item.getString("artists_names"));
                                song_ids.add(item.getString("id"));
                                img_urls.add(item.getString("thumbnail"));
                            }

                            Bitmap[] thumbnails = new Bitmap[img_urls.size()];
                            for (int i = 0; i < img_urls.size(); i++) {
                                int finalI = i;
                                Response.Listener<Bitmap> listener2 =
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap response) {
                                                thumbnails[finalI] = response;
                                                if (finalI == img_urls.size() - 1) {
                                                    try {
                                                        ListView list = view.findViewById(R.id.top_tracks_list);
                                                        TopTrackAdapter topTrackAdapter = new TopTrackAdapter(getContext(), song_names, artist_names, thumbnails, song_ids);
                                                        topTrackAdapter.setCustomButtonListner(new TopTrackAdapter.customButtonListener() {
                                                            @Override
                                                            public void onButtonClickListner(int position, String value) {
                                                                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                                                                intent.putExtra("song_id", value);
                                                                intent.putExtra("song_name", song_names.get(position));
                                                                intent.putExtra("song_artist", artist_names.get(position));
                                                                startActivity(intent);
                                                            }
                                                        });
                                                        list.setAdapter(topTrackAdapter);

                                                        // Spinner disappear when data is ready
                                                        spinner.setVisibility(View.GONE);
                                                    } catch (Exception e) {
                                                    }
                                                }
                                            }
                                        };

                                ImageRequest imageRequest = new ImageRequest(
                                        img_urls.get(i),
                                        listener2, 50, 50, ImageView.ScaleType.CENTER,
                                        Bitmap.Config.ARGB_8888, null);
                                queue.add(imageRequest);
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

}
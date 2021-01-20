
package vn.edu.usth.onlinemusicplayer.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.CustomOnClickListener;
import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.activity.MusicPlayerActivity;
import vn.edu.usth.onlinemusicplayer.adapter.CustomAdapter;
import vn.edu.usth.onlinemusicplayer.model.Audio;

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

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url ="https://mp3.zing.vn/xhr/chart-realtime?songId=0&videoId=0&albumId=0&chart=song&time=-1";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response", response);
                        ArrayList<String> song_names = new ArrayList<String>();
                        ArrayList<String> artist_names = new ArrayList<String>();
                        ArrayList<Drawable> thumbnail = new ArrayList<Drawable>();

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONObject data = obj.getJSONObject("data");
                            JSONArray list_songs = data.getJSONArray("song");
                            for (int i=0;i<list_songs.length();i++){
                                JSONObject item = list_songs.getJSONObject(i);
                                song_names.add(item.getString("name"));
                                artist_names.add(item.getString("artists_names"));
                            }

//                            setTopTracks(song_names, artist_names);
                            ListView list = getView().findViewById(R.id.top_tracks_list);
                            CustomAdapter customAdapter = new CustomAdapter(getContext(), song_names, artist_names);
                            list.setAdapter(customAdapter);

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
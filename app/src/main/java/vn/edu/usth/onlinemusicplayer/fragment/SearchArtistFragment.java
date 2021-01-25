package vn.edu.usth.onlinemusicplayer.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistGridViewAdapter;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistSongsAdapter;
import vn.edu.usth.onlinemusicplayer.adapter.TopTrackAdapter;

public class SearchArtistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String query;

    public SearchArtistFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchArtistFragment newInstance(String param1) {
        SearchArtistFragment fragment = new SearchArtistFragment();
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
        View view = inflater.inflate(R.layout.fragment_search_artist, container, false);

        // Spinner that appears while waiting for the data
        ProgressBar spinner = view.findViewById(R.id.spinner);

        // Initialize list containing track detail
        ArrayList<String> artist_names = new ArrayList<String>();
        ArrayList<Bitmap> thumbnails = new ArrayList<Bitmap>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "http://ac.mp3.zing.vn/complete?type=artist&query=" + query;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray raw_data = obj.getJSONArray("data");
                            JSONObject data = raw_data.getJSONObject(0);
                            JSONArray list_artists = data.getJSONArray("artist");

                            for (int i = 0; i < list_artists.length(); i++) {
                                JSONObject item = list_artists.getJSONObject(i);
                                artist_names.add(item.getString("name"));

                                String path = item.getString("thumb");
                                String img_url = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/" + path;
                                Log.i("src", img_url);

                                int finalI = i;
                                Response.Listener<Bitmap> listener2 =
                                        new Response.Listener<Bitmap>() {
                                            @Override
                                            public void onResponse(Bitmap response) {
                                                thumbnails.add(response);
                                                if (finalI == list_artists.length() - 1) {
                                                    try {
                                                        GridView list = view.findViewById(R.id.search_song);
                                                        ArtistGridViewAdapter artistAdapter = new ArtistGridViewAdapter(getContext(), artist_names, thumbnails);
                                                        list.setAdapter(artistAdapter);

                                                        // Spinner disappear when data is ready
                                                        spinner.setVisibility(View.GONE);
                                                    } catch (Exception e) {
                                                    }
                                                }
                                            }
                                        };
                                ImageRequest imageRequest = new ImageRequest(
                                        img_url,
                                        listener2, 100, 100, ImageView.ScaleType.CENTER,
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
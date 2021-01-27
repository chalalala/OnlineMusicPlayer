package vn.edu.usth.onlinemusicplayer.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import vn.edu.usth.onlinemusicplayer.activity.ArtistSongsActivity;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistSongsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtistSongsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistSongsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private String data;

    public ArtistSongsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ArtistSongsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtistSongsFragment newInstance() {
        ArtistSongsFragment fragment = new ArtistSongsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Bundle bundle = this.getArguments();
//        if (bundle != null){
//            data = bundle.getString("key");
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_songs, container, false);
//        Bundle bundle = getArguments();
//        String data = bundle.getString("key");
        // get sent data from artistsongs activity
        ArtistSongsActivity artistSongsActivity = (ArtistSongsActivity) getActivity();
        String artist = artistSongsActivity.getIntent().getExtras().getString("key");
//        Toast.makeText(getContext(),data,Toast.LENGTH_SHORT).show();
        ImageButton back = view.findViewById(R.id.back_button1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

//        String art = "Blackpink";


        TextView textView = view.findViewById(R.id.songofartist_name);
        textView.setText(artist);
        callApi(artist);
        return view;
    }

    public void callApi(String artist){
        // Initialize list containing track detail
        ArrayList<String> song_name = new ArrayList<String>();
        ArrayList<String> artist_name = new ArrayList<String>();
        ArrayList<String> song_ids = new ArrayList<String>();

        // access button name
//        TextView button_name = view.findViewWithTag("name_artist");
//        String name = button_name.getText().toString();
//        Log.i("Tag","Name: "+name);

        // once, should be performed once per app instance
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        // a simple request to the required songs
        String url = "http://ac.mp3.zing.vn/complete?type=artist,song,key,code&num=10&query="+artist;
        String url2 = "http://ac.mp3.zing.vn/complete?type=artist&num=1&query="+artist;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray data = obj.getJSONArray("data");
                            JSONObject detail = data.getJSONObject(0);
                            if (detail != null) {
                                JSONArray details = detail.getJSONArray("song");
                                Log.i("tesssst","songsss"+details);
                                for (int j = 0; j < details.length(); j++) {
                                    JSONObject song_detail = details.getJSONObject(j);
                                    Log.i("test","songs"+song_detail);
                                    song_name.add(song_detail.getString("name"));
                                    artist_name.add(song_detail.getString("artist"));
                                    song_ids.add(song_detail.getString("id"));
                                }
                            }



                            ListView listView = getView().findViewById(R.id.songofartist);
                            ArtistSongsAdapter artistSongsAdapter = new ArtistSongsAdapter(getContext(),song_name,artist_name,song_ids);
                            listView.setAdapter(artistSongsAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
                    }
                });

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET,url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray data = obj.getJSONArray("data");
                            JSONObject detail = data.getJSONObject(0);
                            JSONArray artist = detail.getJSONArray("artist");
                            JSONObject artist_detail = artist.getJSONObject(0);
                            String img_full = "";
//                            Log.i("Tag","Detaillll: "+artist_detail);
                            if (artist_detail != null) {
                                String img = artist_detail.getString("thumb");
                                img_full = "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/"+img;
//                                Log.i("Tag","Img: "+img_full);
                            }
                            Response.Listener<Bitmap> listener1 = new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    ImageView iv = (ImageView) getView().findViewById(R.id.img_test);
                                    iv.setImageBitmap(response);
                                }
                            };
// a simple request to the required image
                            ImageRequest imageRequest = new ImageRequest(img_full,
                                    listener1, 0, 0, ImageView.ScaleType.FIT_XY,
                                    Bitmap.Config.ARGB_8888,null);
                            queue.add(imageRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
                    }
                });


// go!
        queue.add(stringRequest2);
        queue.add(stringRequest);

    }
}
package vn.edu.usth.onlinemusicplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

class CustomOnClickListener implements View.OnClickListener{
    int param;
    public CustomOnClickListener(int param){
        this.param = param;
    }

    @Override
    public void onClick(View v){
    }
}

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        // Recent - Image
        ImageView first_img = view.findViewById(R.id.recent_first);
        ImageView second_img = view.findViewById(R.id.recent_second);
        ImageView third_img = view.findViewById(R.id.recent_third);

        // Recent - Song name
        TextView first = view.findViewById(R.id.recent_first_name);
        TextView second = view.findViewById(R.id.recent_second_name);
        TextView third = view.findViewById(R.id.recent_third_name);

        // Recent - Artist name
        TextView first_name = view.findViewById(R.id.recent_artist_first);
        TextView second_name = view.findViewById(R.id.recent_artist_second);
        TextView third_name = view.findViewById(R.id.recent_artist_third);

        // Artist name
        TextView artist_first = view.findViewById(R.id.artist_first_name);
        TextView artist_second = view.findViewById(R.id.artist_second_name);
        TextView artist_third = view.findViewById(R.id.artist_third_name);

        TextView[] song_names = {first, second, third};
        TextView[] recent_artists = {first_name, second_name, third_name};
        TextView[] artists_name = {artist_first, artist_second, artist_third};
        ImageView[] images = {first_img, second_img, third_img};

            AssetManager assetManager = getContext().getAssets();

        try {
            StorageUtil storage = new StorageUtil(getActivity().getApplicationContext());
            JsonElement jsonstorage = storage.loadJsonAudio();
            System.out.println("jsonstorage" + jsonstorage);
            System.out.println("jsonstorage isJsonPrimitive" + jsonstorage.isJsonPrimitive());

            String[] fileNames = assetManager.list("musics");
            List<String> songs = new ArrayList<>();
            List<String> artists = new ArrayList<>();
            List<String> single_artist = new ArrayList<>();

            for (int i=0;i<fileNames.length; i++) {
                String[] title = fileNames[i].replace(".mp3", "").split(" - ");
                artists.add(title[0]);
                songs.add(title[1]);

                String[] single_name = artists.get(i).split(" ft ");
                for (String name : single_name){
                    if (!single_artist.contains(name)){
                        single_artist.add(name);
                    }
                }
            }

            for (int i=0; i<song_names.length; i++){
                String sname = songs.get(i);
                String aname = artists.get(i);
                song_names[i].setText(sname);
                recent_artists[i].setText(aname);
                artists_name[i].setText(single_artist.get(i));
                Log.e("hello", "File name" + fileNames[i]);
                Log.e("hello2", "Song name" + songs.get(i));
                // Handle click
                images[i].setOnClickListener(new CustomOnClickListener(i) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                        intent.putExtra("position", param);
                        startActivity(intent);
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}
package vn.edu.usth.onlinemusicplayer;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        AssetManager assetManager = getContext().getAssets();

        try {
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

            for (int i=0; i<song_names.length; i++){
                song_names[i].setText(songs.get(i));
                recent_artists[i].setText(artists.get(i));
                artists_name[i].setText(single_artist.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}
package vn.edu.usth.onlinemusicplayer.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.onlinemusicplayer.CustomOnClickListener;
import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.activity.MusicPlayerActivity;
import vn.edu.usth.onlinemusicplayer.model.Audio;

public class HomeFragment extends Fragment {

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

        ArrayList<Audio> audioList = loadAudio();

        List<String> single_artist = new ArrayList<>();

        for (int i=0;i<audioList.size(); i++) {
            String artists = audioList.get(i).getArtist();

            String[] single_name = artists.split(" ft ");
            for (String name : single_name){
                if (!single_artist.contains(name)){
                    single_artist.add(name);
                }
            }
        }

        for (int i=0; i<song_names.length; i++){
            song_names[i].setText(audioList.get(i).getTitle());
            recent_artists[i].setText(audioList.get(i).getArtist());
            artists_name[i].setText(single_artist.get(i));
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

        return view;
    }

    private ArrayList loadAudio() {
        ArrayList<Audio> audioList = new ArrayList<>();

        ContentResolver contentResolver = getContext().getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            audioList = new ArrayList<>();
            while (cursor.moveToNext()) {
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                // Save to audioList
                audioList.add(new Audio(data, title, album, artist, duration));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return audioList;
    }
}
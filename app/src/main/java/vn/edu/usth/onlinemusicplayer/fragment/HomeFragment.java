package vn.edu.usth.onlinemusicplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.activity.ArtistSongsActivity;
import vn.edu.usth.onlinemusicplayer.model.SongModel;
import vn.edu.usth.onlinemusicplayer.service.MusicService;

public class HomeFragment extends MusicServiceFragment {
    public static final String TAG="HomeFragment";

    List<SongModel> songs;
    private MusicService musicSrv;
    boolean musicServiceStatus = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        songs=new ArrayList<>();
        if(musicServiceStatus) { initFragment(); }
        return view;
    }

    @Override
    public void onServiceConnected(MusicService musicService) {
        musicSrv = musicService;
        musicServiceStatus=true;
        initFragment();
    }

    @Override
    public void onServiceDisconnected() {

    }

    public void displayMusicData() {
        View view = getView();
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

        // Device's Song - Song name
        TextView first_song = view.findViewById(R.id.song_first_name);
        TextView second_song = view.findViewById(R.id.song_second_name);
        TextView third_song = view.findViewById(R.id.song_third_name);

        // Device's Song - Artist name
        TextView first_artist_name = view.findViewById(R.id.song_artist_first);
        TextView second_artist_name = view.findViewById(R.id.song_artist_second);
        TextView third_artist_name = view.findViewById(R.id.song_artist_third);


        // Artist name
        TextView artist_first = view.findViewById(R.id.artist_first_name);
        TextView artist_second = view.findViewById(R.id.artist_second_name);
        TextView artist_third = view.findViewById(R.id.artist_third_name);

        TextView[] song_names = {first, second, third};
        TextView[] recent_artists = {first_name, second_name, third_name};
        TextView[] device_artists = {first_artist_name, second_artist_name, third_artist_name};
        TextView[] device_song_names = {first_song, second_song, third_song};
        TextView[] artists_name = {artist_first, artist_second, artist_third};
        ImageView[] images = {first_img, second_img, third_img};

        for (int i=0; i<3; i++){
            Log.d(TAG,songs.get(i).getTitle());
            song_names[i].setText(songs.get(i).getTitle());
            recent_artists[i].setText(songs.get(i).getArtistName());
            artists_name[i].setText(songs.get(i).getArtistName());
            device_song_names[i].setText(songs.get(i).getTitle());
            device_artists[i].setText(songs.get(i).getArtistName());
        }

        // handle button click to artists' songs
        ImageView img1 = view.findViewById(R.id.artist_first);
        ImageView img2 = view.findViewById(R.id.artist_second);
        ImageView img3 = view.findViewById(R.id.artist_third);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ArtistSongsActivity.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ArtistSongsActivity.class);
                startActivity(intent);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), ArtistSongsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void handleSongClick(){

    }

    //initialize all the component
    public void initFragment() {
        songs=musicSrv.getSongs();
        Log.d(TAG,songs.get(0).getTitle());
        displayMusicData();
        handleSongClick();
    }

    public void playSong(SongModel song) {
        musicSrv.play(song);
    }
}
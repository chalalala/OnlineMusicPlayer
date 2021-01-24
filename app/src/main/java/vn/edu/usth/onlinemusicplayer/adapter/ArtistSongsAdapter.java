package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;

public class ArtistSongsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> song_name;
    ArrayList<String> artist_name;
    LayoutInflater inflater;

    public ArtistSongsAdapter(Context applicationContext, ArrayList<String> song_name, ArrayList<String> artist_name) {
        this.context = context;
        this.song_name = song_name;
        this.artist_name = artist_name;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return song_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_artist_song_single,null);
        TextView songs = (TextView) view.findViewById(R.id.song_name1);
        TextView artists = (TextView) view.findViewById(R.id.artist_name1);

        songs.setText(song_name.get(i));
        artists.setText(artist_name.get(i));
        return view;
    }
}

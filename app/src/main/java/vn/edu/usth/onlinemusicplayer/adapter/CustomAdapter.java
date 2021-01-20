package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> song_names;
    ArrayList<String> artist_names;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, ArrayList<String> song_names, ArrayList<String> artist_names) {
        this.context = context;
        this.song_names = song_names;
        this.artist_names = artist_names;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return song_names.size();
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
        view = inflater.inflate(R.layout.fragment_single_track, null);
        TextView name_song = (TextView) view.findViewById(R.id.name_song);
        TextView name_artist = (TextView) view.findViewById(R.id.name_artist);
//        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        name_song.setText(song_names.get(i));
        name_artist.setText(artist_names.get(i));
//        icon.setImageResource(flags[i]);
        return view;
    }
}

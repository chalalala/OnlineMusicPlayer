package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.model.SongModel;

public class ArtistSongsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> song_name;
    ArrayList<String> artist_name;
    ArrayList<String> song_id;

    LayoutInflater inflater;
    customButtonListener customListner;

    public interface customButtonListener {
        public void onButtonClickListner(int position,String value);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public ArtistSongsAdapter(Context applicationContext, ArrayList<String> song_name, ArrayList<String> artist_name, ArrayList<String> song_id) {
        this.context = applicationContext;
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.song_id = song_id;

        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return song_name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public String getItemSongId(int i) {
        return song_id.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.song_layout,null);
        TextView songs = (TextView) view.findViewById(R.id.title);
        TextView artists = (TextView) view.findViewById(R.id.artist);
        ImageButton playBtn = (ImageButton) view.findViewById(R.id.play_pause);
        songs.setText(song_name.get(i));
        artists.setText(artist_name.get(i));

        playBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(i,getItemSongId(i));
                }

            }
        });

        return view;
    }


}

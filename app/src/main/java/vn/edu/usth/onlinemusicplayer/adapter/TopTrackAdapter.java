package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;

public class TopTrackAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> song_names;
    ArrayList<String> artist_names;
    Bitmap[] thumbnails;
    ArrayList<String> song_ids;
    customButtonListener customListner;

    LayoutInflater inflater;

    public interface customButtonListener {
        public void onButtonClickListner(int position,String value);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public TopTrackAdapter(Context context, ArrayList<String> song_names, ArrayList<String> artist_names, Bitmap[] thumbnails, ArrayList<String> song_ids) {
        this.context = context;
        this.song_names = song_names;
        this.artist_names = artist_names;
        this.thumbnails = thumbnails;
        this.song_ids = song_ids;
        inflater = (LayoutInflater.from(context));
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

    public String getItemSongId(int i) {
        return song_ids.get(i);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_single_track, null);
        TextView name_song = (TextView) view.findViewById(R.id.name_song);
        TextView name_artist = (TextView) view.findViewById(R.id.name_artist);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        ImageButton playBtn = (ImageButton) view.findViewById(R.id.bt_play);
        name_song.setText(song_names.get(i));
        name_artist.setText(artist_names.get(i));
        thumbnail.setImageBitmap(thumbnails[i]);

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

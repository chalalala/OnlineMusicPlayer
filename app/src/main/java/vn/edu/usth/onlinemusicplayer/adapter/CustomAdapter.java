package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> song_names;
    ArrayList<String> artist_names;
    ArrayList<String> thumbnails;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<String> song_names, ArrayList<String> artist_names, ArrayList<String> thumbnails) {
        this.context = context;
        this.song_names = song_names;
        this.artist_names = artist_names;
        this.thumbnails = thumbnails;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_single_track, null);
        TextView name_song = (TextView) view.findViewById(R.id.name_song);
        TextView name_artist = (TextView) view.findViewById(R.id.name_artist);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        RequestQueue queue = Volley.newRequestQueue(context);
        Response.Listener<Bitmap> listener =
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        name_song.setText(song_names.get(i));
                        name_artist.setText(artist_names.get(i));
                        thumbnail.setImageBitmap(response);
                    }
                };

        ImageRequest imageRequest = new ImageRequest(
                thumbnails.get(i),
                listener, 0, 0, ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888, null);
        queue.add(imageRequest);


        return view;
    }
}

package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;

public class ArtistGridViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> artist_name;
    ArrayList<Bitmap> thumbnails;
    LayoutInflater inflater;

    public ArtistGridViewAdapter(Context applicationContext, ArrayList<String> artist_name, ArrayList<Bitmap> thumbnails){
        this.context = applicationContext;
        this.artist_name = artist_name;
        this.thumbnails = thumbnails;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return artist_name.size();
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
        view = inflater.inflate(R.layout.single_item_gridview,null);
        ImageView img = (ImageView) view.findViewById(R.id.imagegrid);
        TextView artist = (TextView) view.findViewById(R.id.textgrid);

        img.setImageBitmap(thumbnails.get(i));
        artist.setText(artist_name.get(i));
        return view;
    }
}

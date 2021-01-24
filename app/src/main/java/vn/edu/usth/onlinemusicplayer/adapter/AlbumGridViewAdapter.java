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

public class AlbumGridViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> album_name;
    ArrayList<Bitmap> thumbnails;
    LayoutInflater inflater;

    public AlbumGridViewAdapter(Context applicationContext, ArrayList<String> album_name, ArrayList<Bitmap> thumbnails){
        this.context = applicationContext;
        this.album_name = album_name;
        this.thumbnails = thumbnails;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return album_name.size();
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
        view = inflater.inflate(R.layout.item_album_gridview,null);
        ImageView thumb = (ImageView) view.findViewById(R.id.imageAlbum);
        TextView album = (TextView) view.findViewById(R.id.textAlbum);

        thumb.setImageBitmap(thumbnails.get(i));
        album.setText(album_name.get(i));
        return view;
    }
}

package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.model.AlbumModel;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    List<AlbumModel> albums;
    Context context;
    public AlbumClickListener albumClickListener;

    public AlbumAdapter(List<AlbumModel> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AlbumModel albumModel = albums.get(position);
        holder.albumName.setText(albumModel.getName());
        holder.albumNoOfSong.setText(albumModel.getNoOfSong()+ " songs");
        Picasso.with(context).load(albumModel.getCoverArt()).placeholder(R.drawable.mamamoo_album).into(holder.albumImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                albumClickListener.OnAlbumClickListener(view,albums.get(position),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumImage;
        TextView albumName;
        TextView albumNoOfSong;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            albumImage = (ImageView)itemView.findViewById(R.id.iv_album_image);
            albumName = (TextView)itemView.findViewById(R.id.tv_album_name);
            albumNoOfSong=(TextView)itemView.findViewById(R.id.tv_album_no_of_song);
            cardView = (CardView)itemView.findViewById(R.id.cv_item_album);
        }
    }
    public interface AlbumClickListener {
        void OnAlbumClickListener(View v, AlbumModel album, int pos);
    }
    public void setAlbumClickListener(AlbumClickListener albumClickListener) {
        this.albumClickListener = albumClickListener;
    }
}

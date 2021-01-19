package vn.edu.usth.onlinemusicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.model.TrackModel;

public class QueueRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public QueueRecyclerViewAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView artistName;
        public ItemViewHolder( View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            artistName = (TextView) itemView.findViewById(R.id.artistName);
//            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.fragment_queue, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                TrackModel track = (TrackModel) listRecyclerItem.get(i);
//                int drawableResourceId = context.getResources().getIdentifier(dayForecast.getIcon(), "drawable", context.getPackageName());

                itemViewHolder.title.setText(track.getTitle());
                itemViewHolder.artistName.setText(track.getArtistName());
//                itemViewHolder.icon.setImageResource(drawableResourceId);

        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

}
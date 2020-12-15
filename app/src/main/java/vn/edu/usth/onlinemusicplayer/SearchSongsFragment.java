package vn.edu.usth.onlinemusicplayer;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Browser;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Field;

public class SearchSongsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchSongsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchSongsFragment newInstance() {
        SearchSongsFragment fragment = new SearchSongsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_songs, container, false);

        LinearLayout searchsongs = (LinearLayout) view.findViewById(R.id.searchsongs);

        AssetManager assetManager = getContext().getAssets();
        try{
            String[] songs = assetManager.list("musics");

            for (int i=0; i<songs.length; i++){
//                Toast.makeText(this.getContext(),songs[i], Toast.LENGTH_LONG).show();
                // Create row by LinearLayout
                RelativeLayout row = new RelativeLayout(this.getActivity());
                RelativeLayout.LayoutParams row_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 120);
                row.setLayoutParams(row_params);

                // Create layout contains Name of songs and Artists
                LinearLayout NameField = new LinearLayout(this.getActivity());
                NameField.setOrientation(LinearLayout.VERTICAL);

                RelativeLayout.LayoutParams nameFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                nameFieldParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                NameField.setLayoutParams(nameFieldParams);

                LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);

                String[] songs_detail = songs[i].replace(".mp3","").split(" - ");

                // Name of song
                TextView name = new TextView(this.getContext());
                name.setText(songs_detail[1]);
                name.setTextSize(16);
                name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                name.setTextColor(Color.BLACK);
                name.setLayoutParams(name_params);
                NameField.addView(name);

                // Artist
                TextView artist = new TextView(this.getContext());
                artist.setText(songs_detail[0]);
                artist.setLayoutParams(name_params);
                NameField.addView(artist);

                row.addView(NameField);

                // Heart button
                ImageView heart = new ImageView(this.getContext());
                Drawable heart_clicked = MaterialDrawableBuilder.with(this.getContext()) // provide a context
                        .setIcon(MaterialDrawableBuilder.IconValue.HEART) // provide an icon
                        .setColor(Color.parseColor("#9c9c9c")) // set the icon color
                        .setSizeDp(24)
                        .build();
                Drawable heart_outline = MaterialDrawableBuilder.with(this.getContext()) // provide a context
                        .setIcon(MaterialDrawableBuilder.IconValue.HEART_OUTLINE) // provide an icon
                        .setColor(Color.parseColor("#9c9c9c")) // set the icon color
                        .setSizeDp(24)
                        .build();
                heart.setImageDrawable(heart_outline);

                RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                buttonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);
                heart.setLayoutParams(buttonParams);

                row.addView(heart);

                // Function for heart clicked
                heart.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        Drawable state = heart.getDrawable();
                        heart.setImageDrawable(state == heart_clicked ? heart_outline : heart_clicked);
                    }
                });
                searchsongs.addView(row);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return view;
    }
}
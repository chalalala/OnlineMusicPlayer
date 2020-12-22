package vn.edu.usth.onlinemusicplayer;

import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import java.io.IOException;

public class MusicPlayerFragment extends Fragment {

    ImageButton play, pause;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MusicPlayerFragment newInstance(String param1, String param2) {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);

        ImageView heart = view.findViewById(R.id.heart);
        Drawable heart_clicked = MaterialDrawableBuilder.with(getContext()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.HEART) // provide an icon
                .setColor(getResources().getColor(R.color.gray)) // set the icon color
                .setSizeDp(28)
                .build();
        Drawable heart_outline = MaterialDrawableBuilder.with(getContext()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.HEART_OUTLINE) // provide an icon
                .setColor(getResources().getColor(R.color.gray)) // set the icon color
                .setSizeDp(28)
                .build();
        heart.setImageDrawable(heart_outline);

        heart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean state = heart.getDrawable() == heart_clicked;
                heart.setImageDrawable(state ? heart_outline : heart_clicked);
            }
        });

        return view;
    }
}

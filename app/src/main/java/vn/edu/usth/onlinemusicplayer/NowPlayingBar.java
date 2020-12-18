package vn.edu.usth.onlinemusicplayer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingBar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingBar() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NowPlayingBar newInstance(String param1, String param2) {
        NowPlayingBar fragment = new NowPlayingBar();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing_bar, container, false);
        ImageView heart = view.findViewById(R.id.heart);
        Drawable heart_clicked = MaterialDrawableBuilder.with(this.getContext()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.HEART) // provide an icon
                .setColor(Color.parseColor("#9c9c9c")) // set the icon color
                .setSizeDp(20)
                .build();
        Drawable heart_outline = MaterialDrawableBuilder.with(this.getContext()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.HEART_OUTLINE) // provide an icon
                .setColor(Color.parseColor("#9c9c9c")) // set the icon color
                .setSizeDp(20)
                .build();
        heart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Drawable state = heart.getDrawable();
                heart.setImageDrawable(state == heart_clicked ? heart_outline : heart_clicked);
            }
        });
        return view;
    }
}
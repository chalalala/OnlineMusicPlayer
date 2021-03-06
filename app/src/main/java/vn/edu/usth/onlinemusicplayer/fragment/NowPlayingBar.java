package vn.edu.usth.onlinemusicplayer.fragment;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import java.io.IOException;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.activity.QueueActivity;

public class NowPlayingBar extends Fragment {
    public static MediaPlayer player = new MediaPlayer();
    public static String songName;
    public static String artist;

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

        // Handle heart clicked
        ImageView heart = view.findViewById(R.id.heart);
        Drawable heart_clicked = MaterialDrawableBuilder.with(this.getContext()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.HEART) // provide an icon
                .setColor(getResources().getColor(R.color.gray)) // set the icon color
                .setSizeDp(20)
                .build();
        Drawable heart_outline = MaterialDrawableBuilder.with(this.getContext()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.HEART_OUTLINE) // provide an icon
                .setColor(getResources().getColor(R.color.gray)) // set the icon color
                .setSizeDp(20)
                .build();
        heart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean state = heart.getDrawable() == heart_clicked ;
                heart.setImageDrawable(state ? heart_outline : heart_clicked);
            }
        });

        // Handle play clicked
        Drawable play_button = getResources().getDrawable(R.drawable.ic_baseline_play_circle_outline_24);
        Drawable pause_button = getResources().getDrawable(R.drawable.ic_baseline_pause_circle_outline_24);
        ImageButton play = view.findViewById(R.id.play);
        play.setImageDrawable(play_button);

        play.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v){
                if (player.isPlaying()) {
                    player.pause();
                    play.setImageDrawable(play_button);
                }
                else {
                    AssetFileDescriptor afd;
                    try {
                        afd = getContext().getAssets().openFd("musics/Demi Lovato - Heart Attack.mp3");
                        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        player.prepare();
                        player.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    play.setImageDrawable(pause_button);
                }
            }
        });

        // Handle queue clicked
        ImageButton queue = view.findViewById(R.id.queue);
        queue.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getContext(), QueueActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
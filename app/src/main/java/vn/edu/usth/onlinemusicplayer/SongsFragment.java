package vn.edu.usth.onlinemusicplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class SongsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SongsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SongsFragment newInstance() {
        SongsFragment fragment = new SongsFragment();
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
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        LinearLayout searchsongs = (LinearLayout) view.findViewById(R.id.searchsongs);

        ArrayList<Audio> audioList = loadAudio();

        for (int i=0; i<audioList.size(); i++){
//                Toast.makeText(this.getContext(),songs[i], Toast.LENGTH_LONG).show();
            // Create row by RelativeLayout
            RelativeLayout row = new RelativeLayout(this.getActivity());
            RelativeLayout.LayoutParams row_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(row_params);

            
            // Name of song
            TextView name = new TextView(this.getContext());
            name.setText(audioList.get(i).getTitle());
            name.setTextSize(16);
            name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            name.setTextColor(Color.BLACK);
            name.setId(i+1);

            // Handle click
            name.setOnClickListener(new CustomOnClickListener(i) {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                    intent.putExtra("position", param);
                    startActivity(intent);
                }
            });
            row.addView(name);

            // Artist
            RelativeLayout.LayoutParams nameFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            nameFieldParams.addRule(RelativeLayout.BELOW, name.getId());
            nameFieldParams.bottomMargin = 20;
            TextView artist = new TextView(this.getContext());
            artist.setText(audioList.get(i).getArtist());
            artist.setLayoutParams(nameFieldParams);
            row.addView(artist);

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

        return view;
    }

    private ArrayList loadAudio() {
        ArrayList<Audio> audioList = new ArrayList<>();

        ContentResolver contentResolver = getContext().getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            audioList = new ArrayList<>();
            while (cursor.moveToNext()) {
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                // Save to audioList
                audioList.add(new Audio(data, title, album, artist));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return audioList;
    }
}
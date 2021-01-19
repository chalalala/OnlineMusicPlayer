package vn.edu.usth.onlinemusicplayer.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.model.Audio;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ArtistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArtistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtistFragment newInstance() {
        ArtistFragment fragment = new ArtistFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);

//        LinearLayout artist_layout = (LinearLayout) view.findViewById(R.id.artistfrag);
        LinearLayout column1 = (LinearLayout) view.findViewById((R.id.col1));
        LinearLayout column2 = (LinearLayout) view.findViewById((R.id.col2));
        LinearLayout column3 = (LinearLayout) view.findViewById((R.id.col3));

        ArrayList<Audio> audioList = loadAudio();
        String[] artist_name = new String[audioList.size()];

        for (int j=0; j<artist_name.length; j++){
            artist_name[j] = audioList.get(j).getArtist();
        }
//            remove duplicate artist name
        artist_name = Arrays.stream(artist_name).distinct().toArray(String[]::new);

        for (int i=0; i<Math.ceil((double) artist_name.length/3); i++) {
//                Row 1
            // Create row by LinearLayout
            LinearLayout row = new LinearLayout(this.getActivity());
            RelativeLayout.LayoutParams row_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,256);
//                LinearLayout.LayoutParams row_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            row.setPadding(5,5,5,5);
            row.setLayoutParams(row_params);
            row.setOrientation(LinearLayout.VERTICAL);

            // Create layout contains Image and Artists
            LinearLayout NameField = new LinearLayout(this.getActivity());
            RelativeLayout.LayoutParams nameFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            NameField.setOrientation(LinearLayout.VERTICAL);
            nameFieldParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            nameFieldParams.addRule(RelativeLayout.CENTER_VERTICAL);
            NameField.setLayoutParams(nameFieldParams);

            LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(128, 128);
            img_params.gravity = Gravity.CENTER;
            img_params.bottomMargin = 20;
            LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            name_params.gravity = Gravity.CENTER;

            //Image
            ImageButton img = new ImageButton(this.getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setBackgroundColor(Color.WHITE);
            img.setImageResource(R.drawable.karaoke);
            img.setLayoutParams(img_params);
            NameField.addView(img);

            // Artist
            TextView artist = new TextView(this.getContext());
            artist.setText(artist_name[i]);
            artist.setLayoutParams(name_params);
            NameField.addView(artist);

            row.addView(NameField);
            column1.addView(row);
        }

        for (int i = (int) Math.ceil((double) artist_name.length/3); i<Math.ceil((double) 2*artist_name.length/3); i++){
            LinearLayout row = new LinearLayout(this.getActivity());
            RelativeLayout.LayoutParams row_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,256);
//                LinearLayout.LayoutParams row_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(row_params);
            row.setPadding(5,5,5,5);
            row.setOrientation(LinearLayout.VERTICAL);

            // Create layout contains Image and Artists
            LinearLayout NameField = new LinearLayout(this.getActivity());
            RelativeLayout.LayoutParams nameFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            NameField.setOrientation(LinearLayout.VERTICAL);
            nameFieldParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            nameFieldParams.addRule(RelativeLayout.CENTER_VERTICAL);
            NameField.setLayoutParams(nameFieldParams);

            LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(128, 128);
            img_params.gravity = Gravity.CENTER;
            img_params.bottomMargin = 20;
            LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            name_params.gravity = Gravity.CENTER;

            //Image
            ImageButton img = new ImageButton(this.getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setBackgroundColor(Color.WHITE);
            img.setImageResource(R.drawable.karaoke);
            img.setLayoutParams(img_params);
            NameField.addView(img);

            // Artist
            TextView artist = new TextView(this.getContext());
            artist.setText(artist_name[i]);
            artist.setLayoutParams(name_params);
            NameField.addView(artist);

            row.addView(NameField);
            column2.addView(row);
        }

        for (int i = (int) Math.ceil((double) 2*artist_name.length/3); i<artist_name.length; i++){
            LinearLayout row = new LinearLayout(this.getActivity());
            RelativeLayout.LayoutParams row_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,256);
//                LinearLayout.LayoutParams row_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(row_params);
            row.setPadding(5,5,5,5);
            row.setOrientation(LinearLayout.VERTICAL);

            // Create layout contains Image and Artists
            LinearLayout NameField = new LinearLayout(this.getActivity());
            RelativeLayout.LayoutParams nameFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            NameField.setOrientation(LinearLayout.VERTICAL);
            nameFieldParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            nameFieldParams.addRule(RelativeLayout.CENTER_VERTICAL);
            NameField.setLayoutParams(nameFieldParams);

            LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(128, 128);
            img_params.gravity = Gravity.CENTER;
            img_params.bottomMargin = 20;
            LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            name_params.gravity = Gravity.CENTER;

            //Image
            ImageButton img = new ImageButton(this.getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setBackgroundColor(Color.WHITE);
            img.setImageResource(R.drawable.karaoke);
            img.setLayoutParams(img_params);
            NameField.addView(img);

            // Artist
            TextView artist = new TextView(this.getContext());
            artist.setText(artist_name[i]);
            artist.setLayoutParams(name_params);
            NameField.addView(artist);

            row.addView(NameField);
            column3.addView(row);
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
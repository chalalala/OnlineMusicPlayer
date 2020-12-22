package vn.edu.usth.onlinemusicplayer;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;

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


        AssetManager assetManager = getContext().getAssets();

        try{
            String[] songs = assetManager.list("musics");
            String[] songs_detail;
            String[] artist_name = new String[songs.length];

            for (int j=0; j<songs.length; j++){
                songs_detail = songs[j].replace(".mp3", "").split(" - ");
                artist_name[j] = songs_detail[0];
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

////                Row 2
//
//                LinearLayout row2 = new LinearLayout(this.getActivity());
//                row2.setLayoutParams(row_params);
//                row2.setOrientation(LinearLayout.VERTICAL);
//
//                // Create 3 columns per row
//
//                // Create layout contains Image and Artists
//                LinearLayout NameField2 = new LinearLayout(this.getActivity());
//                NameField2.setOrientation(LinearLayout.VERTICAL);
//                NameField2.setLayoutParams(nameFieldParams);
//
//
//                //Image
//                ImageButton img2 = new ImageButton(this.getContext());
//                img2.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                img2.setBackgroundColor(Color.WHITE);
//                img2.setImageResource(R.drawable.karaoke);
//                img2.setLayoutParams(img_params);
//                NameField2.addView(img2);
//
//                // Artist
//                TextView artist2 = new TextView(this.getContext());
//                artist2.setText(artist_name[i]);
//                artist2.setLayoutParams(name_params);
//                NameField2.addView(artist2);
//
//                row2.addView(NameField2);
//                column2.addView(row2);
//
////                Row 3
//                LinearLayout row3 = new LinearLayout(this.getActivity());
////                LinearLayout.LayoutParams row_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                row3.setLayoutParams(row_params);
//                row3.setOrientation(LinearLayout.VERTICAL);
//
//                // Create 3 columns per row
//
//                // Create layout contains Image and Artists
//                LinearLayout NameField3 = new LinearLayout(this.getActivity());
////                RelativeLayout.LayoutParams nameFieldParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//                NameField3.setOrientation(LinearLayout.VERTICAL);
////                nameFieldParams.addRule(RelativeLayout.CENTER_VERTICAL);
//                NameField3.setLayoutParams(nameFieldParams);
//
////                LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(120, 120);
////                LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
////                String[] songs_detail = songs[i].replace(".mp3", "").split(" - ");
//
//                //Image
//                ImageButton img3 = new ImageButton(this.getContext());
//                img3.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                img3.setBackgroundColor(Color.WHITE);
//                img3.setImageResource(R.drawable.karaoke);
//                img3.setLayoutParams(img_params);
//                NameField3.addView(img3);
//
//                // Artist
//                TextView artist3 = new TextView(this.getContext());
//                artist3.setText(artist_name[i]);
//                artist3.setLayoutParams(name_params);
//                NameField3.addView(artist3);
//
//                row3.addView(NameField3);
//                column3.addView(row3);
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


        } catch (IOException e){
            e.printStackTrace();
        }


        return view;
    }
}
package vn.edu.usth.onlinemusicplayer.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;

public class UpdatedHomeFragment extends Fragment {
    //Inflate the layout

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the view
        View view = inflater.inflate(R.layout.fragment_updated_home_screen,container,false);
        //Top-song:(Album cover)
        ImageView top_first_song = view.findViewById(R.id.first_top);
        ImageView top_second_song = view.findViewById(R.id.second_top);
        ImageView top_third_song = view.findViewById(R.id.third_top);
        ImageView top_fourth_song = view.findViewById(R.id.fourth_top);
        ImageView top_fifth_song = view.findViewById(R.id.fifth_top);
        ImageView top_sixth_song = view.findViewById(R.id.sixth_top);

        //Top-song name
        TextView top_first_name = view.findViewById(R.id.top_first_name);
        TextView top_second_name = view.findViewById(R.id.top_second_name);
        TextView top_third_name = view.findViewById(R.id.top_third_name);
        TextView top_fourth_name = view.findViewById(R.id.top_fourth_name);
        TextView top_fifth_name = view.findViewById(R.id.top_fifth_name);
        TextView top_sixth_name = view.findViewById(R.id.top_sixth_name);
        //Top-song Artist
        TextView top_first_artist = view.findViewById(R.id.first_top_artist);
        TextView top_second_artist = view.findViewById(R.id.second_top_artist);
        TextView top_third_artist = view.findViewById(R.id.third_top_artist);
        TextView top_fourth_artist = view.findViewById(R.id.fourth_top_artist);
        TextView top_fifth_artist = view.findViewById(R.id.fifth_top_artist);
        TextView top_sixth_artist = view.findViewById(R.id.sixth_top_artist);

        //Artist-Cover
        ImageView firstArtist = view.findViewById(R.id.first_artist);
        ImageView secondArtist = view.findViewById(R.id.second_artist);
        ImageView thirdArtist = view.findViewById(R.id.third_artist);
        ImageView fourthArtist = view.findViewById(R.id.fourth_artist);
        ImageView fifthArtist = view.findViewById(R.id.fifth_artist);
        ImageView sixthArtist = view.findViewById(R.id.sixth_artist);

        //Artist-Name
        TextView firstArtist_name = view.findViewById(R.id.first_artist_name);
        TextView secondArtist_name = view.findViewById(R.id.second_artist_name);
        TextView thirdArtist_name = view.findViewById(R.id.third_artist_name);
        TextView fourthArtist_name = view.findViewById(R.id.fourth_artist_name);
        TextView fifthArtist_name = view.findViewById(R.id.fifth_artist_name);
        TextView sixthArtist_name = view.findViewById(R.id.sixth_artist_name);

        //Initial Request Queue
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "https://mp3.zing.vn/xhr/chart-realtime?songId=0&videoId=0&albumId=0&chart=song&time=-1";
        String url1 = "https://api.deezer.com/artist/1";
        String url2 = "https://api.deezer.com/artist/2";
        String url3 = "https://api.deezer.com/artist/3";
        String url4 = "https://api.deezer.com/artist/4";
        String url5 = "https://api.deezer.com/artist/5";
        String url6 = "https://api.deezer.com/artist/6";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            ArrayList<String> songs_name = new ArrayList<String>();
            ArrayList<String> artist_name = new ArrayList<String>();
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj = new JSONObject(response);
                    JSONObject data = obj.getJSONObject("data");
                    JSONArray list_songs = data.getJSONArray("song");
                    ArrayList<Bitmap> album_covers = new ArrayList<Bitmap>();
                    for (int i = 0; i < 6; i++){
                        JSONObject item = list_songs.getJSONObject(i);
                        songs_name.add(item.getString("name"));
                        artist_name.add(item.getString("artists_names"));
                        Response.Listener<Bitmap>listener= new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                album_covers.add(response);
                                if(album_covers.size()==1){
                                    top_first_song.setImageBitmap(album_covers.get(0));
                                }
                                if(album_covers.size()==2){
                                    top_second_song.setImageBitmap(album_covers.get(1));
                                }
                                if(album_covers.size()==3){
                                    top_third_song.setImageBitmap(album_covers.get(2));
                                }
                                if(album_covers.size()==4){
                                    top_fourth_song.setImageBitmap(album_covers.get(3));
                                }
                                if(album_covers.size()==5){
                                    top_fifth_song.setImageBitmap(album_covers.get(4));
                                }
                                if(album_covers.size()==6){
                                    top_sixth_song.setImageBitmap(album_covers.get(5));
                                }
                            }
                        };
                        ImageRequest imageRequest = new ImageRequest(
                                item.getString("thumbnail"),
                                listener,50,50,ImageView.ScaleType.CENTER,
                                Bitmap.Config.ARGB_8888,null);
                        queue.add(imageRequest);
                    }
                    Log.i("list", songs_name.get(0));
                    top_first_name.setText(songs_name.get(0));
                    top_second_name.setText(songs_name.get(1));
                    top_third_name.setText(songs_name.get(2));
                    top_fourth_name.setText(songs_name.get(3));
                    top_fifth_name.setText(songs_name.get(4));
                    top_sixth_name.setText(songs_name.get(5));
                    top_first_artist.setText(artist_name.get(0));
                    top_second_artist.setText(artist_name.get(1));
                    top_third_artist.setText(artist_name.get(2));
                    top_fourth_artist.setText(artist_name.get(3));
                    top_fifth_artist.setText(artist_name.get(4));
                    top_sixth_artist.setText(artist_name.get(5));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    firstArtist_name.setText(obj.getString("name"));
                    Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            firstArtist.setImageBitmap(response);
                        }
                    };
                    ImageRequest imageRequest = new ImageRequest(obj.getString("picture"),bitmapListener,
                            100,100, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                    queue.add(imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    secondArtist_name.setText(obj.getString("name"));
                    Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            secondArtist.setImageBitmap(response);
                        }
                    };
                    ImageRequest imageRequest = new ImageRequest(obj.getString("picture"),bitmapListener,
                            100,100, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                    queue.add(imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    thirdArtist_name.setText(obj.getString("name"));
                    Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            thirdArtist.setImageBitmap(response);
                        }
                    };
                    ImageRequest imageRequest = new ImageRequest(obj.getString("picture"),bitmapListener,
                            100,100, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                    queue.add(imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        StringRequest stringRequest4 = new StringRequest(Request.Method.GET, url4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    fourthArtist_name.setText(obj.getString("name"));
                    Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            fourthArtist.setImageBitmap(response);
                        }
                    };
                    ImageRequest imageRequest = new ImageRequest(obj.getString("picture"),bitmapListener,
                            100,100, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                    queue.add(imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        StringRequest stringRequest5 = new StringRequest(Request.Method.GET, url5, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    fifthArtist_name.setText(obj.getString("name"));
                    Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            fifthArtist.setImageBitmap(response);
                        }
                    };
                    ImageRequest imageRequest = new ImageRequest(obj.getString("picture"),bitmapListener,
                            100,100, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                    queue.add(imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        StringRequest stringRequest6 = new StringRequest(Request.Method.GET, url6, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    sixthArtist_name.setText(obj.getString("name"));
                    Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            sixthArtist.setImageBitmap(response);
                        }
                    };
                    ImageRequest imageRequest = new ImageRequest(obj.getString("picture"),bitmapListener,
                            100,100, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                    queue.add(imageRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        queue.add(stringRequest);
        queue.add(stringRequest1);
        queue.add(stringRequest2);
        queue.add(stringRequest3);
        queue.add(stringRequest4);
        queue.add(stringRequest5);
        queue.add(stringRequest6);
        return view;
    }

}

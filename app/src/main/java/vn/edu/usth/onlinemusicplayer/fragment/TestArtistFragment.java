package vn.edu.usth.onlinemusicplayer.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

import java.util.ArrayList;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistGridViewAdapter;
import vn.edu.usth.onlinemusicplayer.adapter.ArtistSongsAdapter;
import vn.edu.usth.onlinemusicplayer.adapter.TopTrackAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestArtistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestArtistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestArtistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestArtistFragment newInstance(String param1, String param2) {
        TestArtistFragment fragment = new TestArtistFragment();
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
        View view = inflater.inflate(R.layout.fragment_test_artist, container, false);

        // Spinner that appears while waiting for the data
//        ProgressBar spinner = view.findViewById(R.id.spinner);

        // Initialize list containing artist detail
        ArrayList<String> artist = new ArrayList<String>();
        ArrayList<Bitmap> images = new ArrayList<Bitmap>();

        // once, should be performed once per app instance
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        // get 10 artist for test
//        for (int i=1; i<=10; i++){
//        String url = "https://api.deezer.com/artist/1";
//        String url2 = "https://api.deezer.com/artist/2";
//        String url3 = "https://api.deezer.com/artist/3";
//        String url4 = "https://api.deezer.com/artist/4";
//        String url5 = "https://api.deezer.com/artist/5";
//        String url6 = "https://api.deezer.com/artist/6";
//        String url7 = "https://api.deezer.com/artist/7";
//        String url8 = "https://api.deezer.com/artist/8";
//        String url9 = "https://api.deezer.com/artist/9";
//        String url10 = "https://api.deezer.com/artist/10";
//
//            StringRequest stringRequest1 = new StringRequest(Request.Method.GET,url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject obj = new JSONObject(response);
//                                artist.add(obj.getString("name"));
//
//                                Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                    @Override
//                                    public void onResponse(Bitmap response) {
//                                        images.add(response);
//                                        GridView gridView = getView().findViewById(R.id.artist_grid);
//                                        ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                        gridView.setAdapter(artistGridViewAdapter);
//                                    }
//                                };
//                                ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                        0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                                queue.add(imageRequest);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//        StringRequest stringRequest2 = new StringRequest(Request.Method.GET,url2,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest3 = new StringRequest(Request.Method.GET,url3,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest4 = new StringRequest(Request.Method.GET,url4,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest5 = new StringRequest(Request.Method.GET,url5,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest6 = new StringRequest(Request.Method.GET,url6,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest7 = new StringRequest(Request.Method.GET,url7,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest8 = new StringRequest(Request.Method.GET,url8,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest9 = new StringRequest(Request.Method.GET,url9,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        StringRequest stringRequest10 = new StringRequest(Request.Method.GET,url10,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            artist.add(obj.getString("name"));
//
//                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
//                                @Override
//                                public void onResponse(Bitmap response) {
//                                    images.add(response);
//                                    GridView gridView = getView().findViewById(R.id.artist_grid);
//                                    ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images);
//                                    gridView.setAdapter(artistGridViewAdapter);
//                                }
//                            };
//                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
//                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
//                            queue.add(imageRequest);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
//                    }
//                });
//// go!
//        queue.add(stringRequest1);
//        queue.add(stringRequest2);
//        queue.add(stringRequest3);
//        queue.add(stringRequest4);
//        queue.add(stringRequest5);
//        queue.add(stringRequest6);
//        queue.add(stringRequest7);
//        queue.add(stringRequest8);
//        queue.add(stringRequest9);
//        queue.add(stringRequest10);

        String url = "https://api.deezer.com/artist/1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            artist.add(obj.getString("name"));

                            Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    images.add(response);



                                        try {
                                            GridView gridView = getView().findViewById(R.id.artist_grid);
                                            ArtistGridViewAdapter artistGridViewAdapter = new ArtistGridViewAdapter(getContext(),artist,images)
                                            gridView.setAdapter(artistGridViewAdapter);
                                        } catch (Exception e) {
                                        }
                                    }

                                }
                            };
                            ImageRequest imageRequest = new ImageRequest(obj.getString("picture_medium"),bitmapListener,
                                    0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888,null);
                            queue.add(imageRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Some error occur", Toast.LENGTH_SHORT).show();
                    }
                });
// go!
        queue.add(stringRequest);
//        spinner.setVisibility(View.GONE);
        return view;
    }
}
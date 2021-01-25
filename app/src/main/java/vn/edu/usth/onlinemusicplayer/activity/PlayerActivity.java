package vn.edu.usth.onlinemusicplayer.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.edu.usth.onlinemusicplayer.R;
import vn.edu.usth.onlinemusicplayer.adapter.SectionsPageAdapter;
import vn.edu.usth.onlinemusicplayer.fragment.AlbumListFragment;
import vn.edu.usth.onlinemusicplayer.fragment.ArtistListFragment;
import vn.edu.usth.onlinemusicplayer.fragment.SongListFragment;
import vn.edu.usth.onlinemusicplayer.fragment.MusicPlayerFragment;


public class PlayerActivity extends MusicServiceActivity {
    public static final String TAG = PlayerActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    @Override
    protected void onResume() {
        super.onResume();
        removeNotification();
    }

    public void removeNotification() {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(12302);
    }

    @Override
    public void onServiceConnected() {
        Log.d(TAG,"onService Connected");
        handleAllView();
    }

    public void handleAllView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Fragment musicPlayerFragment = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (musicPlayerFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, new MusicPlayerFragment(), "MusicPlayer").commit();
            Log.d(TAG, "musicPlayerFragment Fragment new created");
        } else {
            Log.d(TAG, "musicPlayerFragment Fragment reused ");
        }

    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        SongListFragment songListFragment;
        AlbumListFragment albumListFragment;
        ArtistListFragment artistListFragment;
        songListFragment = new SongListFragment();
        albumListFragment = new AlbumListFragment();
        artistListFragment = new ArtistListFragment();
        adapter.addFragment(songListFragment, "All Songs");
        adapter.addFragment(albumListFragment, "Albums");
        adapter.addFragment(artistListFragment, "Artist");
        viewPager.setAdapter(adapter);
    }

    public Fragment findWithId(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }

}


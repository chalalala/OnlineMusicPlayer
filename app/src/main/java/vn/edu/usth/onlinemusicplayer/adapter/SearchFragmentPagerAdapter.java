package vn.edu.usth.onlinemusicplayer.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vn.edu.usth.onlinemusicplayer.fragment.SearchAlbumFragment;
import vn.edu.usth.onlinemusicplayer.fragment.SearchArtistFragment;
import vn.edu.usth.onlinemusicplayer.fragment.TopTracksFragment;

public class SearchFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String[] title = {"Songs", "Artists", "Album"};
    public SearchFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return PAGE_COUNT; // number of pages for a ViewPager
    }
    @Override
    public Fragment getItem(int page) {
        // returns an instance of Fragment corresponding to the specified page
        switch (page) {
            case 0: return TopTracksFragment.newInstance();
            case 1: return SearchArtistFragment.newInstance();
            case 2: return SearchAlbumFragment.newInstance();

            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int page) {
        return title[page];
    }
}
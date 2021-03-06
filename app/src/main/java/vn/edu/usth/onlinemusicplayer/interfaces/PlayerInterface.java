package vn.edu.usth.onlinemusicplayer.interfaces;

import vn.edu.usth.onlinemusicplayer.model.SongModel;

public interface PlayerInterface {

    void start();
    void play(long songId);

//    void replay(long songId);

    void play(SongModel song);
//    void replay(SongModel song);
    void pause();
    void stop();
    void seekTo(int position);
    boolean isPlaying();
    long getDuration();
    int getCurrentStreamPosition();
    void setCallback(Callback callback);

    interface Callback {
        void onCompletion(SongModel song);
        void onTrackChange(SongModel song);
        void onPause();
    }
}


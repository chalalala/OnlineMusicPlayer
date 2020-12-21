package vn.edu.usth.onlinemusicplayer.model;

public class TrackModel {
    private final String title;
    //    private final int duration;
    private final String artistName;

    public TrackModel(String title, String artistName) {
        this.title = title;
//        this.duration = duration;
        this.artistName = artistName;
    }

    public String getTitle() {
        return title;
    }

//    public int getDuration() {
//        return duration;
//    }

    public String getArtistName(){
        return artistName;
    }

}

package vn.edu.usth.onlinemusicplayer.model;

public class TrackModel {
    private final String id;
    private final String title;
    //    private final int duration;
    private final String artistName;

    public TrackModel(String id, String title, String artistName) {
        this.id = id;
        this.title = title;
//        this.duration = duration;
        this.artistName = artistName;
    }

    public String getId() {
        return id;
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

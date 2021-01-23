package vn.edu.usth.onlinemusicplayer;

import android.app.Application;

import com.google.firebase.auth.FirebaseUser;

public class MusicPlayerApplication extends Application {
    private FirebaseUser currentUser;

    public void setCurrentUser(FirebaseUser currentUser){
        this.currentUser = currentUser;
    }
}

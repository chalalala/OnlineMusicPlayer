package vn.edu.usth.onlinemusicplayer;

import android.view.View;

public class CustomOnClickListener implements View.OnClickListener{
    public int param;
    public CustomOnClickListener(int param){
        this.param = param;
    }

    @Override
    public void onClick(View v){
    }
}

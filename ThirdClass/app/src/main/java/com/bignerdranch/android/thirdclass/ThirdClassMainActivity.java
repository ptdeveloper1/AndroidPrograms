package com.bignerdranch.android.thirdclass;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static android.R.attr.id;
public class ThirdClassMainActivity extends AppCompatActivity {

    TextView mtextview;
    Button mbutton;
    Counter c;
    MediaPlayer player;
    //We have an abstract in AppCompatActivity, onCreate is the first method that executes first

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_third_class_main);//loads user interface
        mtextview=(TextView)findViewById(R.id.text_Id);
        mbutton=(Button)findViewById(R.id.button_Id);
         c=new Counter();
    }
    public void pressButton(View v) {
        //mtextview.setText("iii");
        c.update();
        mtextview.setText(" "+c.getCount());
        player= MediaPlayer.create(this,R.raw.click);

        player.start();
    }
    public void stopPlaying()
    {
        if(player!=null)
        {
            player.stop();
            player.release();
            player=null;
        }
    }
}
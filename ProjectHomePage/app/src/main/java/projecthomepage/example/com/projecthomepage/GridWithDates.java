package projecthomepage.example.com.projecthomepage;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Timer;
import java.util.TimerTask;

import static android.widget.GridLayout.spec;

/**
 * Created by ADMIN on 7/1/2017.
 */

public class GridWithDates extends AppCompatActivity{
    Button[][] buttons;
    Button mbutton,okbutton,back;;
    int width;
    Point size;
   public static String firstDate;
    private GridLayout gl;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BuildGridByCode();
        build21stButton();
        setDates();
        buildOkButton();
        buildBackButton();
        setContentView(gl);
        alertAboutNotif();
        okbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridWithDates.this, AlarmFirstClass.class);
                startActivity(intent);
                GridWithDates.this.finish();
            }
        });
    }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void BuildGridByCode(){
            buttons=new Button[4][5];
            size=new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            width=size.x/5;
            int height=width;
            gl=new GridLayout(this);
            gl.setRowCount(7);
            gl.setColumnCount(5);
            for(int row=0;row<4;row++){
                for(int col=0;col<5;col++){
                    buttons[row][col]=new Button(this);
                    buttons[row][col].setBackgroundResource(R.drawable.circle);
                    gl.addView(buttons[row][col],width,width);
                }
            }
        }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void buildOkButton(){
            GridLayout.Spec rowspec2 = spec(5, 1);
            GridLayout.Spec colspec2 = spec(2, 2);
            GridLayout.LayoutParams lp1 = new GridLayout.LayoutParams(rowspec2, colspec2);
            okbutton = new Button(this);
            okbutton.setLayoutParams(lp1);
            okbutton.setWidth((int) (width * 1.5));
            okbutton.setHeight((int) (width*0.7));
            okbutton.setText("OK");
            okbutton.setBackgroundColor(Color.parseColor("#ffa500"));
            gl.addView(okbutton);
    }
    private void setDates() {
        int dateValue = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                if (row ==0 && col==0) {
                    buttons[row][col].setText(GridWithDates.firstDate);
                }
                else
                    buttons[row][col].setText(" " + (++dateValue));

            }
        }

    }
    private void alertAboutNotif(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("You will be NOTIFIED for 21 Days! \n GOOD LUCK \n  Press OK");
        builder.setCancelable(true);
        final AlertDialog dlg = builder.create();
        dlg.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 4000);

    }
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void build21stButton(){

        GridLayout.Spec rowspec = spec(4, 1);
        GridLayout.Spec colspec = spec(0, 5);
        GridLayout.LayoutParams lp=new GridLayout.LayoutParams(rowspec,colspec);
        mbutton=new Button(this);
        mbutton.setLayoutParams(lp);
        mbutton.setWidth(size.x);
        mbutton.setHeight(width);
        mbutton.setBackgroundColor(Color.rgb(230,230,250));
        // mbutton.setGravity(Gravity.LEFT);
        mbutton.setText("21");
        gl.addView(mbutton);
        // gl.setBackgroundColor(Color.GRAY);
        gl.setBackgroundResource(R.drawable.images);
    }
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void buildBackButton(){
        GridLayout.Spec rowspec4 = spec(6, 1);
        GridLayout.Spec colspec4 = spec(0, 5);
        GridLayout.LayoutParams backlp=new GridLayout.LayoutParams(rowspec4,colspec4);
        back=new Button(this);
        back.setLayoutParams(backlp);
        back.setWidth(size.x);
        back.setHeight(width);
        back.setBackgroundColor(Color.DKGRAY);
        back.setTextColor(Color.WHITE);
        back.setGravity(Gravity.CENTER);
        back.setText("Check all habits");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(GridWithDates.this,HabitListActivity.class);
                startActivity(myIntent);
            }
        });
        gl.addView(back);
    }


}

package projecthomepage.example.com.projecthomepage;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.GREEN;
import static android.widget.GridLayout.spec;

/**
 * Created by ADMIN on 7/4/2017.
 */

public class AlarmGridDates extends AppCompatActivity {
    Button[][] buttons;
    Button mbutton,mbutton1,mbutton2;
    private GridLayout gl;
    int width,height;
   public static String firstGridDate;
    Point size;
            int result=0;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BuildGridByCode();
        build21stButton();
        setDates();
        setDidButton();
        setMissedButton();
        setContentView(gl);
       /* mbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 5; col++) {
                    result=Integer.parseInt(buttons[row][col].getText().toString() + result);
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AlarmGridDates.this);
                builder.setTitle("");
                if(result > 21/2)
                    builder.setMessage(" You have completed 21days of challenge /n" +
                        "and you have succeeded moret then you missed");
                if(result < 21/2)
                    builder.setMessage(" You have completed 21days of challenge /n" +
                            "and missed more days \n take up the challenge again");
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();
                dlg.show();
                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 5000);
            }
        });*/
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 5; col++) {
                        if ((buttons[row][col].getDrawingCacheBackgroundColor() != Color.YELLOW) &&
                                (buttons[row][col].getDrawingCacheBackgroundColor() != Color.BLUE)) {
                            buttons[row][col].setText("" + 1);
                            buttons[row][col].setTextColor(Color.BLACK);
                            buttons[row][col].setBackgroundColor(Color.YELLOW);
                            count++;
                            AlertDialog.Builder builder = new AlertDialog.Builder(AlarmGridDates.this);
                            builder.setTitle("");
                            builder.setMessage(" WOW, You made it :)");
                            builder.setCancelable(true);
                            final AlertDialog dlg = builder.create();
                            dlg.show();
                            final Timer t = new Timer();
                            t.schedule(new TimerTask() {
                                public void run() {
                                    dlg.dismiss(); // when the task active then close the dialog
                                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                                }
                            }, 5000);

                        }
                        if (count == 1)
                            break;
                    }
                    if (count == 1)
                        break;
                }
                if(v==mbutton){
                    mbutton.setText(""+ +1);
                    mbutton.setTextColor(Color.BLACK);
                    mbutton.setBackgroundColor(Color.YELLOW);
                }
            }

        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 5; col++) {
                        if ((buttons[row][col].getDrawingCacheBackgroundColor() != Color.YELLOW) &&
                                (buttons[row][col].getDrawingCacheBackgroundColor() != Color.CYAN)) {
                            buttons[row][col].setText("" + -1);
                            buttons[row][col].setTextColor(Color.BLACK);
                            buttons[row][col].setBackgroundColor(Color.CYAN);
                            count++;
                            AlertDialog.Builder builder = new AlertDialog.Builder(AlarmGridDates.this);
                            builder.setTitle(" ");
                            builder.setMessage(" MISSED It,:( \n No problem \n You can do it Tomorrow");
                            builder.setCancelable(true);
                            final AlertDialog dlg = builder.create();
                            dlg.show();
                            final Timer t = new Timer();
                            t.schedule(new TimerTask() {
                                public void run() {
                                    dlg.dismiss(); // when the task active then close the dialog
                                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                                }
                            }, 5000);
                        }
                        if (count == 1)
                            break;
                    }
                    if (count == 1)
                        break;
                }
                if(v==mbutton){
                    mbutton.setText(""+ -1);
                    mbutton.setTextColor(Color.BLACK);
                    mbutton.setBackgroundColor(Color.CYAN);
                }
            }

        });
    }

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
    public void build21stButton(){
        gl.setRowCount(7);
        gl.setColumnCount(5);
        GridLayout.Spec rowspec = spec(4, 1);
        GridLayout.Spec colspec = spec(0, 5);
        GridLayout.LayoutParams lp=new GridLayout.LayoutParams(rowspec,colspec);
        mbutton=new Button(this);
        mbutton.setLayoutParams(lp);
        mbutton.setWidth(size.x);
        mbutton.setHeight(width);
        mbutton.setBackgroundColor(Color.rgb(230,230,250));
        mbutton.setText("21");
        gl.addView(mbutton);
        gl.setBackgroundResource(R.drawable.images);
    }

    private void setDates() {
        int dateValue = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                if (row ==0 && col==0) {
                    buttons[row][col].setText(firstGridDate);
                }
                else
                    buttons[row][col].setText(" " + (++dateValue));

            }
        }

    }
    private void setDidButton(){
        gl.setRowCount(7);
        gl.setColumnCount(5);
        GridLayout.Spec rowspecbtn1 = spec(6, 1);
        GridLayout.Spec colspecbtn1 = spec(0, 2);
        GridLayout.LayoutParams lpbtn1=new GridLayout.LayoutParams(rowspecbtn1,colspecbtn1);
        mbutton1=new Button(this);
        mbutton1.setLayoutParams(lpbtn1);
        mbutton1.setWidth((int) (width*1.5));
        mbutton1.setHeight((int) (width*0.7));
        mbutton1.setText("DID IT :)");
        mbutton1.setBackgroundColor(Color.YELLOW);
        gl.addView(mbutton1);

    }
    private void setMissedButton(){
        gl.setRowCount(7);
        gl.setColumnCount(5);
        GridLayout.Spec rowspecbtn2 = spec(6, 1);
        GridLayout.Spec colspecbtn2 = spec(2, 2);
        GridLayout.LayoutParams lpbtn2=new GridLayout.LayoutParams(rowspecbtn2,colspecbtn2);
        mbutton2=new Button(this);
        mbutton2.setLayoutParams(lpbtn2);
        mbutton2.setWidth((int) (width*1.5));
        mbutton2.setHeight((int) (width*0.7));
        mbutton2.setText("MISSED IT :(");
        mbutton2.setBackgroundColor(Color.CYAN);
        gl.addView(mbutton2);

    }



}

package notificationapp.example.com.notificationapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import static android.graphics.Color.GREEN;
import static android.widget.GridLayout.spec;

/**
 * Created by ADMIN on 7/3/2017.
 */

public class GridDates extends AppCompatActivity {

    Button[][] buttons;
    Button mbutton,mbutton1,mbutton2;
    private GridLayout gl;
    int width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BuildGridByCode();
        setContentView(gl);
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                for(int row=0;row<4;row++){
                    for(int col=0;col<5;col++){
                        if(row==0 && col==0) {
                            if ((buttons[0][0].getDrawingCacheBackgroundColor() != Color.GREEN)&&
                                    (buttons[0][0].getDrawingCacheBackgroundColor() != Color.RED)) {
                                buttons[row][col].setTextColor(Color.WHITE);
                                buttons[0][0].setBackgroundColor(GREEN);
                                count++;
                            } else {
                                if ((buttons[row][col].getDrawingCacheBackgroundColor() != Color.GREEN)&&
                                        (buttons[row][col].getDrawingCacheBackgroundColor() != Color.RED)) {
                                    buttons[row][col].setText(""+1);
                                    buttons[row][col].setTextColor(Color.WHITE);
                                    buttons[row][col].setBackgroundColor(GREEN);
                                    count++;
                                }
                            }
                        }
                      if(count==1)
                          break;
                    }
                    if(count==1)
                        break;
                }
                if(v==mbutton){
                    mbutton.setText(""+ -1);
                    mbutton.setTextColor(Color.WHITE);
                    mbutton.setBackgroundColor(Color.GREEN);
                }
            }
        });

        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                for(int row=0;row<4;row++){
                    for(int col=0;col<5;col++){
                        if(row==0 && col==0) {
                            if ((buttons[0][0].getDrawingCacheBackgroundColor() != Color.GREEN)&&
                                    (buttons[0][0].getDrawingCacheBackgroundColor() != Color.RED)) {
                                buttons[row][col].setText(""+ -1);
                                buttons[row][col].setTextColor(Color.WHITE);
                                buttons[0][0].setBackgroundColor(Color.RED);
                                count++;
                            } else {
                                if ((buttons[row][col].getDrawingCacheBackgroundColor() != Color.GREEN)&&
                                        (buttons[row][col].getDrawingCacheBackgroundColor() != Color.RED)) {
                                    buttons[row][col].setText(""+ -1);
                                    buttons[row][col].setTextColor(Color.WHITE);
                                    buttons[row][col].setBackgroundColor(Color.RED);
                                    count++;
                                }
                            }
                        }
                        if(count==1)
                            break;
                    }
                    if(count==1)
                        break;
                }
                if(v==mbutton){
                    mbutton.setText(""+ -1);
                    mbutton.setTextColor(Color.WHITE);
                    mbutton.setBackgroundColor(Color.RED);
                }
            }
        });


    }
    private void BuildGridByCode(){
        buttons=new Button[4][5];
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        width=size.x/5;
        height=width;
        gl=new GridLayout(this);
        gl.setRowCount(4);
        gl.setColumnCount(5);
        for(int row=0;row<4;row++){
            for(int col=0;col<5;col++){
                buttons[row][col]=new Button(this);
                gl.addView(buttons[row][col],width,width);
               // buttons[row][col].setBackgroundColor(Color.GRAY);
            }
        }

        gl.setRowCount(5);
        gl.setColumnCount(5);
        GridLayout.Spec rowspec = spec(4, 1);
        GridLayout.Spec colspec = spec(0, 1);
        GridLayout.LayoutParams lp=new GridLayout.LayoutParams(rowspec,colspec);
        mbutton=new Button(this);
        mbutton.setLayoutParams(lp);
        mbutton.setWidth((int) (width*0.99));
        mbutton.setHeight(width);
        mbutton.setText("21");
        gl.addView(mbutton);
       // setContentView(gl);
        setDates();
        setDidButton();
        setMissedButton();
        setContentView(gl);

    }
    private void setDates(){
        int dateValue=0;
        for(int row=0;row<4;row++){
            for(int col=0;col<5;col++){
                buttons[row][col].setText(" "+ (++dateValue));

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
        mbutton1.setWidth((int) (width*2));
        mbutton1.setHeight(width);
        mbutton1.setText("DID IT :)");
       mbutton1.setBackgroundColor(GREEN);

        gl.addView(mbutton1);

    }
    private void setMissedButton(){
        GridLayout.Spec rowspecbtn2 = spec(6, 1);
        GridLayout.Spec colspecbtn2 = spec(3, 2);
        GridLayout.LayoutParams lpbtn2=new GridLayout.LayoutParams(rowspecbtn2,colspecbtn2);
        mbutton2=new Button(this);
        mbutton2.setLayoutParams(lpbtn2);
        mbutton2.setWidth((int) (width*2));
        mbutton2.setHeight(width);
        mbutton2.setText("MISSED IT :(");
      mbutton2.setBackgroundColor(Color.RED);
        gl.addView(mbutton2);

    }



}

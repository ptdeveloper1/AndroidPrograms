package tictactoe.example.com.homeworktest;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//TextView t;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildGuiByCode();
        //setContentView(R.layout.activity_main);

    }
    public void buildGuiByCode(){

        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width=(size.x)/2;
        int height=(int)(size.y*0.7)/4;
        GridLayout gl=new GridLayout(this);
        gl.setRowCount(4);
        gl.setColumnCount(2);
        Button[][] buttons=new Button[4][2];
        for(int row=0;row<4;row++){
            for(int col=0;col<2;col++){
                buttons[row][col]=new Button(this);
                gl.addView(buttons[row][col], width, height);
            }
        }
        setContentView(gl);
    }
}


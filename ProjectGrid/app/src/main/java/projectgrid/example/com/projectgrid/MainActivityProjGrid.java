package projectgrid.example.com.projectgrid;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import static android.widget.GridLayout.spec;

public class MainActivityProjGrid extends AppCompatActivity {
    Button[][] buttons;
    Button mbutton;
    private GridLayout gl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BuildGridByCode();
        setContentView(gl);
    }
    private void BuildGridByCode(){
        buttons=new Button[4][5];
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width=size.x/5;
        int height=width;
        gl=new GridLayout(this);
        gl.setRowCount(4);
        gl.setColumnCount(5);
    for(int row=0;row<4;row++){
        for(int col=0;col<5;col++){
        buttons[row][col]=new Button(this);
            gl.addView(buttons[row][col],width,width);
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
       // mbutton.setGravity(Gravity.LEFT);
        mbutton.setText("21");
        gl.addView(mbutton);
        gl.setBackgroundColor(Color.GRAY);
        setContentView(gl);
       setDates();

    }
    private void setDates(){
        int dateValue=0;
        for(int row=0;row<4;row++){
            for(int col=0;col<5;col++){
              buttons[row][col].setText(" "+ (++dateValue));

            }
        }

    }


}

package com.bignerdranch.android.twobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecProg extends AppCompatActivity {
    private Button mbutton1;
    private Button mbutton2;
    private TextView mtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_prog);

        mbutton1 = (Button) findViewById(R.id.button1);
        mbutton2 = (Button) findViewById(R.id.button2);
        mtextview = (TextView) findViewById(R.id.tv);
        buttonhandler bh=new buttonhandler();
        mbutton1.setOnClickListener(bh);
        mbutton2.setOnClickListener(bh);
    }
    public class buttonhandler implements View.OnClickListener {

            public void onClick(View v) {
             if(v==mbutton1){
                 mtextview.setText("I did it");
             }
                 else if(v==mbutton2){
                     mtextview.setText("It is SD for MD class");
                 }
             }
            }
        }

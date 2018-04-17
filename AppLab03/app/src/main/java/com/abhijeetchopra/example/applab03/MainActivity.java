package com.abhijeetchopra.example.applab03;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declaring java variables for display components
    TextView mTextViewDisplay;
    EditText mEditTextInput;
    Button mButtonStart;
    Button mButtonReset;

    //
    public long milliseconds = 0;
    public long seconds = (long) (milliseconds / 1000) % 60 ;
    public long minutes = (long) ((milliseconds / (1000*60)) % 60);
    //public long hours   = (long) ((milliseconds / (1000*60*60)) % 24);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mapping display components with java variables
        mTextViewDisplay = (TextView)findViewById(R.id.mtextViewDisplay);
        mEditTextInput = (EditText)findViewById(R.id.mEditTextInput);
        mButtonStart = (Button)findViewById(R.id.mButtonStart);
        mButtonReset = (Button)findViewById(R.id.mButtonReset);

        mButtonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000, 1000){

                    // onTick
                    public void onTick(long millisUntilFinished){

                        mTextViewDisplay.setText(String.valueOf(millisUntilFinished/1000));
                    }

                    // onFinish
                    public  void onFinish(){
                        mTextViewDisplay.setText("FINISH!!");
                    }
                }.start();
            }
        });
    }
}
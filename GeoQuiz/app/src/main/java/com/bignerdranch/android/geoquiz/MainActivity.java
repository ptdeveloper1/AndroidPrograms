
package com.bignerdranch.android.geoquiz;
import com.bignerdranch.android.mylibrary.Question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_Australia, true),
            new Question(R.string.question_Ocean, true),
            new Question(R.string.question_mideast, false),
    };
    private int mcurrentndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
       /* int question=mQuestionBank[mcurrentndex].getTextResId();
        mQuestionTextView.setText(question);*/

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               /* Toast t = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                t.show();*/
               checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Toast t = Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
                t.show();*/
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mcurrentndex = (mcurrentndex + 1) % mQuestionBank.length;
                   /* int question=mQuestionBank[mcurrentndex].getTextResId();
                mQuestionTextView.setText(question);*/
                updateQuestion();
            }
        });
        mCheatButton =(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
             Intent i=new Intent(MainActivity.this,CheatActivity.class);
                startActivity(i);
            }
        });
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mcurrentndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mcurrentndex].isAnswerTrue();
        int messageResId=0;
        if(userPressedTrue==answerIsTrue){
            messageResId=R.string.correct_toast;
        }else{
            messageResId=R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
}

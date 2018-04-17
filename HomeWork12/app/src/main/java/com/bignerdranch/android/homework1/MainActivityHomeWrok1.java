/**
 * Class MainActivityHomework1 builds an application that checks the matching of the passwords, It gives a notification
 * when the password mismatches and also when there is no input is entered in the field. There is a check box to choose
 * hide or show the password.
 * @ author: TirumalaPravalika
 * @ Asssignment CSCI 559 Homework1
 * 06/15/2017
 */

package com.bignerdranch.android.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityHomeWrok1 extends AppCompatActivity {
    EditText pmedittext;
    EditText cmedittext;
    Button mbutton;
    CheckBox mcheckb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_wrok1);
        pmedittext=(EditText) findViewById(R.id.pedittext_id);
        cmedittext=(EditText) findViewById(R.id.cedittext);
        mbutton= (Button)findViewById(R.id.button);
        mcheckb=(CheckBox)findViewById(R.id.show_hideId);

        mcheckb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if(!isChecked){
                    pmedittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cmedittext.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    pmedittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cmedittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    public void pressButton(View v){

        String s1=pmedittext.getText().toString();
        String s2=cmedittext.getText().toString();
        if(!checkValid(s1)){
            Toast.makeText(MainActivityHomeWrok1.this,R.string.blank_toast,Toast.LENGTH_LONG).show();
        } else if(!checkValid(s2)) {
            Toast.makeText(MainActivityHomeWrok1.this,R.string.blank_toast,Toast.LENGTH_LONG).show();

        }else{

                if (s1.equals(s2)) {
                Toast.makeText(MainActivityHomeWrok1.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {

                Toast.makeText(MainActivityHomeWrok1.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    pmedittext.setText(null);
                    cmedittext.setText(null);
                }
                }
    }
    public boolean checkValid(String S){

        String inputstring=S.trim();
        if((inputstring !=null && ! inputstring.isEmpty() )) {
                return true;
            } else {
                return false;
            }
        }





}

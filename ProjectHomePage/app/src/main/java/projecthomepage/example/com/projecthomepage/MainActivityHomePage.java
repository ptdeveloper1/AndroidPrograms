/**
 * Project discription: This app takes activity and time from user and reminds about the activity for 21 days. and Daily it also gives
 * a notification for marking whether the task is done or not. At the end of 21 days it tells you the number of days of doing
 * the work and number of days missed.
 * @Author: Pravalika Tirumala
 * @CSCI 559 Project- 21Day Habits
 * Date: 07/05/2017
 */
package projecthomepage.example.com.projecthomepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class MainActivityHomePage extends AppCompatActivity {

    //**************Declarations*******************
    public static List<Habit> list;

    private EditText meditname, meditpswd;
    private TextView mtextnum;
    private Button mbuttonnext;
    private int numOfAttempts = 0;
    private CheckBox mcheck;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//activity initialization
        setContentView(R.layout.activity_main_home_page);//set the activity content to a view
        list= null;
        //link to the database
        database();

//*********Retrieving widgets that need to be interacted with**************
        meditname = (EditText) findViewById(R.id.nameedit);
        meditpswd = (EditText) findViewById(R.id.pswdedit);
        mbuttonnext = (Button) findViewById(R.id.nextbtn);
        mtextnum = (TextView) findViewById(R.id.textView2);
        mcheck = (CheckBox) findViewById(R.id.checkid);

//*********To hide or show password text*************************
        mcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if(!isChecked){
                    meditpswd.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }else{
                    meditpswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

//*************validation of login credentials and allows for 3 wrong entries***************
    public void database() {
    /******************* Intialize Database *************/
   DBAdapter.init(this);
    }
    public void pressButton(View v){

        String s1=meditname.getText().toString();
        String s2=meditpswd.getText().toString();
        if(!checkValid(s1)){
            Toast.makeText(MainActivityHomePage.this,"Please enter valid field",Toast.LENGTH_LONG).show();
        } else if(!checkValid(s2)) {
            Toast.makeText(MainActivityHomePage.this,"Please enter valid field",Toast.LENGTH_LONG).show();

        }else{

            if ((s1.equalsIgnoreCase("ADMIN"))&&(s2.equals("Admin"))) {
                Toast.makeText(MainActivityHomePage.this, "Redirected...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivityHomePage.this, TitlePage.class);
                startActivity(intent);
                MainActivityHomePage.this.finish();
            } else {
                numOfAttempts++;
                mtextnum.setText("" + numOfAttempts);
                Toast.makeText(MainActivityHomePage.this,"wrong credentials", Toast.LENGTH_SHORT).show();
                if (numOfAttempts == 3) {
                    Toast.makeText(MainActivityHomePage.this, "Number of attempts exceeded, " +
                            "Try after some time...", Toast.LENGTH_SHORT).show();
                    mbuttonnext.setEnabled(false);
                }
                meditname.setText(null);
                meditpswd.setText(null);
            }
        }
    }


//***********checks whether field is empty or not***********************
    public boolean checkValid(String S){

        String inputstring=S.trim();
        if((inputstring !=null && ! inputstring.isEmpty() )) {
            return true;
        } else {
            return false;
        }
    }
}
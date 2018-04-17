package login.example.com.loginpageforproject;

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

import org.w3c.dom.Text;

public class MainActivityLoginPage extends AppCompatActivity {
    EditText meditname, meditpswd;
    TextView mtextnum;
    Button mbuttonnext;
    int numOfAttempts = 0;
    CheckBox mcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_page);

        meditname = (EditText) findViewById(R.id.nameedit);
        meditpswd = (EditText) findViewById(R.id.pswdedit);
        mbuttonnext = (Button) findViewById(R.id.nextbtn);
        mtextnum = (TextView) findViewById(R.id.textView2);
        mcheck = (CheckBox) findViewById(R.id.checkid);

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
    public void pressButton(View v){

        String s1=meditname.getText().toString();
        String s2=meditpswd.getText().toString();
        if(!checkValid(s1)){
            Toast.makeText(MainActivityLoginPage.this,"Please enter valid field",Toast.LENGTH_LONG).show();
        } else if(!checkValid(s2)) {
            Toast.makeText(MainActivityLoginPage.this,"Please enter valid field",Toast.LENGTH_LONG).show();

        }else{

            if ((s1.equalsIgnoreCase("ADMIN"))&&(s2.equals("Admin"))) {
                Toast.makeText(MainActivityLoginPage.this, "Redirecting...", Toast.LENGTH_SHORT).show();
            } else {
                numOfAttempts++;
                mtextnum.setText("" + numOfAttempts);
                Toast.makeText(MainActivityLoginPage.this,"wrong credentials", Toast.LENGTH_SHORT).show();
                if (numOfAttempts == 3) {
                    Toast.makeText(MainActivityLoginPage.this, "Number of attempts exceeded, Try after some time...", Toast.LENGTH_SHORT).show();
                    mbuttonnext.setEnabled(false);
                }
                    meditname.setText(null);
                meditpswd.setText(null);
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

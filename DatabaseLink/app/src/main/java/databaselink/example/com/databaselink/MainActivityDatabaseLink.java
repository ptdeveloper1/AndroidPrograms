package databaselink.example.com.databaselink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class MainActivityDatabaseLink extends AppCompatActivity {
    TextView mtextname;
    TextView mtextpswd;
    EditText meditname;
    EditText meditpswd;
    Button mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_database_link);

        mtextname=(TextView)findViewById(R.id.textView2);
        mtextpswd=(TextView)findViewById(R.id.textView);
        meditname=(EditText)findViewById(R.id.editname);
        meditpswd=(EditText)findViewById(R.id.editText);
        mbutton=(Button)findViewById(R.id.button);

        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String sname= String.valueOf(meditname.getText());
               // String spswd=String.valueOf(meditpswd.getText());
                String spswd=meditpswd.getText().toString();
                if(!validation(sname) || !validation(spswd)) {
                    Toast.makeText(MainActivityDatabaseLink.this,R.string.blank_toast,Toast.LENGTH_SHORT);
                }
                    else
                    if(sname=="admin" && spswd=="admin"){
                        Toast.makeText(MainActivityDatabaseLink.this,R.string.correct_toast,Toast.LENGTH_SHORT);
                    }
                }

        });
    }
    public boolean validation(String s){
        s=s.trim();
        if(!s.isEmpty() && s !=null){
            return true;
        }
        else
            return false;
    }
}

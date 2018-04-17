package database.example.com.database1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivitydatabase extends AppCompatActivity {
    DatabaseHelper mydb;

    EditText meditmarks;
    Button mbutton;
    Button mbuttondel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitydatabase);
        mydb = new DatabaseHelper(this);

        meditmarks = (EditText) findViewById(R.id.marksId);
        mbutton = (Button) findViewById(R.id.buttonId);
        mbuttondel=(Button)findViewById(R.id.delId);

        mbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            String newEntry=meditmarks.getText().toString();
                if(meditmarks.length() !=0){
                    addData(newEntry);
                    meditmarks.setText("");
                }else{
                    Toast.makeText(MainActivitydatabase.this,"field can not be blank",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mbuttondel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            Intent intent=new Intent(MainActivitydatabase.this,ListDataActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addData(String newEntry) {
        boolean insertData=mydb.insertData(newEntry);
        if(insertData==true)
            Toast.makeText(MainActivitydatabase.this,"data is inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivitydatabase.this,"data is not inserted",Toast.LENGTH_SHORT).show();
    }


}
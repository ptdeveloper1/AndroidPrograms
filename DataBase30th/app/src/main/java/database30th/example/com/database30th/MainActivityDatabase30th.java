package database30th.example.com.database30th;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityDatabase30th extends AppCompatActivity {

        private EditText medittextName;
        private EditText medittextAddress;
        private Button mbuttonAdd;
        private Button mbuttonView;

        private SQLiteDatabase db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_database30th);
            medittextName = (EditText) findViewById(R.id.nameId);
            medittextAddress = (EditText) findViewById(R.id.buttonId);
            mbuttonAdd = (Button) findViewById(R.id.addId);
            mbuttonView = (Button) findViewById(R.id.viewId);

            mbuttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == mbuttonAdd)
                        insertInToDb();
                }
            });
            mbuttonView.setOnClickListener((View.OnClickListener) this);
            createDataBase();
        }

        protected void createDataBase() {
            db = openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, address VARCHAR);");
        }

        protected void insertInToDb() {
            String name = medittextName.getText().toString().trim();
            String address = medittextAddress.getText().toString().trim();

            if (name.equals("") || address.equals("")) {
                Toast.makeText(MainActivityDatabase30th.this, "please fill all details", Toast.LENGTH_SHORT).show();
                return;
            }
            String query = "INSERT INTO persons (name,address) VALUES ('" + name + "' , " + address + "');";
            db.execSQL(query);
            Toast.makeText(MainActivityDatabase30th.this, "saved succesfully", Toast.LENGTH_LONG).show();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/




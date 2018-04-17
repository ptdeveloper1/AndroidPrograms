package database.example.com.database1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by ADMIN on 6/30/2017.
 */

class ListDataActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView=(ListView)findViewById(R.id.listview);
        mDatabaseHelper helper=new DatabaseHelper(this);
    }
}

package projecthomepage.example.com.projecthomepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 7/5/2017.
 */

public class HabitListActivity extends AppCompatActivity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);

        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.mainListView );
        List<Habit> data = DBAdapter.getAllHabits();
        List<String> listOfString = new ArrayList<String>();
        for (Habit dt : data) {

            String log = "Id: "+dt.getID()+" ,Time: " + dt.getTime() +
                    " ,Title: " + dt.getTitle()+ "Date： "+dt.getDate() + " Status:"+ dt.getStatus();
            listOfString.add(log);
            Log.d("HabitList", log);
        }
        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, listOfString);
        // Add more Spring
        listAdapter.add( "fdfdfdfdfdf" );
        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );
    }
}

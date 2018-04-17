package lastclass.example.com.lastclassnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import static lastclass.example.com.lastclassnow.DataAdapter.LOG_TAG;

//import com.example.phoenix.sqliteapp.DBAdapter;
public class MainActivityLastClassNow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_last_class_now);
        //link to the database
        database();
    }
    public void database(){
        /******************* Intialize Database *************/
        DataAdapter.init(this);

        // Inserting Contacts
        //Log.d("Insert: ", "Inserting ..");
        Log.d(LOG_TAG, "Inserting ..");
        DataAdapter.addUserData(new UserData("Ravi", "9100000000"));
        DataAdapter.addUserData(new UserData("Srinivas", "9199999999"));
        DataAdapter.addUserData(new UserData("Tommy", "9522222222"));
        DataAdapter.addUserData(new UserData("Karthik", "9533333333"));

        // Reading all contacts
        //Log.d("Reading: ", "Reading all contacts..");
        Log.d(LOG_TAG, "Reading all contacts..");
        List<UserData> data = DataAdapter.getAllUserData();

        for (UserData dt : data) {
            String log = "Id: "+dt.getID()+" ,Name: " + dt.getName() +
                    " ,Phone: " + dt.getEmail();
            // Writing Contacts to log
            //Log.d("Name: ", log);
            Log.d(LOG_TAG, log);
        }
    }
}

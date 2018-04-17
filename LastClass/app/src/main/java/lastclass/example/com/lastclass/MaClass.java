package lastclass.example.com.lastclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import com.example.phoenix.sqliteapp.DBAdapter;

public class MaClass extends AppCompatActivity {

    /******************** Logcat TAG ************/
    public static final String LOG_TAG = "Database";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitylastclass);

        //link to the database
        database();
    }
    public void database(){
        /******************* Intialize Database *************/
        DbAClass.init(this);

        // Inserting Contacts
        //Log.d("Insert: ", "Inserting ..");
        Log.d(LOG_TAG, "Inserting ..");
        DbAClass.addUserData(new UdClass("Ravi", "9100000000"));
        DbAClass.addUserData(new UdClass("Srinivas", "9199999999"));
        DbAClass.addUserData(new UdClass("Tommy", "9522222222"));
        DbAClass.addUserData(new UdClass("Karthik", "9533333333"));

        // Reading all contacts
        //Log.d("Reading: ", "Reading all contacts..");
        Log.d(LOG_TAG, "Reading all contacts..");
        List<UdClass> data = DbAClass.getAllUserData();

        for (UdClass dt : data) {
            String log = "Id: "+dt.getID()+" ,Name: " + dt.getName() +
                    " ,Phone: " + dt.getEmail();
            // Writing Contacts to log
            //Log.d("Name: ", log);
            Log.d(LOG_TAG, log);
        }
    }
}

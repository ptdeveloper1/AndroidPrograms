package localnotification.example.com.locanotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager alarmmanager=(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar c= Calendar.getInstance();
        c.add(Calendar.SECOND,5);
        Intent intent=new Intent(getApplicationContext(),NotificationReceiver.class);
        PendingIntent broadcast=PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);;
        alarmmanager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),broadcast);
    //Android says wrap your intent in a PendingIntent and give some discription I will tell the other
        //other applcation to handle that intent like you do

    }
}

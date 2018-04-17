package notificationapp.example.com.notificationapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;

public class MainActivityNotification extends AppCompatActivity {
    Button mbuttonshow;
    NotificationManager notificationamanager;//to notify the user that something has happened in the background
    boolean isNotiActive = false;//to track notification whether it is in task bar or not
    int notiId = 33;//to track the notification
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);

        mbuttonshow = (Button) findViewById(R.id.showbuttonId);
        mbuttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.set(Calendar.HOUR,9);
                cal.set(Calendar.MINUTE, 54);
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);

                Intent intent = new Intent(MainActivityNotification.this,MoreInformationNoti.class);
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, (PendingIntent.getBroadcast(getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT)));

            }
        });
    }
}


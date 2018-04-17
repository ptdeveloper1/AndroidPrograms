package projecthomepage.example.com.projecthomepage;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ADMIN on 7/4/2017.
 */

public class AlarmFirstClass extends AppCompatActivity {

    boolean debug = true; // for debugging

    Button showbuttonId;
    public static int alarmhour,alarmminute;
    //public static int alarmDay,alarmMonth,alarmYear;
    Button  reminderbtn;
    NotificationManager notificationamanager;//to notify the user that something has happened in the background
    boolean isNotiActive = false;//to track notification whether it is in task bar or not
    int notiId = 33;//to track the notification
    AlarmManager am,amrem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_alarm_class);

        reminderbtn=(Button)findViewById(R.id.reminder);
        reminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar rem = Calendar.getInstance();
                rem.setTimeInMillis(System.currentTimeMillis());
                    rem.set(Calendar.HOUR, alarmhour);
                    rem.set(Calendar.MINUTE, alarmhour);
                    rem.set(Calendar.SECOND, 00);
                    rem.set(Calendar.MILLISECOND, 00);

                Intent intentrem = new Intent(AlarmFirstClass.this, ReminderClass.class);
                amrem = (AlarmManager) getSystemService(ALARM_SERVICE);
                amrem.setRepeating(AlarmManager.RTC_WAKEUP, rem.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, (PendingIntent.getBroadcast(getApplicationContext(), 1, intentrem, PendingIntent.FLAG_UPDATE_CURRENT)));
            }
        });
//Notificatio  4 hours after first notificaion
        showbuttonId = (Button) findViewById(R.id.belowbtn);
        showbuttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.set(Calendar.HOUR,alarmhour+4);
                cal.set(Calendar.MINUTE, alarmminute);
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);

                Intent intent = new Intent(AlarmFirstClass.this,AlarmSecondClass.class);
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, (PendingIntent.getBroadcast(getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT)));

            }
        });

            }


    }



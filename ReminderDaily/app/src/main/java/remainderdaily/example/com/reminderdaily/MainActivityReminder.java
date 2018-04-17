package remainderdaily.example.com.reminderdaily;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivityReminder extends AppCompatActivity {
Button mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reminder);

        mbutton=(Button) findViewById(R.id.buttonId);
        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             Calendar c= Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,20);
                c.set(Calendar.MINUTE,32);


              Intent i=new Intent(getApplicationContext(),NotificationReceiver.class);
                PendingIntent pi= PendingIntent.getBroadcast(getApplicationContext(),100,i,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
                    am.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);

            }

        });
    }
}

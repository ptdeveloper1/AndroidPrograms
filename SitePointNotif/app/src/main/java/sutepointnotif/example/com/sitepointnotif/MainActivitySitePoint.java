package sutepointnotif.example.com.sitepointnotif;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivitySitePoint extends AppCompatActivity {
    private PendingIntent pendingIntent;
    private AlarmManager manager;
    Uri notification;
    Ringtone r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_site_point);

        // Retrieve a PendingIntent that will perform a broadcast
       // Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        Intent alarmIntent = new Intent(this, AlarmExample.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    public void startAlarm(View view) {
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000; // 10 seconds
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
       r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm(View view) {
        if (manager != null) {
            manager.cancel(pendingIntent);
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
            r.stop();
        }

    }
}
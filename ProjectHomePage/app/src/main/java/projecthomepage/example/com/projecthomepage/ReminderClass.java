package projecthomepage.example.com.projecthomepage;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import static projecthomepage.example.com.projecthomepage.R.raw.*;

/**
 * Created by ADMIN on 7/5/2017.
 */

public class ReminderClass extends BroadcastReceiver {
    Uri notification;
    Ringtone r;
    MediaPlayer player;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

      notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
      r = RingtoneManager.getRingtone(context, notification);
      r.play();

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context);
       // builder.setContentIntent(pi)
         builder.setSmallIcon(R.mipmap.ic_launcher)
                .setColor(Color.YELLOW)
                .setContentTitle("Remainder")
                .setContentText("You have decide to take a task \n as a HABIT \n You Can Do it")
                .setTicker("Alert new message")
                .setAutoCancel(true);

        nm.notify(100,builder.build());


    }
}

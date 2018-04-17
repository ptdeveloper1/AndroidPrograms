package notificationapp.example.com.notificationapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

//import alarmnotiapps.example.com.alarmnotificationapp.RepeatingActivity;

public class MoreInformationNoti extends BroadcastReceiver {
    Uri notification;
    Ringtone r;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent repintent=new Intent(context,RepeatingActivity.class);
        PendingIntent pi=PendingIntent.getActivity(context,100,repintent,PendingIntent.FLAG_UPDATE_CURRENT);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(context, notification);
        r.play();

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context);
         builder.setContentIntent(pi)
                 .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Remainder")
                .setContentText("Have You Completed the TASK??" +
                        "Click here to Mark it")
                 .setTicker("Alert new message")
                .setAutoCancel(true);

        nm.notify(100,builder.build());

    }
}

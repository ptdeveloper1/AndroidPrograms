package localnotification.example.com.locanotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

/**
 * Created by ADMIN on 6/28/2017.
 */

public class NotificationReceiver extends BroadcastReceiver{
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationintent=new Intent(context,NotificationActivity.class);

        TaskStackBuilder sbuilder=TaskStackBuilder.create(context);
        sbuilder.addParentStack(NotificationActivity.class);
        sbuilder.addNextIntent(notificationintent);

        PendingIntent pi=sbuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        Notification notification=builder.setContentTitle("demo app notification")
                .setContentText("new notification for demo app")
                .setTicker("new message alert")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi).build();

        NotificationManager notificationmanager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationmanager.notify(0,notification);
    }
}

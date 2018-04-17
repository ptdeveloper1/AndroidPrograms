package mixofnotiapps.example.com.mixofnotifiapps;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivityMix extends AppCompatActivity {



    Button mbuttonshow;
    //mbuttonstop,mbuttonalert;
    NotificationManager notificationamanager;//to notify the user that something has happened in the background
    boolean isNotiActive=false;//to track notification whether it is in task bar or not
    int notiId=33;//to track the notification
    Uri notification;
    Ringtone r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mix);

        mbuttonshow = (Button) findViewById(R.id.button);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(View v){
        android.support.v7.app.NotificationCompat.Builder notificationbuilder= (android.support.v7.app.NotificationCompat.Builder) new android.support.v7.app.NotificationCompat.Builder(this);//build notification that we are going to create
        notificationbuilder.setContentTitle("Message")
                .setContentText("Have you completed the task")
                .setTicker("Alert new message")
                .setSmallIcon(R.mipmap.ic_launcher);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();



        //Uri sounduri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//we have to define that we have the interntion of opening more infor notification
        //which is a another class, that opens the when the notifiation is clicked

        Intent intent=new Intent(this,GridButtons.class);//
        //Intent intent=new Intent(this,null);
        //
       TaskStackBuilder tsb=TaskStackBuilder.create(this);
        //add new intent to the stack
       tsb.addParentStack(NotificationCustom.class);
        tsb.addNextIntent(intent);
        //define an intent and an action to perform with that intent by another application
        //if the intent is existed then update it, dont create the new one
        PendingIntent pi=tsb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
       notificationbuilder.setContentIntent(pi);
        notificationamanager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //to notify the background events
        notificationamanager.notify(notiId,notificationbuilder.build());
        isNotiActive=true;
    }

}

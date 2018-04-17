package notificationmusic.example.com.notificationmusic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivityMusicNotif extends AppCompatActivity {
    Button mbuttonshow;
    Uri notifiMusic;
    Ringtone r;
    NotificationManager notificationamanager;
    boolean isNotiActive=false;
    Context mContext;
   int notiId=30;
    Intent intent;
    private RemoteViews mRemoteViews;
   // private RemoteViews mRemoteViews;
    //private View.OnClickListener context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_music_notif);
       // int notificationId=(int)System.currentTimeMillis();
        mbuttonshow=(Button)findViewById(R.id.button);

    mbuttonshow.setOnClickListener(new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {

            mRemoteViews=new RemoteViews(getPackageName(),R.layout.custom_notification);
            mRemoteViews.setImageViewResource(R.id.imageView,R.mipmap.ic_launcher);
           mRemoteViews.setTextViewText(R.id.textView,"TEXT");

            PendingIntent pi=PendingIntent.getBroadcast(mContext,123,intent,0);
            mRemoteViews.setOnClickPendingIntent(R.id.buttondid,pi);

            NotificationCompat.Builder notificationbuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivityMusicNotif.this);
            notificationbuilder.setContentTitle("Your GOAL")
                    .setContentText("New message")
                    .setTicker("Alert new message")
                    //.setSmallIcon(R.mipmap.ic_launcher)
                   .setCustomBigContentView(mRemoteViews)
            .setColor(Color.GREEN);

            notifiMusic = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            r = RingtoneManager.getRingtone(getApplicationContext(), notifiMusic);
            r.play();

            intent = new Intent(MainActivityMusicNotif.this, MoreInformationNoti.class);
            TaskStackBuilder tsb = TaskStackBuilder.create(MainActivityMusicNotif.this);
            tsb.addParentStack(MoreInformationNoti.class);
            tsb.addNextIntent(intent);
            //PendingIntent pi = tsb.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationbuilder.setContentIntent(pi);
            notificationamanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationamanager.notify(notiId, notificationbuilder.build());
            isNotiActive = true;
        }
    });

    }
    public void onClick(){
      r.stop();
    }
}

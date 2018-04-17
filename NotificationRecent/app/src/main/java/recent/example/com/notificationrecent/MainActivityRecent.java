package recent.example.com.notificationrecent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivityRecent extends AppCompatActivity {
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    int notifId;
    private RemoteViews remoteview;
    private Context context;
    //private Button showbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recent);
        context=this;
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        remoteview=new RemoteViews(getPackageName(),R.layout.custom_notification);

        remoteview.setImageViewResource(R.id.imageView,R.mipmap.ic_launcher_round);
        // for custom text on text view
        remoteview.setTextViewText(R.id.textView,"You have made a GOAL, You can do it");

        notifId=(int ) System.currentTimeMillis();
        Intent buttonintent=new Intent("button_clicked");
       // buttonintent.putExtra("id",notifId);
//to broadcast the intent
        PendingIntent pibutton=PendingIntent.getBroadcast(context,123,buttonintent,0);
        //bind this pending instance with the actual button click
        remoteview.setOnClickPendingIntent(R.id.button,pibutton);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent notiintent=new Intent(context,MainActivityRecent.class);
        PendingIntent pi=PendingIntent.getActivity(context,0,notiintent,0);

                builder=new NotificationCompat.Builder(context);
               /* builder.setSmallIcon(R.mipmap.ic_launcher_round)
                        .setAutoCancel(true)
                        .setCustomBigContentView(remoteview)
                        .setContentIntent(pi);*/
                manager.notify(notifId,builder.build());



            }
        });
    }
}

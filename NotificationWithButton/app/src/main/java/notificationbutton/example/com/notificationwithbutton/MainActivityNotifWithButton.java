package notificationbutton.example.com.notificationwithbutton;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivityNotifWithButton extends AppCompatActivity {
   //private Button mbuttondid;
    //private Button mbuttonmissed;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mManager;
    private int notificationId;
    private RemoteViews mRemoteViews;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notif_with_button);

        context=this;
        mManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mRemoteViews=new RemoteViews(getPackageName(),R.layout.custom_notification);
        mRemoteViews.setImageViewResource(R.id.imageView,R.mipmap.ic_launcher);
        mRemoteViews.setTextViewText(R.id.textView,"TEXT");

        notificationId=(int)System.currentTimeMillis();
        Intent intent=new Intent("button_clicked");
        intent.putExtra("id",notificationId);
        PendingIntent pi=PendingIntent.getBroadcast(context,123,intent,0);
        mRemoteViews.setOnClickPendingIntent(R.id.buttondid,pi);


        //******main activity xml**************************
        findViewById(R.id.buttonId).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent notifintent=new Intent(context,MainActivityNotifWithButton.class);
                PendingIntent pi=PendingIntent.getActivity(context,0,notifintent,0);

                mBuilder=new NotificationCompat.Builder(context);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                        .setCustomBigContentView(mRemoteViews)
                        .setContentIntent(pi);
                mManager.notify(notificationId,mBuilder.build());
            }
        });

        //**********Buttons on the notification***********

    }
}

package notificationbutton.example.com.notificationwithbutton;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ADMIN on 7/1/2017.
 */

public class Button_listener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nmanager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nmanager.cancel(intent.getExtras().getInt("id"));
        Toast.makeText(context,"Button Clicked",Toast.LENGTH_SHORT).show();
    }
}

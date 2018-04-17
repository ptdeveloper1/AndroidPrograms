package recent.example.com.notificationrecent;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ADMIN on 7/2/2017.
 */

public class ButtonClick extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(intent.getExtras().getInt("id"));
        Toast.makeText(context,"Button clicked",Toast.LENGTH_SHORT).show();

    }
}

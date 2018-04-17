package sutepointnotif.example.com.sitepointnotif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ADMIN on 7/2/2017.
 */

public class AlarmExample extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
    }
}

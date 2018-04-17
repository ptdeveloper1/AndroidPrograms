package stotextn.example.com.speechtotextn;

/**
 * Created by ADMIN on 12/11/2017.
 */



        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;

/**
 * Created by ADMIN on 12/8/2017.
 */

class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context k1, Intent k2) {
        // TODO Auto-generated method stub
        // Toast.makeText(k1, "Alarm received!", Toast.LENGTH_LONG).show();

        MainActivity.ma.playAlarm();
    }

}

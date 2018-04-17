package a30thjune.example.com.day30thjune;

import android.app.PendingIntent;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import java.util.prefs.Preferences;

public class MainActivityDay30th extends AppCompatActivity {
    private PendingIntent pendingintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_day30th);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor= preferences.edit();
        int i=preferences.getInt("num of launces",1);
        if(i<2){
            alarmMethod();
            i++;
            editor.putInt("num of launces",1);
            editor.commit();
        }
        if(savedInstanceState==null){
            splashMehod();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}

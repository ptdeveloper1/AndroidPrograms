package stotextn.example.com.speechtotextn;

/**
 * Created by ADMIN on 12/11/2017.
 */


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by ADMIN on 12/8/2017.
 */

public class CallHandler {


    void handleText(String text, MainActivity ma) {
        String pname = text.split(" ")[1];
        ma.speakOut("calling " + pname);
        String ret = ma.getPhoneNumber(pname, ma);
        if (ret == null) {
            ma.speakOut("The contact " + pname + " not found");
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + ret));


            ma.startActivity(callIntent);


        }

    }
}
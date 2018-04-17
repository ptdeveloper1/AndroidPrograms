package otherapps.example.com.openingotherapps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button chromebutton;
    Button fbbutton,utubebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chromebutton=(Button)findViewById(R.id.chrome);
        fbbutton=(Button)findViewById(R.id.fb);
        utubebutton=(Button)findViewById(R.id.utube);

        chromebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent ichrome=getPackageManager().getLaunchIntentForPackage("com.android.chrome");
                startActivity(ichrome);
            }
        });
        fbbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent ifb=getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                startActivity(ifb);
            }
        });
        utubebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent iutube=getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                startActivity(iutube);
            }
        });
    }

}

    /*// Launch Google Chrome after clicking the button
    public void launchGoogleChrome(View view) {
        Intent launchGoogleChrome = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchGoogleChrome);
    }

    // Launch Facebook Application after clicking the button
    public void launchFacebookApplication(View view) {
        Intent launchFacebookApplication = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
        startActivity(launchFacebookApplication);
    }

    // Launch Twitter APplication after clicking the button
    public void launchTwitterApp(View view) {
        Intent launchTwitterApp = getPackageManager().getLaunchIntentForPackage("com.twitter.android");
        startActivity(launchTwitterApp);
    }

    // Launch YouTube App after clicking the button
    public void launchYouTube(View view) {
        Intent launchYouTube = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
        startActivity(launchYouTube);
    }*/


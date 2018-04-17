/**
* Main activity for building voice assistant
* Admin: Pravalika Tirumala
 */
package stotextn.example.com.speechtotextn;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import android.Manifest;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {public TextView txtSpeechInput;

    //Declarations
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextToSpeech tts;
    public static MainActivity ma;

    //database
    public DBInterfacer dbinter;
    CommandHandler cmdHand;

    //for alarm notification
    void playAlarm()
    {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ma=this;

        //Activation and linking to UI

        tts = new TextToSpeech(this, this);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        dbinter = new DBInterfacer(this);
        cmdHand = new CommandHandler();

        // hide the action bar
        //   getActionBar().hide();

        speakOut("Hi , how can I help you ? ");

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    //speech input dialog
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    //calling
    public String getPhoneNumber(String name, Context context) {
        String ret = null;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + name +"%'";
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            ret = c.getString(0);
        }
        c.close();
        //if(ret==null)
        //    ret = null;
        return ret;


    }

    //recieving speech input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String texttoprocess = result.get(0).toLowerCase();
                    //String texttoprocess = result.get(0);
                    txtSpeechInput.setText(texttoprocess);

                    CommandHandler ch = new CommandHandler();
                    boolean ret=ch.handleText(texttoprocess, this);

                    if (ret==true)
                    {
                        return;
                    }
                    else if (texttoprocess.contains("open amazon")|| texttoprocess.contains("shop on amazon"))
                    {
                        speakOut("opening amazon");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("http://www.amazon.com", this);

                    }
                    else if (texttoprocess.contains("open ebay")|| texttoprocess.contains("shop on ebay"))
                    {
                        speakOut("opening ebay");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("https://www.ebay.com/", this);

                    }
                    else if (texttoprocess.contains("open jcpenney")|| texttoprocess.contains("shop on jcpenny"))
                    {
                        speakOut("opening jcpenny");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("https://www.jcpenney.com/", this);

                    }
                    else if (texttoprocess.contains("open microsoft")|| texttoprocess.contains("microsoft website"))
                    {
                        speakOut("opening microsoft");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("https://www.microsoft.com", this);

                    }
                    else if (texttoprocess.contains("open yahoo")|| texttoprocess.contains("yahoo page"))
                    {
                        speakOut("opening yahoo");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("http://www.yahoo.com", this);

                    }
                    else if (texttoprocess.contains("open google")|| texttoprocess.contains("google page"))
                    {
                        speakOut("opening google");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("http://www.google.com", this);

                    }
                    else if (texttoprocess.contains("open Texas A&M university"))
                    {
                        speakOut("opening university website");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("http://www.tamuc.edu", this);

                    }
                    else if (texttoprocess.contains("open cnn")|| texttoprocess.contains("cnn website"))
                    {
                        speakOut("opening cnn");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("http://edition.cnn.com/", this);

                    }
                    else if (texttoprocess.contains("open apple") || texttoprocess.contains("apple website"))
                    {
                        speakOut("opening apple" );
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("https://www.apple.com/", this);

                    }
                    else if (texttoprocess.contains("open stack overflow") || texttoprocess.contains("stack overflow page"))
                    {
                        speakOut("opening stack overflow");
                        URLRedirector urlr = new URLRedirector();
                        urlr.openURL("https://stackoverflow.com/", this);

                    }


                    else if (texttoprocess.contains("shonna search") )
                    {
                        //String sr = texttoprocess.split(" ")[1];

                        texttoprocess = texttoprocess.replace("shonna search","");
                        texttoprocess=texttoprocess.replace(" ", "+");

                        wikiprocess wk = new wikiprocess(this);
                        wk.processWiki(texttoprocess);

                    }
                    else if (texttoprocess.contains("open camera")|| texttoprocess.contains("camera"))
                    {
                        speakOut("opening camera");
                        int CAMERA_PIC_REQUEST = 2500;
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

                    }
                    else if (texttoprocess.contains("open calendar") || texttoprocess.contains("calendar"))
                    {
                        speakOut("opening calendar");
                        Intent i = new Intent();
                        //Froyo or greater (mind you I just tested this on CM7 and the less than froyo one worked so it depends on the phone...)
                        ComponentName cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
                        //less than Froyo
                        cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
                        i.setComponent(cn);
                        startActivity(i);

                    }
                    else if (texttoprocess.contains("open youtube")|| texttoprocess.contains("youtube") || texttoprocess.contains("open youtube link"))
                    {
                        speakOut("opening youtube");
                        String videoId = "CSav51fVlKU";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                        intent.putExtra("VIDEO_ID", videoId);
                        startActivity(intent);


                    }
                    else if (texttoprocess.contains("what is the capital of")|| texttoprocess.contains("capital of"))
                    {
                        speakOut("Directing to google");
                        String searchQuery = result.get(0);
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);


                    }

                    else if (texttoprocess.contains("time in"))
                    {
                        String searchQuery = result.get(0);
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);

                    }
                    else if (texttoprocess.contains("call"))
                    {

                        CallHandler chc = new CallHandler();

                            chc.handleText(result.get(0), this);




                    }
                    else if (texttoprocess.contains("reset"))
                    {
                        dbinter.resetExpense();
                        speakOut("Records are cleared , you can add new entries");

                    }
                    else if (texttoprocess.contains("spent"))
                    {
                        String amount = result.get(0).split(" ")[1];

                        dbinter.addExpense(amount);


                    }
                    else if (texttoprocess.contains("expense"))
                    {
                        String res = dbinter.getExpense();
                        speakOut("Total expense "+res);

                    }
                    else if (texttoprocess.contains("quit application") || texttoprocess.contains("close application") || texttoprocess.contains("exit application"))
                    {
                        speakOut("Closing the application");
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            public void run() {
                                //finish();
                                finishAffinity();
                            }
                        }, 3000);
                    }
                    else if (texttoprocess.contains("open facebook")|| texttoprocess.contains("facebook") || texttoprocess.contains("open facebook account"))
                    {
                        speakOut("opening facebook");
                        String packageName = "com.facebook.katana";

                        Intent mIntent = getPackageManager().getLaunchIntentForPackage(
                                packageName);

                        if (mIntent != null) {

                            try
                            {

                                startActivity(mIntent);
                            }
                            catch(Exception exx)
                            {
                                txtSpeechInput.setText("app not found");
                                speakOut("app not found");


                            }
                        }
                        else
                        {
                            txtSpeechInput.setText("app not found");
                            speakOut("app not found");

                        }


                    }
                    else if (texttoprocess.contains("open twitter")|| texttoprocess.contains("twitter") || texttoprocess.contains("open twitter account"))
                    {
                        speakOut("opening twitter");
                        String packageName = "com.twitter.android";

                        Intent mIntent = getPackageManager().getLaunchIntentForPackage(
                                packageName);

                        if (mIntent != null) {

                            try
                            {

                                startActivity(mIntent);
                            }
                            catch(Exception exx)
                            {
                                txtSpeechInput.setText("app not found");
                                speakOut("app not found");


                            }
                        }
                        else
                        {
                            txtSpeechInput.setText("app not found");
                            speakOut("app not found");

                        }


                    }
                    else if (texttoprocess.contains("open gmail") || texttoprocess.contains("gmail") || texttoprocess.contains("open gmail account"))
                    {
                        speakOut("opening gmail");
                        String packageName = "com.google.android.gm";

                        Intent mIntent = getPackageManager().getLaunchIntentForPackage(
                                packageName);

                        if (mIntent != null) {

                            try
                            {

                                startActivity(mIntent);
                            }
                            catch(Exception exx)
                            {
                                txtSpeechInput.setText("app not found");
                                speakOut("app not found");


                            }
                        }
                        else
                        {
                            txtSpeechInput.setText("app not found");
                            speakOut("app not found");

                        }


                    }
// if the speech text has search
                    else if (texttoprocess.contains("search"))
                    {
                        speakOut("directing to internet");
                        String searchQuery = texttoprocess;
                        searchQuery = searchQuery.replace("search","");
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);

                    }
                    else if (texttoprocess.contains("question"))
                    {
                        QuestionAnswering q = new QuestionAnswering();
                        q.handleText(texttoprocess, this);



                    }
                    else
                    {
                        txtSpeechInput.setText("No matching");
                        speakOut("redirecting too google");
                        String searchQuery = result.get(0);

                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);

                    }



                }
                break;
            }

        }
    }

    public void setAlaramAtTime(int hourOfDay, int minute)
    {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if(calSet.compareTo(calNow) <= 0){
            //Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1);
        }

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(),
                pendingIntent);

    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onInit(int arg0) {

        // TODO Auto-generated method stub
        if (arg0 == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //Log.e("TTS", "This Language is not supported");
            } else {

                speakOut("Hi how can I help you");
            }

        } else {
            // Log.e("TTS", "Initilization Failed!");
        }
    }

    void speakOut(String text)
    {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

}


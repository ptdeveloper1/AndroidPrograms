package stot.example.com.speechtotextpractice;

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
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

//AppCompatActivity- Base class for alla ctivites. java.lang.object-context-Activity-AppCA
//Activity class- takes care of windows where UI is placed
public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    //TextToSpeech class intsance declaration
    private TextToSpeech tts;
    //MainActivity class instance declaration
    public static MainActivity ma;

    void playAlarm() {
        //Uri- abstract class. java.lang-Uri
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
    }

    @Override
    //onCreate-responsible for activity creations.
    protected void onCreate(Bundle savedInstanceState) {
        //inheritance- either overridding or extening the super class method.
        //declaring this keeps the super class method that manages inner complexities.
        super.onCreate(savedInstanceState);
        //Set the activity content to an explicit view
        setContentView(R.layout.activity_main);
        ma = this;
        //TExtToSpeech class is used to synthesize speech from text.
        //instacne can only be used to synthesize text once it has been initialized.
        //OnInitListener interface is used to notify that the initialization is completed.
        //TextToSpeech(Context context,TextToSpeech.OnInitListener)
        tts = new TextToSpeech(this, this);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        //hiding the action bar
        getActionBar().hide();
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }


        });
    }

    //prompting for speech
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

    public String getPhoneNumber(String name, Context context) {
        String ret = null;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like'%" + name + "%'";
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            ret = c.getString(0);
        }
        c.close();
        return ret;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    if (result.get(0).contains("how old are you")) {
                        speakOut("I am doing good. Thanks for asking. How are you");
                        txtSpeechInput.setText("I am doing good. Thanks for asking. How are you");
                    } else if (result.get(0).contains("what is the time now")) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                        String strDate = "The time is " + mdformat.format(calendar.getTime());
                        speakOut(strDate);
                    } else if (result.get(0).contains("set alarm")) {
                        String timea = result.get(0).split(" ")[2];
                        String[] t = timea.split("\\:");
                        int h = Integer.parseInt(t[0]);
                        int m = Integer.parseInt(t[1]);
                        txtSpeechInput.setText("Setting alarm at" + h + ":" + m);
                        setAlarmAtTime(h, m);
                    } else if (result.get(0).contains("open Amazon")) {
                        String url = "http://www.amazon.com";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    } else if (result.get(0).contains("open camera")) {
                        int CAMERA_PIC_REQUEST = 2500;
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                    } else if (result.get(0).contains("open calendar")) {
                        Intent i = new Intent();
                        ComponentName cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
                        cn = new ComponentName("com.anroid.calendar", "com.android.calendar.LaunchActivity");
                        i.setComponent(cn);
                        startActivity(i);
                    } else if (result.get(0).contains("open YouTube")) {
                        String videoId = "CSav51fV1KU";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                        intent.putExtra("VIDEO_ID", videoId);
                        startActivity(intent);
                    } else if (result.get(0).contains("what is the capital of")) {
                        String searchQuery = result.get(0);
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);
                    } else if (result.get(0).contains("how old are you")) {
                        speakOut("I am 2months old");
                    } else if (result.get(0).contains("do you ever get tired")) {
                        speakOut("not at all, I can help you at any time");
                    } else if (result.get(0).contains("who was your first crush")) {
                        speakOut("of course it’s you");
                    } else if (result.get(0).contains("do you like Siri")) {
                        speakOut("of course I do like it");
                    } else if (result.get(0).contains("what is your purpose")) {
                        speakOut("my purpose is to serve you");
                    } else if (result.get(0).contains("what do you look like")) {
                        speakOut("Imagine anything beautiful and that’s me");
                    } else if (result.get(0).contains("where do you live")) {
                        speakOut("I live inside your mobile");
                    } else if (result.get(0).contains("what is speech recognition")) {
                        speakOut("Speech recognition is the ability of a computer to identify and respond to the sounds produced in human speech");
                    } else if (result.get(0).contains("what is voice recognition")) {
                        speakOut("Voice recognition is the ability of a machine or program to receive and interpret dictation, or to understand and carry out spoken commands");
                    } else if (result.get(0).contains("what is the difference between speech recognition and voice recognition")) {
                        speakOut("both can be used interchangeably, It is the ability of a computer to identify and respond to the sounds produced in human speech");
                    } else if (result.get(0).contains("who are your friends")) {
                        speakOut("I have many like Siri , Google assistant , Alexa and so many that includes you too");
                    } else if (result.get(0).contains("what is your name")) {
                        speakOut("I am called Shona your assistant");
                    } else if (result.get(0).contains("what is the meaning of your name")) {
                        speakOut("My name has different meaning in simple terms it is something that you love");
                    } else if (result.get(0).contains("what are the other voice assistants")) {
                        speakOut("Few Important voice assistants are siri developed by Apple.Google assistant developed by Google and alexa developed by Amazon");
                    } else if (result.get(0).contains("call")) {
                        String pname = result.get(0).split(" ")[1];
                        String ret = getPhoneNumber(pname, MainActivity.this);
                        if (ret == null) {
                            speakOut("The contact" + pname + "not found");
                        } else {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + ret));
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(callIntent);
                        }
                    }
                    else if(result.get(0).contains("search")) {
                        String searchQuery = result.get(0);
                        searchQuery = searchQuery.replace("search", "");
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);
                    }

                    }
                    break;
            }
        }
    }
public void setAlarmAtTime (int hourOfDay,int minute){
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
    private void speakOut(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
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
}

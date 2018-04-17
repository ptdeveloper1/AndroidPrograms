package secondaryfullproject.example.com.secndaryfullproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech mTextToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {

            if(status == TextToSpeech.SUCCESS){
                int result = mTextToSpeech.setLanguage(Locale.US);
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("TTS", "This language is not supported");
                }
                speak("Hello, How can I help you ");
            }else{
                Log.e("TTS","Initialization Failed");
            }
            }
        });

        //this is for app to listen
        findViewById(R.id.microphoneButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listen();
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void speak(String recvText) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mTextToSpeech.speak(recvText, TextToSpeech.QUEUE_FLUSH,null,null);
        }else{
            mTextToSpeech.speak(recvText,TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }

    private void listen() {
        Intent inte =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        inte.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        inte.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        inte.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try{
            startActivityForResult(inte,1000);
        }catch(ActivityNotFoundException a){
            Toast.makeText(MainActivity.this,"Your device does not support Speech recognition",Toast.LENGTH_SHORT).show();
        }
    }
    //to catch the  result
    @Override
    protected void onActivityResult(int reqCode,int resCode,Intent resData){
        super.onActivityResult(reqCode,resCode,resData);
        if(reqCode==1000){
            if(resCode==RESULT_OK && null != resData){
                ArrayList<String> result=resData.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String recSpeech=result.get(0);
                recognition(recSpeech);
            }
        }
    }

    private void recognition(String recSpeechPar) {
      Log.e("speech", " "+ recSpeechPar);
    }


    @Override
    public void onDestroy(){
        if(mTextToSpeech != null){
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
        super.onDestroy();
    }


}

package search.example.com.searchtest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public  String[] arr;
    TextView text;
   // Button mButton;
    //String searchQuery ="This is";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.textview);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new doit().execute();
            }
        });
    }
    public class doit extends AsyncTask<Void,Void,Void> {
        String words;
        /*String fir="artificial";
        String sec="intelligence";
        String thi="Texas";*/

        @Override
        protected Void doInBackground(Void... voids) {

            String input="capital of Texas";
            String[] arr=input.split(" ");

            if (arr.length ==1) {
                try {
                    Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + arr[0] ).maxBodySize(32670).get();
                    words = doc.select("p").text();


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (arr.length ==3) {
                try {
                    Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + arr[0] + "_" + arr[1]+"_"+arr[2]).maxBodySize(32760).get();
                    words = doc.select("p").text();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (arr.length ==4) {
                try {
                    Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + arr[0] + "_" + arr[1]+"_"+arr[2]+"_"+arr[3]).maxBodySize(32760).get();
                    words = doc.select("p").text();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            text.setText(words);
        }


    }
}

package com.gini.coign;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	        setContentView(R.layout.help);
	        
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
	        
	        ImageView next = (ImageView) findViewById(R.id.Button02);
	          next.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	                  Intent intent = new Intent();
	                  setResult(RESULT_OK, intent);
	                  finish();
	              }

	          });
	          
	          String help="\nIf you are not getting text to  \nspeech then please follow the below \ninstructions to enable text to speech mode.\n" +
	          		"\n1:-Go to Settings \n\n " +
	          		"2:-Click on Text to Speech Option \n\n" +
	          		"3:-Click on Install voice data \n\n " +
	          		"4:-After installing TTS close \n    settings and restart app\n\n";
	          
	          TextView td=(TextView)findViewById(R.id.hlpcont);
	          
	          td.setText(help);
	          
	          td.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
	                  setResult(RESULT_OK, intent);
	                  finish();
				}
			});
	          
	 }
}

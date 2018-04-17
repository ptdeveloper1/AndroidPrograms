package com.gini.coign;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Meaning extends Activity implements OnInitListener {
	ImageView iv, m1, f1;
	String groupname, childname;
	
	TextToSpeech tts;
	TextView hg, hgg;

	float speed2 = 0;
	float typeval = 0;
	SeekBar sb1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.ans);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.window_title);

		Button ivv = (Button) findViewById(R.id.help);
		ivv.setFocusable(true);
		ivv.setFocusableInTouchMode(true);
		ivv.setTag("asdasdasd");
		ivv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				helpProcess();
			}

		});
		iv = (ImageView) findViewById(R.id.imageView1);
		Bitmap bm = ShrinkBitmap(100, 100);

		iv.setImageBitmap(bm);

		Bundle bun = getIntent().getExtras();

		if (bun != null) {
			groupname = bun.getString("pn");
			childname = bun.getString("cn");

		}

		tts = new TextToSpeech(this, this);

		hg = (TextView) findViewById(R.id.widget29);
		hgg = (TextView) findViewById(R.id.widget30);

		TextView tag = (TextView) findViewById(R.id.headertag);
		tag.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

		
		String[] cn = childname.split(":");
		hg.setText(cn[0]);

		hgg.setText(cn[1]);

		hg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String data1 = hg.getText().toString();
				sayWord(data1);
			}
		});
		hgg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent();
				// setResult(RESULT_OK, intent);
				// finish();
				String data2 = hgg.getText().toString();
				sayWord(data2);
			}
		});
		ImageView next = (ImageView) findViewById(R.id.Button02);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});
		

	}

	private void helpProcess() {
		// TODO Auto-generated method stub

		Intent it = new Intent(this, Help.class);
		startActivityForResult(it, 0);

	}

	protected void onDestroy() {

		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();

	};

	Bitmap ShrinkBitmap(int width, int height) {// this is method in out side of
												// activity

		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
		bmpFactoryOptions.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.logoadd, bmpFactoryOptions);

		int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight
				/ (float) height);
		int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth
				/ (float) width);

		if (heightRatio > 1 || widthRatio > 1) {
			if (heightRatio > widthRatio) {
				bmpFactoryOptions.inSampleSize = heightRatio;
			} else {
				bmpFactoryOptions.inSampleSize = widthRatio;
			}
		}

		bmpFactoryOptions.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.logoadd, bmpFactoryOptions);
		return bitmap;
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			int resultlan = tts.setLanguage(Locale.US);
			if (resultlan == TextToSpeech.LANG_MISSING_DATA
					|| resultlan == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.v("NOt Supported Tag ", "language is not available");
			}

		} else {
			Log.v("TAG", "could not initialize tts");
		}
	}

	private void sayWord(String Data1) {
		tts.setSpeechRate(speed2);
		tts.setPitch(typeval);
		tts.speak(Data1, TextToSpeech.QUEUE_FLUSH, null);
	}

}

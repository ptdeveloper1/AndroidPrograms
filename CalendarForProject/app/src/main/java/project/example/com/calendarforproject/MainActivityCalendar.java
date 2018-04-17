package project.example.com.calendarforproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivityCalendar extends AppCompatActivity {
Button mbutton;
    TextView mtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar);

        mbutton=(Button) findViewById(R.id.button);
        mtextview=(TextView)findViewById(R.id.textview);

        Intent incommingintent=getIntent();
        String date=incommingintent.getStringExtra("date");
        mtextview.setText(date);

        mbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivityCalendar.this,CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}

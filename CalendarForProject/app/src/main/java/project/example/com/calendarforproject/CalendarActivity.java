package project.example.com.calendarforproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

/**
 * Created by ADMIN on 7/1/2017.
 */

public class CalendarActivity extends AppCompatActivity {
private static final String tag="CalendarActivity";
    private CalendarView mcalview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mcalview=(CalendarView)findViewById(R.id.calendarView);


        mcalview.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date=(month)+"/"+(dayOfMonth)+"/"+(year);
                Intent intent=new Intent(CalendarActivity.this, MainActivityCalendar.class);
                intent.putExtra("date",date);
                startActivity(intent);


            }
        });
    }
}

package dateandtimepicker.example.com.dateandtimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivityDataAndTime extends AppCompatActivity {
    Button mbuttondate, mbuttontime;
    EditText mtextdate, mtexttime;
    private int myear, mmonth, mday, mhour, mminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_and_time);

        mbuttondate = (Button) findViewById(R.id.datebutton);
        mbuttontime = (Button) findViewById(R.id.timebutton);
        mtextdate = (EditText) findViewById(R.id.dateedittext);
        mtexttime = (EditText) findViewById(R.id.timeedittext);

        mbuttondate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //get current date
                Calendar cal = Calendar.getInstance();
                myear = cal.get(Calendar.YEAR);
                mmonth = cal.get(Calendar.MONTH);
                mday = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityDataAndTime.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mtextdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, myear, mmonth, mday);
                datePickerDialog.show();
            }
        });


        mbuttontime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                mhour=cal.get(Calendar.HOUR_OF_DAY);
                mminute=cal.get(Calendar.MINUTE);

                TimePickerDialog timepicker=new TimePickerDialog(MainActivityDataAndTime.this,new TimePickerDialog.OnTimeSetListener(){
                @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    mtexttime.setText(hourOfDay+":"+minute);
                }
                }, mhour, mminute, false);
                timepicker.show();

            }
        });

    }
}
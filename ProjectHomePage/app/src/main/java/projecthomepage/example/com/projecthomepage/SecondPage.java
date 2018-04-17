package projecthomepage.example.com.projecthomepage;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SecondPage extends AppCompatActivity {
    Spinner mspinner;//for drop down*/
    Button mbuttonnext;//to navigate to the grid of dates
    Button mbuttondate, mbuttontime;
    EditText mtextdate, mtexttime;
    private int myear, mmonth, mday, mhour, mminute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        mbuttondate = (Button) findViewById(R.id.datebutton);
        mbuttontime = (Button) findViewById(R.id.timebutton);
        mtextdate = (EditText) findViewById(R.id.dateedittext);
        mtexttime = (EditText) findViewById(R.id.timeedittext);


        mbuttonnext=(Button)findViewById(R.id.nextidgrid);
        mbuttonnext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String activityName= (String)(mspinner.getSelectedItem());
                String date= mtextdate.getText().toString();
                String time=mtexttime.getText().toString();
                Log.d("SecondPage",time);
                GridWithDates.firstDate=date.substring(0,date.length()-5);
                Habit habit= new Habit(activityName,date,time);
                DBAdapter.addHabitData(habit);
                Intent intent = new Intent(SecondPage.this, GridWithDates.class);
                startActivity(intent);
                SecondPage.this.finish();
            }
        });

//***********drop down list of Activities******************
       mspinner=(Spinner)findViewById(R.id.spinnerId);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(SecondPage.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.activities));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner.setAdapter(myadapter);

//***********Date and Time***********************************
        mbuttondate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //get current date
                Calendar cal = Calendar.getInstance();
                myear = cal.get(Calendar.YEAR);
                mmonth = cal.get(Calendar.MONTH);
                mday = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SecondPage.this, new DatePickerDialog.OnDateSetListener() {
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
                Calendar cal = Calendar.getInstance();
                mhour = cal.get(Calendar.HOUR_OF_DAY);
                mminute = cal.get(Calendar.MINUTE);
                TimePickerDialog timepicker = new TimePickerDialog(SecondPage.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mtexttime.setText(hourOfDay + ":" + minute);
                       AlarmFirstClass.alarmhour=hourOfDay;
                       AlarmFirstClass.alarmminute=minute;
                    }
                }, mhour, mminute, false);
                timepicker.show();
            }
        });
    }
   /*@Override
    protected void onRestart() {
        super.onRestart();
        mtextdate.setText();
        mtexttime.setText("");
    }*/
}

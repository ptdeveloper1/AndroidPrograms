package projecthomepage.example.com.projecthomepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ADMIN on 7/4/2017.
 */

public class RepeatingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeting_activity);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent dateintent = new Intent(RepeatingActivity.this, AlarmGridDates.class);
                startActivity(dateintent);

            }
        });
    }
}
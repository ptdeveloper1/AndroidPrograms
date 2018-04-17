package projecthomepage.example.com.projecthomepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ADMIN on 7/4/2017.
 */
//This class is part of user interface and directs to other class
public class TitlePage extends AppCompatActivity {
    private Button mbuttonnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_title);
        setMbuttonnext();
    }
    public void setMbuttonnext() {
        mbuttonnext = (Button) findViewById(R.id.mbuttonnext);
        mbuttonnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitlePage.this, SecondPage.class);
                startActivity(intent);
                TitlePage.this.finish();
            }
        });
    }
}

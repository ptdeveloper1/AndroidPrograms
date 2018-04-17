package multiactivity.example.com.multiactivitiesprog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
final static String tag="MainActivity";
    Button mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"on Create is called");
        setContentView(R.layout.activity_main);
        mbutton=(Button) findViewById(R.id.button1);
        //anonymous inner class, we cant instantiate an interface with out implementing it
        //if we write onClick method in xml then just this is enough, no need of class name MainActivity.this
        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void OnClick(){
             /*Intent myintent   =new Intent(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(myintent);*/
             launch();
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag,"on Create is called");//do this in both main activities
        //after run choose dubug option. In regix we can select filter to specify required
        //edit configuration
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();// can be overrided like above
    }

    public void launch(){
        Intent myintent   =new Intent(MainActivity.this,Main2Activity.class);
        this.startActivity(myintent);
    }
   /* public void launch(){
        Intent myintent   =new Intent(Main2Activity.this,MainActivity.class);
        this.startActivity(myintent);
    }*/
}

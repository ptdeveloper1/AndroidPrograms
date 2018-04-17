package multiactivity.example.com.multiactivitiesprog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
Button mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mbutton=(Button)findViewById(R.id.button2);
        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void OnClick(){
                Main2Activity.this.finish();// this is good practice to use this method to save the memory
                //launch();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //this creates many instances in the stack which cause crash of the sytem if that repats hence
    // we need to use finish() method to remove the used methods
}

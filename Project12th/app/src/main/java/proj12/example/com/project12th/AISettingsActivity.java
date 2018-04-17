package proj12.example.com.project12th;

/**
 * Created by ADMIN on 11/28/2017.
 */
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class AISettingsActivity extends BaseActivity implements
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private CheckBox bluetoothSwitch;

    private SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsManager = ((AIApplication) getApplication()).getSettingsManager();

        ViewGroup bluetoothSection = (ViewGroup) findViewById(R.id.activity_settings_bluetooth_section);
        bluetoothSection.setOnClickListener(this);

        bluetoothSwitch = (CheckBox) findViewById(R.id.activity_settings_bluetooth_swith);
        bluetoothSwitch.setChecked(settingsManager.isUseBluetooth());
        bluetoothSwitch.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_settings_bluetooth_section:
                bluetoothSwitch.performClick();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.activity_settings_bluetooth_swith:
                settingsManager.setUseBluetooth(isChecked);
                break;
        }
    }
}
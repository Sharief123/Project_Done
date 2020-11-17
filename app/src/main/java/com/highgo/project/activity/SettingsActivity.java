package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.highgo.project.R;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch aSwitchOne,aSwitchTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");

        aSwitchOne = findViewById(R.id.switchOne);
        aSwitchTwo =  findViewById(R.id.switchTwo);


    }

    // ToolBar Menus
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
        }
        if (item.getItemId()==android.R.id.home)
            finish();
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId())
        {
            case R.id.switchOne:
                if (isChecked){
                    showMessage("Switch is On");
                } else {
                    showMessage("Switch is Off");
                }
                break;

            case R.id.switchTwo:
                if (isChecked)
                    showMessage("Switch is On");
                else {
                    showMessage("Switch is Off");
                }
                break;
            default:

        }
    }

    private void showMessage(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();
    }
}

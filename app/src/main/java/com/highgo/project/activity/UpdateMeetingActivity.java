package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.highgo.project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateMeetingActivity extends AppCompatActivity {

    EditText nameET,reminder_date,description;
    Button send,back,reset_to_default;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meeting);

        //Declaring Values
        nameET = findViewById(R.id.nameET);
        reminder_date = findViewById(R.id.reminderDateET);
        description = findViewById(R.id.descriptionET);
        spinner = findViewById(R.id.statusSpinner);
        send = findViewById(R.id.sendBN);
        back = findViewById(R.id.BackBN);
        reset_to_default = findViewById(R.id.resetToDefaultBN);

        adapter = ArrayAdapter.createFromResource(getBaseContext(),R.array.selection,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();

        String name_value = intent.getStringExtra("m_name");
        String rem_value = intent.getStringExtra("rem_date");
        int status = intent.getIntExtra("status",01);
        String des_value = intent.getStringExtra("descr");

        nameET.setText(name_value);
        reminder_date.setText(rem_value);
        description.setText(des_value);
        spinner.setSelection(status);

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fromate = "MMM dd,YYYY";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromate, Locale.UK);

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                reminder_date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateMeetingActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String p_name = nameET.getText().toString();
                final String rem = reminder_date.getText().toString();
                final String spin = spinner.getSelectedItem().toString();
                final String desc = description.getText().toString();

                Intent intent1 = getIntent();
                intent1.putExtra("m_p_name",p_name);
                intent1.putExtra("m_rem",rem);
                intent1.putExtra("m_spin",spin);
                intent1.putExtra("m_desc",desc);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getSupportActionBar().setTitle("Meeting Details");
        getSupportActionBar().setTitle("Update Meeting");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        }
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return  true;
    }
}

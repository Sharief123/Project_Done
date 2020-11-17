package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.highgo.project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewMeetingActivity extends AppCompatActivity {


    Button addMeeting,resetDefault;
    EditText name,reminder_date,due_date,description;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_meeting);

        // Declaring all the Values
        addMeeting = findViewById(R.id.addMeetingBN);
        resetDefault = findViewById(R.id.resetToDefaultBN);
        name = findViewById(R.id.nameET);
        reminder_date = findViewById(R.id.reminderDateET);
        due_date = findViewById(R.id.dueDateET);
        description = findViewById(R.id.descriptionET);
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.priority,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // DatePicker For Due Date EditText
        final Calendar due_date_calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String formate = "MMM dd,YYYY";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate,Locale.UK);

                due_date_calendar.set(Calendar.YEAR,year);
                due_date_calendar.set(Calendar.MONTH,month);
                due_date_calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                due_date.setText(simpleDateFormat.format(due_date_calendar.getTime()));
            }
        };

        due_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddNewMeetingActivity.this,onDateSetListener,due_date_calendar.get(Calendar.YEAR),
                                    due_date_calendar.get(Calendar.DAY_OF_MONTH),due_date_calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // DatePicker For Reminder Date EditText
        final Calendar reminder_date_calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String formate = "MMM dd,YYYY";
                SimpleDateFormat dateFormat = new SimpleDateFormat(formate, Locale.UK);

                reminder_date_calendar.set(Calendar.YEAR,year);
                reminder_date_calendar.set(Calendar.MONTH,month);
                reminder_date_calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                reminder_date.setText(dateFormat.format(reminder_date_calendar.getTime()));
            }
        };

        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddNewMeetingActivity.this,dateSetListener,reminder_date_calendar.get(Calendar.YEAR),
                                            reminder_date_calendar.get(Calendar.MONTH),reminder_date_calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        // AddMeeting Button
        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String m_name = name.getText().toString();
                final String m_r_date = reminder_date.getText().toString();
                final String m_d_date = due_date.getText().toString();
                final String m_priority = spinner.getSelectedItem().toString();
                final String m_description = description.getText().toString();

                Toast.makeText(getApplicationContext(),"Your Data Saved",Toast.LENGTH_LONG).show();

                Log.e("Hello",m_name);
                Log.e("date",m_r_date);
                Log.e("date",m_d_date);
                Log.e("priority",m_priority);
                Log.e("Back",m_description);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setTitle("New Meetings");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

}

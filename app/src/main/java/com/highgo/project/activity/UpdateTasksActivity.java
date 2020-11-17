package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.highgo.project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateTasksActivity extends AppCompatActivity {


    Button updateTask,saveBN;
    EditText t_name,t_description,t_start_date,t_end_date,t_assigned_by,t_assigned_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tasks);

        getSupportActionBar().setTitle("Update Tasks Details");

        //declaring the fields
        saveBN = findViewById(R.id.saveBN);
        updateTask = findViewById(R.id.updateBN);
        t_name = findViewById(R.id.tnameET);
        t_description = findViewById(R.id.tDescriptionET);
        t_start_date = findViewById(R.id.tStartDateET);
        t_end_date = findViewById(R.id.tEndDateET);
        t_assigned_by = findViewById(R.id.teamLeadET);
        t_assigned_to = findViewById(R.id.sherryET);

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener task_start_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String format = "MMM dd,YYYY";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.UK);

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                t_start_date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        t_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateTasksActivity.this,task_start_date,calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Calendar calendar1 = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener task_end_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String format1 = "MMM dd,YYYY";
                SimpleDateFormat dateFormat = new SimpleDateFormat(format1,Locale.UK);

                calendar1.set(Calendar.YEAR,year);
                calendar1.set(Calendar.MONTH,month);
                calendar1.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                t_end_date.setText(dateFormat.format(calendar1.getTime()));
            }
        };
        t_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateTasksActivity.this,task_end_date,calendar1.get(Calendar.YEAR),
                                        calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Strings coming from TaskDetailsActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("HI_ONE");
        String description = intent.getStringExtra("HI_TWO");
        final String start_date = intent.getStringExtra("HI_THREE");
        String end_date = intent.getStringExtra("HI_FOUR");
        String assigned_by = intent.getStringExtra("HI_FIVE");
        String assigned_to = intent.getStringExtra("HI_SIX");

        t_name.setText(name);
        t_description.setText(description);
        t_start_date.setText(start_date);
        t_end_date.setText(end_date);
        t_assigned_by.setText(assigned_by);
        t_assigned_to.setText(assigned_to);



        updateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = t_name.getText().toString();
                String description = t_description.getText().toString();
                String start_date = t_start_date.getText().toString();
                String end_date = t_end_date.getText().toString();
                String assigned_by = t_assigned_by.getText().toString();
                String assigned_to = t_assigned_to.getText().toString();

                //returning the updated strings to TaskDetailsActivity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("task_name",name);
                returnIntent.putExtra("task_description",description);
                returnIntent.putExtra("task_start_date",start_date);
                returnIntent.putExtra("task_end_date",end_date);
                returnIntent.putExtra("task_assigned_by",assigned_by);
                returnIntent.putExtra("task_assigned_to",assigned_to);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

        }

        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
    }
}

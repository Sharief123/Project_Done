package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.highgo.project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateProjectActivity extends AppCompatActivity {


    EditText name, description,start_date,end_date,done_by,assigned_by;
    Button update,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_project);

        getSupportActionBar().setTitle("Update Project Details");

        //Declaring the values
        name = findViewById(R.id.nameET);
        description = findViewById(R.id.descriptionET);
        start_date = findViewById(R.id.startDateET);
        end_date = findViewById(R.id.endDateET);
        done_by = findViewById(R.id.assignedToTV);
        assigned_by = findViewById(R.id.assignedBYET);

        // Getting date for the Project Start Date using DatePicker
        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener project_start_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String formate = "MMM dd,YYYY";
                SimpleDateFormat dateFormat = new SimpleDateFormat(formate, Locale.UK);

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                start_date.setText(dateFormat.format(calendar.getTime()));
            }
        };

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateProjectActivity.this, project_start_date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Getting date for the Project End Date using DatePicker
        final Calendar calendar1 = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener project_end_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fomate = "MMM dd,YYYY";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fomate, Locale.UK);

                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                end_date.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        };

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateProjectActivity.this, project_end_date, calendar1.get(Calendar.YEAR),
                        calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // Values form ProjectDetailsActivity
        Intent intent = getIntent();
        final String p_name = intent.getStringExtra("project_one");
        String p_description = intent.getStringExtra("project_two");
        String p_start_date = intent.getStringExtra("project_three");
        String p_end_date = intent.getStringExtra("project_four");
        String p_assigned_to = intent.getStringExtra("project_five");
        String p_assigned_by = intent.getStringExtra("project_six");

        name.setText(p_name);
        description.setText(p_description);
        start_date.setText(p_start_date);
        end_date.setText(p_end_date);
        done_by.setText(p_assigned_to);
        assigned_by.setText(p_assigned_by);

        update = findViewById(R.id.updateBN);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p_one = name.getText().toString();
                String p_two = description.getText().toString();
                String p_three = start_date.getText().toString();
                String p_four = end_date.getText().toString();
                String p_five = done_by.getText().toString();
                String p_six = assigned_by.getText().toString();

                Intent returnUpdatedIntent = new Intent();
                returnUpdatedIntent.putExtra("p_1", p_one);
                returnUpdatedIntent.putExtra("p_2", p_two);
                returnUpdatedIntent.putExtra("p_3", p_three);
                returnUpdatedIntent.putExtra("p_4", p_four);
                returnUpdatedIntent.putExtra("p_5", p_five);
                returnUpdatedIntent.putExtra("p_6", p_six);
                setResult(RESULT_FIRST_USER, returnUpdatedIntent);
                finish();
            }
        });

    }

        // This for ToolBar Menus.
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

            }
            if (item.getItemId() == android.R.id.home)
                finish();
            return true;

        }

}

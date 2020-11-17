package com.highgo.project.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.highgo.project.R;
import com.highgo.project.adapter.EmployeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddProjectActivity extends AppCompatActivity implements EmployeAdapter.onOptionSelected {


    List<String> SelectedString = new ArrayList<>();

    Context context = this;
    TextView assigned_to;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EmployeAdapter employeAdapter;
    List<String> stringList = new ArrayList<>();

    TextView pStartDate,pEndDate;
    EditText pNameET;
    Button saveBN,cancelBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        assigned_to = findViewById(R.id.pAssignedToET);
        pNameET = findViewById(R.id.pNameET);
        saveBN = findViewById(R.id.saveBN);

        getSupportActionBar().setTitle("New Project Form");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveBN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("Hello","Hello");
                Toast.makeText(getApplicationContext(),"Your Data is Saved",Toast.LENGTH_LONG).show();
            }
        });

        String name = pNameET.getText().toString();
        pNameET.setText(name);

        pEndDate = findViewById(R.id.pEndDateDP);

        pStartDate = findViewById(R.id.pStartDateDP);

        final Calendar calendar = java.util.Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String myFormat = "MMM dd, yyyy";
                SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.UK);

                calendar.set(java.util.Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                pStartDate.setText(sdformat.format(calendar.getTime()));
            }
        };

        pStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(AddProjectActivity.this, dateSetListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String myFormat = "MMM dd, yyyy";
                SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.UK);

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                pEndDate.setText(sdformat.format(myCalendar.getTime()));

            }
        };

        pEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddProjectActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        assigned_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddProjectActivity.this);

                LayoutInflater inflater = AddProjectActivity.this.getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.employees_list,null);
                builder.setView(alertLayout);

                recyclerView = alertLayout.findViewById(R.id.listOfEmployeesRecyclerView);
                layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                stringList = Arrays.asList(getResources().getStringArray(R.array.lists_of_employees));
                employeAdapter = new EmployeAdapter(stringList,context, AddProjectActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(employeAdapter);
                recyclerView.setHasFixedSize(true);

                builder.setPositiveButton("Assign To", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        assigned_to.setText(SelectedString.toString());
                        dialog.dismiss();

                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onOptionSelected(String option, int type) {
        if (type==1){
            SelectedString.remove(option);
        } else if (type==2){
            SelectedString.add(option);
        }

    }
}

package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.highgo.project.R;
import com.highgo.project.adapter.ListOfTasksAdapter;
import com.highgo.project.adapter.TasksAdapter;
import com.highgo.project.model.Tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListsOfTasksActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener {


    String[] ProjectName = {"Project One", "Project Two", "Project Three", "Project Four", "Project Five",
            "Project Six", "Project Seven", "Project Eight", "Project Nine", "Project Ten"};

    String[] SubjectDescription = {"Task Subject Description One ","Task Subject Description Two", "Task Subject Description Three ","Task Subject Description Four","Task Subject Description Five","Task Subject Description Six",
            "Task Subject Description Seven ","Task Subject Description Eight", "Task Subject Description Nine ","Task Subject Description Ten"};

    String[] StartDate = {"12-08-2020", "10-09-2020", "19-09-2020", "10-08-2020", "25-08-2020",
            "14-08-2020", "03-09-2020", "16-09-2020", "28-08-2020", "15-08-2020"};

    String[] EndDate = {"12-10-2020", "10-11-2020", "19-11-2020", "10-12-2020", "25-12-2020",
            "14-11-2020", "03-10-2020", "16-12-2020", "28-11-2020", "15-10-2020"};

    String[] Names = {"Team A", "Team B", "Team D", "Team C", "Team B",
            "Team A", "Team D", "Team B", "Team A", "Team C"};


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Tasks> tasksList = new ArrayList<>();
    TasksAdapter adapter;

    Spinner spinner;
    EditText start_date,end_date,description,subject;
    ArrayAdapter<CharSequence> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_of_tasks);

        recyclerView = findViewById(R.id.tasksRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new TasksAdapter(tasksList);
        Tasks();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.addTask:

                final AlertDialog.Builder builder = new AlertDialog.Builder(ListsOfTasksActivity.this);
                LayoutInflater inflater = ListsOfTasksActivity.this.getLayoutInflater();

                final View alertLayout = inflater.inflate(R.layout.add_task_layout,null);
                builder.setView(alertLayout);


                spinner = alertLayout.findViewById(R.id.spinnerDataET);
                subject = alertLayout.findViewById(R.id.subjectET);
                start_date = alertLayout.findViewById(R.id.startDateET);
                end_date = alertLayout.findViewById(R.id.endDateET);
                description = alertLayout.findViewById(R.id.descriptionET);
                arrayAdapter = ArrayAdapter.createFromResource(getBaseContext(),R.array.lists_of_projects,android.R.layout.simple_spinner_dropdown_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);


                final Calendar calendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String format = "MMM dd,YYYY";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.UK);

                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        start_date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                start_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            new DatePickerDialog(builder.getContext(),dateSetListener,calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                final Calendar calendar1 = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String format = "MMM dd,YYYY";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.UK);

                        calendar1.set(Calendar.YEAR,year);
                        calendar1.set(Calendar.MONTH,month);
                        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        end_date.setText(simpleDateFormat.format(calendar1.getTime()));
                    }
                };

                end_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DatePickerDialog(builder.getContext(),dateSetListener1,calendar1.get(Calendar.YEAR),
                                calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                builder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String s_d = start_date.getText().toString();
                        final String e_d = end_date.getText().toString();
                        final String project = spinner.getSelectedItem().toString();
                        final String sub = subject.getText().toString();
                        final String desc = description.getText().toString();

                        Log.e("date",s_d);
                        Log.e("date",e_d);
                        Log.e("project",project);
                        Log.e("subject",sub);
                        Log.e("description",desc);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

        }
        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
    }

    public void Tasks()
    {
        int count = 0;
        for (String name : ProjectName){
            Tasks tasks = new Tasks(name,SubjectDescription[count],StartDate[count],EndDate[count],Names[count]);
            tasksList.add(tasks);
            count++;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tasks_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        getSupportActionBar().setTitle("Lists Of Tasks");

        androidx.appcompat.widget.SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();

        List<Tasks> list = new ArrayList<>();

        for (Tasks tasks : tasksList)
        {
            if (tasks.getProjectName().toLowerCase().contains(userInput)){
                list.add(tasks);
            } else if
                (tasks.getSubjectDescription().toLowerCase().contains(userInput)){
                list.add(tasks);
        } else if (tasks.getNames().toLowerCase().contains(userInput)){
                list.add(tasks);
            } else if (tasks.getStartDate().toLowerCase().contains(userInput)){
                list.add(tasks);
            } else if (tasks.getEndDate().toLowerCase().contains(userInput)){
                list.add(tasks);
            }
        }

        adapter.sreachlist(list);
        return true;
    }
}

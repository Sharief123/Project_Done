package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.highgo.project.R;
import com.highgo.project.adapter.ListOfTasksAdapter;
import com.highgo.project.model.ListOfProjects;
import com.highgo.project.model.ListOfTasks;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsActivity extends AppCompatActivity implements ListOfTasksAdapter.CallBackData, SearchView.OnQueryTextListener {

    public static final int task_name_1 = 01;
    public static final int project_name_1 = 01;

    String[] TaskName = {"Task One", "Task Two", "Task Three", "Task Four", "Task Five",
            "Task Six", "Task Seven", "Task Eight", "Task Nine", "Task Ten"};

    String[] TaskStartDate = {"12-08-2020", "10-09-2020", "19-09-2020", "10-08-2020", "25-08-2020",
            "14-08-2020", "03-09-2020", "16-09-2020", "28-08-2020", "15-08-2020"};

    String[] TaskEndDate = {"12-10-2020", "10-11-2020", "19-11-2020", "10-12-2020", "25-12-2020",
            "14-11-2020", "03-10-2020", "16-12-2020", "28-11-2020", "15-10-2020"};

    String[] DoneBy = {"Team A", "Team B", "Team D", "Team C", "Team B",
            "Team A", "Team D", "Team B", "Team A", "Team C"};

    String[] TaskDescription = {"Description of Task One", "Description of Task Two", "Description of Task Three", "Description of Task Four", "Description of Task Five",
            "Description of Task Six", "Description of Task Seven", "Description of Task Eight", "Description of Task Nine", "Description of Task Ten"};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ListOfTasksAdapter listOfTasksAdapter;
    List<ListOfTasks> listOfTasks = new ArrayList<>();
    Context context = this;

    TextView nameTV,tDescriptionTV,start_date,end_date,assigned_to,assigned_by;

    int i = 0;
    ProgressBar progressBar;
    TextView progressData;
    ImageView addNewTask;
    Button updateBN,saveBN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);


        progressBar = findViewById(R.id.projectProgressBarPB);
        progressData = findViewById(R.id.progressBarData);
        addNewTask = findViewById(R.id.addNewTaskIV);
        updateBN = findViewById(R.id.updateBN);
        saveBN = findViewById(R.id.saveBN);

        nameTV = findViewById(R.id.nameTV);
        tDescriptionTV = findViewById(R.id.descriptionTV);
        start_date = findViewById(R.id.startDateTV);
        end_date = findViewById(R.id.endDateTV);
        assigned_to = findViewById(R.id.assignedToTV);
        assigned_by = findViewById(R.id.assignedBYTV);


        recyclerView = findViewById(R.id.listOfTasksRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        ListOfTasks();
        listOfTasksAdapter = new ListOfTasksAdapter(listOfTasks,context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listOfTasksAdapter);
        recyclerView.setHasFixedSize(true);

        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectDetailsActivity.this,AddTaskActivity.class);
                startActivity(intent);
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i<100){
                    progressData.setText(76+"%");
                    progressBar.setProgress(76);
                    i++;
                    progressBar.postDelayed(this,200);
                }
            }
        },200);

        // Values From ListOfProjects Activity(Adapter)
        Intent intent = getIntent();
        final String project_name = intent.getStringExtra("project_name");
        String project_description = intent.getStringExtra("project_description");
        String project_start_date = intent.getStringExtra("project_start_date");
        String project_end_date = intent.getStringExtra("project_end_date");
        String project_assigned_by = intent.getStringExtra("project_assigned_to");

        nameTV.setText(project_name);
        tDescriptionTV.setText(project_description);
        start_date.setText(project_start_date);
        end_date.setText(project_end_date);
        assigned_to.setText(project_assigned_by);

        // Values Passing to UpdateProjectActivity from ProjectDetailsActivity
        final String p_one = nameTV.getText().toString();
        final String p_two = tDescriptionTV.getText().toString();
        final String p_three = start_date.getText().toString();
        final String p_four = end_date.getText().toString();
        final String p_five = assigned_to.getText().toString();
        final String p_six = assigned_by.getText().toString();

        updateBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectDetailsActivity.this,UpdateProjectActivity.class);
                intent.putExtra("project_one",p_one);
                intent.putExtra("project_two",p_two);
                intent.putExtra("project_three",p_three);
                intent.putExtra("project_four",p_four);
                intent.putExtra("project_five",p_five);
                intent.putExtra("project_six",p_six);
                startActivityForResult(intent,project_name_1);
            }
        });

        saveBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updated_project_name = nameTV.getText().toString();
                String updated_project_description = tDescriptionTV.getText().toString();
                String updated_project_start_date = start_date.getText().toString();
                String updated_project_end_date = end_date.getText().toString();
                String updated_project_done_by = assigned_to.getText().toString();

                // returning data to ListOfProjectsActivity
                Intent resultUpdated = new Intent();
                resultUpdated.putExtra("updated_p_name",updated_project_name);
                resultUpdated.putExtra("updated_p_description",updated_project_description);
                resultUpdated.putExtra("updated_p_start_date",updated_project_start_date);
                resultUpdated.putExtra("updated_p_en_date",updated_project_end_date);
                resultUpdated.putExtra("updated_p_done_by",updated_project_done_by);
                setResult(RESULT_OK,resultUpdated);
                finish();
            }
        });


    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch(resultCode) {

            case RESULT_OK:

                if (requestCode == task_name_1) {

                    if (data != null) {

                        List<ListOfTasks> addNewData = new ArrayList<>();

                        // values form updatedTasksActivity
                            String updated_task_name = data.getStringExtra("t_one");


                            Log.e("Hello", updated_task_name);

                            String updated_task_description = data.getStringExtra("t_two");
                            Log.e("Hello", updated_task_description);

                            String updated_task_start = data.getStringExtra("t_three");
                            Log.e("Hello", updated_task_start);

                            String updated_task_end = data.getStringExtra("t_four");
                            Log.e("Hello", updated_task_end);

                            String updated_task_done = data.getStringExtra("t_five");
                            Log.e("Hello", updated_task_done);

                    }
                }

                break;

            case RESULT_FIRST_USER:

                if (requestCode == project_name_1){

                    // Values form updateProjectActivity
                    String p_one = data.getStringExtra("p_1");
                    nameTV.setText(p_one);

                    String p_two = data.getStringExtra("p_2");
                    tDescriptionTV.setText(p_two);

                    String p_three = data.getStringExtra("p_3");
                    start_date.setText(p_three);

                    String p_four = data.getStringExtra("p_4");
                    end_date.setText(p_four);

                    String p_five = data.getStringExtra("p_5");
                    assigned_to.setText(p_five);

                    String p_six = data.getStringExtra("p_6");
                    assigned_by.setText(p_six);

                }
            case RESULT_CANCELED:

                break;
        }
    }

    @Override
    public void onHandleSelection(int position, ListOfTasks listOfTasks) {

        // Passing values to TaskDetailsActivity
        Intent intent = new Intent(ProjectDetailsActivity.this, TaskDetailsActivity.class);
        intent.putExtra("text_one",listOfTasks.getTaskName());
        intent.putExtra("text_two",listOfTasks.getTaskDescription());
        intent.putExtra("text_three",listOfTasks.getTaskStartDate());
        intent.putExtra("text_four",listOfTasks.getTaskEndDate());
        intent.putExtra("text_five",listOfTasks.getDoneBy());
        intent.putExtra("Position", position);
        startActivityForResult(intent, task_name_1);
    }

    private void ListOfTasks(){
        int count = 0;
        for (String name : TaskName)
        {
            ListOfTasks list = new ListOfTasks(name,TaskStartDate[count],TaskEndDate[count],DoneBy[count],TaskDescription[count]);
            listOfTasks.add(list);
            count++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        getSupportActionBar().setTitle("Project Details");

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<ListOfTasks> newList = new ArrayList<>();

        // SearchView For the Tasks in Recyclerview
        for (ListOfTasks listOfTasks : listOfTasks)
        {
            if (listOfTasks.getTaskName().toLowerCase().contains(userInput)){
                newList.add(listOfTasks);
            } else if (listOfTasks.getTaskDescription().toLowerCase().contains(userInput)){
                newList.add(listOfTasks);
            } else if (listOfTasks.getTaskStartDate().toLowerCase().contains(userInput)){
                newList.add(listOfTasks);
            } else if (listOfTasks.getTaskEndDate().toLowerCase().contains(userInput)){
                newList.add(listOfTasks);
            } else if (listOfTasks.getDoneBy().toLowerCase().contains(userInput)){
                newList.add(listOfTasks);
            }
        }
        listOfTasksAdapter.updateList(newList);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return true;
    }
}


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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.highgo.project.R;
import com.highgo.project.adapter.ListOfProjectsAdapter;
import com.highgo.project.model.ListOfProjects;

import java.util.ArrayList;
import java.util.List;

public class ListOfProjectsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListOfProjectsAdapter.DataOfProjects {

    public static final int project_name_01 = 01;

    Context context = this;

    String[] NameProject = {"Project One", "Project Two", "Project Three", "Project Four", "Project Five",
            "Project Six", "Project Seven", "Project Eight", "Project Nine", "Project Ten"};

    String[] ProjectStartDate = {"12-08-2020", "10-09-2020", "19-09-2020", "10-08-2020", "25-08-2020",
            "14-08-2020", "03-09-2020", "16-09-2020", "28-08-2020", "15-08-2020"};

    String[] ProjectEndDate = {"12-10-2020", "10-11-2020", "19-11-2020", "10-12-2020", "25-12-2020",
            "14-11-2020", "03-10-2020", "16-12-2020", "28-11-2020", "15-10-2020"};

    String[] CreatedBy = {"Team A", "Team B", "Team D", "Team C", "Team B",
            "Team A", "Team D", "Team B", "Team A", "Team C"};

    String[] Description = {"Description of Project One", "Description of Project Two", "Description of Project Three", "Description of Project Four", "Description of Project Five",
            "Description of Project Six", "Description of Project Seven", "Description of Project Eight", "Description of Project Nine", "Description of Project Ten"};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ListOfProjectsAdapter listOfProjectsAdapter;
    List<ListOfProjects> listOfProjects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_projects);


        recyclerView = findViewById(R.id.listOfProjectsRecyclerview);
        layoutManager = new LinearLayoutManager(this);
        ListOfProjects();
        listOfProjectsAdapter = new ListOfProjectsAdapter(listOfProjects, context);
        recyclerView.setAdapter(listOfProjectsAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }

    private void ListOfProjects() {
        int count = 0;
        for (String name : NameProject) {
            ListOfProjects list = new ListOfProjects(name, ProjectStartDate[count], ProjectEndDate[count], CreatedBy[count], Description[count] );
            listOfProjects.add(list);
            count++;
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (project_name_01==requestCode)
        {
            if (resultCode==RESULT_OK)
            {

                 String updated_project_name = data.getStringExtra("updated_p_name");
                Log.e("name", updated_project_name);
               /*String updated_project_description = data.getStringExtra("updated_p_description");
                Log.e("description",updated_project_description);

                String updated_project_start_date = data.getStringExtra("updated_p_start_date");
                Log.e("s_date",updated_project_start_date);

                String updated_project_end_date = data.getStringExtra("updated_p_en_date");
                Log.e("end_date",updated_project_end_date);

                String updated_project_assigned_to = data.getStringExtra("updated_p_done_by");
                Log.e("done_by",updated_project_assigned_to); */
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.listofprojects_toolbar, menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        getSupportActionBar().setTitle("List of Projects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.addNewProject:
                Intent intent = new Intent(ListOfProjectsActivity.this, AddProjectActivity.class);
                startActivity(intent);
                return true;
        }
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<ListOfProjects> newList = new ArrayList<>();

        // SearchView for Projects in RecyclerView
        for (ListOfProjects listOfProjects : listOfProjects)
        {
            if (listOfProjects.getNameProject().toLowerCase().contains(userInput)) {
                newList.add(listOfProjects);
            } else if (listOfProjects.getCreatedBy().toLowerCase().contains(userInput)){
                newList.add(listOfProjects);
            } else if (listOfProjects.getProjectStartDate().toLowerCase().contains(userInput)){
                newList.add(listOfProjects);
            } else if (listOfProjects.getProjectEndDate().toLowerCase().contains(userInput)){
                newList.add(listOfProjects);
        } else if (listOfProjects.getDescription().toLowerCase().contains(userInput)){
                newList.add(listOfProjects);
            }
        }
        listOfProjectsAdapter.updateList(newList);
        return true;
    }

    @Override
    public void onProjects(int position, ListOfProjects projects) {
        Intent intent = new Intent(this,ProjectDetailsActivity.class);
        intent.putExtra("project_name",projects.getNameProject());
        intent.putExtra("project_description",projects.getDescription());
        intent.putExtra("project_start_date",projects.getProjectStartDate());
        intent.putExtra("project_end_date",projects.getProjectEndDate());
        intent.putExtra("project_assigned_to",projects.getCreatedBy());
        intent.putExtra("Position", position);
        startActivityForResult(intent,project_name_01);
    }
}

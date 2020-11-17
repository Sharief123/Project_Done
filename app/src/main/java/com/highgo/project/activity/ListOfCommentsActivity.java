package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.highgo.project.R;
import com.highgo.project.adapter.CommentsAdapter;
import com.highgo.project.model.Comments;
import com.highgo.project.model.Meetings;

import java.util.ArrayList;
import java.util.List;

public class ListOfCommentsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, CommentsAdapter.DataForward {

    public static final int comments = 01;

    String Project[] = {"Project Name One","Project Name Two","Project Name Three","Project Name Four","Project Name Five",
                        "Project Name Six","Project Name Seven","Project Name Eight","Project Name Nine","Project Name Ten"};

    String Subject[] = {"Subject Of Project One","Subject Of Project Two","Subject Of Project Three","Subject Of Project Four","Subject Of Project Five",
                            "Subject Of Project Six","Subject Of Project Seven","Subject Of Project Eight","Subject Of Project Nine","Subject Of Project Ten"};

    String CreatedBy[] = {"A","B","D","A","C","B","A","C","D","A"};

    String Creation_Date[] = {"12-10-2020", "10-11-2020", "19-11-2020", "10-12-2020", "25-12-2020",
                                  "14-11-2020", "03-10-2020", "16-12-2020", "28-11-2020", "15-10-2020"};

    List<Comments> commentsList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CommentsAdapter commentsAdapter;
    Context context = this;

    Spinner spinner;
    EditText subject_name,c_description;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_comments);

        recyclerView = findViewById(R.id.commentRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        Comments();
        commentsAdapter = new CommentsAdapter(commentsList,context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(commentsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public void Comments()
    {
        int count = 0;
        for (String project : Project)
        {
            Comments comments = new Comments(project,Subject[count],CreatedBy[count],Creation_Date[count]);
            commentsList.add(comments);
            count++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setTitle("Create comment ");

        getMenuInflater().inflate(R.menu.tasks_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.addTask:

                final AlertDialog.Builder builder = new AlertDialog.Builder(ListOfCommentsActivity.this);
                LayoutInflater inflater = this.getLayoutInflater();


                final View alertLayout = inflater.inflate(R.layout.add_new_comments,null);
                builder.setView(alertLayout);
                spinner = alertLayout.findViewById(R.id.projectSpinner);
                subject_name = alertLayout.findViewById(R.id.subjectET);
                c_description = alertLayout.findViewById(R.id.descriptionET);
                adapter = ArrayAdapter.createFromResource(getBaseContext(),R.array.lists_of_projects,android.R.layout.simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                builder.setPositiveButton("Create a Comment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String c_p_name = spinner.getSelectedItem().toString();
                        final String c_s_name = subject_name.getText().toString();
                        final String c_des = c_description.getText().toString();

                        Log.e("p_name",c_p_name);
                        Log.e("s_name",c_s_name);
                        Log.e("c_des",c_des);
                    }
                });

                builder.setNegativeButton("Reset To Default", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Add New Comment");
                alertDialog.show();

        }

        if (item.getItemId()==android.R.id.home)
            finish();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    // Sending Data to CommentDetails Activity from the Comments Adapter
    @Override
    public void Data(int position, Comments comment) {

        Intent intent = new Intent(this,CommentDetailsActivity.class);
        intent.putExtra("name",comment.getProject());
        intent.putExtra("subject",comment.getSubject());
        intent.putExtra("created_by",comment.getCreatedBy());
        intent.putExtra("created_at",comment.getCreation_Date());
        startActivityForResult(intent,comments);
    }
}

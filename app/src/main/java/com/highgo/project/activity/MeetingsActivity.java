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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.highgo.project.R;
import com.highgo.project.adapter.MeetingAdapter;
import com.highgo.project.model.Meetings;

import java.util.ArrayList;
import java.util.List;

public class MeetingsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MeetingAdapter.MeetingsData {

    public static final int meetings = 01;

    String[] Project = {"P One","P Two","P Three","P Four","P Five","P Six","P Seven","P Eight","P Nine","P Ten"};
    String[] Date = {"12-08-2020", "10-09-2020", "19-09-2020", "10-08-2020", "25-08-2020",
            "14-08-2020", "03-09-2020", "16-09-2020", "28-08-2020", "15-08-2020"};
    String[] ReSubmission_Date = {"12-10-2020", "10-11-2020", "19-11-2020", "10-12-2020", "25-12-2020",
            "14-11-2020", "03-10-2020", "16-12-2020", "28-11-2020", "15-10-2020"};
    String[] Meeting_Description = {"Meeting Subject Description One ","Meeting Subject Description Two", "Meeting Subject Description Three ","Meeting Subject Description Four","Meeting Subject Description Five","Meeting Subject Description Six",
            "Meeting Subject Description Seven ","Meeting Subject Description Eight", "Meeting Subject Description Nine ","Meeting Subject Description Ten"};
    String[] Assigned = {"Team A", "Team B", "Team D", "Team C", "Team B",
            "Team A", "Team D", "Team B", "Team A", "Team C"};
    String[] Meeting_Progress_Data = {"100%","100%","100%","100%","100%","100%","100%","100%","100%","100%"};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MeetingAdapter adapter;
    List<Meetings> meetingsList = new ArrayList<>();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        recyclerView = findViewById(R.id.meetingRecyclerview);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MeetingAdapter(meetingsList,context);
        Meetings();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.addTask:
                Intent intent = new Intent(MeetingsActivity.this,AddNewMeetingActivity.class);
                startActivity(intent);
        }

        if (item.getItemId()==android.R.id.home)
            finish();
        return true;
    }

    public void Meetings(){
        int count = 0;
        for (String project : Project){
            Meetings meetings = new Meetings(project,Date[count],ReSubmission_Date[count],Meeting_Description[count],Assigned[count],Meeting_Progress_Data[count]);
            meetingsList.add(meetings);
            count++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tasks_menu,menu);

        getSupportActionBar().setTitle("Meetings");

        MenuItem menuItem = menu.findItem(R.id.searchView);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==meetings)
        {
            if (resultCode==RESULT_OK)
            {
                String name = data.getStringExtra("u_name");
                Log.e("name",name);
                String ass = data.getStringExtra("u_ass");
                Log.e("name",ass);
                String rem = data.getStringExtra("u_m_rem");
                Log.e("name",rem);
                String status = data.getStringExtra("u_m_status");
                Log.e("name",status);
                String desc = data.getStringExtra("u_m_desc");
                Log.e("name",desc);

            }
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();

        List<Meetings> meetings = new ArrayList<>();

        for (Meetings meetings1 : meetingsList){
            if (meetings1.getProject().toLowerCase().contains(userInput)){
                meetings.add(meetings1);
            } else if (meetings1.getMeeting_Description().toLowerCase().contains(userInput)){
                meetings.add(meetings1);
            } else if (meetings1.getDate().toLowerCase().contains(userInput)){
                meetings.add(meetings1);
            } else if (meetings1.getReSubmission_Date().toLowerCase().contains(userInput)){
                meetings.add(meetings1);
            } else if (meetings1.getAssigned().toLowerCase().contains(userInput)){
                meetings.add(meetings1);
            }
        }
        adapter.searchList(meetings);
        return true;
    }

    @Override
    public void MData(int position, Meetings meeting) {
        Intent intent = new Intent(MeetingsActivity.this,MeetingDetailsActivity.class);
        intent.putExtra("title_name",meeting.getProject());
        intent.putExtra("assigned",meeting.getAssigned());
        intent.putExtra("date",meeting.getDate());
        intent.putExtra("reSubmission",meeting.getReSubmission_Date());
        intent.putExtra("status",meeting.getMeeting_Progress_Data());
        intent.putExtra("description",meeting.getMeeting_Description());
        startActivityForResult(intent,meetings);
    }
}

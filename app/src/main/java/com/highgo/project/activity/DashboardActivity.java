package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.highgo.project.R;
import com.highgo.project.adapter.DashboardAdapter;
import com.highgo.project.model.Dashboard;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {


    String[] ProjectName = {"Projects","Tasks","Add Note","Projects on Hold","Completed Projects","All Projects"};

    String[] ProjectsCount = {"20 Projects","Add New Task","Add New Comment","15 Projects on Hold","40 Completed Projects","75 All Projects"};

    int[] ProjectPic = {R.drawable.pro,R.drawable.tasks,R.drawable.ic_comment_black_24dp,R.drawable.onhold,R.drawable.cmpltedpro,R.drawable.allpro};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DashboardAdapter dashboardAdapter;
    List<Dashboard> dashboards = new ArrayList<>();

    ProgressBar progressBarOne,progressBarTwo,progressBarThree,progressBarFour;
    TextView value1,value2,value3,value4;
    int i = 0;

    PieChart pieChart;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("Dashboard");


        pieChart = findViewById(R.id.pieChartPC);
        //toolbar = findViewById(R.id.toolBar);
        //setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        progressBarOne = findViewById(R.id.progressBarOnePB);
        progressBarTwo = findViewById(R.id.progressBarTwoPB);
        progressBarThree = findViewById(R.id.progressBarThreePB);
        progressBarFour = findViewById(R.id.progressBarFourPB);
        value1 = findViewById(R.id.value1);
        value2 = findViewById(R.id.value2);
        value3 = findViewById(R.id.value3);
        value4 = findViewById(R.id.value4);
        recyclerView = findViewById(R.id.dashboardRecyclerView);
        layoutManager = new GridLayoutManager(this,2);
        DashboardValues();
        dashboardAdapter = new DashboardAdapter(dashboards, context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dashboardAdapter);
        recyclerView.setHasFixedSize(true);


        // Drawer Layout Menus onClick Events
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.projectsItem:
                        Intent intent = new Intent(context, ListOfProjectsActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.projectTasksItem:
                        Intent intent3 = new Intent(context,ListsOfTasksActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.homeItem:
                        Intent intent1 = new Intent(context,DashboardActivity.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.settingsItem:
                        Intent intent2 = new Intent(context,SettingsActivity.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.meetingsItem:
                        Intent intent4 = new Intent(context,MeetingsActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.createACommentItem:
                        Intent intent5 = new Intent(context,ListOfCommentsActivity.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawers();
                        return true;
                }

                return false;
            }
        });

        // Drawer Header Layout Menus Onclick Events
        View headerView = navigationView.getHeaderView(0);

        ImageView imageView = (ImageView) headerView.findViewById(R.id.personPicCIV);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();

            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (1<100)
                {
                    value1.setText(20+"%");
                    progressBarOne.setProgress(20);
                    value2.setText(15+"%");
                    progressBarTwo.setProgress(15);
                    value3.setText(40+"%");
                    progressBarThree.setProgress(40);
                    value4.setText(75+"%");
                    progressBarFour.setProgress(75);
                    i++;
                    handler.postDelayed(this,200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        },200);

        ArrayList Data = new ArrayList();

        Data.add(new Entry(20,1));
        Data.add(new Entry(15,2));
        Data.add(new Entry(40,3));
        Data.add(new Entry(75,4));

        PieDataSet pieDataSet = new PieDataSet(Data,"All Projects Details");

        ArrayList Topics = new ArrayList();

        Topics.add("Projects");
        Topics.add("Projects on Hold");
        Topics.add("Completed Projects");
        Topics.add("All Projects");
        PieData pieData = new PieData(Topics,pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart.animateXY(5000,5000);

    }

    private void DashboardValues(){
        int count = 0;
        for (String name : ProjectName){
            Dashboard dashboard = new Dashboard(name, ProjectsCount[count], ProjectPic[count]);
            dashboards.add(dashboard);
            count++;
        }
    }
}

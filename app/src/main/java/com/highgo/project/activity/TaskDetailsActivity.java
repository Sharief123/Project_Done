package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.highgo.project.R;

public class TaskDetailsActivity extends AppCompatActivity {

    public static final int task_name_1 = 01;

    Button update,saveBN;
    TextView nameTV,tDescriptionTV,start_date,end_date,assigned_by,assigned_to;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        nameTV = findViewById(R.id.tnameTV);
        tDescriptionTV = findViewById(R.id.tDescriptionTV);
        start_date = findViewById(R.id.tStartDateTV);
        end_date = findViewById(R.id.tEndDateTV);
        assigned_by = findViewById(R.id.teamLeadTV);
        assigned_to = findViewById(R.id.sherryTV);

        // values Form Adapter
        final Intent intent = getIntent();
        final String task_name = intent.getStringExtra("text_one");
        final String task_description = intent.getStringExtra("text_two");
        String task_start_date = intent.getStringExtra("text_three");
        String task_end_date = intent.getStringExtra("text_four");
        String task_done_by = intent.getStringExtra("text_five");

        nameTV.setText(task_name);
        tDescriptionTV.setText(task_description);
        start_date.setText(task_start_date);
        end_date.setText(task_end_date);
        assigned_to.setText(task_done_by);

        //This TaskDetailsActivity values to UpdateTasksActivity
        final String t_name = nameTV.getText().toString();
        final String t_description = tDescriptionTV.getText().toString();
        final String t_start_Date = start_date.getText().toString();
        final String t_end_Date = end_date.getText().toString();
        final String t_assigned_by = assigned_by.getText().toString();
        final String t_assigned_to = assigned_to.getText().toString();


        getSupportActionBar().setTitle("Task Details");
        update = findViewById(R.id.updateBN);
        saveBN = findViewById(R.id.saveBN);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Values to UpdateTasksActivity
                Intent intent = new Intent(TaskDetailsActivity.this, UpdateTasksActivity.class);
                intent.putExtra("HI_ONE", t_name);
                intent.putExtra("HI_TWO", t_description);
                intent.putExtra("HI_THREE", t_start_Date);
                intent.putExtra("HI_FOUR", t_end_Date);
                intent.putExtra("HI_FIVE", t_assigned_by);
                intent.putExtra("HI_SIX", t_assigned_to);
                startActivityForResult(intent, task_name_1);
            }
        });

        saveBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String t_n = nameTV.getText().toString();
                final String t_d = tDescriptionTV.getText().toString();
                final String t_s = start_date.getText().toString();
                final String t_e = end_date.getText().toString();
                final String t_a = assigned_to.getText().toString();


                Intent result = new Intent();
                result.putExtra("t_one",t_n);
                result.putExtra("t_two",t_d);
                result.putExtra("t_three",t_s);
                result.putExtra("t_four",t_e);
                result.putExtra("t_five",t_a);
                setResult(RESULT_OK,result);
                finish();
            }
        });

    }

    //  By This Method we will return the strings which are updated data from the UpdatedTasksActivity
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (task_name_1==requestCode)
        {
            if (resultCode==RESULT_OK){

                String updated_task_name = data.getStringExtra("task_name");
                nameTV.setText(updated_task_name);

                String updated_task_description = data.getStringExtra("task_description");
                tDescriptionTV.setText(updated_task_description);

                String updated_task_start_date = data.getStringExtra("task_start_date");
                start_date.setText(updated_task_start_date);

                String updated_task_end_date = data.getStringExtra("task_end_date");
                end_date.setText(updated_task_end_date);

                String updated_task_assigned_to = data.getStringExtra("task_assigned_to");
                assigned_to.setText(updated_task_assigned_to);

                String updated_task_assigned_by = data.getStringExtra("task_assigned_by");
                assigned_by.setText(updated_task_assigned_by);

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
            finish();
        return true;
    }
}

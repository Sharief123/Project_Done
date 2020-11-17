package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.highgo.project.R;

import org.w3c.dom.Text;

public class MeetingDetailsActivity extends AppCompatActivity {

    public static final int meetings = 01;

    TextView assignedValuesTV,dateValuesTV,reSubmissionTV,statusValueTV,descriptionValueTV;
    Button to_edit,clear,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        // Declaring values
        assignedValuesTV = findViewById(R.id.assignedValuesTV);
        dateValuesTV = findViewById(R.id.dateValueTV);
        reSubmissionTV = findViewById(R.id.reSubmissiondDateValueTV);
        statusValueTV = findViewById(R.id.statusValueTV);
        descriptionValueTV = findViewById(R.id.descriptionValueTV);
        to_edit = findViewById(R.id.toEditBN);
        clear = findViewById(R.id.clearBN);
        save = findViewById(R.id.saveBN);

        // Getting Values Form the MeetingActivity
        final Intent intent = getIntent();
        final String a_value = intent.getStringExtra("status");
        final String d_value = intent.getStringExtra("date");
        final String resub_value = intent.getStringExtra("reSubmission");
        final String s_value = intent.getStringExtra("assigned");
        final String des_value = intent.getStringExtra("description");

        assignedValuesTV.setText(a_value);
        dateValuesTV.setText(d_value);
        reSubmissionTV.setText(resub_value);
        statusValueTV.setText(s_value);
        descriptionValueTV.setText(des_value);

        to_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = getSupportActionBar().getTitle().toString();
                final String rem_date = reSubmissionTV.getText().toString();
                final String status = statusValueTV.getText().toString();
                final String description = descriptionValueTV.getText().toString();


                Intent intent1 = new Intent(MeetingDetailsActivity.this,UpdateMeetingActivity.class);
                intent1.putExtra("m_name",name);
                intent1.putExtra("rem_date",rem_date);
                intent1.putExtra("status",status);
                intent1.putExtra("descr",description);
                startActivityForResult(intent1,meetings);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = getSupportActionBar().getTitle().toString();
                final String assigned = assignedValuesTV.getText().toString();
                final String u_m_rem_date = reSubmissionTV.getText().toString();
                final String u_m_status = statusValueTV.getText().toString();
                final String u_m_desc = descriptionValueTV.getText().toString();

                Intent intent1 = getIntent();
                intent1.putExtra("u_name",name);
                intent1.putExtra("u_ass",assigned);
                intent1.putExtra("u_m_rem",u_m_rem_date);
                intent1.putExtra("u_m_status",u_m_status);
                intent1.putExtra("u_m_desc",u_m_desc);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == meetings)
        {
            if (resultCode == RESULT_OK)
            {
                getSupportActionBar().setTitle(data.getStringExtra("m_p_name"));

                String rem_date = data.getStringExtra("m_rem");
                reSubmissionTV.setText(rem_date);

                String m_spin = data.getStringExtra("m_spin");
                statusValueTV.setText(m_spin);

                String m_desc = data.getStringExtra("m_desc");
                descriptionValueTV.setText(m_desc);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getSupportActionBar().setTitle("Meeting Details");
        getSupportActionBar().setTitle(getIntent().getStringExtra("title_name"));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        }
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return  true;
    }
}

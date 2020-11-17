package com.highgo.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.highgo.project.R;

public class UpdateCommentActivity extends AppCompatActivity {


    TextView name;
    EditText subject,description;
    Button comment_renew,back,reset_to_default;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_comment);
        name = findViewById(R.id.projectNameTV);
        subject = findViewById(R.id.commentSubjectET);
        description = findViewById(R.id.commentDescriptionET);
        comment_renew = findViewById(R.id.commentRenewBN);
        back = findViewById(R.id.BackBN);
        reset_to_default = findViewById(R.id.resetToDefaultBN);

        // Values Coming Form the CommentDetailsActivity
        final Intent intent = getIntent();
        final String c_name = intent.getStringExtra("c_name");
        final String c_sub = intent.getStringExtra("c_subj");
        final String c_des = intent.getStringExtra("c_des");

        name.setText(c_name);
        subject.setText(c_sub);
        description.setText(c_des);

        // Returning the updated values to Comment Details Activity
        comment_renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String c_name = name.getText().toString();
                final String c_sub = subject.getText().toString();
                final String c_des = description.getText().toString();

                Intent return_data_to_comment_details = new Intent();
                return_data_to_comment_details.putExtra("c_p_name",c_name);
                return_data_to_comment_details.putExtra("c_p_sub",c_sub);
                return_data_to_comment_details.putExtra("c_p_des",c_des);
                setResult(RESULT_OK,return_data_to_comment_details);

                Toast toast = Toast.makeText(getApplicationContext(),"Record Updated Successfully",Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundColor(Color.YELLOW);
                toast.show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("Edit Comment");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        }
        if (item.getItemId()==android.R.id.home)
            finish();
        return true;
    }
}

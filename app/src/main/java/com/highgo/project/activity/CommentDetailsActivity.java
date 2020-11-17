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

public class CommentDetailsActivity extends AppCompatActivity {

    public static final int comments = 01;

    TextView project_name,name,on_date,comments_subject,comment_description;
    Button edit_comment,delete_comment,saveBN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_details);

        // Declaring Values
        project_name = findViewById(R.id.projectNameTV);
        name = findViewById(R.id.nameTV);
        on_date = findViewById(R.id.onDateTV);
        comments_subject = findViewById(R.id.commentSubjectsTV);
        comment_description = findViewById(R.id.commentDescriptionTV);
        edit_comment = findViewById(R.id.editCommentBN);
        delete_comment = findViewById(R.id.deleteCommentsBN);


        // Values From the CommentsAdapter
        Intent intent = getIntent();
        final String n_name = intent.getStringExtra("name");
        final String subject = intent.getStringExtra("subject");
        final String created_by = intent.getStringExtra("created_by");
        final String created_at = intent.getStringExtra("created_at");

        project_name.setText(n_name);
        name.setText(created_by);
        on_date.setText(created_at);
        comments_subject.setText(subject);

        // Values Sending to UpdateCommentActivity class
        edit_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String c_name = project_name.getText().toString();
                final String c_des = comment_description.getText().toString();
                final String c_subj = comments_subject.getText().toString();

                Intent intent1 = new Intent(CommentDetailsActivity.this,UpdateCommentActivity.class);
                intent1.putExtra("c_name",c_name);
                intent1.putExtra("c_des",c_des);
                intent1.putExtra("c_subj",c_subj);
                startActivityForResult(intent1,comments);
            }
        });
    }

    // Values Coming back from UpdateCommentActivity.Class
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == comments)
        {
            if (resultCode==RESULT_OK)
            {
                String name = data.getStringExtra("c_p_name");
                project_name.setText(name);

                String subject = data.getStringExtra("c_p_sub");
                comments_subject.setText(subject);

                String description = data.getStringExtra("c_p_des");
                comment_description.setText(description);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("Comment Details");
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

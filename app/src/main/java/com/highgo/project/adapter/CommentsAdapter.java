package com.highgo.project.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.R;
import com.highgo.project.model.Comments;
import com.highgo.project.model.ListOfProjects;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    List<Comments> commentsList;
    private DataForward dataForward;

    public interface DataForward{
        void Data(int position, Comments comments);

    }
    public CommentsAdapter(List<Comments> commentsList, Context context) {
        this.commentsList = commentsList;
        this.dataForward = (DataForward) context;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout,parent,false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, final int position) {

        holder.name.setText(commentsList.get(position).getProject());
        holder.subject_name.setText(commentsList.get(position).getSubject());
        holder.created_name.setText(commentsList.get(position).getCreatedBy());
        holder.date.setText(commentsList.get(position).getCreation_Date());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataForward!=null){
                    dataForward.Data(position, commentsList.get(position));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView project,subject,created_by,creation_date;
        TextView name,subject_name,created_name,date;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            project = itemView.findViewById(R.id.projectTV);
            subject = itemView.findViewById(R.id.subjectTV);
            created_by = itemView.findViewById(R.id.createdByTV);
            creation_date = itemView.findViewById(R.id.creationDateTV);

            name = itemView.findViewById(R.id.projectNameTV);
            subject_name = itemView.findViewById(R.id.projectSubjectTV);
            created_name = itemView.findViewById(R.id.createdNameTV);
            date = itemView.findViewById(R.id.cdateTV);
        }
    }
}

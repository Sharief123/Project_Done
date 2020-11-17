package com.highgo.project.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.R;
import com.highgo.project.model.ListOfTasks;
import com.highgo.project.model.Tasks;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    List<Tasks> tasksList;

    public TasksAdapter(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);

        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TasksViewHolder holder, final int position) {
        holder.tSDTV.setText(tasksList.get(position).getSubjectDescription());
        holder.projectNameTV.setText(tasksList.get(position).getProjectName());
        holder.tStartDateTV.setText(tasksList.get(position).getStartDate());
        holder.tEndDateTV.setText(tasksList.get(position).getEndDate());
        holder.shariefTV.setText(tasksList.get(position).getNames());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (position==0){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==1){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==2){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==3){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==4){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==5){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==6){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==7){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==8){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                } else if (position==9){
                    holder.tProgressDataTV.setText(100+"%");
                    holder.tProgressBar.setProgress(100);
                }
                holder.tProgressBar.postDelayed(this,200);
            }
        },200);

    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }
    public class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView subjectTV,projectTV,startDateTV,endDateTV,doneByTV;
        ProgressBar tProgressBar;
        TextView tSDTV,projectNameTV,tStartDateTV,tEndDateTV,shariefTV,tProgressDataTV;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectTV = itemView.findViewById(R.id.subjectTV);
            projectTV = itemView.findViewById(R.id.projectTV);
            startDateTV = itemView.findViewById(R.id.startDateTV);
            endDateTV = itemView.findViewById(R.id.endDateTV);
            doneByTV = itemView.findViewById(R.id.doneByTV);
            tProgressBar = itemView.findViewById(R.id.tProgressBar);


            tSDTV = itemView.findViewById(R.id.tSDTV);
            projectNameTV = itemView.findViewById(R.id.projectNameTV);
            tStartDateTV = itemView.findViewById(R.id.tStartDateTV);
            tEndDateTV = itemView.findViewById(R.id.tEndDateTV);
            shariefTV = itemView.findViewById(R.id.shariefTV);
            tProgressDataTV = itemView.findViewById(R.id.tProgressDataTV);
        }
    }

    public void sreachlist(List<Tasks> newList){
        tasksList = new ArrayList<>();
        tasksList.addAll(newList);
        notifyDataSetChanged();
    }
}

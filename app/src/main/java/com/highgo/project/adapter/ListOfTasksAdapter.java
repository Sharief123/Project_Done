package com.highgo.project.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.R;
import com.highgo.project.activity.LoginActivity;
import com.highgo.project.activity.ProjectDetailsActivity;
import com.highgo.project.activity.TaskDetailsActivity;
import com.highgo.project.activity.UpdateTasksActivity;
import com.highgo.project.model.ListOfTasks;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ListOfTasksAdapter extends RecyclerView.Adapter<ListOfTasksAdapter.ListOfTasksViewHolder> {

    public ListOfTasksAdapter(List<ListOfTasks> tasksList, Context context) {
        this.tasksList = tasksList;
        this.context = context;
        callBackData = (CallBackData) context;
    }

    List<ListOfTasks> tasksList;
    Context context;

   private CallBackData callBackData;

    public void updateList(List<ListOfTasks> newList) {
        tasksList = new ArrayList<>();
        tasksList.addAll(newList);
        notifyDataSetChanged();
    }

    public interface CallBackData {
        void onHandleSelection(int position, ListOfTasks listOfTasks);
    }


    int i = 0;

    @NonNull
    @Override
    public ListOfTasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_tasks, parent, false);

        return new ListOfTasksViewHolder(view, context, tasksList);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListOfTasksViewHolder holder, final int position) {

        holder.tname.setText(tasksList.get(position).getTaskName());
        holder.tdescription.setText(tasksList.get(position).getTaskDescription());
        holder.tstartdate.setText(tasksList.get(position).getTaskStartDate());
        holder.tenddate.setText(tasksList.get(position).getTaskEndDate());
        holder.edone.setText(tasksList.get(position).getDoneBy());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBackData!=null){
                    callBackData.onHandleSelection(position, tasksList.get(position));
                }
            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (position == 0) {
                    holder.tprogressdata.setText(30 + "%");
                    holder.progressBar.setProgress(30);
                } else if (position == 1) {
                    holder.tprogressdata.setText(28 + "%");
                    holder.progressBar.setProgress(28);
                } else if (position == 2) {
                    holder.tprogressdata.setText(48 + "%");
                    holder.progressBar.setProgress(48);
                } else if (position == 3) {
                    holder.tprogressdata.setText(18 + "%");
                    holder.progressBar.setProgress(18);
                } else if (position == 4) {
                    holder.tprogressdata.setText(78 + "%");
                    holder.progressBar.setProgress(78);
                } else if (position == 5) {
                    holder.tprogressdata.setText(40 + "%");
                    holder.progressBar.setProgress(40);
                } else if (position == 6) {
                    holder.tprogressdata.setText(58 + "%");
                    holder.progressBar.setProgress(58);
                } else if (position == 7) {
                    holder.tprogressdata.setText(80 + "%");
                    holder.progressBar.setProgress(80);
                } else if (position == 8) {
                    holder.tprogressdata.setText(8 + "%");
                    holder.progressBar.setProgress(8);
                } else if (position == 9) {
                    holder.tprogressdata.setText(44 + "%");
                    holder.progressBar.setProgress(44);
                }
                i++;
                holder.progressBar.postDelayed(this, 200);
            }
        }, 200);
    }


    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ListOfTasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, description, startDate, endDate, done;
        TextView tname, tdescription, tstartdate, tenddate, edone, tprogressdata;
        ProgressBar progressBar;
        Context context;
        List<ListOfTasks> tasksList;
        CardView cardView;

        public ListOfTasksViewHolder(@NonNull View itemView, Context context, List<ListOfTasks> tasksList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.context = context;
            this.tasksList = tasksList;
            name = itemView.findViewById(R.id.taskNameTV);
            description = itemView.findViewById(R.id.taskDescriptionTV);
            startDate = itemView.findViewById(R.id.startDateTV);
            endDate = itemView.findViewById(R.id.endDateTV);
            done = itemView.findViewById(R.id.doneByTV);
            cardView = itemView.findViewById(R.id.cardView);

            tname = itemView.findViewById(R.id.nametaskTV);
            tdescription = itemView.findViewById(R.id.descriptionTV);
            tstartdate = itemView.findViewById(R.id.taskStartDateTV);
            tenddate = itemView.findViewById(R.id.taskEndDateTV);
            edone = itemView.findViewById(R.id.shariefTV);
            tprogressdata = itemView.findViewById(R.id.taskProgressDataTV);
            progressBar = itemView.findViewById(R.id.taskProgressBar);
        }

        @Override
        public void onClick(View v) {

        }
    }
    }
package com.highgo.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.activity.ListOfCommentsActivity;
import com.highgo.project.activity.ListOfProjectsActivity;
import com.highgo.project.R;
import com.highgo.project.activity.ListsOfTasksActivity;
import com.highgo.project.model.Dashboard;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    List<Dashboard> dashboardList;
    Context context;

    public DashboardAdapter(List<Dashboard> dashboardList, Context context) {
        this.dashboardList = dashboardList;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_layout,parent,false);

        return new DashboardViewHolder(view, context, dashboardList);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {

        holder.projectPic.setImageResource(dashboardList.get(position).getProjectPic());
        holder.projectName.setText(dashboardList.get(position).getProjectName());
        holder.projectCount.setText(dashboardList.get(position).getProjectsCount());
    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }


    public class DashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView projectPic;
        TextView projectName,projectCount;
        Context context;
        List<Dashboard> dashboardList;

        public DashboardViewHolder(@NonNull View itemView, Context context,List<Dashboard> dashboardList) {
            super(itemView);
            projectPic = itemView.findViewById(R.id.projectPicIV);
            projectName = itemView.findViewById(R.id.projectNameTV);
            projectCount = itemView.findViewById(R.id.noOfProjectsTV);
            itemView.setOnClickListener(this);
            this.context = context;
            this.dashboardList = dashboardList;
        }

        @Override
        public void onClick(View v) {
            if (getLayoutPosition()==0){
                Intent intent = new Intent(context, ListOfProjectsActivity.class);
                context.startActivity(intent);
            } else if (getLayoutPosition()==1){
                Intent intent = new Intent(context, ListsOfTasksActivity.class);
                context.startActivity(intent);
            } else if (getLayoutPosition()==2){
                Intent intent = new Intent(context, ListOfCommentsActivity.class);
                context.startActivity(intent);
            } else if (getLayoutPosition()==3){
                Intent intent = new Intent(context,ListOfProjectsActivity.class);
                context.startActivity(intent);
            }
        }
    }
}

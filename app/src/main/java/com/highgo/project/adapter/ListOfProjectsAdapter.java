package com.highgo.project.adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.model.ListOfProjects;
import com.highgo.project.activity.ProjectDetailsActivity;
import com.highgo.project.R;

import java.util.ArrayList;
import java.util.List;

public class ListOfProjectsAdapter extends RecyclerView.Adapter<ListOfProjectsAdapter.ListOfProjectsViewHolder> {


    public interface DataOfProjects{
        void onProjects(int position, ListOfProjects projects);

    }

    private DataOfProjects dataOfProjects;

    List<ListOfProjects> listOfProjectsList;
    Context context;

    int i = 0;

    public ListOfProjectsAdapter(List<ListOfProjects> listOfProjectsList, Context context) {
        this.listOfProjectsList = listOfProjectsList;
        this.context = context;
        dataOfProjects = (DataOfProjects) context;
    }

    @NonNull
    @Override
    public ListOfProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_layout,parent,false);

        return new ListOfProjectsViewHolder(view, context, listOfProjectsList);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListOfProjectsViewHolder holder, final int position) {

        holder.nameProjectTV.setText(listOfProjectsList.get(position).getNameProject());
        holder.projectStartDateTV.setText(listOfProjectsList.get(position).getProjectStartDate());
        holder.projectEndDateTV.setText(listOfProjectsList.get(position).getProjectEndDate());
        holder.createdTV.setText(listOfProjectsList.get(position).getCreatedBy());
        holder.descriptionTV.setText(listOfProjectsList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataOfProjects!=null){
                    dataOfProjects.onProjects(position,listOfProjectsList.get(position));
                    notifyDataSetChanged();
                }
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i<100){
                    if (position==0) {
                        holder.projectProgressDataTV.setText(20+"%");
                        holder.progressBar.setProgress(20);
                    } else if (position==1) {
                        holder.projectProgressDataTV.setText(45+"%");
                        holder.progressBar.setProgress(45);
                    } else if (position==2) {
                        holder.projectProgressDataTV.setText(15+"%");
                        holder.progressBar.setProgress(15);
                    } else if (position==3) {
                        holder.projectProgressDataTV.setText(28+"%");
                        holder.progressBar.setProgress(28);
                    } else if (position==4) {
                        holder.projectProgressDataTV.setText(37+"%");
                        holder.progressBar.setProgress(37);
                    } else if (position==5){
                        holder.projectProgressDataTV.setText(47+"%");
                        holder.progressBar.setProgress(47);
                    } else if (position==6){
                        holder.projectProgressDataTV.setText(79+"%");
                        holder.progressBar.setProgress(79);
                    } else if (position==7){
                        holder.projectProgressDataTV.setText(67+"%");
                        holder.progressBar.setProgress(67);
                    } else if (position==8){
                        holder.projectProgressDataTV.setText(48+"%");
                        holder.progressBar.setProgress(48);
                    } else if (position==9){
                        holder.projectProgressDataTV.setText(18+"%");
                        holder.progressBar.setProgress(18);
                    }
                    i++;
                    holder.progressBar.postDelayed(this,200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        },200);

    }

    @Override
    public int getItemCount() {
        return listOfProjectsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ListOfProjectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        List<ListOfProjects> listOfProjectsList;
        ProgressBar progressBar;
        TextView nameTV,startDateTV,endDateTV,createdByTV;
        TextView nameProjectTV,projectStartDateTV,projectEndDateTV,createdTV,projectProgressDataTV;
        TextView projectDescriptionTV,descriptionTV;

        public ListOfProjectsViewHolder(@NonNull View itemView,Context context, List<ListOfProjects> listOfProjectsList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.context = context;
            this.listOfProjectsList = listOfProjectsList;
            nameTV = itemView.findViewById(R.id.NameTV);
            startDateTV = itemView.findViewById(R.id.startDateTV);
            endDateTV = itemView.findViewById(R.id.endDateTV);
            createdByTV = itemView.findViewById(R.id.createdByTV);
            projectDescriptionTV = itemView.findViewById(R.id.projectDescriptionTV);

            progressBar = itemView.findViewById(R.id.projectProgressBar);
            nameProjectTV = itemView.findViewById(R.id.nameProjectTV);
            projectStartDateTV = itemView.findViewById(R.id.projecttStartDateTV);
            projectEndDateTV = itemView.findViewById(R.id.projectEndDateTV);
            createdTV = itemView.findViewById(R.id.createdTV);
            projectProgressDataTV = itemView.findViewById(R.id.projectProgressDataTV);
            descriptionTV = itemView.findViewById(R.id.descriptionTV);
        }

        @Override
        public void onClick(View v) {

        }

    }

    public void updateList(List<ListOfProjects> newList)
    {
        listOfProjectsList = new ArrayList<>();
        listOfProjectsList.addAll(newList);
        notifyDataSetChanged();
    }
}

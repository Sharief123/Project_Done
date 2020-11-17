package com.highgo.project.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.R;
import com.highgo.project.model.Meetings;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingsViewHolder> {

    List<Meetings> meetings;
    private MeetingsData meetingsData;

    public interface MeetingsData{
        void MData(int position,Meetings meeting);
    }
    public MeetingAdapter(List<Meetings> meetings, Context context) {
        this.meetings = meetings;
        this.meetingsData = (MeetingsData) context;
    }

    int i = 0;
    @NonNull
    @Override
    public MeetingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meetings_layout,parent,false);

        return new MeetingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MeetingsViewHolder holder, final int position) {

        holder.mDescription.setText(meetings.get(position).getMeeting_Description());
        holder.mProject.setText(meetings.get(position).getProject());
        holder.mResubmissionDate.setText(meetings.get(position).getReSubmission_Date());
        holder.mDate.setText(meetings.get(position).getDate());
        holder.admins.setText(meetings.get(position).getMeeting_Progress_Data());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (meetingsData!=null){
                    meetingsData.MData(position,meetings.get(position));
                }
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (position==0){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==1){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==2){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==3){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==4){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==5){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==6){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==7){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==8){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                } else if (position==9){
                    holder.progressData.setText(100+"%");
                    holder.progressBar.setProgress(100);
                }
                holder.progressBar.postDelayed(this,200);
            }
        },200);
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class MeetingsViewHolder extends RecyclerView.ViewHolder {

        TextView meetingsDescription,project,resubmissionDate,meetingDate,assigned;
        TextView progressData,mDescription,mProject,mResubmissionDate,mDate,admins;
        ProgressBar progressBar;

        public MeetingsViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            meetingsDescription = itemView.findViewById(R.id.meetingDescriptionTV);
            project = itemView.findViewById(R.id.projectTV);
            resubmissionDate = itemView.findViewById(R.id.reSubmissionDateTV);
            meetingDate = itemView.findViewById(R.id.meetingDateTV);
            assigned = itemView.findViewById(R.id.assignedTV);

            progressData = itemView.findViewById(R.id.progressDataTV);
            mDescription = itemView.findViewById(R.id.mDescriptionTV);
            mProject = itemView.findViewById(R.id.mProjectTV);
            mResubmissionDate = itemView.findViewById(R.id.mReSubmissionDateTV);
            mDate = itemView.findViewById(R.id.mDateTV);
            admins = itemView.findViewById(R.id.adminsTV);
        }
    }

    public void searchList(List<Meetings> newsList){
        meetings = new ArrayList<>();
        meetings.addAll(newsList);
        notifyDataSetChanged();
    }
}

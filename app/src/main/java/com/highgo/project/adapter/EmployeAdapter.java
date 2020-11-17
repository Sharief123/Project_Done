package com.highgo.project.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highgo.project.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeAdapter extends RecyclerView.Adapter<EmployeAdapter.EmployeeViewHolder> {

    List<String> stringList;
    Context context;
    private Object Context;

    private onOptionSelected onOptionSelected;


    List<String> SelectedStrings = new ArrayList<>();

    public interface onOptionSelected{
        void onOptionSelected(String option, int type);

    }

    public EmployeAdapter(List<String> stringList, Context context, onOptionSelected onOptionSelected) {
        this.stringList = stringList;
        this.context = context;
        this.onOptionSelected = onOptionSelected;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists_of_employees,parent,false);

        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {

        holder.employee.setText(stringList.get(position));
    }


    @Override
    public int getItemCount() {
        return stringList.size();
    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckBox checkBox;
        TextView employee;
        View container;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView;
            container.setOnClickListener(this);
            container.setClickable(true);
            checkBox = itemView.findViewById(R.id.checkboxCB);
            employee = itemView.findViewById(R.id.eNameTV);
            checkBox.setFocusable(false);
            checkBox.setFocusableInTouchMode(false);
        }

        @Override
        public void onClick(View v) {
            if (onOptionSelected != null) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    onOptionSelected.onOptionSelected(stringList.get(getAdapterPosition()), 1);
                    employee.setTypeface(null, Typeface.NORMAL);
                    SelectedStrings.remove(stringList.get(getAdapterPosition()));

                } else {
                    checkBox.setChecked(true);
                    onOptionSelected.onOptionSelected(stringList.get(getAdapterPosition()), 2);
                    employee.setTypeface(null, Typeface.BOLD);
                    SelectedStrings.add(stringList.get(getAdapterPosition()));
                }
            }
            notifyDataSetChanged();
        }
    }
}

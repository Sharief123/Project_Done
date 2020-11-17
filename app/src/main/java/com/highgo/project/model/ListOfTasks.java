package com.highgo.project.model;

public class ListOfTasks {
    String TaskName;
    String TaskStartDate;
    String TaskEndDate;
    String DoneBy;
    String TaskDescription;

    public String getTaskName() {
        return TaskName;
    }

    public String getTaskStartDate() {
        return TaskStartDate;
    }

    public String getTaskEndDate() {
        return TaskEndDate;
    }

    public String getDoneBy() {
        return DoneBy;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public ListOfTasks(String taskName, String taskStartDate, String taskEndDate, String doneBy, String taskDescription) {
        TaskName = taskName;
        TaskStartDate = taskStartDate;
        TaskEndDate = taskEndDate;
        DoneBy = doneBy;
        TaskDescription = taskDescription;
    }
}

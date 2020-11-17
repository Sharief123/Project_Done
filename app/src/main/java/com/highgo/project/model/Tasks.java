package com.highgo.project.model;

public class Tasks {

    String ProjectName;
    String SubjectDescription;
    String StartDate;

    public String getProjectName() {
        return ProjectName;
    }

    public String getSubjectDescription() {
        return SubjectDescription;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getNames() {
        return Names;
    }

    public Tasks(String projectName, String subjectDescription, String startDate, String endDate, String names) {
        ProjectName = projectName;
        SubjectDescription = subjectDescription;
        StartDate = startDate;
        EndDate = endDate;
        Names = names;
    }

    String EndDate;
    String Names;

}

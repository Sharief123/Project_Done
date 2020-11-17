package com.highgo.project.model;

public class Meetings {
    String Project;
    String Date;
    String ReSubmission_Date;
    String Meeting_Description;
    String Meeting_Progress_Data;

    public String getMeeting_Progress_Data() {
        return Meeting_Progress_Data;
    }

    public String getProject() {
        return Project;
    }

    public String getDate() {
        return Date;
    }

    public String getReSubmission_Date() {
        return ReSubmission_Date;
    }

    public String getMeeting_Description() {
        return Meeting_Description;
    }

    public String getAssigned() {
        return Assigned;
    }

    public Meetings(String project, String date, String reSubmission_Date, String meeting_Description, String meeting_Progress_Data, String assigned) {
        Project = project;
        Date = date;
        ReSubmission_Date = reSubmission_Date;
        Meeting_Description = meeting_Description;
        Meeting_Progress_Data = meeting_Progress_Data;
        Assigned = assigned;
    }

    String Assigned;
}

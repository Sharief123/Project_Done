package com.highgo.project.model;

public class Comments {

    String Project;
    String Subject;
    String CreatedBy;
    String Creation_Date;

    public String getProject() {
        return Project;
    }

    public String getSubject() {
        return Subject;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public String getCreation_Date() {
        return Creation_Date;
    }

    public Comments(String project, String subject, String createdBy, String creation_Date) {
        Project = project;
        Subject = subject;
        CreatedBy = createdBy;
        Creation_Date = creation_Date;
    }
}

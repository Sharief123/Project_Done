package com.highgo.project.model;

public class ListOfProjects {

    String NameProject;
    String ProjectStartDate;
    String ProjectEndDate;
    String CreatedBy;
    String Description;

    public String getDescription() {
        return Description;
    }

    public String getNameProject() {
        return NameProject;
    }

    public String getProjectStartDate() {
        return ProjectStartDate;
    }

    public String getProjectEndDate() {
        return ProjectEndDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public ListOfProjects(String nameProject, String projectStartDate, String projectEndDate, String createdBy, String description) {
        NameProject = nameProject;
        ProjectStartDate = projectStartDate;
        ProjectEndDate = projectEndDate;
        CreatedBy = createdBy;
        Description = description;
    }
}

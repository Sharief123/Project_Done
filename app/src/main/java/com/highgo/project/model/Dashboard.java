package com.highgo.project.model;

public class Dashboard {
    String ProjectName,ProjectsCount;
    int ProjectPic;

    public String getProjectName() {
        return ProjectName;
    }

    public String getProjectsCount() {
        return ProjectsCount;
    }

    public int getProjectPic() {
        return ProjectPic;
    }

    public Dashboard(String projectName, String projectsCount, int projectPic) {
        ProjectName = projectName;
        ProjectsCount = projectsCount;
        ProjectPic = projectPic;
    }
}

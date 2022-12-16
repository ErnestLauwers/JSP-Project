package domain.service;

import domain.model.Project;

import java.util.ArrayList;

public interface ProjectService {

    public ArrayList<Project> getAllProjects();

    public void addProject(Project project);

    public void deleteProject(int projectid);

    public Project getProject(int projectid);

    public void update(Project project);

    public ArrayList<Project> sortAllProjectsDescending();

    public ArrayList<Project> sortAllProjectsAscending();

}
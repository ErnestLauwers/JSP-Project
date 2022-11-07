package domain.model;

import java.time.LocalDate;

public class Project {

    private int projectId = 0;
    private String name;
    private Team team;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String name, Team team, LocalDate startDate, LocalDate endDate) {
        this.setProjectId(projectId++);
        this.setName(name);
        this.setTeam(team);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Project(int projectId, String name, Team team, LocalDate startDate, LocalDate endDate) {
        this.setProjectId(projectId);
        this.setName(name);
        this.setTeam(team);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Project(int projectId, String name, Team team, LocalDate startDate) {
        this.setProjectId(projectId);
        this.setName(name);
        this.setTeam(team);
        this.setStartDate(startDate);
        this.endDate = null;
    }

    public Project() {

    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name is obligated!");
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Startdate can't be in the past!");
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public String getName() {
        return this.name;
    }

    public Team getTeam() {
        return this.team;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

}
package domain.service;

import domain.model.Project;
import domain.model.Team;
import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ProjectServiceDB implements ProjectService {

    private Connection connection;
    private String schema;

    public ProjectServiceDB() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    @Override
    public void addProject(Project project) {
        String query = String.format("insert into %s.projects (name, team, startdate, enddate) values (?, ?, ?, ?)", schema);
        try {
            String startdate = project.getStartDate().toString();
            String startdate2 = startdate + " 00:00:00";
            Timestamp startdateTimestamp = Timestamp.valueOf(startdate2);


            String team = project.getTeam().toString();
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, team);
            preparedStatement.setTimestamp(3, startdateTimestamp);
            LocalDate enddate = project.getEndDate();
            if (enddate != null) {
                String enddate2 = enddate + " 00:00:00";
                Timestamp enddateTimestamp = Timestamp.valueOf(enddate2);
                preparedStatement.setTimestamp(4, enddateTimestamp);
            } else {
                preparedStatement.setTimestamp(4, null);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Project project) {
        String query = String.format("update %s.projects set name = (?), startdate = (?), enddate = (?) where projectid = (?)", schema);
        try {
            String startdate = project.getStartDate().toString();
            String startdate2 = startdate + " 00:00:00";
            Timestamp startdateTimestamp = Timestamp.valueOf(startdate2);

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setTimestamp(2, startdateTimestamp);

            LocalDate enddate = project.getEndDate();
            if (enddate != null) {
                String enddate2 = enddate + " 00:00:00";
                Timestamp enddateTimestamp = Timestamp.valueOf(enddate2);
                preparedStatement.setTimestamp(3, enddateTimestamp);
            } else {
                preparedStatement.setTimestamp(3, null);
            }
            preparedStatement.setInt(4, project.getProjectId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Project getProject(int projectid) {
        String query = String.format("SELECT * from %s.projects where projectid = (?)", schema);
        Project project = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, projectid);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int projectId = result.getInt("projectid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                LocalDate startdate = result.getTimestamp("startdate").toLocalDateTime().toLocalDate();
                if (result.getTimestamp("enddate") == null) {
                    project = new Project(projectId, name, team, startdate);
                } else {
                    LocalDate enddate = result.getTimestamp("enddate").toLocalDateTime().toLocalDate();
                    project = new Project(projectId, name, team, startdate, enddate);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return project;
    }

    @Override
    public void deleteProject(int projectid) {
        String query = String.format("delete from %s.projects where projectid = (?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, projectid);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        String query = String.format("SELECT * from %s.projects", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int projectId = result.getInt("projectid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                LocalDate startdate = result.getTimestamp("startdate").toLocalDateTime().toLocalDate();
                if (result.getTimestamp("enddate") == null) {
                    Project project = new Project(projectId, name, team, startdate);
                    projects.add(project);
                } else {
                    LocalDate enddate = result.getTimestamp("enddate").toLocalDateTime().toLocalDate();
                    Project project = new Project(projectId, name, team, startdate, enddate);
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return projects;
    }

    @Override
    public ArrayList<Project> sortAllProjectsDescending() {
        ArrayList<Project> sortedProjects = new ArrayList<>();
        String query = String.format("select * from %s.projects order by startdate desc, enddate desc", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int projectId = result.getInt("projectid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp startDate = result.getTimestamp("startdate");
                Timestamp endDate = result.getTimestamp("enddate");

                String dateCorStart = startDate.toString();
                String years = dateCorStart.substring(0, 4);
                String months = dateCorStart.substring(5, 7);
                String days = dateCorStart.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);
                if (endDate != null) {
                    String dateCorEnd = endDate.toString();
                    String years2 = dateCorEnd.substring(0, 4);
                    String months2 = dateCorEnd.substring(5, 7);
                    String days2 = dateCorEnd.substring(8, 10);
                    String date5 = days2 + "/" + months2 + "/" + years2;
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date6 = LocalDate.parse(date5, formatter2);
                    Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                    Project project = new Project(projectId, name, team, date4, date6);
                    sortedProjects.add(project);
                } else {
                    Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                    Project project = new Project(projectId, name, team, date4);
                    sortedProjects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return sortedProjects;
    }

    @Override
    public ArrayList<Project> sortAllProjectsAscending() {
        ArrayList<Project> sortedProjects = new ArrayList<>();
        String query = String.format("select * from %s.projects order by startdate asc, enddate asc", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int projectId = result.getInt("projectid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp startDate = result.getTimestamp("startdate");
                Timestamp endDate = result.getTimestamp("enddate");

                String dateCorStart = startDate.toString();
                String years = dateCorStart.substring(0, 4);
                String months = dateCorStart.substring(5, 7);
                String days = dateCorStart.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);

                if (endDate != null) {
                    String dateCorEnd = endDate.toString();
                    String years2 = dateCorEnd.substring(0, 4);
                    String months2 = dateCorEnd.substring(5, 7);
                    String days2 = dateCorEnd.substring(8, 10);
                    String date5 = days2 + "/" + months2 + "/" + years2;
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date6 = LocalDate.parse(date5, formatter2);
                    Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                    Project project = new Project(projectId, name, team, date4, date6);
                    sortedProjects.add(project);
                } else {
                    Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                    Project project = new Project(projectId, name, team, date4);
                    sortedProjects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return sortedProjects;
    }

    private void checkConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection has been closed");
                this.reConnect();
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    private void reConnect() {
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.reconnect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
    }

    /**
     * Check the connection and reconnect when necessery
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        checkConnection();
        return this.connection;
    }
}
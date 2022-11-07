package domain.service;

import domain.model.Team;
import domain.model.WorkOrder;
import util.DbConnectionService;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class WorkOrderServiceDB implements WorkOrderService{

    private Connection connection;
    private String schema;

    public WorkOrderServiceDB() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    @Override
    public WorkOrder getWorkOrder(int workOrderId) {
        String query = String.format("SELECT * from %s.workorders WHERE workorderid = (?)", schema);

        WorkOrder workOrder = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, workOrderId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int workorderId = result.getInt("workorderid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp date = result.getTimestamp("date");
                Time starttime = result.getTime("starttime");
                Time endtime = result.getTime("endtime");
                String description = result.getString("description");
                int userid = result.getInt("userid");

                String dateCor = date.toString();
                String years = dateCor.substring(0, 4);
                String months = dateCor.substring(5, 7);
                String days = dateCor.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);

                String startTimeCorrect = starttime.toString();
                String endTimeCorrect = endtime.toString();
                String startTimeCorrect2 = startTimeCorrect.substring(0, 5);
                String endTimeCorrect2 = endTimeCorrect.substring(0, 5);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime starttime2 = LocalTime.parse(startTimeCorrect2, formatter2);
                LocalTime endtime2 = LocalTime.parse(endTimeCorrect2, formatter2);

                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));

                workOrder = new WorkOrder(workorderId, name, team, date4, starttime2, endtime2, description, userid);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return workOrder;
    }

    @Override
    public ArrayList<WorkOrder> getWorkOrdersWithId(int userId) {
        ArrayList<WorkOrder> workOrders = new ArrayList<>();
        String query = String.format("SELECT * from %s.workorders WHERE userid = (?)", schema);

        WorkOrder workOrder = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int workorderId = result.getInt("workorderid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp date = result.getTimestamp("date");
                Time starttime = result.getTime("starttime");
                Time endtime = result.getTime("endtime");
                String description = result.getString("description");
                int userid = result.getInt("userid");

                String dateCor = date.toString();
                String years = dateCor.substring(0, 4);
                String months = dateCor.substring(5, 7);
                String days = dateCor.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);

                String startTimeCorrect = starttime.toString();
                String endTimeCorrect = endtime.toString();
                String startTimeCorrect2 = startTimeCorrect.substring(0, 5);
                String endTimeCorrect2 = endTimeCorrect.substring(0, 5);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime starttime2 = LocalTime.parse(startTimeCorrect2, formatter2);
                LocalTime endtime2 = LocalTime.parse(endTimeCorrect2, formatter2);

                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));

                workOrder = new WorkOrder(workorderId, name, team, date4, starttime2, endtime2, description, userid);
                workOrders.add(workOrder);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return workOrders;
    }

    @Override
    public int getNumberOfWorkOrders() {
        String query = String.format("SELECT count(userid) as result from %s.users", schema);
        int numberOfUsers = 0;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            numberOfUsers = result.getInt("result");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return numberOfUsers;
    }

    @Override
    public void update(WorkOrder workOrder) {
        String query = String.format("update %s.workorders set starttime = (?), endtime = (?), description = (?) WHERE workorderid = (?)", schema);
        try {
            String startTime = workOrder.getStartTime().toString();
            startTime = startTime + ":00";
            String endTime = workOrder.getEndTime().toString();
            endTime = endTime + ":00";
            Time startTimeCorrect = Time.valueOf(startTime);
            Time endTimeCorrect = Time.valueOf(endTime);

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setTime(1, startTimeCorrect);
            preparedStatement.setTime(2, endTimeCorrect);
            preparedStatement.setString(3,  workOrder.getDescription());
            preparedStatement.setInt(4, workOrder.getWorkOrderId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteWorkOrder(int workOrderId) {
        String query = String.format("delete from %s.workorders where workorderid = (?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, workOrderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void addWorkOrder(WorkOrder workOrder) {
        String query = String.format("insert into %s.workorders (name, team, date, starttime, endtime, description, userid) values (?, ?, ?, ?, ?, ?, ?)", schema);
        try {
            String teamName = workOrder.getTeam().getStringValue().substring(0,1).toUpperCase(Locale.ROOT) + workOrder.getTeam().getStringValue().substring(1).toLowerCase(Locale.ROOT);
            String date = workOrder.getDate().toString();
            String date4 = date + " 00:00:00";
            String startTime = workOrder.getStartTime().toString();
            startTime = startTime + ":00";
            String endTime = workOrder.getEndTime().toString();
            endTime = endTime + ":00";
            Timestamp timestampDate = Timestamp.valueOf(date4);
            Time startTimeCorrect = Time.valueOf(startTime);
            Time endTimeCorrect = Time.valueOf(endTime);
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, workOrder.getName());
            preparedStatement.setString(2, teamName);
            preparedStatement.setTimestamp(3, timestampDate);
            preparedStatement.setTime(4, startTimeCorrect);
            preparedStatement.setTime(5, endTimeCorrect);
            preparedStatement.setString(6, workOrder.getDescription());
            preparedStatement.setInt(7, workOrder.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<WorkOrder> getAllWorkOrders() {
        ArrayList<WorkOrder> workOrders = new ArrayList<>();
        String query = String.format("SELECT * from %s.workorders", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int workorderId = result.getInt("workorderid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp date = result.getTimestamp("date");
                Time starttime = result.getTime("starttime");
                Time endtime = result.getTime("endtime");
                String description = result.getString("description");
                int userid = result.getInt("userid");

                String dateCor = date.toString();
                String years = dateCor.substring(0, 4);
                String months = dateCor.substring(5, 7);
                String days = dateCor.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);
                String startTimeCorrect = starttime.toString();
                String endTimeCorrect = endtime.toString();
                String startTimeCorrect2 = startTimeCorrect.substring(0, 5);
                String endTimeCorrect2 = endTimeCorrect.substring(0, 5);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");
                LocalTime starttime2 = LocalTime.parse(startTimeCorrect2, formatter2);
                LocalTime endtime2 = LocalTime.parse(endTimeCorrect2, formatter2);
                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));

                WorkOrder workOrder = new WorkOrder(workorderId, name, team, date4, starttime2, endtime2, description, userid);
                workOrders.add(workOrder);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return workOrders;
    }

    @Override
    public ArrayList<WorkOrder> sortAllWorkOrdersDescending() {
        ArrayList<WorkOrder> sortedWorkOrders = new ArrayList<>();
        String query = String.format("select * from %s.workorders order by date desc, starttime desc", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int workorderId = result.getInt("workorderid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp date = result.getTimestamp("date");
                Time starttime = result.getTime("starttime");
                Time endtime = result.getTime("endtime");
                String description = result.getString("description");
                int userid = result.getInt("userid");

                String dateCor = date.toString();
                String years = dateCor.substring(0, 4);
                String months = dateCor.substring(5, 7);
                String days = dateCor.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);
                String startTimeCorrect = starttime.toString();
                String endTimeCorrect = endtime.toString();
                String startTimeCorrect2 = startTimeCorrect.substring(0, 5);
                String endTimeCorrect2 = endTimeCorrect.substring(0, 5);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");
                LocalTime starttime2 = LocalTime.parse(startTimeCorrect2, formatter2);
                LocalTime endtime2 = LocalTime.parse(endTimeCorrect2, formatter2);
                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));

                WorkOrder workOrder = new WorkOrder(workorderId, name, team, date4, starttime2, endtime2, description, userid);
                sortedWorkOrders.add(workOrder);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return sortedWorkOrders;
    }

    @Override
    public ArrayList<WorkOrder> sortAllWorkOrdersAscending() {
        ArrayList<WorkOrder> sortedWorkOrders = new ArrayList<>();
        String query = String.format("select * from %s.workorders order by date asc, starttime asc", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int workorderId = result.getInt("workorderid");
                String name = result.getString("name");
                String teamname = result.getString("team");
                Timestamp date = result.getTimestamp("date");
                Time starttime = result.getTime("starttime");
                Time endtime = result.getTime("endtime");
                String description = result.getString("description");
                int userid = result.getInt("userid");

                String dateCor = date.toString();
                String years = dateCor.substring(0, 4);
                String months = dateCor.substring(5, 7);
                String days = dateCor.substring(8, 10);
                String date3 = days + "/" + months + "/" + years;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date4 = LocalDate.parse(date3, formatter);
                String startTimeCorrect = starttime.toString();
                String endTimeCorrect = endtime.toString();
                String startTimeCorrect2 = startTimeCorrect.substring(0, 5);
                String endTimeCorrect2 = endTimeCorrect.substring(0, 5);
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");
                LocalTime starttime2 = LocalTime.parse(startTimeCorrect2, formatter2);
                LocalTime endtime2 = LocalTime.parse(endTimeCorrect2, formatter2);
                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));

                WorkOrder workOrder = new WorkOrder(workorderId, name, team, date4, starttime2, endtime2, description, userid);
                sortedWorkOrders.add(workOrder);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return sortedWorkOrders;
    }

    /**
     * Check the connection and reconnect when necessery
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        checkConnection();
        return this.connection;
    }

    /**
     * Check if the connection is still open
     * When connection has been closed: reconnect
     */
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

    /**
     * Reconnects application to db
     */
    private void reConnect() {
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.reconnect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
    }
}

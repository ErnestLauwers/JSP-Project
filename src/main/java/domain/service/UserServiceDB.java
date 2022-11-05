package domain.service;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class UserServiceDB implements UserService {

    private Connection connection;
    private String schema;

    public UserServiceDB() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    @Override
    public User getUser(int userid) {
        String query = String.format("SELECT * from %s.user WHERE id = (?)", schema);

        User user = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userid);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int id = result.getInt("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String teamname = result.getString("team");
                String rolename = result.getString("role");

                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                Role role = Role.valueOf(rolename.toUpperCase(Locale.ROOT));
                user = new User(id, email, password, firstname, lastname, team);
                user.setRole(role);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return user;
    }

    @Override
    public User getUserIfAuthenticated(String email, String password) {
        String query = String.format("SELECT * from %s.user where email = (?) and password = (?)");
        User user = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();

            int id = result.getInt("userid");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String teamname = result.getString("team");
            String rolename = result.getString("role");

            Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
            Role role = Role.valueOf(rolename.toUpperCase(Locale.ROOT));
            user = new User(id, email, password, firstname, lastname, team);
            user.setRole(role);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return user;
    }

    @Override
    public int getNumberOfUsers() {
        String query = String.format("SELECT count(userid) as result from %s.user", schema);
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
    public void update(User user) {
        String query = String.format("update %s.user set email = (?), password = (?), firstName = (?), lastName = (?), team = (?), role = (?) where userid = (?)");
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getTeam().getStringValue());
            preparedStatement.setString(6, user.getRole().getStringValue());
            preparedStatement.setInt(7, user.getUserid());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        String query = String.format("delete from %s.user where userid = id");
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) {
        String query = String.format("insert into %s.user (email, password, firstName, lastName, team, role) values (?, ?, ?, ?, ?, ?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getTeam().getStringValue());
            preparedStatement.setString(6, user.getRole().getStringValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = String.format("SELECT * from %s.user", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String teamname = result.getString("team");
                String rolename = result.getString("role");

                Team team = Team.valueOf(teamname.toUpperCase(Locale.ROOT));
                Role role = Role.valueOf(rolename.toUpperCase(Locale.ROOT));
                User user = new User(id, email, password, firstname, lastname, team);
                user.setRole(role);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return users;
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
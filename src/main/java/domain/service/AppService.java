package domain.service;

import domain.model.User;
import java.util.ArrayList;

public class AppService {

    private UserService users = new UserServiceDB();

    public User getUser(int userid) {
        return users.getUser(userid);
    }

    public ArrayList<User> getAllUsers() {
        return users.getAllUsers();
    }

    public void addUser(User user) {
        users.addUser(user);
    }

    public void update(User user) {
        users.update(user);
    }

    public void deleteUser(int id) {
        users.deleteUser(id);
    }

    public int getNumberOfUsers() {
        return users.getNumberOfUsers();
    }

    public User getUserIfAuthenticated(String email, String password) {
        return users.getUserIfAuthenticated(email, password);
    }

}
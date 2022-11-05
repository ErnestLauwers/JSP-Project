package domain.service;

import domain.model.User;
import java.util.ArrayList;

public interface UserService {

    public User getUser(int userid);

    public ArrayList<User> getAllUsers();

    public void addUser(User user);

    public void update(User user);

    public void deleteUser(int id);

    public int getNumberOfUsers();

    public User getUserIfAuthenticated(String email, String password);

}
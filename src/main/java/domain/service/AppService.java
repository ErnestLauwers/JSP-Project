package domain.service;

import domain.model.User;
import domain.model.WorkOrder;

import java.util.ArrayList;

public class AppService {

    private UserService users = new UserServiceDB();

    private WorkOrderService workOrders = new WorkOrderServiceDB();

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

    public WorkOrder getWorkOrder(int workOrderId) {
        return workOrders.getWorkOrder(workOrderId);
    }

    public ArrayList<WorkOrder> getAllWorkOrders() {
        return workOrders.getAllWorkOrders();
    }

    public void addWorkOrder(WorkOrder workOrder) {
        workOrders.addWorkOrder(workOrder);
    }

    public void update(WorkOrder workOrder) {
        workOrders.update(workOrder);
    }

    public void deleteWorkOrder(int workOrderId) {
        workOrders.deleteWorkOrder(workOrderId);
    }

    public int getNumberOfWorkOrders() {
        return workOrders.getNumberOfWorkOrders();
    }
}
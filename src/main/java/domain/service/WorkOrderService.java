package domain.service;

import domain.model.WorkOrder;

import java.util.ArrayList;

public interface WorkOrderService {

    public WorkOrder getWorkOrder(int workOrderId);

    public ArrayList<WorkOrder> getAllWorkOrders();

    public void addWorkOrder(WorkOrder workOrder);

    public void update(WorkOrder workOrder);

    public void deleteWorkOrder(int workOrderId);

    public int getNumberOfWorkOrders();

}

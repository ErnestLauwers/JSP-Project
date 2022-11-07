package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditWorkOrderPage extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String workOrderId = request.getParameter("workOrderId");
        int id = Integer.parseInt(workOrderId);
        request.setAttribute("startTimeCorrect", service.getWorkOrder(id).getStartTime());
        request.setAttribute("endTimeCorrect", service.getWorkOrder(id).getEndTime());
        request.setAttribute("descriptionCorrect", service.getWorkOrder(id).getDescription());
        request.setAttribute("workOrderToEdit", service.getWorkOrder(id));
        request.setAttribute("workOrderId", workOrderId);
        return "editWorkOrder.jsp";
    }
}
package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteWorkOrderConf extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String workOrderId = request.getParameter("workOrderId");
        int id = Integer.parseInt(workOrderId);
        request.setAttribute("workOrderToDelete", service.getWorkOrder(id));
        return "deleteWorkOrder.jsp";
    }
}
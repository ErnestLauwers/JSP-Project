package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        return "editWorkOrder.jsp";
    }
}
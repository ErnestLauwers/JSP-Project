package ui.controller;

import domain.model.Role;
import domain.model.User;
import domain.model.WorkOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Locale;

public class SearchWorkOrder extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String workOrderTeam = request.getParameter("search");
        ArrayList<WorkOrder> workOrders = new ArrayList<>();
        if (workOrderTeam == null || workOrderTeam.isEmpty()) {
            request.setAttribute("errorAanwezig", true);
            HttpSession session = request.getSession();
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            request.setAttribute("error", "Gelieve iets in te vullen voor we kunnen zoeken!");
            return "searchWorkOrderPage.jsp";
        }
        for (WorkOrder workOrder : service.getAllWorkOrders()) {
            if (workOrder.getTeam().getStringValue().toLowerCase(Locale.ROOT).equals(workOrderTeam.toLowerCase(Locale.ROOT))) {
                request.setAttribute("found", true);
                workOrders.add(workOrder);
                HttpSession session = request.getSession();
                Role role = (Role) session.getAttribute("userRole");
                request.setAttribute("roleLoggedIn", role);
                User user = (User) session.getAttribute("user");
                request.setAttribute("userLoggedIn", user);
                request.setAttribute("workOrders", workOrders);
            }
        }
        if (workOrders.size() == 0) {
            request.setAttribute("found", false);
        }
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        return "searchWorkOrderResult.jsp";
    }

}
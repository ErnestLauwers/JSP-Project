package ui.controller;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteWorkOrder extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String workOrderId = request.getParameter("workOrderId");
        int id = Integer.parseInt(workOrderId);
        service.deleteWorkOrder(id);
        HttpSession session = request.getSession();
        Team team = (Team) session.getAttribute("userTeam");
        request.setAttribute("teamLoggedIn", team);
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        return "index.jsp";
    }
}
package ui.controller;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SortWorkOrdersAsc extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        request.setAttribute("idLoggedIn", id);
        Team team = (Team) session.getAttribute("userTeam");
        request.setAttribute("teamLoggedIn", team);
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        request.setAttribute("workOrders", service.sortAllWorkOrdersAscending());
        return "workOrders.jsp";
    }

}
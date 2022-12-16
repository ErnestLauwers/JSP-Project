package ui.controller;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SortWorkOrdersDesc extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("workOrders", service.sortAllWorkOrdersDescending());
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        int id = (int) session.getAttribute("userId");
        request.setAttribute("idLoggedIn", id);
        Team team = (Team) session.getAttribute("userTeam");
        request.setAttribute("teamLoggedIn", team);
        return "workOrders.jsp";
    }

}
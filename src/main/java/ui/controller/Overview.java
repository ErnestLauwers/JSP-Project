package ui.controller;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Overview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("users", service.getAllUsers());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        Team team = (Team) session.getAttribute("userTeam");
        request.setAttribute("teamLoggedIn", team);
        if (session.getAttribute("userId") != null) {
            int id = (int) session.getAttribute("userId");
            request.setAttribute("idLoggedIn", id);
        }
        return "userOverview.jsp";
    }

}
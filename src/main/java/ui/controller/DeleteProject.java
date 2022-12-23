package ui.controller;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectid = request.getParameter("projectid");
        if (projectid != null) {
            int id = Integer.parseInt(projectid);
            service.deleteProject(id);
            request.setAttribute("projects", getService().getAllProjects());
            HttpSession session = request.getSession();
            Team team = (Team) session.getAttribute("userTeam");
            request.setAttribute("teamLoggedIn", team);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
        }
        return "projects.jsp";
    }

}
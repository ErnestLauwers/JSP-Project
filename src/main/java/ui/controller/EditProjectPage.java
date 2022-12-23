package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProjectPage extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectid");
        if (projectId != null) {
            int id = Integer.parseInt(projectId);
            request.setAttribute("correctName", service.getProject(id).getName());
            request.setAttribute("correctStartDate", service.getProject(id).getStartDate());
            request.setAttribute("correctEndDate", service.getProject(id).getEndDate());
            request.setAttribute("projectToEdit", service.getProject(id));
            HttpSession session = request.getSession();
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
        }
        return "editProject.jsp";
    }

}
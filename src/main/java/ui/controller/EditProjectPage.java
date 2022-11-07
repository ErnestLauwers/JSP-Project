package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProjectPage extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectid");
        int id = Integer.parseInt(projectId);
        request.setAttribute("correctName", service.getProject(id).getName());
        request.setAttribute("correctStartDate", service.getProject(id).getStartDate());
        request.setAttribute("correctEndDate", service.getProject(id).getEndDate());
        request.setAttribute("projectToEdit", service.getProject(id));
        return "editProject.jsp";
    }

}
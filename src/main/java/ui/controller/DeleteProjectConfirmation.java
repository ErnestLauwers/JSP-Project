package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProjectConfirmation extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter("projectId");
        int id = Integer.parseInt(projectId);
        request.setAttribute("projectToDelete", service.getProject(id));
        return "deleteProject.jsp";
    }

}
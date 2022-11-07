package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectid = request.getParameter("projectid");
        int id = Integer.parseInt(projectid);
        service.deleteProject(id);
        request.setAttribute("projects", getService().getAllProjects());
        return "projects.jsp";
    }

}
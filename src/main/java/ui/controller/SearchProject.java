package ui.controller;

import domain.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchProject extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectNaam = request.getParameter("search");
        if (projectNaam == null || projectNaam.isEmpty()) {
            request.setAttribute("errorAanwezig", true);
            request.setAttribute("error", "Gelieve iets in te vullen voor we kunnen zoeken!");
            return "searchProjectPage.jsp";
        }
        for (Project project: service.getAllProjects()) {
            if (project.getName().equals(projectNaam)) {
                request.setAttribute("found", true);
                request.setAttribute("foundResult", project);
                return "searchResult.jsp";
            }
        }
        request.setAttribute("found", false);
        return "searchResult.jsp";
    }

}


package ui.controller;

import domain.model.Project;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchProject extends RequestHandler {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectNaam = request.getParameter("search");
        if (projectNaam == null || projectNaam.isEmpty()) {
            HttpSession session = request.getSession();
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            request.setAttribute("errorAanwezig", true);
            request.setAttribute("error", "Gelieve iets in te vullen voor we kunnen zoeken!");
            return "searchProjectPage.jsp";
        }
        for (Project project: service.getAllProjects()) {
            if (project.getName().equals(projectNaam)) {
                request.setAttribute("found", true);
                request.setAttribute("foundResult", project);
                HttpSession session = request.getSession();
                Role role = (Role) session.getAttribute("userRole");
                request.setAttribute("roleLoggedIn", role);
                User user = (User) session.getAttribute("user");
                request.setAttribute("userLoggedIn", user);
                return "searchResult.jsp";
            }
        }
        request.setAttribute("found", false);
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("userRole");
        request.setAttribute("roleLoggedIn", role);
        User user = (User) session.getAttribute("user");
        request.setAttribute("userLoggedIn", user);
        return "searchResult.jsp";
    }

}
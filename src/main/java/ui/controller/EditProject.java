package ui.controller;

import domain.model.Project;
import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class EditProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String projectid = request.getParameter("projectId");
        if (projectid == null) {
            return "editProject.jsp";
        }
        int id = Integer.parseInt(projectid);
        Project project = service.getProject(id);
        registerStartDate(request, project, errors);
        registerEndDate(request, project, errors);
        if (errors.isEmpty()) {
            getService().update(project);
            request.setAttribute("projects", service.getAllProjects());
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            return "projects.jsp";
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            request.setAttribute("errors", errors);
            return "editProject.jsp";
        }
    }

    private void registerStartDate(HttpServletRequest request, Project project, ArrayList<String> errors) {
        String startdate = request.getParameter("startDate");
        if (startdate.equals("")) {
            throw new IllegalArgumentException("Startdate cannot be empty!");
        }
        LocalDate startdateLocalDate = LocalDate.parse(startdate);
        request.setAttribute("correctStartDate", LocalDate.now());
        try {
            project.setStartDate(startdateLocalDate);
            request.setAttribute("correctStartDate", startdateLocalDate);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void registerEndDate(HttpServletRequest request, Project project, ArrayList<String> errors) {
        String enddate = request.getParameter("endDate");
        String startdate = request.getParameter("startDate");
        LocalDate startdateLocaleDate = LocalDate.parse(startdate);
        if (!enddate.isEmpty()) {
            LocalDate enddateLocaleDate = LocalDate.parse(enddate);
            try {
                request.setAttribute("correctEndDate", enddateLocaleDate);
                if (enddateLocaleDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Enddate can't be in the past!");
                if (enddateLocaleDate.isBefore(startdateLocaleDate)) throw new IllegalArgumentException("Enddate can't be before startdate!");
                project.setEndDate(enddateLocaleDate);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
    }

}


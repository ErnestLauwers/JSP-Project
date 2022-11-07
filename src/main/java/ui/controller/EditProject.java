package ui.controller;

import domain.model.Project;
import domain.model.Team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class EditProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String projectid = request.getParameter("projectId");
        int id = Integer.parseInt(projectid);
        ArrayList<String> errors = new ArrayList<>();
        Project project = service.getProject(id);
        registerStartDate(request, project, errors);
        registerEndDate(request, project, errors);
        if (errors.isEmpty()) {
            getService().update(project);
            request.setAttribute("projects", service.getAllProjects());
            return "projects.jsp";
        }
        else {
            request.setAttribute("errors", errors);
            return "editProject.jsp";
        }
    }

    private void registerStartDate(HttpServletRequest request, Project project, ArrayList<String> errors) {
        String startdate = request.getParameter("startDate");
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
                if (enddateLocaleDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Enddate can't be in the past!");
                if (enddateLocaleDate.isBefore(startdateLocaleDate)) throw new IllegalArgumentException("Enddate can't be before startdate!");
                project.setEndDate(enddateLocaleDate);
                request.setAttribute("correctEndDate", enddateLocaleDate);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
    }

}


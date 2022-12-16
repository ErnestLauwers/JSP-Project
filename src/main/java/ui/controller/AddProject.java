package ui.controller;

import domain.model.Project;
import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class AddProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Project project = new Project();
        ArrayList<String> errors = new ArrayList<>();
        try {
            registerName(request, project, errors);
            registerTeam(request, project, errors);
            registerStartDate(request, project, errors);
            registerEndDate(request, project, errors);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        if (errors.size() == 0) {
            getService().addProject(project);
            request.setAttribute("projects", getService().getAllProjects());
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            return "projects.jsp";
        } else {
            request.setAttribute("errors", errors);
            return "addProject.jsp";
        }

    }

    private void registerName(HttpServletRequest request, Project project, ArrayList<String> errors) {
        String name = request.getParameter("projectName");
        try {
            for (Project p: service.getAllProjects()) {
                if (p.getName().equals(name)) throw new IllegalArgumentException("There is already a project with this name!");
            }
            project.setName(name);
            request.setAttribute("correctName", name);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void registerTeam(HttpServletRequest request, Project project, ArrayList<String> errors) {
        try {
            HttpSession session = request.getSession();
            String team = session.getAttribute("userTeam").toString();
            Team teamName = Team.valueOf(team.toUpperCase(Locale.ROOT));
            project.setTeam(teamName);
            request.setAttribute("correctTeam", teamName);
        } catch (Exception e) {
            errors.add(e.getMessage());
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

package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.Role;
import domain.model.User;

import java.util.ArrayList;

public class Edit extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        String userId = request.getParameter("userId");
        int id = Integer.parseInt(userId);
        request.setAttribute("userToEdit", service.getUser(id));
        User userE = service.getUser(id);
        registerFirstName(request, userE, errors);
        registerLastName(request, userE, errors);
        registerEmail(request, userE, errors);
        registerRole(request, userE, errors);
        registerTeam(request, userE, errors);
        try {
            if (errors.isEmpty()) {
                getService().update(userE);
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("userLoggedIn", user);
                Role role = (Role) session.getAttribute("userRole");
                request.setAttribute("roleLoggedIn", role);
                return "index.jsp";
            }
            else {
                request.setAttribute("errors", errors);
                return "edit.jsp";
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errors", errors);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            return "index.jsp)";
        }
    }

    private void registerFirstName(HttpServletRequest request, User user, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try{
            user.setFirstName(firstName);
            request.setAttribute("firstNameCorrect", firstName);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerLastName(HttpServletRequest request, User user, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try{
            user.setLastName(lastName);
            request.setAttribute("lastNameCorrect", lastName);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerEmail(HttpServletRequest request, User user, ArrayList<String> errors){
        String email = request.getParameter("email");
        try{
            user.setEmail(email);
            request.setAttribute("emailCorrect", email);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerRole(HttpServletRequest request, User user, ArrayList<String> errors) {
        String role = request.getParameter("role");
        try{
            user.setRole(role);
            request.setAttribute("userToEditRole", role);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerTeam(HttpServletRequest request, User user, ArrayList<String> errors) {
        String team = request.getParameter("team");
        try{
            user.setTeam(team);
            request.setAttribute("userToEditTeam", team);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }
}
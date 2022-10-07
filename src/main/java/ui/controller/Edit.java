package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.User;

import java.util.ArrayList;

public class Edit extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        String userId = request.getParameter("userId");
        int id = Integer.parseInt(userId);
        request.setAttribute("userToEdit", service.get(id));
        User userE = service.get(id);
        registerFirstName(request, userE, errors);
        registerLastName(request, userE, errors);
        registerEmail(request, userE, errors);
        registerRole(request, userE, errors);
        registerTeam(request, userE, errors);
        try {
            if (errors.isEmpty()) {
                return "index.jsp";
            }
            else {
                request.setAttribute("errors", errors);
                return "edit.jsp";
            }
        } catch (IllegalArgumentException e) {
            return "index.jsp)";
        }

/*
        ArrayList<String> errors = new ArrayList<>();
        String id = request.getParameter("id");
        User user = new User();
        try{
            user.setUserid(Integer.parseInt(id));
            registerFirstName(request, user, errors);
            registerLastName(request, user, errors);
            registerEmail(request, user, errors);
            registerRole(request, user, errors);
            registerTeam(request, user, errors);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
        if (errors.size() == 0 ){
            service.update(user);
            request.setAttribute("users", service.getAll());
            try {
                Controller.setSendRedirect();
                response.sendRedirect("userOverview.jsp");
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            return "userOverview.jsp";
        }else{
            if (errors.get(0).length() > 20) {
                errors.remove(0);
            }
            request.setAttribute("errors", errors);
            return "edit.jsp";
        }*/
    }

    private void registerFirstName(HttpServletRequest request, User user, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try{
            user.setFirstName(firstName);
            request.setAttribute("userToEditFirstName", firstName);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerLastName(HttpServletRequest request, User user, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try{
            user.setLastName(lastName);
            request.setAttribute("userToEditLastName", lastName);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerEmail(HttpServletRequest request, User user, ArrayList<String> errors){
        String email = request.getParameter("email");
        try{
            user.setEmail(email);
            request.setAttribute("userToEditEmail", email);
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
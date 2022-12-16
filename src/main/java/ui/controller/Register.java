package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;

import java.util.ArrayList;

public class Register extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        ArrayList<String> errors = new ArrayList<>();
        try{
            registerLastName(request, user, errors);
            registerFirstName(request, user, errors);
            registerEmail(request, user, errors);
            registerPassword(request, user, errors);
            registerTeam(request, user, errors);
        }catch (IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        if (errors.size() == 0 ){
            try{
                getService().addUser(user);
            }catch (Exception e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                HttpSession session = request.getSession();
                User user2 = (User) session.getAttribute("user");
                request.setAttribute("userLoggedIn", user2);
                Role role = (Role) session.getAttribute("userRole");
                request.setAttribute("roleLoggedIn", role);
                return "register.jsp";
            }
            request.setAttribute("errors", errors);
            request.setAttribute("users", getService().getAllUsers());
            HttpSession session = request.getSession();
            User user2 = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user2);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            int id = (int) session.getAttribute("userId");
            request.setAttribute("idLoggedIn", id);
            Team team = (Team) session.getAttribute("userTeam");
            request.setAttribute("teamLoggedIn", team);
            return "userOverview.jsp";
        }else{
            if (errors.get(0).length() > 20) {
                errors.remove(0);
            }
            request.setAttribute("errors", errors);
            HttpSession session = request.getSession();
            User user2 = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user2);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            return "register.jsp";
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

    private void registerPassword(HttpServletRequest request, User user, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try{
            user.setPassword(password);
            request.setAttribute("passwordCorrect", password);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerTeam(HttpServletRequest request, User user, ArrayList<String> errors) {
        String team = request.getParameter("team");
        try{
            user.setTeam(team);
            request.setAttribute("teamCorrect", team);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }
}
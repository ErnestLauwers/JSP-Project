package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = getService().getUserIfAuthenticated(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            String name = user.getFirstName() + " " + user.getLastName();
            session.setAttribute("userName", name);
            session.setAttribute("userTeam", user.getTeam());
            session.setAttribute("email", email);
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("userId", user.getUserid());
            return "index.jsp";
        } else {
            String error = "No valid email/password";
            request.setAttribute("error", error);
            return "login.jsp";
        }

    }

}
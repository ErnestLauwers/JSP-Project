package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("email") == null) {
            return "index.jsp";
        }
        String email = request.getParameter("email").toLowerCase(Locale.ROOT);
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
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("user", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user2 = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user2);
            return "index.jsp";
        } else {
            String error = "No valid email/password";
            request.setAttribute("error", error);
            return "login.jsp";
        }

    }

}
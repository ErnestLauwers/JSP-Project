package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        if (userId != null) {
            int id = Integer.parseInt(userId);
            service.deleteUser(id);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            return "index.jsp";
        }
        return "delete.jsp";
    }
}
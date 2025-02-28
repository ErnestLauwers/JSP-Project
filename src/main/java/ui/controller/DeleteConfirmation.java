package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteConfirmation extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        if (userId != null) {
            int id = Integer.parseInt(userId);
            request.setAttribute("userToDelete", service.getUser(id));
            HttpSession session = request.getSession();
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
        }
        return "delete.jsp";
    }
}
package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartEdit extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        if (userId != null) {
            int id = Integer.parseInt(userId);
            request.setAttribute("userToEditId", id);
            request.setAttribute("lastNameCorrect", service.getUser(id).getLastName());
            request.setAttribute("firstNameCorrect", service.getUser(id).getFirstName());
            request.setAttribute("emailCorrect", service.getUser(id).getEmail());
            request.setAttribute("userToEdit", service.getUser(id));
            HttpSession session = request.getSession();
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            if (session.getAttribute("userId") != null) {
                int id2 = (int) session.getAttribute("userId");
                request.setAttribute("idLoggedIn", id2);
            }
        }
        return "edit.jsp";
    }
}
package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartEdit extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        int id = Integer.parseInt(userId);
        request.setAttribute("userToEdit", service.getUser(id));
        return "edit.jsp";
    }
}
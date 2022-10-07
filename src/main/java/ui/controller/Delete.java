package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        int id = Integer.parseInt(userId);
        service.delete(id);
        return "index.jsp";
    }
}
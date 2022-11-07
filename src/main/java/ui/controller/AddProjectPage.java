package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddProjectPage extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        LocalDate today = LocalDate.now();
        request.setAttribute("correctStartDate", today);
        return "addProject.jsp";
    }

}

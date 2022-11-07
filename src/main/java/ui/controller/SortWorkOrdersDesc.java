package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortWorkOrdersDesc extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("workOrders", service.sortAllWorkOrdersDescending());
        return "workOrders.jsp";
    }

}
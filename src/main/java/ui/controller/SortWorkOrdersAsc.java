package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortWorkOrdersAsc extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("workOrders", service.sortAllWorkOrdersAscending());
        return "workOrders.jsp";
    }

}
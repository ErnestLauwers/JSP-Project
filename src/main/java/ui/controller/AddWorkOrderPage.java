package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddWorkOrderPage extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String tempTime = time.toString().substring(3, 5);
        int startTimeTemp = Integer.parseInt(tempTime) - 2;
        int endTimeTemp = Integer.parseInt(tempTime) - 1;
        String startTimeFinal = time.toString().substring(0, 3) + startTimeTemp;
        String endTimeFinal = time.toString().substring(0, 3) + endTimeTemp;
        request.setAttribute("dateCorrect", date);
        request.setAttribute("endTimeCorrect", endTimeFinal);
        request.setAttribute("startTimeCorrect", startTimeFinal);
        return "addWorkOrder.jsp";
    }

}

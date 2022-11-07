package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.DomainException;
import domain.model.WorkOrder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditWorkOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        String workOrderId = request.getParameter("workOrderId");
        int id = Integer.parseInt(workOrderId);
        request.setAttribute("workOrderToEdit", service.getWorkOrder(id));
        WorkOrder workOrderE = service.getWorkOrder(id);
        registerStartTime(request, workOrderE, errors);
        registerEndTime(request, workOrderE, errors);
        registerDescription(request, workOrderE, errors);
        try {
            if (errors.isEmpty()) {
                getService().update(workOrderE);
                return "index.jsp";
            }
            else {
                request.setAttribute("errors", errors);
                return "editWorkOrder.jsp";
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("errors", errors);
            return "index.jsp)";
        }
    }

    private void registerStartTime(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String workOrderId = request.getParameter("workOrderId");
        int id = Integer.parseInt(workOrderId);
        LocalDate date4 = service.getWorkOrder(id).getDate();
        try{
            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTime2 = LocalTime.parse(startTime, formatter3);
            LocalTime endTime2 = LocalTime.parse(endTime, formatter3);
            LocalTime time = LocalTime.now();
            String tempTime = time.toString().substring(3, 5);
            int startTimeTemp = Integer.parseInt(tempTime) - 2;
            String startTimeFinal = time.toString().substring(0, 3) + startTimeTemp;
            request.setAttribute("startTimeCorrect", startTimeFinal);
            if (!workOrder.isCorrectDate(startTime2, endTime2, date4)) {
                throw new DomainException("End time cannot be before start time and work order date/time must be in the past");
            }
            workOrder.setStartTime(startTime2);
            request.setAttribute("startTimeCorrect", startTime);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerEndTime(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        String endTime = request.getParameter("endTime");
        try{
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime endTime2 = LocalTime.parse(endTime, formatter2);
            LocalTime time = LocalTime.now();
            String tempTime = time.toString().substring(3, 5);
            int endTimeTemp = Integer.parseInt(tempTime) - 1;
            String endTimeFinal = time.toString().substring(0, 3) + endTimeTemp;
            request.setAttribute("endTimeCorrect", endTimeFinal);
            workOrder.setEndTime(endTime2);
            request.setAttribute("endTimeCorrect", endTime);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerDescription(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        String description = request.getParameter("description");
        try{
            workOrder.setDescription(description);
            request.setAttribute("descriptionCorrect", description);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }
}
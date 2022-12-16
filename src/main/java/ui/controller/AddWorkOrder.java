package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class AddWorkOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        WorkOrder workOrder = new WorkOrder();
        ArrayList<String> errors = new ArrayList<>();
        try{
            registerDate(request, workOrder, errors);
            registerStartTime(request, workOrder, errors);
            registerEndTime(request, workOrder, errors);
            registerDescription(request, workOrder, errors);
            registerName(request, workOrder, errors);
            registerTeam(request, workOrder, errors);
        }catch (IllegalArgumentException e){
            errors.add(e.getMessage());
        }
        if (errors.size() == 0 ){
            try{
                getService().addWorkOrder(workOrder);
            }catch (Exception e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "addWorkOrder.jsp";
            }
            request.setAttribute("errors", errors);
            request.setAttribute("workOrders", getService().getAllWorkOrders());
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("userLoggedIn", user);
            Role role = (Role) session.getAttribute("userRole");
            request.setAttribute("roleLoggedIn", role);
            int id = (int) session.getAttribute("userId");
            request.setAttribute("idLoggedIn", id);
            Team team = (Team) session.getAttribute("userTeam");
            request.setAttribute("teamLoggedIn", team);
            return "workOrders.jsp";
        }else{
            request.setAttribute("errors", errors);
            return "addWorkOrder.jsp";
        }
    }

    private void registerName(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        try{
            HttpSession session = request.getSession();
            String userName = session.getAttribute("userName").toString();
            workOrder.setName(userName);
            request.setAttribute("nameCorrect", userName);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerTeam(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        try{
            HttpSession session = request.getSession();
            String team = session.getAttribute("userTeam").toString();
            Team teamName = Team.valueOf(team.toUpperCase(Locale.ROOT));
            workOrder.setTeam(teamName);
            request.setAttribute("teamCorrect", teamName);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }

    private void registerDate(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        String date = request.getParameter("date");
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date2 = LocalDate.parse(date, formatter);
        String years = date.substring(0, 4);
        String months = date.substring(5, 7);
        String days = date.substring(date.length() - 2);
        String date3 = days + "/" + months + "/" + years;
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date4 = LocalDate.parse(date3, formatter2);
        request.setAttribute("dateCorrect", dateNow);

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime2 = LocalTime.parse(startTime, formatter3);
        LocalTime endTime2 = LocalTime.parse(endTime, formatter3);
        HttpSession session = request.getSession();
        String userId = session.getAttribute("userId").toString();
        int id = Integer.parseInt(userId);
        workOrder.setUserId(id);
        ArrayList<WorkOrder> workOrders = service.getWorkOrdersWithId(id);
        try{
            if (!workOrder.dateIsInPast(date4)) {
                throw new DomainException("Date cannot be in the future.");
            }
            if (workOrder.getUserId() == id) {
                for (WorkOrder order : workOrders) {
                    if (workOrder.getUserId() == id) {
                        if (order.getDate() == date4) {
                            throw new DomainException("A user cannot perform two jobs at the same time");
                            /*if (startTime2.isAfter(order.getStartTime()) && startTime2.isBefore(order.getEndTime()) ||
                                    endTime2.isAfter(order.getStartTime()) && endTime2.isBefore(order.getEndTime())) {
                                throw new DomainException("A user cannot perform two jobs at the same time");
                            }*/
                        }
                    }
                }
            }
            workOrder.setDate(date4);
            request.setAttribute("dateCorrect", date4);
        }catch (Exception e){
            errors.add(e.getMessage());
        }
    }


    /*if (order.getDate() == date4) {
        if (startTime2.isAfter(order.getStartTime()) && startTime2.isBefore(order.getEndTime()) ||
                endTime2.isAfter(order.getStartTime()) && endTime2.isBefore(order.getEndTime())) {
            throw new DomainException("A user cannot perform two jobs at the same time");
        }
    }*/

    private void registerStartTime(HttpServletRequest request, WorkOrder workOrder, ArrayList<String> errors) {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String date = request.getParameter("date");
        String years = date.substring(0, 4);
        String months = date.substring(5, 7);
        String days = date.substring(date.length() - 2);
        String date3 = days + "/" + months + "/" + years;
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date4 = LocalDate.parse(date3, formatter2);
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
                throw new DomainException("End time cannot be before start time");
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
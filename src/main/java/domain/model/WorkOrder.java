package domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkOrder {
    private int workOrderId = 0;
    private String name;
    private Team team;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private int userId;

    public WorkOrder(String name, Team team, LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
        this.setWorkOrderId(workOrderId++);
        this.setName(name);
        this.setTeam(team);
        this.setDate(date);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setDescription(description);
        this.setUserId(userId);
    }

    public WorkOrder(int workOrderId, String name, Team team, LocalDate date, LocalTime startTime, LocalTime endTime, String description, int userId) {
        this.setWorkOrderId(workOrderId);
        this.setName(name);
        this.setTeam(team);
        this.setDate(date);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setDescription(description);
        this.setUserId(userId);
    }

    public WorkOrder() {
    }

    public void setUserId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("The user id must be positive");
        }
        this.userId = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setWorkOrderId(int workOrderId) {
        if (workOrderId <= 0) {
            throw new IllegalArgumentException("The work order id must be positive");
        }
        this.workOrderId = workOrderId;
    }

    public int getWorkOrderId() {
        return this.workOrderId;
    }

    public void setDescription(String description) {
        if (description == null);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("No description given");
        }
        if (description.length() > 100) {
            throw new IllegalArgumentException("No valid description given");
        }
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setEndTime(LocalTime endTime) {
        if (endTime == null);
        this.endTime = endTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setStartTime(LocalTime startTime) {
        if (startTime == null);
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setDate(LocalDate date) {
        if (date == null);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("No team given");
        }
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setName(String name) {
        if (name == null);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("No name given");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCorrectDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        return !date.isBefore(LocalDate.now());
    }

    public String getDurationHour() {
        long duration = Duration.between(this.startTime, this.endTime).toHours();
        String duration2 = duration+"";
        if (duration2.length() < 2) {
            duration2 = "0" + duration2;
        }
        return duration2;
    }

    public String getDurationMinute() {
        long duration = Duration.between(this.startTime, this.endTime).toMinutes();
        String duration2 = duration+"";
        int duration3 = Integer.parseInt(duration2);
        duration3 = duration3 % 60;
        String duration4 = String.valueOf(duration3);
        if (duration4.length() < 2) {
            duration4 = "0" + duration4;
        }
        return duration4;
    }

    public boolean isCorrectDate(LocalTime startTime, LocalTime endTime, LocalDate date) {
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        if (date.isBefore(dateNow) && endTime.isAfter(startTime)) {
            return true;
        }
        return date.isEqual(dateNow) && endTime.isAfter(startTime) && endTime.isBefore(timeNow);
    }

    public boolean dateIsInPast(LocalDate date) {
        return !date.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return getWorkOrderId() + " " + getName() + ": " + getTeam() + ", " + getDate() + ", " + getStartTime() + ", " + getEndTime() + ", " + getDescription();
    }
}

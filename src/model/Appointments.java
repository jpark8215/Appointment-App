package model;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;

    public Appointments(int appointmentId, String title, String description, String location, String contact, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description ;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get a list of all appointments.
     @return A list of appointments.
     */
    public static ObservableList<Appointments> getAllAppointments(ObservableList<Appointments> allAppointments) { return allAppointments; }
}

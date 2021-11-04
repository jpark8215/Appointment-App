package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private int contactId;
    private String type;
    private Timestamp startTime;
    private Timestamp endTime;
    private int customerId;
    private int userId;


    public Appointment(int appointmentId, String title, String description, String location, int contactId, String type, Timestamp startTime, Timestamp endTime, int customerId, int userId){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description ;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
    }


    public int getAppointmentId() { return appointmentId; }

    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

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

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) { this.contactId = contactId; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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


    private static LocalDateTime formatDate(Timestamp formatDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return formatDate.toLocalDateTime();
    }

    public LocalDateTime getStartTime() { return formatDate(startTime); }



    public LocalDateTime getEndTime() { return formatDate(endTime); }


    public static LocalDateTime formatLocalDate(LocalDateTime localFormat) {
        DateTimeFormatter localeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

        return localFormat;
    }


    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }

    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }

}

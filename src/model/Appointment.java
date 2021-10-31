package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private int contactId;
    private String type;
    private String startTime;
    private String endTime;
    private int customerId;
    private int userId;


    public Appointment(int appointmentId, String title, String description, String location, int contactId, String type, String startTime, String endTime, int customerId, int userId){
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



    /**
     * @return the start date formatted for a sql query
     */
    public String getStartTime() { return formatDate(LocalDateTime.parse(startTime)); }

    /**
     * @return the end date formatted for a sql query
     */
    public String getEndTime() { return formatDate(LocalDateTime.parse(endTime)); }

    /**
     * formats a date for sql queries
     * @param date the date to format
     * @return the string for the sql query
     */
    private static String formatDate(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        return dateTimeFormatter.format(date.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")));
    }

    /**
     * @return the start date formatted for display in the table
     */
    public String getFormattedStartTime() { return formatLocalDate(LocalDateTime.parse(startTime)); }

    /**
     * @return the end date formatted for display in the table
     */
    public String getFormattedEndTime() { return formatLocalDate(LocalDateTime.parse(endTime)); }

    /**
     * formats a date for display in the table
     *
     * @param date the date to format
     * @return the string to display
     */
    public static String formatLocalDate(LocalDateTime date) {
        DateTimeFormatter localeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault());
        return date.format(localeFormatter);
    }


    /**
     * @return the start time in the local user's time zone
     */
    public ZonedDateTime getLocalStartTime() { return ZonedDateTime.ofLocal(LocalDateTime.parse(startTime), ZoneId.systemDefault(), ZoneOffset.UTC); }

    /**
     * @return the end time in the local user's time zone
     */
    public ZonedDateTime getLocalEndTime() { return ZonedDateTime.ofLocal(LocalDateTime.parse(endTime), ZoneId.systemDefault(), ZoneOffset.UTC); }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = String.valueOf(startTime);
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = String.valueOf(endTime);
    }

}

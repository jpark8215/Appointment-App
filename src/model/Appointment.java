package model;

import java.time.LocalDateTime;

/**
 * Appointment class
 */
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private int contactId;
    private String contactName;
    private String type;
//    private Timestamp startTime;
//    private Timestamp endTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;


    /**
     * Appointment constructor
     *
     * @param appointmentId appointmentId
     * @param title         title
     * @param description   description
     * @param location      location
     * @param contactId     contactId
     * @param contactName   contactName
     * @param type          type
     * @param startDateTime startDateTime
     * @param endDateTime   endDateTime
     * @param customerId    customerId
     * @param userId        userId
     */
    public Appointment(int appointmentId, String title, String description, String location, int contactId, String contactName, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.contactName = contactName;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
    }


    /**
     * @param newCustomerId newCustomerId
     * @param newUserId newUserId
     * @param newTitle newTitle
     * @param newType newType
     * @param newLocation newLocation
     * @param newDescription newDescription
     * @param newContactId newContactId
     * @param newStartDateTime newStartDateTime
     * @param newEndDateTime newEndDateTime
     */
    public Appointment(int newCustomerId, int newUserId, String newTitle, String newType, String newLocation, String newDescription, int newContactId, LocalDateTime newStartDateTime, LocalDateTime newEndDateTime) {
        customerId = newCustomerId;
        userId = newUserId;
        title = newTitle;
        type = newType;
        location = newLocation;
        description = newDescription;
        contactId = newContactId;
        startDateTime = newStartDateTime;
        endDateTime = newEndDateTime;
    }

    public Appointment(int updateAppointmentId, int updateCustomerId, int updateUserId, String updateTitle, String updateType, String updateLocation, String updateDescription, int updateContactId, LocalDateTime updateStartDateTime, LocalDateTime updateEndDateTime) {
        appointmentId = updateAppointmentId;
        customerId = updateCustomerId;
        userId = updateUserId;
        title = updateTitle;
        type = updateType;
        location = updateLocation;
        description = updateDescription;
        contactId = updateContactId;
        startDateTime = updateStartDateTime;
        endDateTime = updateEndDateTime;
    }


    /**
     * @return appointmentId
     */
    public int getAppointmentId() { return appointmentId; }

    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return contactId
     */
    public int getContactId() { return contactId; }

    public void setContactId(int contactId) { this.contactId = contactId; }

    /**
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) { this.contactName = contactName; }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return startDateTime
     */
    public LocalDateTime getStartDateTime() { return startDateTime; }

    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    /**
     * @return endDateTime
     */
    public LocalDateTime getEndDateTime() { return endDateTime; }

    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }


}

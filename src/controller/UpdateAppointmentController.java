package controller;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Update appointment controller class
 */
public class UpdateAppointmentController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField appointmentIdField;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;

    @FXML
    private DatePicker startDateField;

    @FXML
    private DatePicker endDateField;

    @FXML
    private ComboBox<Integer> startHourCombo;

    @FXML
    private ComboBox<Integer> startMinuteCombo;

    @FXML
    private ComboBox<Integer> endHourCombo;

    @FXML
    private ComboBox<Integer> endMinuteCombo;

    @FXML
    private TextField userIdField;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private TextField locationField;

    @FXML
    private TextField descriptionField;


    /**
     * @param event Cancels updating appointment and returns to appointment view
     */
    @FXML
    void CancelHandler(ActionEvent event) throws IOException {

        int confirmed = JOptionPane.showConfirmDialog(null, "Cancel and return to appointment view?", "EXIT", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Gets data from text fields, date picker, and combo boxes
     * Changes time to UTC
     * Checks forms are completed, business hour requirement, and conflict of appointment
     * @param event Updates selected appointment
     */
    @FXML
    void SaveHandler(ActionEvent event) {

        int updateContactId;
        int updateCustomerId;
        int updateUserId;
        LocalDateTime updateStartDateTime;
        LocalDateTime updateEndDateTime;

        String updateTitle = titleField.getText();
        String updateType = typeField.getText();
        String updateLocation = locationField.getText();
        String updateDescription = descriptionField.getText();

        try {
            if (updateTitle.isBlank()) {
                JOptionPane.showMessageDialog(null, "Title is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateType.isBlank()) {
                JOptionPane.showMessageDialog(null, "Type is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateLocation.isBlank()) {
                JOptionPane.showMessageDialog(null, "Location is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateDescription.isBlank()) {
                JOptionPane.showMessageDialog(null, "Description is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (userIdField.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "User is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (customerComboBox.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Customer is not selected.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (contactComboBox.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Contact is not selected.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (startDateField.getValue() == null || startHourCombo.getValue() == null || startMinuteCombo.getValue() == null ||
                    endDateField.getValue() == null || endHourCombo.getValue() == null || endMinuteCombo.getValue() == null ) {
                JOptionPane.showMessageDialog(null, "Please complete date and time.", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save and return to appointment view?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {

                    updateContactId = contactComboBox.getValue().getContactId();
                    updateCustomerId = customerComboBox.getValue().getCustomerId();
                    updateUserId = Integer.parseInt(userIdField.getText());

                    LocalDate startDateFieldValue = startDateField.getValue();
                    int startHourComboValue = startHourCombo.getValue();
                    int startMinuteComboValue = startMinuteCombo.getValue();
                    LocalTime startTime = LocalTime.of(startHourComboValue, startMinuteComboValue);
                    LocalDateTime startLDT = LocalDateTime.of(startDateFieldValue, startTime);
                    ZonedDateTime startZDT = startLDT.atZone(ZoneId.systemDefault());
                    ZonedDateTime startUTC = startZDT.withZoneSameInstant(ZoneId.of("UTC"));
                    updateStartDateTime = startUTC.toLocalDateTime();

                    //Check business hour
                    ZonedDateTime businessStartZone = startLDT.atZone(ZoneId.of("America/New_York"));
                    int businessStartLocalHour = businessStartZone.toLocalTime().getHour();
                    int businessStartLocalMinute = businessStartZone.toLocalTime().getMinute();

                    LocalDate endDateFieldValue = endDateField.getValue();
                    int endHourComboValue = endHourCombo.getValue();
                    int endMinuteComboValue = endMinuteCombo.getValue();
                    LocalTime endTime = LocalTime.of(endHourComboValue, endMinuteComboValue);
                    LocalDateTime endLDT = LocalDateTime.of(endDateFieldValue, endTime);
                    ZonedDateTime endZDT = endLDT.atZone(ZoneId.systemDefault());
                    ZonedDateTime endUTC = endZDT.withZoneSameInstant(ZoneId.of("UTC"));
                    updateEndDateTime = endUTC.toLocalDateTime();

                    //Check business hour
                    ZonedDateTime businessEndZone = endLDT.atZone(ZoneId.of("America/New_York"));
                    int businessEndLocalHour = businessEndZone.toLocalTime().getHour();
                    int businessEndLocalMinute = businessEndZone.toLocalTime().getMinute();

                    int updateAppointmentId = Integer.parseInt(appointmentIdField.getText()) ;
                    Appointment updateAppointment = new Appointment(updateAppointmentId, updateCustomerId, updateUserId, updateTitle, updateType, updateLocation, updateDescription, updateContactId, updateStartDateTime, updateEndDateTime);

                    if (updateEndDateTime.equals(updateStartDateTime) || updateEndDateTime.isBefore(updateStartDateTime)) {
                        JOptionPane.showMessageDialog(null, "End time must be after Start time.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (businessStartLocalHour < 8 || businessEndLocalHour > 22 || (businessEndLocalHour == 22 && businessEndLocalMinute > 0)) {
                        JOptionPane.showMessageDialog(null, "Outside of business hour. Please select between 8:00 and 22:00.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (DBAccess.getConflictedAppointment(updateAppointment)) {
                        JOptionPane.showMessageDialog(null, "Appointment times conflict with other appointments.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {

                        DBAccess.updateAppointment(updateAppointment);

                        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * Gets and loads selected appointment data in fields and combo boxes
     * Adds items in hour and minute combo boxes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Appointment selectedAppointment = AppointmentController.getSelectedAppointment();
        appointmentIdField.setEditable(false);

        appointmentIdField.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        userIdField.setText(String.valueOf(selectedAppointment.getUserId()));
        titleField.setText(String.valueOf(selectedAppointment.getTitle()));
        typeField.setText(String.valueOf(selectedAppointment.getType()));
        descriptionField.setText(String.valueOf(selectedAppointment.getDescription()));
        locationField.setText(String.valueOf(selectedAppointment.getLocation()));

        customerComboBox.setItems(DBAccess.getAllCustomers());
        Customer selectedCustomer = null;
        for (Customer customer : DBAccess.getAllCustomers()) {
            if (customer.getCustomerId() == selectedAppointment.getCustomerId()) {
                selectedCustomer = customer;
            }
        }
        customerComboBox.getSelectionModel().select(selectedCustomer);

        contactComboBox.setItems(DBAccess.getAllContacts());
        Contact selectedContact = null;
        for (Contact contact : DBAccess.getAllContacts()) {
            if (contact.getContactId() == selectedAppointment.getContactId()) {
                selectedContact = contact;
            }
        }
        contactComboBox.getSelectionModel().select(selectedContact);

        startHourCombo.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        startMinuteCombo.getItems().addAll(0, 15, 30, 45);
        endHourCombo.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        endMinuteCombo.getItems().addAll(0, 15, 30, 45);

        startDateField.setValue(LocalDate.from(selectedAppointment.getStartDateTime()));
        startHourCombo.setValue(selectedAppointment.getStartDateTime().getHour());
        startMinuteCombo.setValue(selectedAppointment.getStartDateTime().getMinute());

        endDateField.setValue(LocalDate.from(selectedAppointment.getEndDateTime()));
        endHourCombo.setValue(selectedAppointment.getEndDateTime().getHour());
        endMinuteCombo.setValue(selectedAppointment.getEndDateTime().getMinute());
    }
}

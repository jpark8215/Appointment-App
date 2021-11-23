package controller;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Add appointment controller class
 */
public class AddAppointmentController implements Initializable {

//    /**
//     * Generates a random number for appointment
//     * Checks existing appointment numbers to confirm for duplicates
//     */
//    Random random = new Random();
//    int appointmentId;

//    private int assignAppointmentId() {
//        int randomAppointmentId;
//        randomAppointmentId = random.nextInt(1000);
//
//        for (Appointment appointment : DBAccess.getAllAppointments() ) {
//            if (appointmentId == randomAppointmentId) {
//                boolean isMatch = false;
//                assignAppointmentId();
//            }
//        }
//        return randomAppointmentId;
//    }

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
    private Label timeZoneLabel;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private ComboBox<Contact> contactComboBox;


    /**
     * @param event Cancels adding appointment and returns to appointment view
     */
    @FXML
    void CancelHandler(ActionEvent event) throws IOException {

        int confirmed = JOptionPane.showConfirmDialog(null, "Cancel and return to appointment view?", "Exit", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * @param event Checks appointment requirements, changes time to UTC, and adds appointment to database
     */
    @FXML
    void SaveHandler(ActionEvent event) {

        int newContactId;
        int newCustomerId;
        int newUserId;
        LocalDateTime newStartDateTime;
        LocalDateTime newEndDateTime;

        String newTitle = titleField.getText();
        String newType = typeField.getText();
        String newLocation = locationField.getText();
        String newDescription = descriptionField.getText();

        try {
            if (newTitle.isBlank()) {
                JOptionPane.showMessageDialog(null, "Title is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newType.isBlank()) {
                JOptionPane.showMessageDialog(null, "Type is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newLocation.isBlank()) {
                JOptionPane.showMessageDialog(null, "Location is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newDescription.isBlank()) {
                JOptionPane.showMessageDialog(null, "Description is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (userIdField.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "User is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (customerComboBox.getSelectionModel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer is not selected.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (contactComboBox.getSelectionModel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Contact is not selected.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (startDateField.getValue() == null || startHourCombo.getValue() == null || startMinuteCombo.getValue() == null ||
                    endDateField.getValue() == null || endHourCombo.getValue() == null || endMinuteCombo.getValue() == null ) {
                JOptionPane.showMessageDialog(null, "Please complete date and time.", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save and return to appointment view?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {

                    newContactId = contactComboBox.getValue().getContactId();
                    newCustomerId = customerComboBox.getValue().getCustomerId();
                    newUserId = Integer.parseInt(userIdField.getText());

                    LocalDate startDateFieldValue = startDateField.getValue();
                    int startHourComboValue = startHourCombo.getValue();
                    int startMinuteComboValue = startMinuteCombo.getValue();
                    LocalTime startTime = LocalTime.of(startHourComboValue, startMinuteComboValue);
                    LocalDateTime startLDT = LocalDateTime.of(startDateFieldValue, startTime);
                    ZonedDateTime startZDT = startLDT.atZone(ZoneId.systemDefault());
                    ZonedDateTime startUTC = startZDT.withZoneSameInstant(ZoneId.of("UTC"));
                    newStartDateTime = startUTC.toLocalDateTime();

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
                    newEndDateTime = endUTC.toLocalDateTime();

                    //Check business hour
                    ZonedDateTime businessEndZone = endLDT.atZone(ZoneId.of("America/New_York"));
                    int businessEndLocalHour = businessEndZone.toLocalTime().getHour();
                    int businessEndLocalMinute = businessEndZone.toLocalTime().getMinute();

                    Appointment newAppointment = new Appointment(newCustomerId, newUserId, newTitle, newType, newLocation, newDescription, newContactId, newStartDateTime, newEndDateTime);

                    if (newEndDateTime.equals(newStartDateTime) || newEndDateTime.isBefore(newStartDateTime)) {
                        JOptionPane.showMessageDialog(null, "End time must be after Start time.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (businessStartLocalHour < 8 || businessEndLocalHour > 22 || (businessEndLocalHour == 22 && businessEndLocalMinute > 0)) {
                        JOptionPane.showMessageDialog(null, "Outside of business hour. Please select between 8:00 and 22:00.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (DBAccess.getConflictedAppointment(newAppointment)) {
                        JOptionPane.showMessageDialog(null, "Appointment times conflict with other appointments.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {

                        DBAccess.addAppointment(newAppointment);

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
     * Sets timezone label
     * Fills hour, minute, customer, and contact combo boxes
     * Sets current userId
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeZoneLabel.setText(String.valueOf(ZoneId.systemDefault()));

        startHourCombo.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        startMinuteCombo.getItems().addAll(0, 15, 30, 45);
        endHourCombo.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        endMinuteCombo.getItems().addAll(0, 15, 30, 45);

        appointmentIdField.setEditable(false);
        userIdField.setText(String.valueOf(User.userId));

        customerComboBox.setItems(DBAccess.getAllCustomers());
        contactComboBox.setItems(DBAccess.getAllContacts());

    }

}


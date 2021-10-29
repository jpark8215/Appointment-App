package controller;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Appointment;

import java.util.Random;

public class AddAppointmentController {

    /**
     * Generate a random number for appointment.
     * Check existing appointment numbers to confirm for duplicates.
     * @return Random appointment ID.
     */
    Random random = new Random();
    int appointmentId;


    private int assignAppointmentId() {
        int randomAppointmentId;
        randomAppointmentId = random.nextInt(1000);

        for (Appointment appointment : DBAccess.getAllAppointments() ) {
            if (appointmentId == randomAppointmentId) {
                boolean isMatch = false;
                assignAppointmentId();
            }
        }
        return randomAppointmentId;
    }


    @FXML
    private TextField AppointmentIdField;

    @FXML
    private Button CancelButton;

    @FXML
    private TextField CustomerIdField;

    @FXML
    private DatePicker EndDateField;

    @FXML
    private TextField EndHourField;

    @FXML
    private TextField EndMinuteField;

    @FXML
    private Button SaveButton;

    @FXML
    private DatePicker StartDateField;

    @FXML
    private TextField StartHourField;

    @FXML
    private TextField StartMinuteField;

    @FXML
    private Label TimeZoneLabel;

    @FXML
    private TextField TitleField;

    @FXML
    private TextField TypeField;

    @FXML
    private ChoiceBox<?> UserBox;

    @FXML
    void CancelHandler(ActionEvent event) {

    }

    @FXML
    void SaveHandler(ActionEvent event) {

    }

}

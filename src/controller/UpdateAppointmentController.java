package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Update appointment controller class
 */
public class UpdateAppointmentController implements Initializable {

    @FXML
    private TextField appointmentIdField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField customerIdField;

    @FXML
    private DatePicker endDateField;

    @FXML
    private TextField endHourField;

    @FXML
    private TextField endMinuteField;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDateField;

    @FXML
    private TextField startHourField;

    @FXML
    private TextField startMinuteField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;

    @FXML
    void CancelHandler(ActionEvent event) throws IOException {

        int confirmed = JOptionPane.showConfirmDialog(null, "Cancel and return to previous page?", "EXIT", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateCustomer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void SaveHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Appointment selectedAppointment = UpdateCustomerController.getSelectedAppointment();
        appointmentIdField.setEditable(false);
        customerIdField.setEditable(false);
        appointmentIdField.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        customerIdField.setText(String.valueOf(selectedAppointment.getCustomerId()));
        titleField.setText(String.valueOf(selectedAppointment.getTitle()));
        typeField.setText(String.valueOf(selectedAppointment.getType()));

        int endHour = selectedAppointment.getEndTime().getHour();
        int endMinute = selectedAppointment.getEndTime().getMinute();
        endDateField.setValue(LocalDate.from(selectedAppointment.getEndTime()));
        endHourField.setText(String.valueOf(endHour));
        endMinuteField.setText(String.valueOf(endMinute));

        int startHour = selectedAppointment.getStartTime().getHour();
        int startMinute = selectedAppointment.getStartTime().getMinute();
        startDateField.setValue(LocalDate.from(selectedAppointment.getStartTime()));
        startHourField.setText(String.valueOf(startHour));
        startMinuteField.setText(String.valueOf(startMinute));

    }
}

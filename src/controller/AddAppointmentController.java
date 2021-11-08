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
import model.Customer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    /**
     * Generate a random number for appointment.
     * Check existing appointment numbers to confirm for duplicates.
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
    private Label timeZoneLabel;

    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;

    @FXML
    private ChoiceBox<?> userBox;

    @FXML
    void CancelHandler(ActionEvent event) throws IOException {
        int confirmed = JOptionPane.showConfirmDialog(null, "Cancel and return to customer view?", "Exit", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void SaveHandler(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        appointmentId = assignAppointmentId();
        appointmentIdField.setText(Integer.toString(appointmentId));
        appointmentIdField.setEditable(false);

        Customer selectedCustomer = CustomerController.getSelectedCustomer();
        customerIdField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerIdField.setEditable(false);
    }

}

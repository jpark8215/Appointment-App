package controller;

import database.DBAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class UpdateCustomerController implements Initializable {

    /**
     * List of associated appointments with the customer.
     */
    private final ObservableList<Appointment> associatedAppointment = FXCollections.observableArrayList();
    /**
     * Customer selected in customerController.
     */
    Customer selectedCustomer;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button saveButton;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TableView<Appointment> associatedAppointmentTableView;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String> titleColumn;
    @FXML
    private TableColumn<Appointment, String> typeColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startDateColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endDateColumn;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private ComboBox<Division> divisionComboBox;
    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    void DeleteAppointmentHandler(ActionEvent event) {
        Appointment selectedAppointment = associatedAppointmentTableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            //        JOptionPane.showMessageDialog(null, "Please select a customer.", "Error", JOptionPane.ERROR_MESSAGE);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select an appointment.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Do you want to delete the appointment?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedAppointment.remove(selectedAppointment);
                associatedAppointmentTableView.setItems(associatedAppointment);
            }
        }
    }

    @FXML
    void UpdateAppointmentHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateAppointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        int confirmed = JOptionPane.showConfirmDialog(null, "Return to Customer?", "EXIT", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void saveButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeZoneLabel.setText(String.valueOf(ZonedDateTime.now()));

        customerIdField.setEditable(false);

        selectedCustomer = CustomerController.getCustomerToUpdate();
        //    associatedAppointment = selectedCustomer.getAllAssociatedAppointment();

        customerIdField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameField.setText(selectedCustomer.getCustomerName());
        addressField.setText(String.valueOf(selectedCustomer.getCustomerAddress()));
        postalCodeField.setText(String.valueOf(selectedCustomer.getCustomerPostalCode()));
//        divisionComboBox.setItems(selectedCustomer.getCustomerDivision());
//        countryComboBox.setItems(selectedCustomer.getCustomerCountry());;
        phoneField.setText(String.valueOf(selectedCustomer.getCustomerPhone()));


//        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

//        appointmentTable.setItems(appointments.getAllAppointments());
//
//        associatedAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        associatedAppointmentTable.setItems(associatedAppointment);

    }
}
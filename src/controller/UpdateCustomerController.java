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
import model.Appointments;
import model.Countries;
import model.Customers;
import model.Divisions;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    /**
     * Customer selected in customerController.
     */
    Customers selectedCustomer;


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
    private TableView<Appointments> associatedAppointmentTableView;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDColumn;

    @FXML
    private TableColumn<Appointments, String> titleColumn;

    @FXML
    private TableColumn<Appointments, String> typeColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> startDateColumn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> endDateColumn;

    @FXML
    private Label timeZoneLabel;

    @FXML
    private ComboBox<Divisions> divisionComboBox;

    @FXML
    private ComboBox<Countries> countryComboBox;


    /**
     * List of associated appointments with the customer.
     */
    private final ObservableList<Appointments> associatedAppointment = FXCollections.observableArrayList();


    @FXML
    void DeleteAppointmentHandler(ActionEvent event) {
        Appointments selectedAppointment = associatedAppointmentTableView.getSelectionModel().getSelectedItem();

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
        Parent scene = FXMLLoader.load(getClass().getResource("/view/updateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        int confirmed = JOptionPane.showConfirmDialog(null, "Return to Customer?", "EXIT", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void saveButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customerIdField.setEditable(false);

        countryComboBox.setItems(DBAccess.getAllCountries());
        countryComboBox.setPromptText("Select Country");

//        divisionComboBox.setItems(DBAccess.getAllDivisions());
//        divisionComboBox.setPromptText("Select Division");

        selectedCustomer = CustomerController.getCustomerToUpdate();
        //    associatedAppointment = selectedCustomer.getAllAssociatedAppointment();

        customerIdField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameField.setText(selectedCustomer.getCustomerName());
        addressField.setText(String.valueOf(selectedCustomer.getCustomerAddress()));
        postalCodeField.setText(String.valueOf(selectedCustomer.getCustomerPostalCode()));
        phoneField.setText(String.valueOf(selectedCustomer.getCustomerPhone()));


//        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));

//        appointmentTable.setItems(appointments.getAllAppointments());
//
//        associatedAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        associatedAppointmentTable.setItems(associatedAppointment);

    }
}


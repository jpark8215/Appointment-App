package controller;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

/**
 * Update customer controller class
 */
public class UpdateCustomerController implements Initializable {

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
    private TableView<Appointment> associatedAppointmentTable;

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
    private ComboBox<Division> divisionComboBox;

    @FXML
    private ComboBox<Country> countryComboBox;


    /**
     * @param event Deletes selected associated appointment
     *              Displays appointmentId and type of deleted appointment
     */
    @FXML
    void DeleteAppointmentHandler(ActionEvent event) throws IOException {
        Appointment selectedAppointment = associatedAppointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            JOptionPane.showMessageDialog(null, "Please select an appointment.", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to delete the appointment?", "Confirmation", JOptionPane.YES_NO_OPTION);
            {
                if (confirmed == JOptionPane.YES_OPTION) {
                    DBAccess.deleteAppointment(selectedAppointment);
                    associatedAppointmentTable.setItems(DBAccess.getAssociatedAppointments(selectedAppointment.getCustomerId()));

//                    Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cancel Confirmation");
//                        alert.setHeaderText("Do you want to delete the following appointment?");
                    alert.setHeaderText( "Appointment ID : " + selectedAppointment.getAppointmentId() + " & Type : " + selectedAppointment.getType() + " deleted");
                    alert.showAndWait();
//                    });

                }
                else {
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateCustomer.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }
    }


    /**
     * @param event Returns to customer view
     */
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        int confirmed = JOptionPane.showConfirmDialog(null, "Return to Customer view?", "EXIT", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Gets data from text fields and combo boxes
     * Checks forms are completed
     * @param event Updates selected customer
     */
    @FXML
    void saveButtonHandler(ActionEvent event) {

        String updateCustomerName = nameField.getText();
        String updateCustomerAddress = addressField.getText();
        String updateCustomerPostalCode = postalCodeField.getText();
        String updateCustomerPhone = phoneField.getText();

        try {
            if (updateCustomerName.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Name is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateCustomerAddress.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Address is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateCustomerPostalCode.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Zip Code is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateCustomerPhone.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Phone is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (countryComboBox.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Customer Zip Code is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (divisionComboBox.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Customer Zip Code is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save and return to Customer?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if ( confirmed == JOptionPane.YES_OPTION) {

                    int updateCustomerId = Integer.parseInt(customerIdField.getText());
                    int updateCustomerDivisionId =  divisionComboBox.getValue().getDivisionId();
                    String updateCustomerDivision = divisionComboBox.getValue().getDivision();
                    int updateCustomerCountryId = divisionComboBox.getValue().getCountryId();
                    String updateCustomerCountry = String.valueOf(countryComboBox.getValue());

                    Customer updateCustomer = new Customer(updateCustomerId, updateCustomerName, updateCustomerAddress, updateCustomerPostalCode, updateCustomerDivisionId, updateCustomerDivision, updateCustomerCountryId, updateCustomerCountry, updateCustomerPhone);
                    DBAccess.updateCustomer(updateCustomer);

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * @param event Gets filtered division based on user's country choice
     */
    @FXML
    public void userCountryChoice(ActionEvent event) {
        divisionComboBox.setItems(DBAccess.getFilteredDivisions(countryComboBox.getValue()));
    }


    /**
     * Gets and loads selected customer data in fields and combo boxes
     * Displays associated appointments for selected customer
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Customer selectedCustomer = CustomerController.getSelectedCustomer();
        customerIdField.setEditable(false);
        customerIdField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        nameField.setText(valueOf(selectedCustomer.getCustomerName()));
        addressField.setText(valueOf(selectedCustomer.getCustomerAddress()));
        postalCodeField.setText(valueOf(selectedCustomer.getCustomerPostalCode()));
        phoneField.setText(valueOf(selectedCustomer.getCustomerPhone()));

        Division selectedDivision = new Division(selectedCustomer.getCustomerDivisionId(), selectedCustomer.getCustomerDivision(), selectedCustomer.getCustomerCountryId() );
        divisionComboBox.getItems().add(selectedDivision);
        divisionComboBox.getSelectionModel().select(selectedDivision);

        countryComboBox.setItems(DBAccess.getAllCountries());
        Country selectedCountry = new Country(selectedCustomer.getCustomerCountryId(), selectedCustomer.getCustomerCountry());
//        countryComboBox.getItems().add(selectedCountry);
        countryComboBox.getSelectionModel().select(selectedCountry);

//        Appointment associatedAppointment = selectedCustomer.getAllAssociatedAppointments();
        associatedAppointmentTable.setItems(DBAccess.getAssociatedAppointments(CustomerController.getSelectedCustomer().getCustomerId()));
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));

    }
}
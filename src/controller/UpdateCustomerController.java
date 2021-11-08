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
import java.util.TimeZone;

import static java.lang.String.valueOf;

/**
 * Update customer controller class
 */
public class UpdateCustomerController implements Initializable {

    private static Appointment selectedAppointment;
    public static Appointment getSelectedAppointment() { return selectedAppointment; }

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
    private Label timeZoneLabel;

    @FXML
    private ComboBox<Division> divisionComboBox;

    @FXML
    private ComboBox<Country> countryComboBox;


    /**
     * Deletes selected associated appointment when user confirms
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
     * Gets selected appointment data and loads in appointment update
     * Opens update appointment view
     */
    @FXML
    void UpdateAppointmentHandler(ActionEvent event) throws IOException {

        selectedAppointment = associatedAppointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            JOptionPane.showMessageDialog(null, "Please select an appointment.", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateAppointment.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Returns to customer view when user confirms
     */
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


    /**
     * Checks required fields are completed
     * Saves updated customer data in database
     */
    @FXML
    void saveButtonHandler(ActionEvent event) {
        int updateCustomerId = Integer.parseInt(customerIdField.getText());
        String updateCustomerName = nameField.getText();
        String updateCustomerAddress = addressField.getText();
        String updateCustomerPostalCode = postalCodeField.getText();
        int updateCustomerDivisionId =  divisionComboBox.getValue().getDivisionId();
        String updateCustomerDivision = divisionComboBox.getValue().getDivision();
        int updateCustomerCountryId = divisionComboBox.getValue().getCountryId();
        String updateCustomerCountry = String.valueOf(countryComboBox.getValue());
        String updateCustomerPhone = phoneField.getText();


        try {
            if (updateCustomerName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Name is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateCustomerAddress.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Address is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateCustomerPostalCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Zip Code is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (updateCustomerPhone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Phone is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save and return to Customer?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if ( confirmed == JOptionPane.YES_OPTION) {

                    Customer updateCustomer = new Customer(updateCustomerId, updateCustomerName, updateCustomerAddress, updateCustomerPostalCode, updateCustomerDivisionId, updateCustomerDivision, updateCustomerCountryId, updateCustomerCountry, updateCustomerPhone);
                    DBAccess.updateCustomer(updateCustomer);

//                    Division updateCustomerDiv = new Division(updateCustomerDivisionId, updateCustomerDivision, updateCustomerCountryId);
//                    divisionComboBox.getItems().add(updateCustomerDiv);

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
     * Gets filtered division based on user's country choice
     */
    @FXML
    public void userCountryChoice(ActionEvent event) {
        divisionComboBox.setItems(DBAccess.getFilteredDivisions(countryComboBox.getValue()));
    }


    /**
     * Gets and displays ID of the system timezone
     * Displays selected customer from customer view
     * Displays associated appointments for selected customer
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeZoneLabel.setText(TimeZone.getDefault().getID());

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
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

    }
}
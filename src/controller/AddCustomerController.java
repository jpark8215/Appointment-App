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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Add customer controller class
 */
public class AddCustomerController implements Initializable {

    /**
     * Generates random number for customer
     * Checks existing customer numbers to avoid duplicates
     */
    Random random = new Random();
    int customerId;

    private int assignCustomerId() {
        int randomCustomerId;
        randomCustomerId = random.nextInt(1000);

        for (Customer customer : DBAccess.getAllCustomers() ) {
            if (customerId == randomCustomerId) {
                boolean isMatch = false;
                assignCustomerId();
            }
        }
        return randomCustomerId;
    }

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
    private ComboBox<Country> countryComboBox;

    @FXML
    private ComboBox<Division> divisionComboBox;


    /**
     * @param event Cancels adding customer and returns to customer view
     */
    @FXML
    void CancelButtonHandler(ActionEvent event) throws IOException {

        int confirmed = JOptionPane.showConfirmDialog(null, "Cancel and return to customer view?", "EXIT", JOptionPane.YES_NO_OPTION);

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
     * @param event Adds to database
     */
    @FXML
    void saveButtonHandler(ActionEvent event) {

        String newCustomerName = nameField.getText();
        String newCustomerAddress = addressField.getText();
        String newCustomerPostalCode = postalCodeField.getText();
        String newCustomerPhone = phoneField.getText();

        try {
            if (newCustomerName.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Name is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newCustomerAddress.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Address is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newCustomerPostalCode.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Zip Code is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newCustomerPhone.isBlank()) {
                JOptionPane.showMessageDialog(null, "Customer Phone is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (countryComboBox.getSelectionModel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Country is not selected.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (divisionComboBox.getSelectionModel().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Division is not selected.", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save and return to Customer?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if ( confirmed == JOptionPane.YES_OPTION) {

                    int newCustomerId = Integer.parseInt(customerIdField.getText());
                    int newCustomerDivisionId =  divisionComboBox.getValue().getDivisionId();
                    String newCustomerDivision = divisionComboBox.getValue().getDivision();
                    int newCustomerCountryId = divisionComboBox.getValue().getCountryId();
                    String newCustomerCountry = String.valueOf(countryComboBox.getValue());

                    Customer newCustomer = new Customer(newCustomerId, newCustomerName, newCustomerAddress, newCustomerPostalCode, newCustomerDivisionId, newCustomerDivision, newCustomerCountryId, newCustomerCountry, newCustomerPhone);
                    DBAccess.addCustomer(newCustomer);

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
     * @param event Gets filtered division list based on user's country choice
     */
    @FXML
    public void userCountryChoice(ActionEvent event) {

        divisionComboBox.setItems(DBAccess.getFilteredDivisions(countryComboBox.getValue()));
    }


    /**
     * Assigns customerID
     * Sets country and division combo boxes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customerId = assignCustomerId();
        customerIdField.setText(Integer.toString(customerId));
        customerIdField.setEditable(false);

        countryComboBox.setItems(DBAccess.getAllCountries());
        countryComboBox.setPromptText("Select Country");

//        divisionComboBox.setItems(DBAccess.getAllDivisions());
        divisionComboBox.setPromptText("Select Country First");
    }
}

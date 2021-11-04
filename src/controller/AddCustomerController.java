package controller;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    /**
     * Generate a random number for customer.
     * Check existing customer numbers to confirm for duplicates.
     * @return Random customer ID.
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
    private ChoiceBox<User> userBox;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private ComboBox<Division> divisionComboBox;

    @FXML
    void CancelButtonHandler(ActionEvent event) throws IOException {
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
        int newCustomerId = Integer.parseInt(customerIdField.getText());
        String newCustomerName = nameField.getText();
        String newCustomerAddress = addressField.getText();
        String newCustomerPostalCode = postalCodeField.getText();
        int newCustomerDivisionId =  divisionComboBox.getValue().getDivisionId();
        String newCustomerDivision = divisionComboBox.getValue().getDivision();
        int newCustomerCountryId = divisionComboBox.getValue().getCountryId();
        String newCustomerCountry = String.valueOf(countryComboBox.getValue());
        String newCustomerPhone = phoneField.getText();


        try {
            if (newCustomerName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Name is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newCustomerAddress.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Address is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newCustomerPostalCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Zip Code is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (newCustomerPhone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Customer Phone is empty.", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save and return to Customer?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if ( confirmed == JOptionPane.YES_OPTION) {

                    Customer newCustomer = new Customer(newCustomerId, newCustomerName, newCustomerAddress, newCustomerPostalCode, newCustomerDivisionId, newCustomerDivision, newCustomerCountryId, newCustomerCountry, newCustomerPhone);
                    DBAccess.addCustomer(newCustomer);

                    Division newCustomerDiv = new Division(newCustomerDivisionId, newCustomerDivision, newCustomerCountryId);
                    divisionComboBox.getItems().add(newCustomerDiv);


;

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

    @FXML
    public void userCountryChoice(ActionEvent event) {

        divisionComboBox.setItems(DBAccess.getFilteredDivisions(countryComboBox.getValue()));

    }


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

package controller;

import database.DBAccess;
import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Divisions;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

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

        for (Customers customers : DBAccess.getAllCustomers() ) {
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
    private ChoiceBox<?> userBox;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private ComboBox<Divisions> divisionComboBox;

    @FXML
    void CancelButtonHandler(ActionEvent event) throws IOException {
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
        int newCustomerId = Integer.parseInt(customerIdField.getText());
        String newCustomerName = nameField.getText();
        String newCustomerAddress = addressField.getText();
        String newCustomerPostalCode = postalCodeField.getText();
        String newCustomerPhone = phoneField.getText();
        ComboBox<Countries> countryComboBox = this.countryComboBox;
        ComboBox<Divisions> divisionsComboBox = this.divisionComboBox;

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
                    Customers newCustomer = new Customers(newCustomerId, newCustomerName, newCustomerAddress, newCustomerPostalCode, newCustomerPhone);
                    Customers.addCustomer(newCustomer);
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void initializeDivision() {
//
//        try {
//            Countries country = countryComboBox.getValue();
//
//            String sql = "SELECT division.division "
//                    + "FROM division, country "
//                    + "WHERE division.countryId = country.countryId "
//                    + "AND country.country = \"" + country + "\"";
//
//            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            divisionComboBox.getItems().clear();
//            while (rs.next()) {
//
//                divisionComboBox.getItems().add(1, divisionComboBox.getValue());
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    private void initializeCountry() {
//
//        try {
//            String sql = "SELECT country FROM country";
//            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//
//                countryComboBox.getItems().add(1, countryComboBox.getValue());
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerId = assignCustomerId();
        customerIdField.setText(Integer.toString(customerId));
        customerIdField.setEditable(false);

        countryComboBox.setItems(DBAccess.getAllCountries());
        countryComboBox.setPromptText("Select Country");

//        divisionComboBox.setItems(DBAccess.getAllDivisions());
//        divisionComboBox.setPromptText("Select Division");

    }
}

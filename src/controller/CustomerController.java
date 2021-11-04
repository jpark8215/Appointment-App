package controller;


import database.DBAccess;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {



    @FXML
    private Button returnButton;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, String> customerAddressCol;

    @FXML
    private TableColumn<Customer, String> customerPostalCodeCol;

    @FXML
    private TableColumn<Customer, Division> customerDivisionCol;

    @FXML
    private TableColumn<Customer, Country> customerCountryCol;

    @FXML
    private TableColumn<Customer, String> customerPhoneCol;




    @FXML
    void addAppointmentHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addAppointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void addCustomerHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addCustomer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void deleteCustomerHandler(ActionEvent event) {
//        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
//
//        if (selectedProduct == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setContentText("Please select a product.");
//            Optional<ButtonType> result = alert.showAndWait();
//        }
//
//        else if (!selectedProduct.getAllAssociatedPart().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setContentText("Product has associated part.");
//            Optional<ButtonType> result = alert.showAndWait();
//        }
//
//        else {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("CONFIRMATION");
//            alert.setContentText("Do you want to delete the selected product?");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.isPresent() && result.get() == ButtonType.OK) {
//                Inventory.deleteProduct(selectedProduct);
//
//            }
//        }
    }




    private static Customer selectedCustomer;
    public static Customer getSelectedCustomer() { return selectedCustomer; }

    @FXML
    void updateCustomerHandler(ActionEvent event) throws IOException {

        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(null, "Please select a customer.", "Error", JOptionPane.ERROR_MESSAGE);

        }else {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateCustomer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }



    @FXML
    void returnButtonHandler(ActionEvent event) throws IOException {

        int confirmed = JOptionPane.showConfirmDialog(null, "Return to Appointment View?", "EXIT", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerTable.setItems(DBAccess.getAllCustomers());

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
//        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("customerDivision")); ;
//        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));
        customerDivisionCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerDivision());
        });
        customerCountryCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerCountry());
        });



    }
}


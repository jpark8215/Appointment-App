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

/**
 * Customer controller class
 */
public class CustomerController implements Initializable {

    @FXML
    private Button appointmentButton;

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


    /**
     * @param event Opens add appointment view
     */
    @FXML
    void appointmentButtonHandler(ActionEvent event) throws IOException {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }


    /**
     * @param event Opens add customer view
     */
    @FXML
    void addCustomerHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addCustomer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    private static Customer selectedCustomer;

    public static Customer getSelectedCustomer() { return selectedCustomer; }

    /**
     * @param event Checks associated appointments for selected customer
     *              Deletes selected customer from database
     */
    @FXML
    void deleteCustomerHandler(ActionEvent event) throws IOException {

        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(null, "Please select a customer.", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (!((DBAccess.getAssociatedAppointments(selectedCustomer.getCustomerId())).isEmpty())) {

            int confirmed = JOptionPane.showConfirmDialog(null, "Customer has associated appointment(s). \n The appointment(s) will also be deleted. \n Do you want to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);
            {
                if (confirmed == JOptionPane.YES_OPTION) {

                    DBAccess.deleteCustomer(selectedCustomer);
                    customerTable.setItems(DBAccess.getAllCustomers());

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();

                }
            }
        } else if ((DBAccess.getAssociatedAppointments(selectedCustomer.getCustomerId())).isEmpty()) {

            int confirmed = JOptionPane.showConfirmDialog(null, "Please confirm to delete the selected customer. \n Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
            {
                if (confirmed == JOptionPane.YES_OPTION) {

                    DBAccess.deleteCustomer(selectedCustomer);
                    customerTable.setItems(DBAccess.getAllCustomers());

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }

        else {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }


    /**
     * @param event Gets selected customer data to load in customer update view
     *              Opens update customer view when button is selected
     */
    @FXML
    void updateCustomerHandler(ActionEvent event) throws IOException {

        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            JOptionPane.showMessageDialog(null, "Please select a customer.", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateCustomer.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


//    /**
//     * Returns to appointment view when button is selected
//     */
//    @FXML
//    void returnButtonHandler(ActionEvent event) throws IOException {
//
//        int confirmed = JOptionPane.showConfirmDialog(null, "Return to Appointment View?", "EXIT", JOptionPane.YES_NO_OPTION);
//        if (confirmed == JOptionPane.YES_OPTION) {
//            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
//            stage.setScene(new Scene(scene));
//            stage.show();
//        }
//
//    }


    /**
     * @param event Opens report view
     */
    @FXML
    void reportButtonHandle(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/report.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }




    /**
     * *****LAMBDA SetCellValueFactory specifies how to populate all cells in tableview columns.
     *             Using PropertyValueFactory requires getting specified property by looking for property with certain name.
     *             Property is passed directly using LAMBDA.
     * Gets customer data from database and displays on customer table
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customerTable.setItems(DBAccess.getAllCustomers());

//        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
//        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
//        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
//        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));
//        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("customerCountry"));

        customerIdCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerId()); });
        customerNameCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerName()); });
        customerAddressCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerAddress()); });
        customerPostalCodeCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerPostalCode()); });
        customerPhoneCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerPhone()); });
        customerDivisionCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerDivision()); });
        customerCountryCol.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerCountry()); });

    }
}


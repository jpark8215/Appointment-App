package controller;

import database.DBAccess;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Appointment controller class
 */
public class AppointmentController implements Initializable {

    private static Appointment selectedAppointment;
    @FXML
    private Button ExitButton;
    @FXML
    private RadioButton weekAppointment;
    @FXML
    private ToggleGroup appointmentView;
    @FXML
    private RadioButton monthAppointment;
    @FXML
    private RadioButton defaultAppointment;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactNameCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;
    @FXML
    private TableColumn<Appointment, Integer> userIdCol;
    @FXML
    private Button customerButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button updateAppointmentButton;

    public static Appointment getSelectedAppointment() { return selectedAppointment; }

    /**
     * @param event Opens add appointment view
     */
    @FXML
    void addAppointmentButtonHandler(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addAppointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param event Gets selected appointment data and loads in update appointment view
     *              Opens update appointment view
     */
    @FXML
    void updateAppointmentButtonHandler (ActionEvent event) throws IOException {

        selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

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
     * @param event Exits application
     */
    @FXML
    void ExitHandler(ActionEvent event) {
        int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?", "EXIT", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    /**
     * @param event Opens customer view
     */
    @FXML
    void customerButtonHandler(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customer.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * @param event Deletes selected appointment
     *              Displays appointmentId and type of deleted appointment
     */
    @FXML
    void deleteButtonHandler(ActionEvent event) throws IOException {

        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            JOptionPane.showMessageDialog(null, "Please select an appointment.", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to delete the appointment?", "Confirmation", JOptionPane.YES_NO_OPTION);

            {
                if (confirmed == JOptionPane.YES_OPTION) {
//                if (alert.getResult() == ButtonType.YES) {
                    DBAccess.deleteAppointment(selectedAppointment);
                    appointmentTable.setItems(DBAccess.getAllAppointments());

//                    Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cancel Confirmation");
//                        alert.setHeaderText("Do you want to delete the following appointment?");
                    alert.setHeaderText( "Appointment ID : " + selectedAppointment.getAppointmentId() + " & Type : " + selectedAppointment.getType() + " deleted");
                    alert.showAndWait();
//                    });

                } else {

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/updateCustomer.fxml")));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        }
    }


    /**
     * @param event Opens main appointment view
     */
    @FXML
    void defaultAppointmentView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * @param event Opens month view
     */
    @FXML
    void monthAppointmentView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/month.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * @param event Opens week view
     */
    @FXML
    void weekAppointmentView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/week.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * *****LAMBDA Platform.runLater() used it prevents the app being unresponsive when there are many appointments to compare and load.
     *             Checks appointments in database if there is an appointment within 15 minutes
     * Gets appointment data from database and loads data into appointment table
     * Sets combo box on contact column in table
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            if (DBAccess.appointmentInFifteenID() != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment in 15 minutes!");
                alert.setHeaderText("Appointment # " + DBAccess.appointmentInFifteenID() + ", Starting at " + DBAccess.appointmentInFifteenTime());
                alert.showAndWait();

            } else {
                JOptionPane.showMessageDialog(null, "You don't have an appointment soon.", "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        appointmentTable.setItems(DBAccess.getAllAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime")) ;
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        appointmentTable.setEditable(true);
        contactNameCol.setCellFactory(ComboBoxTableCell.forTableColumn());
        contactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));

    }

}

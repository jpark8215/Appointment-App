package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

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
    private TableView<Appointment> appointmentTableView;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private TableColumn<Appointment, Integer> contactIdCol;

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
    void ExitHandler(ActionEvent event) {
        int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?", "EXIT", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @FXML
    void customerButtonHandler(ActionEvent event) throws IOException {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    @FXML
    void defaultAppointmentView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void monthAppointmentView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/month.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void weekAppointmentView(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/week.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
//        titleCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
//        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
//        locationCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
//        contactIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactId"));
//        typeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
//        startCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("startTime"));
//        endCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("endTime"));
//        customerIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
//        userIdCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
//
//        appointmentTableView.setItems(DBAccess.getAllAppointments());

    }
}

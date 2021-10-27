package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class AppointmentController {

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
    private TableView<?> appointmentTableView;

    @FXML
    private TableColumn<?, ?> AppointmentIdCol;

    @FXML
    private TableColumn<?, ?> TitleCol;

    @FXML
    private TableColumn<?, ?> DescriptionCol;

    @FXML
    private TableColumn<?, ?> LocationCol;

    @FXML
    private TableColumn<?, ?> ContactCol;

    @FXML
    private TableColumn<?, ?> TypeCol;

    @FXML
    private TableColumn<?, ?> StartCol;

    @FXML
    private TableColumn<?, ?> EndCol;

    @FXML
    private TableColumn<?, ?> CustomerIdCol;

    @FXML
    private TableColumn<?, ?> UserIdCol;

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
    void defaultAppointmentView(ActionEvent event) {

    }

    @FXML
    void monthAppointmentView(ActionEvent event) {

    }

    @FXML
    void weekAppointmentView(ActionEvent event) {

    }

}

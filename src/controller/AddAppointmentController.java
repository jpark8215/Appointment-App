package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAppointmentController {

    @FXML
    private TextField AppointmentIdField;

    @FXML
    private Button CancelButton;

    @FXML
    private TextField CustomerIdField;

    @FXML
    private DatePicker EndDateField;

    @FXML
    private TextField EndHourField;

    @FXML
    private TextField EndMinuteField;

    @FXML
    private Button SaveButton;

    @FXML
    private DatePicker StartDateField;

    @FXML
    private TextField StartHourField;

    @FXML
    private TextField StartMinuteField;

    @FXML
    private Label TimeZoneLabel;

    @FXML
    private TextField TitleField;

    @FXML
    private TextField TypeField;

    @FXML
    private ChoiceBox<?> UserBox;

    @FXML
    void CancelHandler(ActionEvent event) {

    }

    @FXML
    void SaveHandler(ActionEvent event) {

    }

}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class WeekController {

    @FXML
    private Label WeekMonth;

    @FXML
    private Label WeekYear;

    @FXML
    private RadioButton weekAppointment;

    @FXML
    private ToggleGroup appointmentView;

    @FXML
    private RadioButton monthAppointment;

    @FXML
    private RadioButton defaultAppointment;

    @FXML
    private Button customerButton;

    @FXML
    private GridPane WeekGridPane;

    @FXML
    private AnchorPane AnchorPane00;

    @FXML
    private Label Label00;

    @FXML
    private ListView<?> ListView00;

    @FXML
    private AnchorPane AnchorPane10;

    @FXML
    private Label Label10;

    @FXML
    private ListView<?> ListView10;

    @FXML
    private AnchorPane AnchorPane20;

    @FXML
    private Label Label20;

    @FXML
    private ListView<?> ListView20;

    @FXML
    private AnchorPane AnchorPane30;

    @FXML
    private Label Label30;

    @FXML
    private ListView<?> ListView30;

    @FXML
    private AnchorPane AnchorPane40;

    @FXML
    private Label Label40;

    @FXML
    private ListView<?> ListView40;

    @FXML
    private AnchorPane AnchorPane50;

    @FXML
    private Label Label50;

    @FXML
    private ListView<?> ListView50;

    @FXML
    private AnchorPane AnchorPane60;

    @FXML
    private Label Label60;

    @FXML
    private ListView<?> ListView60;

    @FXML
    private Button Week1Button;

    @FXML
    private Button Week2Button;

    @FXML
    private Button Week3Button;

    @FXML
    private Button Week4Button;

    @FXML
    private Button Week5Button;

    @FXML
    private Label TimeZoneLabel;

    @FXML
    void customerButtonHandler(ActionEvent event) throws IOException {

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/view/customer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

        @FXML
        void Week1Handler (ActionEvent event){

        }

        @FXML
        void Week2Handler (ActionEvent event){

        }

        @FXML
        void Week3Handler (ActionEvent event){

        }

        @FXML
        void Week4Handler (ActionEvent event){

        }

        @FXML
        void Week5Handler (ActionEvent event){

        }

        @FXML
        void defaultAppointmentView (ActionEvent event) throws IOException {

        }

        @FXML
        void monthAppointmentView (ActionEvent event) throws IOException {

        }

        @FXML
        void weekAppointmentView (ActionEvent event) throws IOException {

        }

    }



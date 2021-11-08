package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MonthController {

    @FXML
    private Button CustomerButton;

    @FXML
    private Label Month;

    @FXML
    private Label Year;

    @FXML
    private RadioButton weekAppointment;

    @FXML
    private ToggleGroup appointmentView;

    @FXML
    private RadioButton monthAppointment;

    @FXML
    private RadioButton defaultAppointment;

    @FXML
    private GridPane MonthGridPane;

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
    private AnchorPane AnchorPane01;

    @FXML
    private Label Label01;

    @FXML
    private ListView<?> ListView01;

    @FXML
    private AnchorPane AnchorPane11;

    @FXML
    private Label Label11;

    @FXML
    private ListView<?> ListView11;

    @FXML
    private AnchorPane AnchorPane21;

    @FXML
    private Label Label21;

    @FXML
    private ListView<?> ListView21;

    @FXML
    private AnchorPane AnchorPane31;

    @FXML
    private Label Label31;

    @FXML
    private ListView<?> ListView31;

    @FXML
    private AnchorPane AnchorPane41;

    @FXML
    private Label Label41;

    @FXML
    private ListView<?> ListView41;

    @FXML
    private AnchorPane AnchorPane51;

    @FXML
    private Label Label51;

    @FXML
    private ListView<?> ListView51;

    @FXML
    private AnchorPane AnchorPane61;

    @FXML
    private Label Label61;

    @FXML
    private ListView<?> ListView61;

    @FXML
    private AnchorPane AnchorPane02;

    @FXML
    private Label Label02;

    @FXML
    private ListView<?> ListView02;

    @FXML
    private AnchorPane AnchorPane12;

    @FXML
    private Label Label12;

    @FXML
    private ListView<?> ListView12;

    @FXML
    private AnchorPane AnchorPane22;

    @FXML
    private Label Label22;

    @FXML
    private ListView<?> ListView22;

    @FXML
    private AnchorPane AnchorPane32;

    @FXML
    private Label Label32;

    @FXML
    private ListView<?> ListView32;

    @FXML
    private AnchorPane AnchorPane42;

    @FXML
    private Label Label42;

    @FXML
    private ListView<?> ListView42;

    @FXML
    private AnchorPane AnchorPane52;

    @FXML
    private Label Label52;

    @FXML
    private ListView<?> ListView52;

    @FXML
    private AnchorPane AnchorPane62;

    @FXML
    private Label Label62;

    @FXML
    private ListView<?> ListView62;

    @FXML
    private AnchorPane AnchorPane03;

    @FXML
    private Label Label03;

    @FXML
    private ListView<?> ListView03;

    @FXML
    private AnchorPane AnchorPane13;

    @FXML
    private Label Label13;

    @FXML
    private ListView<?> ListView13;

    @FXML
    private AnchorPane AnchorPane23;

    @FXML
    private Label Label23;

    @FXML
    private ListView<?> ListView23;

    @FXML
    private AnchorPane AnchorPane33;

    @FXML
    private Label Label33;

    @FXML
    private ListView<?> ListView33;

    @FXML
    private AnchorPane AnchorPane43;

    @FXML
    private Label Label43;

    @FXML
    private ListView<?> ListView43;

    @FXML
    private AnchorPane AnchorPane53;

    @FXML
    private Label Label53;

    @FXML
    private ListView<?> ListView53;

    @FXML
    private AnchorPane AnchorPane63;

    @FXML
    private Label Label63;

    @FXML
    private ListView<?> ListView63;

    @FXML
    private AnchorPane AnchorPane04;

    @FXML
    private Label Label04;

    @FXML
    private ListView<?> ListView04;

    @FXML
    private AnchorPane AnchorPane14;

    @FXML
    private Label Label14;

    @FXML
    private ListView<?> ListView14;

    @FXML
    private AnchorPane AnchorPane24;

    @FXML
    private Label Label24;

    @FXML
    private ListView<?> ListView24;

    @FXML
    private AnchorPane AnchorPane34;

    @FXML
    private Label Label34;

    @FXML
    private ListView<?> ListView34;

    @FXML
    private AnchorPane AnchorPane44;

    @FXML
    private Label Label44;

    @FXML
    private ListView<?> ListView44;

    @FXML
    private AnchorPane AnchorPane54;

    @FXML
    private Label Label54;

    @FXML
    private ListView<?> ListView54;

    @FXML
    private AnchorPane AnchorPane64;

    @FXML
    private Label Label64;

    @FXML
    private ListView<?> ListView64;

    @FXML
    private GridPane WeekGridPane;

    @FXML
    private Label TimeZoneLabelMonth;

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


}




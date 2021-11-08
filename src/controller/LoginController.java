package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Login controller class
 */
public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Label timeZone;

    //   private JOptionPane AOptionPane;

    /**
     * @param event Checks login ID and password, and open appointment view when processed.
     */
    @FXML
    void loginHandler(ActionEvent event) throws IOException {

        if (username.getText().equals("test") && password.getText().equals("test")) {
//        if (username.getText().equals("admin") && password.getText().equals("admin")) {


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();

        } else {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                JOptionPane.showMessageDialog(null, "Le nom d’utilisateur et le mot de passe ne correspondaient pas!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Username and password did not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Displays translated login page based on system locale
     * Displays system location
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Locale.setDefault(new Locale("fr"));

        try {
            ResourceBundle rb = ResourceBundle.getBundle("login", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                username.setPromptText(rb.getString("Username"));
                password.setPromptText(rb.getString("Password"));
                loginButton.setText(rb.getString("LoginButton"));

            } else {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    JOptionPane.showMessageDialog(null, "Aucune langue appropriée n'a été trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No appropriate language was found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            timeZone.setText(String.valueOf(ZoneId.systemDefault()));

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField Password;

    @FXML
    private TextField Username;

    @FXML
    private Label TimeZone;

    private JOptionPane AOptionPane;

    /**
     * @param event Check login ID and password, and open calendar view when processed.
     */
    @FXML
    void loginHandler(ActionEvent event) throws IOException {

        if (Username.getText().equals("test") && Password.getText().equals("test")) {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/appointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } else {
            if(Locale.getDefault().getLanguage().equals("fr")) {
                JOptionPane.showMessageDialog(null, "Le nom d’utilisateur et le mot de passe ne correspondaient pas!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Username and password did not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Locale.setDefault(new Locale("fr"));

            //Display login form in English and French
            try {
                ResourceBundle rb = ResourceBundle.getBundle("login", Locale.getDefault());

                if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                    Username.setPromptText(rb.getString("Username"));
                    Password.setPromptText(rb.getString("Password"));
                    LoginButton.setText(rb.getString("LoginButton"));

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


            //Determine User Location and display in label
            try {
                TimeZone.setText(String.valueOf(ZoneId.systemDefault()));

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

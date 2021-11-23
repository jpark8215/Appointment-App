package controller;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Login controller class
 */
public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label timeZone;


    /**
     * @param event Checks login ID and password and opens appointment view
     *              Logs login attempts in log.txt
     */
    @FXML
    void loginHandler(ActionEvent event) {

        int currentUserId = 0;
        String username = usernameField.getText();
        String password = passwordField.getText();
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fileHandler = new FileHandler("login_activity.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            log.addHandler(fileHandler);

            String sql = "select * FROM users WHERE User_Name  = '" + username + "'" + " And Password  = '" + password + "'";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

//            ps.setString(1, username);
//            ps.setString(2, password);

            if (rs.next()) {
                ObservableList<User> currentUserList = FXCollections.observableArrayList();
                User currentUser = new User(currentUserId, username, password);
                currentUser.setUserId(rs.getInt("User_ID"));
                currentUser.setUserName(rs.getString("User_Name"));
                currentUser.setUserPassword(rs.getString("Password"));
                currentUserList.add(currentUser);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                log.info("User '" + usernameField.getText() + "' login was successful. " + LocalDateTime.now().format(formatter));

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
                stage.setScene(new Scene(scene));
                stage.show();

            } else {

                if (Locale.getDefault().getLanguage().equals("fr")) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    log.info("User '" + usernameField.getText() + "' login failed. " + LocalDateTime.now().format(formatter));

                    JOptionPane.showMessageDialog(null, "Le nom d’utilisateur et le mot de passe ne correspondaient pas!", "Erreur", JOptionPane.ERROR_MESSAGE);

                } else {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    log.info("User '" + usernameField.getText() + "' login failed. " + LocalDateTime.now().format(formatter));
                    JOptionPane.showMessageDialog(null, "Username and password did not match!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
//    void loginHandler(ActionEvent event) throws IOException {
//
//        if (username.getText().equals("test") && password.getText().equals("test")) {
//
//            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointment.fxml")));
//            stage.setScene(new Scene(scene));
//            stage.show();
//
//        } else {
//            if (Locale.getDefault().getLanguage().equals("fr")) {
//                JOptionPane.showMessageDialog(null, "Le nom d’utilisateur et le mot de passe ne correspondaient pas!", "Erreur", JOptionPane.ERROR_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(null, "Username and password did not match!", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }


    /**
     * Displays system timezone and translated login page based on system locale
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        Locale.setDefault(new Locale("fr"));

        try {
            ResourceBundle rb = ResourceBundle.getBundle("login", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                usernameField.setPromptText(rb.getString("Username"));
                passwordField.setPromptText(rb.getString("Password"));
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

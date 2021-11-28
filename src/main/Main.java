/**
 * @author Jieun Park
 */

package main;

import controller.LoginController;
import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main class
 */
public class Main extends Application {
    /**
     * @param primaryStage Main stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        primaryStage.setTitle("C195 Software 2");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

    /**
     * @param args Creates login_activity.txt file
     *             Makes and closes connections
     */
    public static void main(String[] args) throws Exception {
        Logger log = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fileHandler = new FileHandler("login_activity.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            log.addHandler(fileHandler);


        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.WARNING, "Login attempt failed.", ex);
        }


        DBConnection.makeConnection();
//        DBAccess.checkDateConversion();
        launch(args);
        DBConnection.closeConnection();

    }
}

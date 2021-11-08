/**
 * @author Jieun Park
 */

package main;

import database.DBConnection;
import database.DBAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main class
 */
public class Main extends Application {
    @Override

    public void start (Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        primaryStage.setTitle("C195 Software 2");
        primaryStage.setScene(new Scene(root, 800,400));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {


        DBConnection.makeConnection();

        DBAccess.checkDateConversion();
        launch(args);
        DBConnection.closeConnection();
    }
}

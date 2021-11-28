package controller;

import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Report controller class
 */
public class ReportController {

    @FXML
    private Button report1;

    @FXML
    private Button report2;

    @FXML
    private Button report3;

    @FXML
    private TextArea textArea;


    /**
     * @return Returns number of appointments sorted by month
     */
    public String reportByMonth() throws SQLException {

        Connection connection = DBConnection.getConnection();

        try {

            StringBuilder monthReportText = new StringBuilder();
            monthReportText.append("Month / Year | # of Appointments  \n______________________________________________________________________\n");

            ResultSet rs = connection.createStatement().executeQuery(
                    "SELECT MONTH (`Start`) as `Month`, YEAR (`Start`) as `Year`, COUNT(*) as `Count` " +
                            "FROM appointments GROUP BY MONTH(`Start`) " +
                            "ORDER BY MONTH(`Start`); ");

            while (rs.next()) {
                monthReportText.append(rs.getString("Month") + " / " + rs.getString("Year") + " : " + rs.getString("Count") + "\n");
            }

            return monthReportText.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Report1 Error";
        }
    }


    /**
     * @return Returns number of appointments sorted by type
     */
    public String reportByType() throws SQLException {

        Connection connection = DBConnection.getConnection();

        try {

            StringBuilder typeReportText = new StringBuilder();
            typeReportText.append("Type | # of Appointments  \n______________________________________________________________________\n");

            ResultSet rs = connection.createStatement().executeQuery(
                    "SELECT `Type`, COUNT(*) AS `Count` " +
                            "FROM appointments GROUP BY `Type` " +
                            "ORDER BY `Type`");

            while (rs.next()) {
                typeReportText.append(rs.getString("Type") + " : " + rs.getString("Count") + "\n");
            }

            return typeReportText.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Report1 Error";
        }
    }


    /**
     * Displays report by month and type
     */
    @FXML
    public void runReportOneHandle() throws SQLException {
        textArea.clear();
        textArea.setText(reportByMonth() + " \n" + reportByType());
//        textArea.setText(reportByType());
    }


    /**
     * Changes UTC to local time
     * @return Returns appointment info per contact
     */
    public String reportForContact() throws SQLException {
        Connection connection = DBConnection.getConnection();
        try {

            StringBuilder contactReportText = new StringBuilder();

            ResultSet rs = connection.createStatement().executeQuery("SELECT Contact_name, Appointment_ID, Title, Type, Description, Start, End, Customer_Id "
                    + "FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID GROUP BY Contact_Name; ");

            while (rs.next()) {
                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime startDateTime = startZone.toLocalDateTime();
                LocalDateTime endLocal = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime endUTC = endLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime endZone = endUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime endDateTime = endZone.toLocalDateTime();

                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                int customerId = rs.getInt("Customer_ID");
                String contactName = rs.getString("Contact_Name");

                contactReportText.append("Contact " + contactName + "'s Schedule\n_______________________________________________________________________________________________\n" +
                        "Appointment Info\n_______________________________________________________________________________________________\n");

                contactReportText.append( "Appt ID : " + appointmentId + "\n"
                        + "Title : " + title  + "\n"
                        + "Type : " + type  + "\n"
                        + "Description : " + description + "\n"
                        + "Start : " + startDateTime + "\n"
                        + "End : " + endDateTime + "\n"
                        + "Customer ID : " + customerId +  "\n\n\n");
            }

            return contactReportText.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Report2 Error";
        }
    }


    /**
     * Displays report per contact
     */
    @FXML
    void runReportTwoHandle(ActionEvent event) throws SQLException {
        textArea.clear();
        textArea.setText(reportForContact());

    }


    /**
     * Changed UTc to local time
     * @return Returns appointment info per user
     */
    public String reportForUser() throws SQLException {
        Connection connection = DBConnection.getConnection();
        try {

            StringBuilder userReportText = new StringBuilder();

            ResultSet rs = connection.createStatement().executeQuery("SELECT User_name, Appointment_ID, Title, Type, Description, Start, End, Customer_Id "
                    + "FROM appointments AS a INNER JOIN users AS u ON a.User_ID = u.User_ID GROUP BY User_Name; ");

            while (rs.next()) {
                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime startDateTime = startZone.toLocalDateTime();

                LocalDateTime endLocal = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime endUTC = endLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime endZone = endUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime endDateTime = endZone.toLocalDateTime();

                int appointmentId = rs.getInt("Appointment_ID");
//                String title = rs.getString("Title");
//                String type = rs.getString("Type");
//                String description = rs.getString("Description");
//                int customerId = rs.getInt("Customer_ID");
                String username = rs.getString("User_Name");

                userReportText.append("Contact " + username + "'s Schedule\n_______________________________________________________________________________________________\n" +
                        "Appointment Info\n_______________________________________________________________________________________________\n");

                userReportText.append( "Appt ID : " + appointmentId + "\n"
//                        + "Title : " + title  + "\n"
//                        + "Type : " + type  + "\n"
//                        + "Description : " + description + "\n"
                        + "Start : " + startDateTime + "\n"
                        + "End : " + endDateTime + "\n"
//                        + "Customer ID : " + customerId
                        +  "\n\n\n");
            }

            return userReportText.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Report3 Error";
        }
    }


    /**
     * Displays report per user
     */
    @FXML
    void runReportThreeHandle(ActionEvent event) throws SQLException {
        textArea.clear();
        textArea.setText(reportForUser());

    }
}


package database;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;


/**
 * Database access class
 */
public class DBAccess {

    /**
     * Gets all customers from database
     *
     * @return customerObservableList
     */
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from customers" +
                    " JOIN first_level_divisions AS f ON customers.division_ID = f.division_ID" +
                    " JOIN countries ON f.country_ID = countries.country_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                int customerDivisionId = rs.getInt("Division_ID");
                String customerDivision = rs.getString("Division");
                int customerCountryId = rs.getInt("Country_ID");
                String customerCountry = rs.getString("Country");
                String customerPhone = rs.getString("Phone");

                Customer customer = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerDivisionId, customerDivision, customerCountryId, customerCountry, customerPhone);
                customerObservableList.add(customer);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return customerObservableList;
    }

    /**
     * Adds new customer to database
     */
    public static void addCustomer(Customer newCustomer) {

        try {
            String sql = "INSERT INTO customers " +
                    "( Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, newCustomer.getCustomerId());
            ps.setString(2, newCustomer.getCustomerName());
            ps.setString(3, newCustomer.getCustomerAddress());
            ps.setString(4, newCustomer.getCustomerPostalCode());
            ps.setString(5, newCustomer.getCustomerPhone());
            ps.setInt(6, newCustomer.getCustomerDivisionId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    /**
     * Updates customer to database
     */
    public static void updateCustomer(Customer updateCustomer) {

        try {
            String sql = "UPDATE customers " +
                    "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? " +
                    "WHERE Customer_ID = ? ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, updateCustomer.getCustomerName());
            ps.setString(2, updateCustomer.getCustomerAddress());
            ps.setString(3, updateCustomer.getCustomerPostalCode());
            ps.setString(4, updateCustomer.getCustomerPhone());
            ps.setInt(5, updateCustomer.getCustomerDivisionId());
            ps.setInt(6, updateCustomer.getCustomerId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    /**
     * Deletes selected customer from database
     */
    public static void deleteCustomer(Customer selectedCustomer) {
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, selectedCustomer.getCustomerId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


    /**
     * Gets all countries from database
     *
     * @return countryObservableList
     */
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countryObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * From countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country country = new Country(countryId, countryName);
                countryObservableList.add(country);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return countryObservableList;
    }


    /**
     * Gets country ID from the country selected in combo box
     *
     * @return country ID
     */
    public static int getCountryId(ComboBox<Country> userCountry) {

        try {
            String sql = "Select Country_ID From countries " +
                    "WHERE Country_Name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(userCountry));
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return 0;
    }


    /**
     * Gets all divisions from database
     *
     * @return divisionObservableList
     */
    public static ObservableList<Division> getAllDivisions() {
        ObservableList<Division> divisionObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Division divisions = new Division(divisionId, division, countryId);
                divisionObservableList.add(divisions);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return divisionObservableList;
    }


    /**
     * Gets filtered Divisions by user's country choice
     *
     * @return filteredDivisionObservableList
     */
    public static ObservableList<Division> getFilteredDivisions(Country userChoice) {
        ObservableList<Division> filteredDivisionObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                if (userChoice.getCountryId() == countryId) {

                    Division filteredDivision = new Division(divisionId, division, countryId);
                    filteredDivisionObservableList.add(filteredDivision);

                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return filteredDivisionObservableList;
    }


    /**
     * Gets division ID from the division selected in combo box
     *
     * @return division ID
     */
    public static int getDivisionId(ComboBox<Division> userDivision) {

        try {
            String sql = "Select Division_ID From first_level_divisions " +
                    "WHERE Division_Name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(userDivision));
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return 0;
    }


    /**
     * Gets all appointments from database
     *
     * @return appointmentObservableList
     */

    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments " +
                    "JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");

                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime startDateTime = startZone.toLocalDateTime();

                LocalDateTime endLocal = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime endUTC = endLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime endZone = endUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime endDateTime = endZone.toLocalDateTime();

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, contactName, type, startDateTime, endDateTime, customerId, userId);
                appointmentObservableList.add(appointment);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return appointmentObservableList;
    }


    /**
     * Gets associated appointments with customer
     *
     * @return associatedAppointmentObservableList
     */

    public static ObservableList<Appointment> getAssociatedAppointments(int userChoice) {

        ObservableList<Appointment> associatedAppointmentObservableList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from appointments " +
                    "JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int appointmentId = rs.getInt("Appointment_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");

                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime startDateTime = startZone.toLocalDateTime();

                LocalDateTime endLocal = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime endUTC = endLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime endZone = endUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime endDateTime = endZone.toLocalDateTime();

                if (userChoice == customerId) {

                    Appointment associatedAppointment = new Appointment(appointmentId, title, description, location, contactId, contactName, type, startDateTime, endDateTime, customerId, userId);
                    associatedAppointmentObservableList.add(associatedAppointment);

                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return associatedAppointmentObservableList;
    }


    /**
     * Adds new appointment to database
     */
    public static void addAppointment(Appointment newAppointment) {

        try {
            String sql = "INSERT INTO appointments " +
                    "( Title , Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID ) " +
                    "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ); " ;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, newAppointment.getTitle());
            ps.setString(2, newAppointment.getDescription());
            ps.setString(3, newAppointment.getLocation());
            ps.setString(4, newAppointment.getType());
            ps.setString(5, String.valueOf(newAppointment.getStartDateTime()));
            ps.setString(6, String.valueOf(newAppointment.getEndDateTime()));
            ps.setInt(7, newAppointment.getCustomerId());
            ps.setInt(8, newAppointment.getUserId());
            ps.setInt(9, newAppointment.getContactId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates appointment to database
     */
    public static void updateAppointment(Appointment updateAppointment) {

        try {
            String sql = "UPDATE appointments " +
                    "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = ? ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, updateAppointment.getTitle());
            ps.setString(2, updateAppointment.getDescription());
            ps.setString(3, updateAppointment.getLocation());
            ps.setString(4, updateAppointment.getType());
            ps.setString(5, String.valueOf(updateAppointment.getStartDateTime()));
            ps.setString(6, String.valueOf(updateAppointment.getEndDateTime()));
            ps.setInt(7, updateAppointment.getCustomerId());
            ps.setInt(8, updateAppointment.getUserId());
            ps.setInt(9, updateAppointment.getContactId());
            ps.setInt(10, updateAppointment.getAppointmentId());
            System.out.println(updateAppointment.getAppointmentId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    /**
     * Deletes selected appointment from database
     */
    public static void deleteAppointment(Appointment selectedAppointment) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, selectedAppointment.getAppointmentId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Gets monthly appointments from database
     * @return monthlyAppointmentList
     */

    public static ObservableList<Appointment> getMonthlyAppointments()  {

//        LocalDate now = LocalDate.now();
//        LocalDate nowPlus1Month = now.plusMonths(1);
        int firstDay = Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH);
        int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate startMonth = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(), firstDay);
        LocalDate endMonth = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(), lastDay);

        ObservableList<Appointment> monthlyAppointmentList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments " +
                    "JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                    "WHERE Start BETWEEN ? AND ? ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(startMonth.minusDays(1)));
            ps.setString(2, String.valueOf(endMonth.plusDays(1)));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");

                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime startDateTime = startZone.toLocalDateTime();

                LocalDateTime endLocal = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime endUTC = endLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime endZone = endUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime endDateTime = endZone.toLocalDateTime();

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, contactName, type, startDateTime, endDateTime, customerId, userId);
                monthlyAppointmentList.add(appointment);

//                //filter appointments by week or month
//                filterAppointmentsByMonth(monthlyAppointmentList);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return monthlyAppointmentList;
    }


    /**
     * Gets weekly appointments from database
     * @return weeklyAppointmentList
     */

    public static ObservableList<Appointment> getWeeklyAppointments() {

//        LocalDate now = LocalDate.now();
//        LocalDate nowPlus1Week = now.plusWeeks(1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        LocalDate firstDay = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        LocalDate lastDay = firstDay.plusWeeks(1);

        ObservableList<Appointment> weeklyAppointmentList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments " +
                    "JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                    "WHERE Start BETWEEN ? AND ?  ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(firstDay.minusDays(1)));
            ps.setString(2, String.valueOf(lastDay));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");

                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime startDateTime = startZone.toLocalDateTime();

                LocalDateTime endLocal = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime endUTC = endLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime endZone = endUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime endDateTime = endZone.toLocalDateTime();

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, contactName, type, startDateTime, endDateTime, customerId, userId);
                weeklyAppointmentList.add(appointment);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return weeklyAppointmentList;
    }


    /**
     * Gets all users from database
     * @return userObservableList
     */

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> userObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Timestamp userCreateDate = rs.getTimestamp("Create_Date");
                String userCreatedBy = rs.getString("Created_By");
                Timestamp userUpdateDate = rs.getTimestamp("Last_Update");
                String userUpdatedBy = rs.getString("Last_Updated_By");

                User user = new User(userId, userName, userPassword, userCreateDate, userCreatedBy, userUpdateDate, userUpdatedBy);
                userObservableList.add(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return userObservableList;
    }


    /**
     * Gets all contacts from database
     *
     * @return contactObservableList
     */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("email");

                Contact contact = new Contact(contactId, contactName, email);
                contactObservableList.add(contact);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return contactObservableList;
    }


    /**
     * @param newContact Adds new contact to database
     */
    public static void addContact(Contact newContact) {
        try {
            String sql = "INSERT INTO contacts ( Contact_ID, Contact_Name) VALUES (?, ? )";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, newContact.getContactId());
            ps.setString(2, newContact.getContactName());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    /**
     * @param newContact Gets contact by contactName
     */
    public static void getContactId(Contact newContact) {
        try {
            String sql = "SELECT FROM contacts WHERE Contact_Name = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, newContact.getContactName());
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


    /**
     * Checks database for conflicting appointments for new appointment
     * @return Boolean result
     */
    public static boolean getConflictedAppointment (Appointment newAppointment) {

        try {
            String sql =  "SELECT * FROM appointments "
                    + "WHERE (( ? BETWEEN Start AND End ) OR ( ? BETWEEN Start AND End ) OR ( ? < Start AND ? > End)) "
                    + "AND ( User_ID = ? AND Appointment_ID != ? )";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(newAppointment.getStartDateTime()));
            ps.setTimestamp(2, Timestamp.valueOf(newAppointment.getEndDateTime()));
            ps.setTimestamp(3, Timestamp.valueOf(newAppointment.getStartDateTime()));
            ps.setTimestamp(4, Timestamp.valueOf(newAppointment.getEndDateTime()));
            ps.setInt(5, newAppointment.getUserId());
            ps.setInt(6, newAppointment.getAppointmentId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }


    /**
     //     * Checks if there are appointment within 15 minutes
     //     * @return Appointment ID
     //     */
    public static String  appointmentInFifteenID() {

        String appointmentId = null;

        try {
            String sql = "SELECT Appointment_ID " +
                    "FROM appointments AS a " +
                    "INNER JOIN users AS u ON a.User_ID = u.User_ID " +
                    "WHERE a.User_ID= ? AND a.Start BETWEEN ? AND ? ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, User.userId);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")).plusMinutes(15)));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                appointmentId = String.valueOf(rs.getInt("Appointment_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentId;
    }


    /**
     //     * Checks if there are appointment within 15 minutes
     //     * @return DateTime
     //     */
    public static String  appointmentInFifteenTime() {

        String startDateTime = null;

        try {
            String sql = "SELECT Start " +
                    "FROM appointments AS a " +
                    "INNER JOIN users AS u ON a.User_ID = u.User_ID " +
                    "WHERE a.User_ID= ? AND a.Start BETWEEN ? AND ? ";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, User.userId);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")).plusMinutes(15)));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                LocalDateTime startLocal = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime startUTC = startLocal.atZone(ZoneOffset.UTC);
                ZonedDateTime startZone = startUTC.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
                startDateTime = String.valueOf(startZone.toLocalDateTime());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return startDateTime;
    }
}

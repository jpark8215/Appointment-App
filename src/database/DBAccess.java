package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * Database access class
 */
public class DBAccess {

    /**
     *
     */
    public static void checkDateConversion() {
        System.out.println("Create Date Test");
        String sql = "select Create_Date from Countries";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CO: " + ts.toLocalDateTime().toString());
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Gets all customers from database
     * @return customerObservableList
     */
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from customers"
                    + " JOIN first_level_divisions AS f ON customers.division_ID = f.division_ID"
                    + " JOIN countries ON f.country_ID = countries.country_ID";
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
     * @return countryObservableList
     */
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countryObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from countries";
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
     * Gets all divisions from database
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
     * @return division ID
     */
    public static int getDivisionId(ComboBox<Division> userDivision) {
        try {
            String sql = "Select Division_ID From first_level_divisions "
                    + "WHERE Division_Name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(userDivision));
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    /**
     * Gets country ID from the country selected in combo box
     * @return country ID
     */
    public static int getCountryId(ComboBox<Country> userCountry) {
        try {
            String sql = "Select Country_ID From countries "
                    + "WHERE Country_Name = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(userCountry));
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    /**
     * Gets all appointments from database
     * @return appointmentObservableList
     */

    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getNString("Title");
                String description = rs.getNString("Description");
                String location = rs.getNString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getNString("Type");
                Timestamp startTime = rs.getTimestamp("Start");
                Timestamp endTime = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, type, startTime, endTime, customerId, userId);
                appointmentObservableList.add(appointment);

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return appointmentObservableList;
    }


    /**
     * Gets associated appointments with customer
     * @return associatedAppointmentObservableList
     */

    public static ObservableList<Appointment> getAssociatedAppointments(int userChoice) {

        ObservableList<Appointment> associatedAppointmentObservableList = FXCollections.observableArrayList();
        try {
            String sql = "Select * from appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getNString("Title");
                String description = rs.getNString("Description");
                String location = rs.getNString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getNString("Type");
                Timestamp startTime = rs.getTimestamp("Start");
                Timestamp endTime = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                if (userChoice == customerId) {

                    Appointment associatedAppointment = new Appointment(appointmentId, title, description, location, contactId, type, startTime, endTime, customerId, userId);
                    associatedAppointmentObservableList.add(associatedAppointment);
                }
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return associatedAppointmentObservableList;
    }


    /**
     * Deletes selected appointment from database
     */
    public static void deleteAppointment(Appointment selectedAppointment) {
        try {
            String sql = "DELETE FROM appointments WHERE Customer_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, selectedAppointment.getCustomerId());
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


    /**
     * Gets all users from database
     */

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> userObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from users";
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

    public static ObservableList <Contact> getAllContacts() {
        ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from contacts ";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");


                Contact contact = new Contact(contactId, contactName,email );
                contactObservableList.add(contact);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactObservableList;
    }
    }


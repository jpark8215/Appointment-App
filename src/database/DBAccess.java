package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAccess {


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

    public static void addCustomer(Customer newCustomer) {

        try {
            String sql = "INSERT INTO customers ( Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID ) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);


            ps.setInt(1, newCustomer.getCustomerId());
            ps.setString(2, newCustomer.getCustomerName());
            ps.setString(3, newCustomer.getCustomerAddress());
            ps.setString(4, newCustomer.getCustomerPostalCode());
            ps.setString(5, newCustomer.getCustomerPhone());
            ps.setInt(6,newCustomer.getCustomerDivisionId());
            ps.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


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



    public static ObservableList <Division> getFilteredDivisions(Country userChoice) {
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

        } return filteredDivisionObservableList;
    }


    public static int getDivisionId (ComboBox<Division> userDivision) {
        try{
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

    public static int getCountryId (ComboBox<Country> userCountry) {
        try{
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


    public static ObservableList <Appointment> getAllAppointments() {
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

        }return appointmentObservableList;
    }


}

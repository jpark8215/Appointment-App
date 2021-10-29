package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
            String sql = "Select * from customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                Customer customer = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerPhone);
                customerObservableList.add(customer);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return customerObservableList;
    }

    public static Customer addCustomer(Customer newCustomer) {

        String sql = "INSERT INTO customers ("
                + " Customer_ID,"
                + " Customer_Name,"
                + " Address,"
                + " Postal_Code,"
                + " Division,"
                + " Country,"
                + " Phone ) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, newCustomer.getCustomerId());
            ps.setString(2, newCustomer.getCustomerName());
            ps.setString(3, newCustomer.getCustomerAddress());
            ps.setString(4, newCustomer.getCustomerPostalCode());
            ps.setString(5, getAllDivisions().toString());
            ps.setString(6, getAllCountries().toString());
            ps.setString(7, newCustomer.getCustomerPhone());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return newCustomer;
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } return filteredDivisionObservableList;
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
                LocalDateTime startTime = LocalDateTime.parse(rs.getString("Start"));
                LocalDateTime endTime = LocalDateTime.parse(rs.getString("End"));
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

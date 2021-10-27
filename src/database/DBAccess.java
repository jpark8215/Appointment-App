package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import model.Countries;
import model.Customers;
import model.Divisions;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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


    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

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
                Customers customers = new Customers(customerId, customerName, customerAddress, customerPostalCode, customerPhone);
                customersObservableList.add(customers);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return customersObservableList;
    }

    public static Customers addCustomer(Customers newCustomer) {
        String sql = String.join(" ",
                "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Division, Country, Phone)",
                "VALUES (?, ?, ?, ?, ?, ?, ?)");

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


    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries country = new Countries(countryId, countryName);
                countriesObservableList.add(country);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return countriesObservableList;
    }


    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> divisionsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "Select * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Divisions divisions = new Divisions(divisionId, division, countryId);
                divisionsObservableList.add(divisions);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return divisionsObservableList;
    }


//    public void filteredComboBox () {
//
//        ObservableList<Countries> countries = countryComboBox.getItems();
//        // lambda to iterate over all the key vale pairs. cleaner and more readable than an anonymous class
//        divisionMap.forEach((id, division) -> {
//            Countries country = countryMap.get(Divisions.getCountryId());
//            if (!countries.contains(country)) {
//                countries.add(country);
//            }
//        });
//        countries.sort(Comparator.comparing(Countries::getCountryId));
//
//        Divisions division = divisionMap.get(Divisions.getDivisionId());
//        countryComboBox.getSelectionModel().select(countryMap.get(division.getCountryId()));
//
//        divisionComboBox.getSelectionModel().select(division);
//
//        Countries country = countryComboBox.getValue();
//        final ObservableList<Divisions> divisions = divisionComboBox.getItems();
//        divisions.clear();
//        // lambda to iterate over all the key value pairs. cleaner and more readable than an anonymous class
//        divisionMap.forEach((key, division) -> {
//            if (division.getCountryId() == country.getId()) divisions.add(division);
//        });
//        divisions.sort(Comparator.comparing(Divisions::getDivision));
//    }



}

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private String customerDivision;
    private String customerCountry;

    /**
     * Customers constructor
     @param customerId Initialize ID object
     @param customerName Initialize name object
     @param customerAddress Initialize address object
     @param customerPostalCode Initialize zipcode object
     @param customerDivision
     @param customerCountry
     @param customerPhone Initialize phone object
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerDivision, String customerCountry, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivision = customerDivision;
        this.customerCountry = customerCountry;
        this.customerPhone = customerPhone;

    }


    public int getCustomerId() { return customerId; }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerAddress() { return customerAddress; }

    public void setCustomerAddress(String address) { this.customerAddress = address; }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String postalCode) { this.customerPostalCode = postalCode; }

    public String getCustomerPhone() { return customerPhone; }

    public void setCustomerPhone(String phone) { this.customerPhone = phone; }

    public String getCustomerDivision() { return customerDivision; }

    public void setCustomerDivision(String division) { this.customerDivision = division; }

    public String getCustomerCountry() { return customerCountry; }

    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }



    public static void addCustomer(Customer newCustomer) {
        ObservableList <Customer> addCustomer = FXCollections.observableArrayList();
        addCustomer.add(newCustomer);
    }


}


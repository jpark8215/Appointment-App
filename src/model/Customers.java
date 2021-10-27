package model;

public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;

    /**
     * Customers constructor
     @param customerId Initialize ID object
     @param customerName Initialize name object
     @param customerAddress Initialize address object
     @param customerPostalCode Initialize zipcode object
     @param customerPhone Initialize phone object
     */
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
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




    public static void addCustomer(Customers newCustomer) {
    }


}


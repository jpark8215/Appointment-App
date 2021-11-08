package model;

/**
 * Customer class
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivisionId;
    public String customerDivision;
    private int customerCountryId;
    private String customerCountry;

    /**
     * Customer constructor
     * @param customerId customerId
     * @param customerName customerName
     * @param customerAddress customerAddress
     * @param customerPostalCode customerPostalCode
     * @param customerDivisionId customerDivisionId
     * @param customerDivision customerDivisionId
     * @param customerCountryId customerCountryId
     * @param customerCountry customerCountry
     * @param customerPhone customerPhone
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, int customerDivisionId, String customerDivision, int customerCountryId, String customerCountry, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivisionId = customerDivisionId;
        this.customerDivision = customerDivision;
        this.customerCountryId = customerCountryId;
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

    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) { this.customerPostalCode = customerPostalCode; }

    public String getCustomerPhone() { return customerPhone; }

    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    public void setCustomerDivisionId(int customerDivisionId) { this.customerDivisionId = customerDivisionId; }

    public String getCustomerDivision() { return customerDivision; }

    public void setCustomerDivision(String customerDivision) { this.customerDivision = customerDivision; }

    public int getCustomerCountryId() { return customerCountryId; }

    public void setCustomerCountryId(int customerCountryId) { this.customerCountryId = customerCountryId; }

    public String getCustomerCountry() { return customerCountry; }

    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }

//    public static void addCustomer(Customer newCustomer) {
//    }
}


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

//    public static void addCustomer(Customer newCustomer) {
//    }

    /**
     * @return customerId
     */
    public int getCustomerId() { return customerId; }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return customerName
     */
    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    /**
     * @return customerAddress
     */
    public String getCustomerAddress() { return customerAddress; }

    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }

    /**
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) { this.customerPostalCode = customerPostalCode; }

    /**
     * @return customerPhone
     */
    public String getCustomerPhone() { return customerPhone; }

    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    /**
     * @return customerDivisionId
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    public void setCustomerDivisionId(int customerDivisionId) { this.customerDivisionId = customerDivisionId; }

    /**
     * @return customerDivision
     */
    public String getCustomerDivision() { return customerDivision; }

    public void setCustomerDivision(String customerDivision) { this.customerDivision = customerDivision; }

    /**
     * @return customerCountryId
     */
    public int getCustomerCountryId() { return customerCountryId; }

    public void setCustomerCountryId(int customerCountryId) { this.customerCountryId = customerCountryId; }

    /**
     * @return customerCountry
     */
    public String getCustomerCountry() { return customerCountry; }

    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry; }


    /**
     * @return customerId and customerName in string
     */
    @Override
    public String toString() {return (customerId + " " + customerName);}
}



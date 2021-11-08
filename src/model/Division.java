package model;

/**
 * Division class
 */
public class Division {

    private int divisionId;
    private String division;
    private int countryId;

    /**
     * @param divisionId divisionId
     * @param division division
     * @param countryId countryId
     */
    public Division(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

//    public static void add(Division newCustomerDivision) {
//    }

    public int getDivisionId() { return divisionId; }

    public void setDivisionId(int divisionId) { this.divisionId = divisionId; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public int getCountryId() { return countryId; }

    public void setCountryId(int countryId) { this.countryId = countryId; }

    /**
     * @return divisionId and divisionName in string
     */
    @Override

    public String toString() {return (divisionId + " " + division);}
}

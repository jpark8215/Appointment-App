package model;

public class Divisions {

    private int divisionId;
    private String division;
    private int countryId;

    public Divisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }


    public int getDivisionId() { return divisionId; }

    public void setDivisionId(int divisionId) { this.divisionId = divisionId; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public int getCountryId() { return countryId; }

    public void setCountryId(int countryId) { this.countryId = countryId; }

    /**
     * Overrides built-in toString() for display in a ComboBox
     * @return the name of the division
     */
    @Override

    public String toString() {return (divisionId + " " + division);}
}

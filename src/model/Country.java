package model;

/**
 * Country class
 */
public class Country {

    private int countryId;
    private String countryName;

    /**
     * @param countryId countryId
     * @param countryName countryName
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * @return countryId
     */
    public int getCountryId() { return countryId; }

    public void setCountryId(int countryId) { this.countryId = countryId; }

    /**
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    /**
     * @return countryId and countryName in string
     */
    @Override
    public String toString() {return (countryId + " " + countryName);}
}

